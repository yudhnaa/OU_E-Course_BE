package com.ou.services.impl;

import com.ou.exceptions.NotFoundException;
import com.ou.pojo.Student;
import com.ou.pojo.User;
import com.ou.repositories.StudentRepository;
import com.ou.services.LocalizationService;
import com.ou.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of StudentService interface for managing student entities.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = Logger.getLogger(StudentServiceImpl.class.getName());

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private LocalizationService localizationService;

    @Override
    public Student getStudentById(int id) {
        return studentRepo.getStudentById(id)
                .orElseThrow(() -> new NotFoundException(localizationService.getMessage(
                        "student.notFound", LocaleContextHolder.getLocale())));
    }

    @Override
    public Student getStudentByUserId(int userId) {
        return studentRepo.getStudentByUserId(userId)
                .orElseThrow(() -> new NotFoundException(localizationService.getMessage(
                        "student.userNotFound", LocaleContextHolder.getLocale())));
    }

    @Override
    public Student addStudent(Student student) {
        LOGGER.log(Level.INFO, "Adding new student for user ID: " +
                (student.getUserId() != null ? student.getUserId().getId() : "null"));
        return studentRepo.addStudent(student);
    }

    @Override
    public Student createStudentFromUser(User user) {
        LOGGER.log(Level.INFO, "Creating student from user ID: " + user.getId());
        return studentRepo.createStudentFromUser(user);
    }

    @Override
    public Student updateStudent(Student student) {
        // Verify the student exists before updating
        getStudentById(student.getId());
        LOGGER.log(Level.INFO, "Updating student ID: " + student.getId());
        return studentRepo.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(int id) {
        // Verify the student exists before attempting to delete
        getStudentById(id);
        LOGGER.log(Level.INFO, "Deleting student ID: " + id);
        return studentRepo.deleteStudent(id);
    }

    @Override
    public List<Student> getStudents(Map<String, String> params) {
        LOGGER.log(Level.INFO, "Getting list of students with params: " + params);
        return studentRepo.getStudents(params);
    }

    @Override
    public List<Student> searchStudents(Map<String, String> filters, Map<String, String> params) {
        LOGGER.log(Level.INFO, "Searching students with filters: " + filters);
        return studentRepo.searchStudents(filters, params);
    }

    @Override
    public List<Student> getStudentsByCourse(Integer courseId, Map<String, String> params) {
        LOGGER.log(Level.INFO, "Getting students enrolled in course ID: " + courseId);
        return studentRepo.getStudentsByCourse(courseId, params);
    }

    @Override
    public List<Student> getStudentsNotInCourse(Integer courseId, Map<String, String> params) {
        LOGGER.log(Level.INFO, "Getting students not enrolled in course ID: " + courseId);
        return studentRepo.getStudentsNotInCourse(courseId, params);
    }

    @Override
    public long countStudents() {
        return studentRepo.countStudents();
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        return studentRepo.countSearchResults(filters);
    }

    @Override
    public long countStudentsByCourse(Integer courseId) {
        return studentRepo.countStudentsByCourse(courseId);
    }

    @Override
    public long countStudentsNotInCourse(Integer courseId) {
        return studentRepo.countStudentsNotInCourse(courseId);
    }
}
