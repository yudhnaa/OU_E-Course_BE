package com.ou.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CourseWithProgressDto extends CourseDto {
    private Double progress;

    public CourseWithProgressDto() {
        super();
    }

    public CourseWithProgressDto(Integer id, String name, String description, String image,
                                 LocalDateTime dateStart, LocalDateTime dateEnd,
                                 Integer categoryIdId, String categoryIdName,
                                 Long studentCount, BigDecimal price,
                                 Double progress) {
        super(id, name, description, image, dateStart, dateEnd, categoryIdId, categoryIdName, studentCount, price);
        this.progress = progress;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }
}
