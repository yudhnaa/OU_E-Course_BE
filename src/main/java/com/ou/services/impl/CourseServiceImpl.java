package com.ou.services.impl;

import com.ou.mappers.CloudinaryHelper;
import com.ou.pojo.Course;
import com.ou.repositories.CourseRepository;
import com.ou.services.CourseService;
import com.ou.services.LocalizationService;
import com.ou.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LocalizationService localizationService;

    @Autowired
    private CloudinaryHelper cloudinaryHelper;


    @Override
    public Course addCourse(Course course) throws IOException {
        // Validate course before adding
        if (!isValidCourse(course)) {
            throw new IllegalArgumentException("Invalid course data");
        }
        
        // Check for duplicate name
        if (!isNameUnique(course.getName(), null)) {
            throw new IllegalArgumentException("Course name already exists");
        }
        
        // Validate dates
        if (!hasValidDates(course)) {
            throw new IllegalArgumentException("Invalid course dates");
        }
        
        // Set current date as date added if not provided
        if (course.getDateAdded() == null) {
            course.setDateAdded(LocalDateTime.now());
        }

        //Upload image to Cloudinary if provided
        if (!course.getImageFile().isEmpty()){
            Map<String, String> imageUrl = cloudinaryHelper.uploadFile(course.getImageFile());
            course.setImage(imageUrl.get("url"));
            course.setPublicId(imageUrl.get("publicId"));
        }
        
        return courseRepository.addCourse(course);
    }

    @Override
    public Course updateCourse(Course course) throws IOException {
        // Check if course exists
        if (course.getId() == null || !courseRepository.getCourseById(course.getId()).isPresent()) {
            throw new IllegalArgumentException(localizationService.getMessage("course.notFound", LocaleContextHolder.getLocale()));
        }
        
        // Validate course data
        if (!isValidCourse(course)) {
            throw new IllegalArgumentException(localizationService.getMessage("course.invalidData", LocaleContextHolder.getLocale()));
        }
        
        // Check for duplicate name (excluding this course)
        if (!isNameUnique(course.getName(), course.getId())) {
            throw new IllegalArgumentException("course.invalidData.name.exists");
        }
        
        // Validate dates
        if (!hasValidDates(course)) {
            throw new IllegalArgumentException(localizationService.getMessage("course.invalidData", LocaleContextHolder.getLocale()));
        }

        //Upload and delete image to Cloudinary if provided
        if (!course.getImageFile().isEmpty()){
            Map<String, String> imageUrl = cloudinaryHelper.uploadFile(course.getImageFile());
            course.setImage(imageUrl.get("url"));
            course.setPublicId(imageUrl.get("publicId"));

            if (course.getPublicId() != null) {
                cloudinaryHelper.deleteFile(course.getPublicId());
            }
        }


        // not allow to update dateAdded
        course.setDateAdded(courseRepository.getCourseById(course.getId()).get().getDateAdded());

        return courseRepository.updateCourse(course);
    }

    @Override
    public boolean deleteCourse(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        
        // Check if course exists
        Optional<Course> courseOpt = courseRepository.getCourseById(id);
        if (courseOpt.isEmpty()) {
            throw new IllegalArgumentException("Course not found");
        }
        
        // TODO: Add additional business logic validation if needed
        // For example: check if there are active students enrolled before deletion
        
        return courseRepository.deleteCourse(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> getCourseById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        return courseRepository.getCourseById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getCourses(Map<String, String> params) {
        // Validate pagination parameters if provided
        ValidateUtils.validatePaginationParams(params);
        return courseRepository.getCourses(params);
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourses() {
        return courseRepository.countCourses();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> searchCourses(Map<String, String> filters, Map<String, String> params) {
        // Validate pagination params
        ValidateUtils.validatePaginationParams(params);
        
        // Validate filter parameters
        validateFilterParams(filters);
        
        return courseRepository.searchCourses(filters, params);
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        // Validate filter parameters
        validateFilterParams(filters);
        
        return courseRepository.countSearchResults(filters);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> getCourseByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty");
        }
        return courseRepository.getCourseByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getCoursesByCategory(Integer categoryId, Map<String, String> params) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        
        // Validate pagination params
        ValidateUtils.validatePaginationParams(params);
        
        return courseRepository.getCoursesByCategory(categoryId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public long countCoursesByCategory(Integer categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        return courseRepository.countCoursesByCategory(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getCoursesCreatedByUser(Integer userId, Map<String, String> params) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        
        // Validate pagination params
        ValidateUtils.validatePaginationParams(params);
        
        return courseRepository.getCoursesCreatedByUser(userId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public long countCoursesCreatedByUser(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return courseRepository.countCoursesCreatedByUser(userId);
    }

    @Override
    public boolean isValidCourse(Course course) {
        if (course == null) {
            return false;
        }
        
        // Check required fields
        if (course.getName() == null || course.getName().trim().isEmpty()) {
            return false;
        }
        
        // Check name length (max 50 characters as per entity definition)
        if (course.getName().length() > 50) {
            return false;
        }
        
        // Check category - it's marked as optional=false in the entity
        if (course.getCategoryId() == null) {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean isNameUnique(String name, Integer excludeId) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        
        Optional<Course> existingCourse = courseRepository.getCourseByName(name);
        
        // If no course found with the same name, the name is unique
        if (!existingCourse.isPresent()) {
            return true;
        }
        
        // If excludeId is provided, check if the found course is the same as the one being updated
        if (excludeId != null && existingCourse.get().getId().equals(excludeId)) {
            return true;
        }
        
        return false;
    }

    @Override
    public boolean hasValidDates(Course course) {
        // If end date is set, it must be after start date
        if (course.getDateEnd() != null && course.getDateStart() != null) {
            return course.getDateEnd().isAfter(course.getDateStart());
        }
        
        // If only one date is set or none are set, consider it valid
        return true;
    }

    private void validateFilterParams(Map<String, String> filters) {
        if (filters == null) {
            return;
        }
        
        // Validate numeric filter values
        String[] numericFields = {"categoryId", "createdByUserId"};
        for (String field : numericFields) {
            if (filters.containsKey(field)) {
                try {
                    Integer.parseInt(filters.get(field));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid " + field + " format");
                }
            }
        }
    }
}
