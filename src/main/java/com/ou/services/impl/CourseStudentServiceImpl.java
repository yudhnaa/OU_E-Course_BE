package com.ou.services.impl;

import com.ou.exceptions.NotFoundException;
import com.ou.pojo.*;
import com.ou.repositories.CourseStudentRepository;
import com.ou.services.*;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private ExerciseAttemptService exerciseAttemptService;
    @Autowired
    private TestAttemptService testAttemptService;
    @Autowired
    private TestService testService;
    @Autowired
    private CourseCertificateService courseCertificateService;
    @Autowired
    private CertificateService certificateService;
    @Autowired
    private CourseStudentService courseStudentService;

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
    public List<CourseStudent> getCourseStudentsByUser(Integer userId, Map<String, String> params) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        return courseStudentRepository.getCourseStudentsByUserId(userId, params);
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

        return courseStudentRepository.getCourseStudentByCourseAndUser(courseId, userId);
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

    @Override
    public double calculateCourseProgress(Integer courseId, Integer studentId) throws Exception {
        if (courseId == null || studentId == null) {
            throw new IllegalArgumentException("Course ID and Student ID cannot be null");
        }

        // Get all lessons for the course
        List<Lesson> lessons = lessonService.getLessonsByCourse(courseId, null);
        if (lessons.isEmpty()) {
            return 0.0;
        }

        // Count completed lessons
        int completedLessons = 0;
        for (Lesson lesson : lessons) {
            if (lessonStudentService.isLessonCompleted(lesson.getId(), studentId)) {
                completedLessons++;
            }
        }

        // Calculate base progress from lessons (70% weight)
        double lessonProgress = (double) completedLessons / lessons.size();
        double weightedLessonProgress = lessonProgress * 0.7;

        // Calculate exercise/test completion progress (30% weight)
        double exerciseTestProgress = calculateExerciseTestProgress(courseId, studentId);
        double weightedExerciseTestProgress = exerciseTestProgress * 0.3;

        // Total progress
        return Math.min(1.0, weightedLessonProgress + weightedExerciseTestProgress);
    }

    @Override
    public CourseStudent updateCourseProgress(Integer courseId, Integer studentId) throws Exception {
        if (courseId == null || studentId == null) {
            throw new IllegalArgumentException("Course ID and Student ID cannot be null");
        }

        // Get the course-student record
        Optional<CourseStudent> courseStudentOpt = getCourseStudentByCourseAndStudent(courseId, studentId);
        if (courseStudentOpt.isEmpty()) {
            throw new Exception("Student is not enrolled in this course");
        }

        CourseStudent courseStudent = courseStudentOpt.get();

        // Calculate new progress
        double newProgress = calculateCourseProgress(courseId, studentId);

        // Update progress if it has changed
        if (Math.abs(newProgress - courseStudent.getProgress()) > 0.001) {
            courseStudent.setProgress(newProgress);
            CourseStudent updateCourseStudent = updateCourseStudent(courseStudent);

            // Check if certificate should be generated
            if (shouldGenerateCertificate(updateCourseStudent)) {
                generateCertificateForStudent(updateCourseStudent);
            }
        }

        return courseStudent;
    }

    @Override
    public boolean shouldGenerateCertificate(CourseStudent courseStudent) {
        return courseStudent.getProgress() == 1.0 && !hasCertificate(courseStudent);
    }

    // Helper method to calculate exercise and test completion progress
    private double calculateExerciseTestProgress(Integer courseId, Integer studentId) {

        List<Exercise> exercises = exerciseService.getExercisesByCourse(courseId, null);
        List<Test> tests = testService.getTestsByCourse(courseId, null);

//        Boolean isComplete = exerciseAttemptService.isStudentDidAllCourseExercise(exercises, studentId, null);
//        if (isComplete)
//            return 1.0;

        Double exerciseCompletePercent = exerciseAttemptService.calculateExerciseStudentProgress(exercises, studentId, courseId, null);
        Double testCompletePercent = testAttemptService.calculateTestStudentProgress(tests, studentId, courseId, null);

        return (exerciseCompletePercent + testCompletePercent) / 2.0; // AVG progress for exercises and tests
    }

    // Helper method to check if student already has a certificate for this course
    private boolean hasCertificate(CourseStudent courseStudent) {
        List<CourseCertificate> courseStudents =  courseCertificateService.getCertificatesByCourseStudentId(courseStudent.getCourseId().getId(), null );
        return !courseStudents.isEmpty();
    }

    // Helper method to generate certificate (placeholder)
    private void generateCertificateForStudent(CourseStudent courseStudent) throws Exception {

//        Boolean isCompleteCourse = courseStudentService.isCourseCompleted(courseStudent);
//
//        if (!isCompleteCourse) {
//            throw new Exception("Course is not completed, cannot generate certificate");
//        }

//        Optional<CourseStudent> courseStudent = courseStudentRepository.getCourseStudentById(courseStudentId);
//        if (courseStudent == null) {
//            throw new NotFoundException("CourseStudent not found with ID: " + courseStudentId);
//        }

        CourseCertificate createdCourseCertificate = courseCertificateService.createCourseCertificate(courseStudent, "N/A");
        if (createdCourseCertificate == null) {
            throw new Exception("Failed to create course certificate");
        }

        File generatedFile = certificateService.generatePdf(createdCourseCertificate);

        if (generatedFile == null || !generatedFile.exists()) {
            throw new Exception("Failed to generate certificate file");
        }

        CourseCertificate updatedCourseCertificate = courseCertificateService.updateCourseCertificate(createdCourseCertificate, generatedFile);

    }
}
