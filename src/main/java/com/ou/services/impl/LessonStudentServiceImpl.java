package com.ou.services.impl;

import com.ou.pojo.LessonStudent;
import com.ou.pojo.Lesson;
import com.ou.pojo.Student;
import com.ou.repositories.LessonStudentRepository;
import com.ou.services.LessonStudentService;
import com.ou.services.LessonService;
import com.ou.services.StudentService;
import com.ou.services.CourseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class LessonStudentServiceImpl implements LessonStudentService {
    @Autowired
    private LessonStudentRepository lessonStudentRepo;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private StudentService studentService;

    @Autowired
    @Lazy // Use @Lazy to avoid circular dependency
    private CourseStudentService courseStudentService;

    @Override
    public LessonStudent save(LessonStudent lessonStudent) {
        return lessonStudentRepo.updateLessonStudent(lessonStudent);
    }

    @Override
    public List<LessonStudent> findAll() {
        return lessonStudentRepo.getAllLessonStudents();
    }

    @Override
    public List<LessonStudent> findByLessonId(Integer lessonId) {
        return lessonStudentRepo.findByLessonId(lessonId);
    }

    @Override
    public List<LessonStudent> findByStudentId(Integer studentId) {
        return lessonStudentRepo.findByStudentId(studentId);
    }

    @Override
    public List<LessonStudent> findByLearningStatus(Boolean isLearn) {
        return lessonStudentRepo.findByLearningStatus(isLearn);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isLessonCompleted(Integer lessonId, Integer studentId) {
        LessonStudent lessonStudent = lessonStudentRepo.findByLessonIdAndStudentId(lessonId, studentId);
        return lessonStudent != null && lessonStudent.getIsLearn() != null && lessonStudent.getIsLearn();
    }

    @Override
    public LessonStudent markLessonAsLearned(Integer lessonId, Integer studentId) throws Exception {
        // Validate inputs
        if (lessonId == null || studentId == null) {
            throw new IllegalArgumentException("Lesson ID and Student ID cannot be null");
        }

        // Check if lesson exists
        Optional<Lesson> lesson = lessonService.getLessonById(lessonId);
        if (lesson.isEmpty()) {
            throw new Exception("Lesson not found with ID: " + lessonId);
        }

        // Check if student exists
        Student student = studentService.getStudentById(studentId);

        // Check if lesson-student record already exists
        LessonStudent existingLessonStudent = lessonStudentRepo.findByLessonIdAndStudentId(lessonId, studentId);

        LessonStudent result;
        if (existingLessonStudent != null) {
            // Update existing record
            existingLessonStudent.setIsLearn(true);
            existingLessonStudent.setLearnedAt(LocalDateTime.now());
            result = lessonStudentRepo.updateLessonStudent(existingLessonStudent);
        } else {
            // Create new record
            LessonStudent newLessonStudent = new LessonStudent();
            newLessonStudent.setLessonId(lesson.get());
            newLessonStudent.setStudentId(student);
            newLessonStudent.setIsLearn(true);
            newLessonStudent.setLearnedAt(LocalDateTime.now());
            result = lessonStudentRepo.updateLessonStudent(newLessonStudent);
        }

        // Automatically update course progress after marking lesson as learned
        try {
            Integer courseId = lesson.get().getCourseId().getId();
            courseStudentService.updateCourseProgress(courseId, studentId);
        } catch (Exception e) {
            // Log the error but don't fail the lesson marking operation
            System.err.println("Failed to update course progress after marking lesson as learned: " + e.getMessage());
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LessonStudent> getLessonStudentByLessonAndStudent(Integer lessonId, Integer studentId) {
        LessonStudent lessonStudent = lessonStudentRepo.findByLessonIdAndStudentId(lessonId, studentId);
        return Optional.ofNullable(lessonStudent);
    }
}
