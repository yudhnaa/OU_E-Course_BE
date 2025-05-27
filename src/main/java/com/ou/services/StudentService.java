package com.ou.services;

import com.ou.exceptions.NotFoundException;
import com.ou.pojo.Student;
import com.ou.pojo.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {
    // Retrieve a student by ID
    Student getStudentById(int id);

    // Retrieve a student by user ID
    Student getStudentByUserId(int userId);

    // Add a new student
    Student addStudent(Student student);

    // Create a student from an existing user
    Student createStudentFromUser(User user);

    // Update an existing student
    Student updateStudent(Student student);

    // Delete a student by ID
    boolean deleteStudent(int id);

    // Retrieve a list of students with optional pagination
    List<Student> getStudents(Map<String, String> params);

    // Search for students based on filters and pagination
    List<Student> searchStudents(Map<String, String> filters, Map<String, String> params);

    // Get students enrolled in a specific course
    List<Student> getStudentsByCourse(Integer courseId, Map<String, String> params);

    // Get students not enrolled in a specific course
    List<Student> getStudentsNotInCourse(Integer courseId, Map<String, String> params);

    // Count total students
    long countStudents();

    // Count search results based on filters
    long countSearchResults(Map<String, String> filters);

    // Count students in a specific course
    long countStudentsByCourse(Integer courseId);

    // Count students not in a specific course
    long countStudentsNotInCourse(Integer courseId);
}
