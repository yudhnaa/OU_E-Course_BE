package com.ou.repositories;

import com.ou.pojo.Student;
import com.ou.pojo.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentRepository {
    // Create operations
    Student addStudent(Student student);
    Student createStudentFromUser(User user);

    // Read operations
    Optional<Student> getStudentById(Integer id);
    Optional<Student> getStudentByUserId(Integer userId);
    List<Student> getStudents(Map<String, String> params);
    List<Student> searchStudents(Map<String, String> filters, Map<String, String> params);
    List<Student> getStudentsByCourse(Integer courseId, Map<String, String> params);
    List<Student> getStudentsNotInCourse(Integer courseId, Map<String, String> params);

    // Update operations
    Student updateStudent(Student student);

    // Delete operations
    boolean deleteStudent(Integer id);

    // Count methods for pagination
    long countStudents();
    long countSearchResults(Map<String, String> filters);
    long countStudentsByCourse(Integer courseId);
    long countStudentsNotInCourse(Integer courseId);
}
