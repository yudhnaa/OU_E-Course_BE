package com.ou.dto;

import com.ou.pojo.Course;
import com.ou.pojo.Lecturer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link Course}
 */
public class CourseDto implements Serializable {
    private  Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private  String name;
    @Size(max = 65535)
    private  String description;
    @Size(max = 255)
    private  String image;
    private  LocalDateTime dateStart;
    private  LocalDateTime dateEnd;
    private  Integer categoryIdId;
    private  String categoryIdName;
    private  Long studentCount;
    private List<LecturerDto> lecturers;
    private double averageRate;

    @NotNull
    private  BigDecimal price;

    public CourseDto(Integer id, String name, String description, String image, LocalDateTime dateStart, LocalDateTime dateEnd, Integer categoryIdId, String categoryIdName, Long studentCount, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.categoryIdId = categoryIdId;
        this.categoryIdName = categoryIdName;
        this.studentCount = studentCount;
        this.price = price;
    }

    public CourseDto() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDto entity = (CourseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.image, entity.image) &&
                Objects.equals(this.dateStart, entity.dateStart) &&
                Objects.equals(this.dateEnd, entity.dateEnd) &&
                Objects.equals(this.categoryIdId, entity.categoryIdId) &&
                Objects.equals(this.categoryIdName, entity.categoryIdName) &&
                Objects.equals(this.studentCount, entity.studentCount) &&
                Objects.equals(this.price, entity.price);
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public List<LecturerDto> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<LecturerDto> lecturers) {
        this.lecturers = lecturers;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setCategoryIdId(Integer categoryIdId) {
        this.categoryIdId = categoryIdId;
    }

    public void setCategoryIdName(String categoryIdName) {
        this.categoryIdName = categoryIdName;
    }

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCategoryIdId() {
        return categoryIdId;
    }

    public String getCategoryIdName() {
        return categoryIdName;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, image, dateStart, dateEnd, categoryIdId, categoryIdName, price, studentCount);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "image = " + image + ", " +
                "dateStart = " + dateStart + ", " +
                "dateEnd = " + dateEnd + ", " +
                "categoryIdId = " + categoryIdId + ", " +
                "categoryIdName = " + categoryIdName + ", " +
                "studentCount = " + studentCount + ", " +
                "price = " + price + ")";
    }
}