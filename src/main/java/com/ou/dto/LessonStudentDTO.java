package com.ou.dto;

import com.ou.pojo.Lesson;
import com.ou.pojo.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.LessonStudent}
 */
public class LessonStudentDTO implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private final String name;
    private final Boolean isLearn;
    private final Lesson lessonId;
    private final User studentId;

    public LessonStudentDTO(Integer id, String name, Boolean isLearn, Lesson lessonId, User studentId) {
        this.id = id;
        this.name = name;
        this.isLearn = isLearn;
        this.lessonId = lessonId;
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsLearn() {
        return isLearn;
    }

    public Lesson getLessonId() {
        return lessonId;
    }

    public User getStudentId() {
        return studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonStudentDTO entity = (LessonStudentDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.isLearn, entity.isLearn) &&
                Objects.equals(this.lessonId, entity.lessonId) &&
                Objects.equals(this.studentId, entity.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isLearn, lessonId, studentId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "isLearn = " + isLearn + ", " +
                "lessonId = " + lessonId + ", " +
                "studentId = " + studentId + ")";
    }
}