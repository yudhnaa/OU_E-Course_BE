package com.ou.services.impl;

import com.ou.pojo.CourseRate;
import com.ou.repositories.CourseRateRepository;
import com.ou.services.CourseRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class CourseRateServiceImpl implements CourseRateService {

    @Autowired
    private CourseRateRepository courseRateRepository;
    
    @Override
    public CourseRate addCourseRate(CourseRate courseRate) throws Exception {
        // Validate rate value
        if (!isValidRate(courseRate.getRate())) {
            throw new Exception("Invalid rating value. Rating must be between 1 and 5.");
        }
        
        // Check if student is allowed to rate this course
//        if (!studentCanRateCourse(courseRate.getStudentId().getId(), courseRate.getCourseId().getId())) {
//            throw new Exception("Student is not eligible to rate this course.");
//        }
        
        // Check for duplicate ratings
        Map<String, String> filters = Map.of(
            "courseId", courseRate.getCourseStudentId().getCourseId().getId().toString(),
            "studentId", courseRate.getCourseStudentId().getStudentId().getId().toString()
        );
        
        List<CourseRate> existingRatings = courseRateRepository.searchCourseRates(filters, null);
        if (!existingRatings.isEmpty()) {
            throw new Exception("Student has already rated this course. Please update the existing rating instead.");
        }
        
        return courseRateRepository.addCourseRate(courseRate);
    }

    @Override
    public CourseRate updateCourseRate(CourseRate courseRate) throws Exception {
        // Validate rate exists
        Optional<CourseRate> existingRateOpt = courseRateRepository.getCourseRateById(courseRate.getId());
        if (existingRateOpt.isEmpty()) {
            throw new Exception("Course rating not found.");
        }
        
        CourseRate existingRate = existingRateOpt.get();
        
        // Ensure student can only update their own ratings
        if (!existingRate.getCourseStudentId().getStudentId().getId().equals(courseRate.getCourseStudentId().getStudentId().getId())) {
            throw new Exception("Students can only update their own ratings.");
        }
        
        // Validate rate value
        if (!isValidRate(courseRate.getRate())) {
            throw new Exception("Invalid rating value. Rating must be between 1 and 5.");
        }
        
        return courseRateRepository.updateCourseRate(courseRate);
    }

    @Override
    public boolean deleteCourseRate(Integer id) throws Exception {
        // Verify the course rate exists
        Optional<CourseRate> existingRateOpt = courseRateRepository.getCourseRateById(id);
        if (existingRateOpt.isEmpty()) {
            throw new Exception("Course rating not found.");
        }
        
        // Note: Additional authorization check could be added here
        // to ensure only the student who created it or an admin can delete
        
        return courseRateRepository.deleteCourseRate(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseRate> getCourseRateById(Integer id) {
        return courseRateRepository.getCourseRateById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseRate> getCourseRates(Map<String, String> params) {
        return courseRateRepository.getCourseRates(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseRate> searchCourseRates(Map<String, String> filters, Map<String, String> params) {
        return courseRateRepository.searchCourseRates(filters, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseRate> getCourseRatesByCourse(Integer courseId, Map<String, String> params) {
        return courseRateRepository.getCourseRatesByCourse(courseId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseRate> getCourseRatesByStudent(Integer studentId, Map<String, String> params) {
        return courseRateRepository.getCourseRatesByStudent(studentId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourseRates() {
        return courseRateRepository.countCourseRates();
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourseRatesByCourse(Integer courseId) {
        return courseRateRepository.countCourseRatesByCourse(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourseRatesByStudent(Integer studentId) {
        return courseRateRepository.countCourseRatesByStudent(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        return courseRateRepository.countSearchResults(filters);
    }

    @Override
    public boolean isValidRate(double rate) {
        // Rating is typically between 1 and 5
        return rate >= 1 && rate <= 5;
    }

    @Override
    public boolean studentCanRateCourse(Integer studentId, Integer courseId) {
        // Implementation would typically check if:
        // 1. The student is enrolled in the course
        // 2. The student has completed some percentage of the course
        // 3. The course is active
        
        // For now, this is a placeholder implementation
        // In a real application, you would inject the necessary repositories to check these conditions
        return true; // Placeholder - assume students can rate any course
    }
}
