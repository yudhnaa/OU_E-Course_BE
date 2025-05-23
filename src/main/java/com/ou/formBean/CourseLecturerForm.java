package com.ou.formBean;

import com.ou.dto.LecturerDto;
import com.ou.pojo.CourseLecturer;
import com.ou.pojo.Lecturer;

import java.util.List;

public class CourseLecturerForm {
    List<CourseLecturer> courseLecturers;

    List<LecturerDto> lecturers;

    Long countCurrentLecturers;

    public List<CourseLecturer> getCourseLecturers() {
        return courseLecturers;
    }

    public void setCourseLecturers(List<CourseLecturer> courseLecturers) {
        this.courseLecturers = courseLecturers;
    }

    public List<LecturerDto> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<LecturerDto> lecturers) {
        this.lecturers = lecturers;
    }

    public Long getCountCurrentLecturers() {
        return countCurrentLecturers;
    }

    public void setCountCurrentLecturers(Long countCurrentLecturers) {
        this.countCurrentLecturers = countCurrentLecturers;
    }
}
