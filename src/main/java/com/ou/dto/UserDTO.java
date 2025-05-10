package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.User}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID must be positive")
    private final Integer id;
    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, max = 50)
    @NotBlank(message = "nLast name cannot be blank!")
    private final String lastName;
    @NotNull
    @Size(min = 1, max = 50)
    private final String firstName;
    @NotNull
    @Size(min = 1, max = 50)
    private final String birthday;
    @NotNull
    @Size(min = 1, max = 50)
    private final String username;
    @NotNull
    @Size(min = 1, max = 255)
    private final String password;
    @NotNull
    @Size(min = 1, max = 255)
    private final String avatar;
    @NotNull(message = "Email cannot be null!")
    @Size(min = 1, max = 100)
    @Email(regexp = "/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$/")
    @NotBlank(message = "Email cannot be blank!")
    private final String email;
    private final boolean isActive;
    private final Set<Exercise> exerciseSet;
    private final Set<CourseRate> courseRateSet;
    private final Set<Course> courseSet;
    private final Set<CourseLecturer> courseLecturerSet;
    private final Set<Test> testSet;
    private final Set<ExerciseAttempt> exerciseAttemptSet;
    private final Set<Lesson> lessonSet;
    private final Set<TestAttempt> testAttemptSet;
    private final Set<LessonStudent> lessonStudentSet;
    private final UserRole userRoleId;
    private final Set<CourseStudent> courseStudentSet;

    public UserDTO(Integer id, String lastName, String firstName, String birthday, String username, String password, String avatar, String email, boolean isActive, Set<Exercise> exerciseSet, Set<CourseRate> courseRateSet, Set<Course> courseSet, Set<CourseLecturer> courseLecturerSet, Set<Test> testSet, Set<ExerciseAttempt> exerciseAttemptSet, Set<Lesson> lessonSet, Set<TestAttempt> testAttemptSet, Set<LessonStudent> lessonStudentSet, UserRole userRoleId, Set<CourseStudent> courseStudentSet) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.isActive = isActive;
        this.exerciseSet = exerciseSet;
        this.courseRateSet = courseRateSet;
        this.courseSet = courseSet;
        this.courseLecturerSet = courseLecturerSet;
        this.testSet = testSet;
        this.exerciseAttemptSet = exerciseAttemptSet;
        this.lessonSet = lessonSet;
        this.testAttemptSet = testAttemptSet;
        this.lessonStudentSet = lessonStudentSet;
        this.userRoleId = userRoleId;
        this.courseStudentSet = courseStudentSet;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public Set<Exercise> getExerciseSet() {
        return exerciseSet;
    }

    public Set<CourseRate> getCourseRateSet() {
        return courseRateSet;
    }

    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public Set<CourseLecturer> getCourseLecturerSet() {
        return courseLecturerSet;
    }

    public Set<Test> getTestSet() {
        return testSet;
    }

    public Set<ExerciseAttempt> getExerciseAttemptSet() {
        return exerciseAttemptSet;
    }

    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public Set<TestAttempt> getTestAttemptSet() {
        return testAttemptSet;
    }

    public Set<LessonStudent> getLessonStudentSet() {
        return lessonStudentSet;
    }

    public UserRole getUserRoleId() {
        return userRoleId;
    }

    public Set<CourseStudent> getCourseStudentSet() {
        return courseStudentSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO entity = (UserDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.birthday, entity.birthday) &&
                Objects.equals(this.username, entity.username) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.avatar, entity.avatar) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.isActive, entity.isActive) &&
                Objects.equals(this.exerciseSet, entity.exerciseSet) &&
                Objects.equals(this.courseRateSet, entity.courseRateSet) &&
                Objects.equals(this.courseSet, entity.courseSet) &&
                Objects.equals(this.courseLecturerSet, entity.courseLecturerSet) &&
                Objects.equals(this.testSet, entity.testSet) &&
                Objects.equals(this.exerciseAttemptSet, entity.exerciseAttemptSet) &&
                Objects.equals(this.lessonSet, entity.lessonSet) &&
                Objects.equals(this.testAttemptSet, entity.testAttemptSet) &&
                Objects.equals(this.lessonStudentSet, entity.lessonStudentSet) &&
                Objects.equals(this.userRoleId, entity.userRoleId) &&
                Objects.equals(this.courseStudentSet, entity.courseStudentSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, birthday, username, password, avatar, email, isActive, exerciseSet, courseRateSet, courseSet, courseLecturerSet, testSet, exerciseAttemptSet, lessonSet, testAttemptSet, lessonStudentSet, userRoleId, courseStudentSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "lastName = " + lastName + ", " +
                "firstName = " + firstName + ", " +
                "birthday = " + birthday + ", " +
                "username = " + username + ", " +
                "password = " + password + ", " +
                "avatar = " + avatar + ", " +
                "email = " + email + ", " +
                "isActive = " + isActive + ", " +
                "exerciseSet = " + exerciseSet + ", " +
                "courseRateSet = " + courseRateSet + ", " +
                "courseSet = " + courseSet + ", " +
                "courseLecturerSet = " + courseLecturerSet + ", " +
                "testSet = " + testSet + ", " +
                "exerciseAttemptSet = " + exerciseAttemptSet + ", " +
                "lessonSet = " + lessonSet + ", " +
                "testAttemptSet = " + testAttemptSet + ", " +
                "lessonStudentSet = " + lessonStudentSet + ", " +
                "userRoleId = " + userRoleId + ", " +
                "courseStudentSet = " + courseStudentSet + ")";
    }
}