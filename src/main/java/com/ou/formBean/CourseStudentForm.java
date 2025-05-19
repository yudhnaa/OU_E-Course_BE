package com.ou.formBean;

import com.ou.pojo.CourseStudent;

import java.util.List;

// Boc List lai neu khong se khong bind duoc
public class CourseStudentForm {
    private List<CourseStudent> items;

    public List<CourseStudent> getItems() {
        return items;
    }

    public void setItems(List<CourseStudent> items) {
        this.items = items;
    }
}

