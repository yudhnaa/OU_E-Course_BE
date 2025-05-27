package com.ou.mappers;

import com.ou.dto.CourseCertificateDto;
import com.ou.pojo.Course;
import com.ou.pojo.CourseCertificate;
import com.ou.pojo.CourseStudent;
import com.ou.pojo.Student;
import com.ou.pojo.User;
import com.ou.pojo.UserRole;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Component
public class CourseCertificateMapper{

//    public CourseCertificate partialUpdate(CourseCertificateDto courseCertificateDto, CourseCertificate courseCertificate) {
//        if ( courseCertificateDto == null ) {
//            return courseCertificate;
//        }
//
//        if ( courseCertificate.getCourseStudentId() == null ) {
//            courseCertificate.setCourseStudentId( new CourseStudent() );
//        }
//        courseCertificateDtoToCourseStudent( courseCertificateDto, courseCertificate.getCourseStudentId() );
//        if ( courseCertificateDto.getId() != null ) {
//            courseCertificate.setId( courseCertificateDto.getId() );
//        }
//        if ( courseCertificateDto.getDownloadLink() != null ) {
//            courseCertificate.setDownloadLink( courseCertificateDto.getDownloadLink() );
//        }
//
//        return courseCertificate;
//    }

    public CourseCertificateDto toDto(CourseCertificate courseCertificate) {
        if ( courseCertificate == null ) {
            return null;
        }

        CourseCertificateDto courseCertificateDto = new CourseCertificateDto();

        courseCertificateDto.setCourseStudentIdStudentIdUserIdUserRoleIdName( courseCertificateCourseStudentIdStudentIdUserIdUserRoleIdName( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdStudentIdUserIdUserRoleIdId( courseCertificateCourseStudentIdStudentIdUserIdUserRoleIdId( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdStudentIdUserIdEmail( courseCertificateCourseStudentIdStudentIdUserIdEmail( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdStudentIdUserIdAvatar( courseCertificateCourseStudentIdStudentIdUserIdAvatar( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdStudentIdUserIdUsername( courseCertificateCourseStudentIdStudentIdUserIdUsername( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdStudentIdUserIdFirstName( courseCertificateCourseStudentIdStudentIdUserIdFirstName( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdStudentIdUserIdLastName( courseCertificateCourseStudentIdStudentIdUserIdLastName( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdStudentIdUserIdId( courseCertificateCourseStudentIdStudentIdUserIdId( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdStudentIdId( courseCertificateCourseStudentIdStudentIdId( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdCourseIdImage( courseCertificateCourseStudentIdCourseIdImage( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdCourseIdName( courseCertificateCourseStudentIdCourseIdName( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdCourseIdId( courseCertificateCourseStudentIdCourseIdId( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdProgress( courseCertificateCourseStudentIdProgress( courseCertificate ) );
        courseCertificateDto.setCourseStudentIdId( courseCertificateCourseStudentIdId( courseCertificate ) );
        courseCertificateDto.setDownloadLink( courseCertificate.getDownloadLink() );
        courseCertificateDto.setId( courseCertificate.getId() );

        return courseCertificateDto;
    }

//    public CourseCertificate toEntity(CourseCertificateDto courseCertificateDto) {
//        if ( courseCertificateDto == null ) {
//            return null;
//        }
//
//        CourseCertificate courseCertificate = new CourseCertificate();
//
//        courseCertificate.setCourseStudentId( courseCertificateDtoToCourseStudent1( courseCertificateDto ) );
//        courseCertificate.setId( courseCertificateDto.getId() );
//        courseCertificate.setDownloadLink( courseCertificateDto.getDownloadLink() );
//
//        return courseCertificate;
//    }

    protected void courseCertificateDtoToUserRole(CourseCertificateDto courseCertificateDto, UserRole mappingTarget) {
        if ( courseCertificateDto == null ) {
            return;
        }

        if ( courseCertificateDto.getCourseStudentIdStudentIdUserIdUserRoleIdName() != null ) {
            mappingTarget.setName( courseCertificateDto.getCourseStudentIdStudentIdUserIdUserRoleIdName() );
        }
        if ( courseCertificateDto.getCourseStudentIdStudentIdUserIdUserRoleIdId() != null ) {
            mappingTarget.setId( courseCertificateDto.getCourseStudentIdStudentIdUserIdUserRoleIdId() );
        }
    }

    protected void courseCertificateDtoToUser(CourseCertificateDto courseCertificateDto, User mappingTarget) {
        if ( courseCertificateDto == null ) {
            return;
        }

        if ( mappingTarget.getUserRoleId() == null ) {
            mappingTarget.setUserRoleId( new UserRole() );
        }
        courseCertificateDtoToUserRole( courseCertificateDto, mappingTarget.getUserRoleId() );
        if ( courseCertificateDto.getCourseStudentIdStudentIdUserIdEmail() != null ) {
            mappingTarget.setEmail( courseCertificateDto.getCourseStudentIdStudentIdUserIdEmail() );
        }
        if ( courseCertificateDto.getCourseStudentIdStudentIdUserIdAvatar() != null ) {
            mappingTarget.setAvatar( courseCertificateDto.getCourseStudentIdStudentIdUserIdAvatar() );
        }
        if ( courseCertificateDto.getCourseStudentIdStudentIdUserIdUsername() != null ) {
            mappingTarget.setUsername( courseCertificateDto.getCourseStudentIdStudentIdUserIdUsername() );
        }
        if ( courseCertificateDto.getCourseStudentIdStudentIdUserIdFirstName() != null ) {
            mappingTarget.setFirstName( courseCertificateDto.getCourseStudentIdStudentIdUserIdFirstName() );
        }
        if ( courseCertificateDto.getCourseStudentIdStudentIdUserIdLastName() != null ) {
            mappingTarget.setLastName( courseCertificateDto.getCourseStudentIdStudentIdUserIdLastName() );
        }
        if ( courseCertificateDto.getCourseStudentIdStudentIdUserIdId() != null ) {
            mappingTarget.setId( courseCertificateDto.getCourseStudentIdStudentIdUserIdId() );
        }
    }

    protected void courseCertificateDtoToStudent(CourseCertificateDto courseCertificateDto, Student mappingTarget) {
        if ( courseCertificateDto == null ) {
            return;
        }

        if ( mappingTarget.getUserId() == null ) {
            mappingTarget.setUserId( new User() );
        }
        courseCertificateDtoToUser( courseCertificateDto, mappingTarget.getUserId() );
        if ( courseCertificateDto.getCourseStudentIdStudentIdId() != null ) {
            mappingTarget.setId( courseCertificateDto.getCourseStudentIdStudentIdId() );
        }
    }

    protected void courseCertificateDtoToCourse(CourseCertificateDto courseCertificateDto, Course mappingTarget) {
        if ( courseCertificateDto == null ) {
            return;
        }

        if ( courseCertificateDto.getCourseStudentIdCourseIdImage() != null ) {
            mappingTarget.setImage( courseCertificateDto.getCourseStudentIdCourseIdImage() );
        }
        if ( courseCertificateDto.getCourseStudentIdCourseIdName() != null ) {
            mappingTarget.setName( courseCertificateDto.getCourseStudentIdCourseIdName() );
        }
        if ( courseCertificateDto.getCourseStudentIdCourseIdId() != null ) {
            mappingTarget.setId( courseCertificateDto.getCourseStudentIdCourseIdId() );
        }
    }

    protected void courseCertificateDtoToCourseStudent(CourseCertificateDto courseCertificateDto, CourseStudent mappingTarget) {
        if ( courseCertificateDto == null ) {
            return;
        }

        if ( mappingTarget.getStudentId() == null ) {
            mappingTarget.setStudentId( new Student() );
        }
        courseCertificateDtoToStudent( courseCertificateDto, mappingTarget.getStudentId() );
        if ( mappingTarget.getCourseId() == null ) {
            mappingTarget.setCourseId( new Course() );
        }
        courseCertificateDtoToCourse( courseCertificateDto, mappingTarget.getCourseId() );
        mappingTarget.setProgress( courseCertificateDto.getCourseStudentIdProgress() );
        if ( courseCertificateDto.getCourseStudentIdId() != null ) {
            mappingTarget.setId( courseCertificateDto.getCourseStudentIdId() );
        }
    }

    private String courseCertificateCourseStudentIdStudentIdUserIdUserRoleIdName(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Student studentId = courseStudentId.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        User userId = studentId.getUserId();
        if ( userId == null ) {
            return null;
        }
        UserRole userRoleId = userId.getUserRoleId();
        if ( userRoleId == null ) {
            return null;
        }
        return userRoleId.getName();
    }

    private Integer courseCertificateCourseStudentIdStudentIdUserIdUserRoleIdId(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Student studentId = courseStudentId.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        User userId = studentId.getUserId();
        if ( userId == null ) {
            return null;
        }
        UserRole userRoleId = userId.getUserRoleId();
        if ( userRoleId == null ) {
            return null;
        }
        return userRoleId.getId();
    }

    private String courseCertificateCourseStudentIdStudentIdUserIdEmail(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Student studentId = courseStudentId.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        User userId = studentId.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getEmail();
    }

    private String courseCertificateCourseStudentIdStudentIdUserIdAvatar(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Student studentId = courseStudentId.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        User userId = studentId.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getAvatar();
    }

    private String courseCertificateCourseStudentIdStudentIdUserIdUsername(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Student studentId = courseStudentId.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        User userId = studentId.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getUsername();
    }

    private String courseCertificateCourseStudentIdStudentIdUserIdFirstName(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Student studentId = courseStudentId.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        User userId = studentId.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getFirstName();
    }

    private String courseCertificateCourseStudentIdStudentIdUserIdLastName(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Student studentId = courseStudentId.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        User userId = studentId.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getLastName();
    }

    private Integer courseCertificateCourseStudentIdStudentIdUserIdId(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Student studentId = courseStudentId.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        User userId = studentId.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getId();
    }

    private Integer courseCertificateCourseStudentIdStudentIdId(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Student studentId = courseStudentId.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        return studentId.getId();
    }

    private String courseCertificateCourseStudentIdCourseIdImage(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Course courseId = courseStudentId.getCourseId();
        if ( courseId == null ) {
            return null;
        }
        return courseId.getImage();
    }

    private String courseCertificateCourseStudentIdCourseIdName(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Course courseId = courseStudentId.getCourseId();
        if ( courseId == null ) {
            return null;
        }
        return courseId.getName();
    }

    private Integer courseCertificateCourseStudentIdCourseIdId(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        Course courseId = courseStudentId.getCourseId();
        if ( courseId == null ) {
            return null;
        }
        return courseId.getId();
    }

    private double courseCertificateCourseStudentIdProgress(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return 0.0d;
        }
        return courseStudentId.getProgress();
    }

    private Integer courseCertificateCourseStudentIdId(CourseCertificate courseCertificate) {
        CourseStudent courseStudentId = courseCertificate.getCourseStudentId();
        if ( courseStudentId == null ) {
            return null;
        }
        return courseStudentId.getId();
    }

    protected UserRole courseCertificateDtoToUserRole1(CourseCertificateDto courseCertificateDto) {
        if ( courseCertificateDto == null ) {
            return null;
        }

        UserRole userRole = new UserRole();

        userRole.setName( courseCertificateDto.getCourseStudentIdStudentIdUserIdUserRoleIdName() );
        userRole.setId( courseCertificateDto.getCourseStudentIdStudentIdUserIdUserRoleIdId() );

        return userRole;
    }

    protected User courseCertificateDtoToUser1(CourseCertificateDto courseCertificateDto) {
        if ( courseCertificateDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserRoleId( courseCertificateDtoToUserRole1( courseCertificateDto ) );
        user.setEmail( courseCertificateDto.getCourseStudentIdStudentIdUserIdEmail() );
        user.setAvatar( courseCertificateDto.getCourseStudentIdStudentIdUserIdAvatar() );
        user.setUsername( courseCertificateDto.getCourseStudentIdStudentIdUserIdUsername() );
        user.setFirstName( courseCertificateDto.getCourseStudentIdStudentIdUserIdFirstName() );
        user.setLastName( courseCertificateDto.getCourseStudentIdStudentIdUserIdLastName() );
        user.setId( courseCertificateDto.getCourseStudentIdStudentIdUserIdId() );

        return user;
    }

    protected Student courseCertificateDtoToStudent1(CourseCertificateDto courseCertificateDto) {
        if ( courseCertificateDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setUserId( courseCertificateDtoToUser1( courseCertificateDto ) );
        student.setId( courseCertificateDto.getCourseStudentIdStudentIdId() );

        return student;
    }

    protected Course courseCertificateDtoToCourse1(CourseCertificateDto courseCertificateDto) {
        if ( courseCertificateDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setImage( courseCertificateDto.getCourseStudentIdCourseIdImage() );
        course.setName( courseCertificateDto.getCourseStudentIdCourseIdName() );
        course.setId( courseCertificateDto.getCourseStudentIdCourseIdId() );

        return course;
    }

    protected CourseStudent courseCertificateDtoToCourseStudent1(CourseCertificateDto courseCertificateDto) {
        if ( courseCertificateDto == null ) {
            return null;
        }

        CourseStudent courseStudent = new CourseStudent();

        courseStudent.setStudentId( courseCertificateDtoToStudent1( courseCertificateDto ) );
        courseStudent.setCourseId( courseCertificateDtoToCourse1( courseCertificateDto ) );
        courseStudent.setProgress( courseCertificateDto.getCourseStudentIdProgress() );
        courseStudent.setId( courseCertificateDto.getCourseStudentIdId() );

        return courseStudent;
    }
}
