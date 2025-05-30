package com.ou.services.impl;

import com.ou.pojo.CourseStudent;
import com.ou.pojo.Lesson;
import com.ou.pojo.Student;
import com.ou.repositories.CourseStudentRepository;
import com.ou.services.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseStudentServiceImpl implements CourseStudentService {

    @Autowired
    private CourseStudentRepository courseStudentRepository;
    @Autowired
    private LessonStudentService lessonStudentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private StudentService studentService;

    @Override
    public CourseStudent addCourseStudent(CourseStudent courseStudent) throws Exception {
        // Validate course student data
        if (courseStudent == null) {
            throw new IllegalArgumentException("CourseStudent cannot be null");
        }
        
        if (courseStudent.getCourseId() == null || courseStudent.getStudentId() == null) {
            throw new IllegalArgumentException("Course and Student must be specified");
        }
        
        // Check if student is already enrolled in this course
        if (!isEnrollmentValid(courseStudent.getCourseId().getId(), courseStudent.getStudentId().getId())) {
            throw new Exception("Student is already enrolled in this course");
        }

        // Initialize progress to 0 for new enrollments
        courseStudent.setProgress(0);
        
        return courseStudentRepository.addCourseStudent(courseStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseStudent> getCourseStudents(Map<String, String> params) {
        return courseStudentRepository.getCourseStudents(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseStudent> searchCourseStudents(Map<String, String> filters, Map<String, String> params) {
        return courseStudentRepository.searchCourseStudents(filters, params);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseStudent> getCourseStudentById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("CourseStudent ID cannot be null");
        }
        return courseStudentRepository.getCourseStudentById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseStudent> getCourseStudentsByCourse(Integer courseId, Map<String, String> params) {
        if (courseId == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        return courseStudentRepository.getCourseStudentsByCourse(courseId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseStudent> getCourseStudentsByStudent(Integer studentId, Map<String, String> params) {
        if (studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        return courseStudentRepository.getCourseStudentsByStudent(studentId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseStudent> getCourseStudentByCourseAndStudent(Integer courseId, Integer studentId) {
        if (courseId == null || studentId == null) {
            throw new IllegalArgumentException("Course ID and Student ID cannot be null");
        }
        return courseStudentRepository.getCourseStudentByCourseAndStudent(courseId, studentId);
    }

    @Override
    public Optional<CourseStudent> getCourseStudentByCourseAndUser(Integer courseId, Integer userId) {
        if (courseId == null || userId == null) {
            throw new IllegalArgumentException("Course ID and Student ID cannot be null");
        }

        Student student = studentService.getStudentByUserId(userId);

        return courseStudentRepository.getCourseStudentByCourseAndStudent(courseId, student.getId());
    }

    @Override
    public CourseStudent updateCourseStudent(CourseStudent courseStudent) throws Exception {
        if (courseStudent == null || courseStudent.getId() == null) {
            throw new IllegalArgumentException("CourseStudent and ID cannot be null");
        }
        
        // Check if course student exists
        Optional<CourseStudent> existingCourseStudent = courseStudentRepository.getCourseStudentById(courseStudent.getId());
        if (existingCourseStudent.isEmpty()) {
            throw new Exception("CourseStudent not found with ID: " + courseStudent.getId());
        }
        
        // Validate progress updates
        if (!canUpdateProgress(existingCourseStudent.get(), courseStudent.getProgress())) {
            throw new Exception("Invalid progress value. Progress must be between 0 and 100 and cannot decrease.");
        }
        
        return courseStudentRepository.updateCourseStudent(courseStudent);
    }

    @Override
    public boolean deleteCourseStudent(Integer id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("CourseStudent ID cannot be null");
        }
        
        // Check if course student exists
        Optional<CourseStudent> existingCourseStudent = courseStudentRepository.getCourseStudentById(id);
        if (existingCourseStudent.isEmpty()) {
            throw new Exception("CourseStudent not found with ID: " + id);
        }

        // All student course certificates will be deleted, ON DELETE CASCADE set in DB

        return courseStudentRepository.deleteCourseStudent(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourseStudents() {
        return courseStudentRepository.countCourseStudents();
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourseStudentsByCourse(Integer courseId) {
        if (courseId == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        return courseStudentRepository.countCourseStudentsByCourse(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourseStudentsByStudent(Integer studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        return courseStudentRepository.countCourseStudentsByStudent(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        return courseStudentRepository.countSearchResults(filters);
    }
    
    @Override
    public boolean isEnrollmentValid(Integer courseId, Integer studentId) {
        if (courseId == null || studentId == null) {
            return false;
        }
        
        // Check if student is already enrolled in this course
        Optional<CourseStudent> existingEnrollment = courseStudentRepository.getCourseStudentByCourseAndStudent(courseId, studentId);
        return existingEnrollment.isEmpty(); // Valid if no existing enrollment found
    }
    
    @Override
    public boolean canUpdateProgress(CourseStudent courseStudent, double newProgress) {
        // Progress must be between 0 and 100
        if (newProgress < 0 || newProgress > 1) {
            return false;
        }
        
        // Progress cannot decrease (optional business rule, can be removed if needed)
        if (newProgress < courseStudent.getProgress()) {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean isCourseCompleted(CourseStudent courseStudent) {
        List<Lesson> lessons = lessonService.getLessonsByCourse(courseStudent.getCourseId().getId(), null);
        if (lessons.isEmpty()) {
            return false;
        }

        // Check if all lessons are completed
        // If any lesson is not completed, course is not completed
        for (Lesson lesson : lessons) {
            if (!lessonStudentService.isLessonCompleted(lesson.getId(), courseStudent.getStudentId().getId())) {
                return false;
            }
        }

        return true;
    }
}
