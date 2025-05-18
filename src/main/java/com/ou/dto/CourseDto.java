package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.Course;
import com.ou.pojo.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Course}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @Size(max = 65535)
    private String description;
    private Date dateAdded;
    private Date dateStart;
    private Date dateEnd;
    private Integer categoryIdId;
    private String categoryIdName;
    private Set<Integer> courseLecturerSetIds;
    private Set<User> courseLecturerSetLecturerIds;

    public CourseDto() {
    }

    public CourseDto(Integer id, String name, String description, Date dateAdded, Date dateStart, Date dateEnd, Integer categoryIdId, String categoryIdName, Set<Integer> courseLecturerSetIds, Set<User> courseLecturerSetLecturerIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateAdded = dateAdded;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.categoryIdId = categoryIdId;
        this.categoryIdName = categoryIdName;
        this.courseLecturerSetIds = courseLecturerSetIds;
        this.courseLecturerSetLecturerIds = courseLecturerSetLecturerIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDto entity = (CourseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.dateAdded, entity.dateAdded) &&
                Objects.equals(this.dateStart, entity.dateStart) &&
                Objects.equals(this.dateEnd, entity.dateEnd) &&
                Objects.equals(this.categoryIdId, entity.categoryIdId) &&
                Objects.equals(this.categoryIdName, entity.categoryIdName) &&
                Objects.equals(this.courseLecturerSetIds, entity.courseLecturerSetIds) &&
                Objects.equals(this.courseLecturerSetLecturerIds, entity.courseLecturerSetLecturerIds);
    }

    public Integer getCategoryIdId() {
        return categoryIdId;
    }

    public String getCategoryIdName() {
        return categoryIdName;
    }

    public Set<Integer> getCourseLecturerSetIds() {
        return courseLecturerSetIds;
    }

    public Set<User> getCourseLecturerSetLecturerIds() {
        return courseLecturerSetLecturerIds;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, dateAdded, dateStart, dateEnd, categoryIdId, categoryIdName, courseLecturerSetIds, courseLecturerSetLecturerIds);
    }

    public void setCategoryIdId(Integer categoryIdId) {
        this.categoryIdId = categoryIdId;
    }

    public void setCategoryIdName(String categoryIdName) {
        this.categoryIdName = categoryIdName;
    }

    public void setCourseLecturerSetIds(Set<Integer> courseLecturerSetIds) {
        this.courseLecturerSetIds = courseLecturerSetIds;
    }

    public void setCourseLecturerSetLecturerIds(Set<User> courseLecturerSetLecturerIds) {
        this.courseLecturerSetLecturerIds = courseLecturerSetLecturerIds;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "dateAdded = " + dateAdded + ", " +
                "dateStart = " + dateStart + ", " +
                "dateEnd = " + dateEnd + ", " +
                "categoryIdId = " + categoryIdId + ", " +
                "categoryIdName = " + categoryIdName + ", " +
                "courseLecturerSetIds = " + courseLecturerSetIds + ", " +
                "courseLecturerSetLecturerIds = " + courseLecturerSetLecturerIds + ")";
    }

//    public static Course toEntity(CourseDto courseDto) {
//        if (courseDto == null) return null;
//        Course course = new Course();
//        course.setId(courseDto.getId());
//        course.setName(courseDto.getName());
//        course.setDescription(courseDto.getDescription());
//        course.setDateAdded(courseDto.getDateAdded());
//        course.setDateStart(courseDto.getDateStart());
//        course.setDateEnd(courseDto.getDateEnd());
//        // Category
//        if (courseDto.getCategoryIdId() != null) {
//            com.ou.pojo.Category category = new com.ou.pojo.Category();
//            category.setId(courseDto.getCategoryIdId());
//            course.setCategoryId(category);
//        } else {
//            course.setCategoryId(null);
//        }
//        // Note: Not mapping courseLecturerSet or other sets here
//        return course;
//    }
//
//    public static CourseDto fromEntity(Course course) {
//        if (course == null) return null;
//        CourseDto dto = new CourseDto();
//        dto.setId(course.getId());
//        dto.setName(course.getName());
//        dto.setDescription(course.getDescription());
//        dto.setDateAdded(course.getDateAdded());
//        dto.setDateStart(course.getDateStart());
//        dto.setDateEnd(course.getDateEnd());
//        if (course.getCategoryId() != null) {
//            dto.setCategoryIdId(course.getCategoryId().getId());
//            dto.setCategoryIdName(course.getCategoryId().getName());
//        }
//        // Note: Not mapping courseLecturerSet or other sets here
//        return dto;
//    }
}