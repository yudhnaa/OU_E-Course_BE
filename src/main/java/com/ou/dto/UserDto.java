package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.User}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 50)
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
    @NotNull
    @Size(min = 1, max = 100)
    private final String email;
    private final boolean isActive;
    private final Set<CourseDto> courseSet;
    private final Set<LessonDto> lessonSet;
    private final Integer userRoleIdId;

    public UserDto(Integer id, String lastName, String firstName, String birthday, String username, String password, String avatar, String email, boolean isActive, Set<CourseDto> courseSet, Set<LessonDto> lessonSet, Integer userRoleIdId) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.isActive = isActive;
        this.courseSet = courseSet;
        this.lessonSet = lessonSet;
        this.userRoleIdId = userRoleIdId;
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

    public Set<CourseDto> getCourseSet() {
        return courseSet;
    }

    public Set<LessonDto> getLessonSet() {
        return lessonSet;
    }

    public Integer getUserRoleIdId() {
        return userRoleIdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.birthday, entity.birthday) &&
                Objects.equals(this.username, entity.username) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.avatar, entity.avatar) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.isActive, entity.isActive) &&
                Objects.equals(this.courseSet, entity.courseSet) &&
                Objects.equals(this.lessonSet, entity.lessonSet) &&
                Objects.equals(this.userRoleIdId, entity.userRoleIdId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, birthday, username, password, avatar, email, isActive, courseSet, lessonSet, userRoleIdId);
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
                "courseSet = " + courseSet + ", " +
                "lessonSet = " + lessonSet + ", " +
                "userRoleIdId = " + userRoleIdId + ")";
    }

    /**
     * DTO for {@link com.ou.pojo.Course}
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CourseDto implements Serializable {
        private final Integer id;
        private final String name;
        private final String description;
        private final Date dateAdded;
        private final Date dateStart;
        private final Date dateEnd;

        public CourseDto(Integer id, String name, String description, Date dateAdded, Date dateStart, Date dateEnd) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.dateAdded = dateAdded;
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Date getDateAdded() {
            return dateAdded;
        }

        public Date getDateStart() {
            return dateStart;
        }

        public Date getDateEnd() {
            return dateEnd;
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
                    Objects.equals(this.dateEnd, entity.dateEnd);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, description, dateAdded, dateStart, dateEnd);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "description = " + description + ", " +
                    "dateAdded = " + dateAdded + ", " +
                    "dateStart = " + dateStart + ", " +
                    "dateEnd = " + dateEnd + ")";
        }
    }

    /**
     * DTO for {@link com.ou.pojo.Lesson}
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LessonDto implements Serializable {
        private final Integer id;
        private final String name;
        private final String embedLink;
        private final String description;

        public LessonDto(Integer id, String name, String embedLink, String description) {
            this.id = id;
            this.name = name;
            this.embedLink = embedLink;
            this.description = description;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmbedLink() {
            return embedLink;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LessonDto entity = (LessonDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.embedLink, entity.embedLink) &&
                    Objects.equals(this.description, entity.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, embedLink, description);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "embedLink = " + embedLink + ", " +
                    "description = " + description + ")";
        }
    }
}