package com.ou.services.impl;

import com.ou.pojo.Course;
import com.ou.pojo.CourseLecturer;
import com.ou.pojo.Lecturer;
import com.ou.repositories.CourseLecturerRepository;
import com.ou.services.CourseLecturerService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CourseLecturerServiceImpl implements CourseLecturerService {

    @Autowired
    private CourseLecturerRepository courseLecturerRepository;
    @Autowired
    private LocalizationService localizationService;

    @Override
    public CourseLecturer addCourseLecturer(CourseLecturer courseLecturer) throws Exception {
        // Validate the lecturer assignment before saving
        if (!validateCourseLecturerAssignment(courseLecturer)) {
            throw new Exception("Invalid course-lecturer assignment. Validation failed.");
        }
        
        // Check if lecturer is already assigned to this course
        if (isLecturerAssignedToCourse(courseLecturer.getLecturerId().getId(), 
                                      courseLecturer.getCourseId().getId())) {
            throw new Exception("This lecturer is already assigned to the course.");
        }
        
        return courseLecturerRepository.addCourseLecturer(courseLecturer);
    }

    @Override
    public Boolean updateCourseLecturer(List<CourseLecturer> courseLecturer) throws Exception {

        boolean isSuccess = false;

        List<CourseLecturer> currentCourseLecturers = courseLecturerRepository
                .getCourseLecturersByCourse(courseLecturer.get(0).getCourseId().getId(), null);

        // Remove existing assignments that are not in the new list
        for (CourseLecturer existing : currentCourseLecturers) {
            boolean exists = courseLecturer.stream()
                    .anyMatch(item -> item.getId().equals(existing.getId()));
            if (!exists) {
                courseLecturerRepository.deleteCourseLecturer(existing.getId());
                isSuccess = true;
            }
        }

        for (CourseLecturer item : courseLecturer) {
            // Validate the lecturer assignment before saving
            if (!validateCourseLecturerAssignment(item)) {
                throw new Exception(localizationService.getMessage("courseLecturer.invalidData", LocaleContextHolder.getLocale()));
            }

            // Check if lecturer is already assigned to this course
            boolean isAssigned = isLecturerAssignedToCourse(item.getLecturerId().getId(), item.getCourseId().getId());
            if (isAssigned) {
                continue;
            }
            else {
                CourseLecturer addedCourseLecturer = courseLecturerRepository.addCourseLecturer(item);
                if (addedCourseLecturer != null) {
                    isSuccess = true;
                }
            }
        }

        return isSuccess;
    }

    @Override
    public CourseLecturer updateCourseLecturer(CourseLecturer courseLecturer) throws Exception {
        // Verify the entity exists before updating
        Optional<CourseLecturer> existingAssignment = 
                courseLecturerRepository.getCourseLecturerById(courseLecturer.getId());
        
        if (existingAssignment.isEmpty()) {
            throw new Exception("Course-lecturer assignment not found with ID: " + courseLecturer.getId());
        }
        
        // Validate the updated assignment
        if (!validateCourseLecturerAssignment(courseLecturer)) {
            throw new Exception("Invalid course-lecturer assignment. Validation failed.");
        }
        
        // Check if the updated assignment would create a duplicate
        Integer currentLecturerId = courseLecturer.getLecturerId().getId();
        Integer currentCourseId = courseLecturer.getCourseId().getId();
        Integer originalLecturerId = existingAssignment.get().getLecturerId().getId();
        Integer originalCourseId = existingAssignment.get().getCourseId().getId();
        
        if ((currentLecturerId != originalLecturerId || currentCourseId != originalCourseId) && 
            isLecturerAssignedToCourse(currentLecturerId, currentCourseId)) {
            throw new Exception("This lecturer is already assigned to the course.");
        }
        
        return courseLecturerRepository.updateCourseLecturer(courseLecturer);
    }

    @Override
    public boolean deleteCourseLecturer(Integer id) throws Exception {
        // Check if the assignment exists
        Optional<CourseLecturer> existing = courseLecturerRepository.getCourseLecturerById(id);
        if (existing.isEmpty()) {
            throw new Exception("Cannot delete. Course-lecturer assignment not found with ID: " + id);
        }
        
        // Additional business rules could be added here
        // For example, prevent deletion if course is active and has students
        
        return courseLecturerRepository.deleteCourseLecturer(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseLecturer> getCourseLecturerById(Integer id) {
        return courseLecturerRepository.getCourseLecturerById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseLecturer> getCourseLecturers(Map<String, String> params) {
        return courseLecturerRepository.getCourseLecturers(params);
    }

    @Override
    public List<Lecturer> getLecturersByCourseId(Integer courseId, Map<String, String> params) {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Invalid course ID");
        }
        return courseLecturerRepository.getLecturersByCourseId(courseId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseLecturer> searchCourseLecturers(Map<String, String> filters, Map<String, String> params) {
        return courseLecturerRepository.searchCourseLecturers(filters, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseLecturer> getCourseLecturersByCourse(Integer courseId, Map<String, String> params) {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Invalid course ID");
        }
        return courseLecturerRepository.getCourseLecturersByCourse(courseId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseLecturer> getCourseLecturersByLecturer(Integer lecturerId, Map<String, String> params) {
        if (lecturerId == null || lecturerId <= 0) {
            throw new IllegalArgumentException("Invalid lecturer ID");
        }
        return courseLecturerRepository.getCourseLecturersByLecturer(lecturerId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourseLecturers() {
        return courseLecturerRepository.countCourseLecturers();
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourseLecturersByCourse(Integer courseId) {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Invalid course ID");
        }
        return courseLecturerRepository.countCourseLecturersByCourse(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourseLecturersByLecturer(Integer lecturerId) {
        if (lecturerId == null || lecturerId <= 0) {
            throw new IllegalArgumentException("Invalid lecturer ID");
        }
        return courseLecturerRepository.countCourseLecturersByLecturer(lecturerId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        return courseLecturerRepository.countSearchResults(filters);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isLecturerAssignedToCourse(Integer lecturerId, Integer courseId) {
        if (lecturerId == null || courseId == null || lecturerId <= 0 || courseId <= 0) {
            throw new IllegalArgumentException("Invalid lecturer ID or course ID");
        }
        
        Map<String, String> filters = new HashMap<>();
        filters.put("lecturerId", lecturerId.toString());
        filters.put("courseId", courseId.toString());
        
        return courseLecturerRepository.countSearchResults(filters) > 0;
    }
    
    @Override
    public boolean validateCourseLecturerAssignment(CourseLecturer courseLecturer) throws Exception {
        if (courseLecturer == null) {
            throw new Exception("Course-lecturer assignment cannot be null");
        }
        
        if (courseLecturer.getCourseId() == null) {
            throw new Exception("Course must be specified");
        }
        
        if (courseLecturer.getLecturerId() == null) {
            throw new Exception("Lecturer must be specified");
        }
        
        // Additional validation could check if the course exists and is active
        // Additional validation could check if the user is actually a lecturer
        
        return true;
    }
}
