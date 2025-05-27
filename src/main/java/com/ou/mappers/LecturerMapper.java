package com.ou.mappers;

import com.ou.dto.LecturerDto;
import com.ou.pojo.Lecturer;
import com.ou.pojo.User;
import com.ou.services.CourseLecturerService;
import com.ou.services.CourseService;
import com.ou.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ou.services.UserService;

@Component
public class LecturerMapper {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseLecturerService courseLecturerService;

//    public Lecturer partialUpdate(LecturerDto lecturerDto, Lecturer lecturer) {
//        if ( lecturerDto == null ) {
//            return lecturer;
//        }
//
//        if ( lecturerDto.getId() != null ) {
//            lecturer.setId( lecturerDto.getId() );
//        }
//
//        return lecturer;
//    }

    public  LecturerDto toDto(Lecturer lecturer) {
        if (lecturer == null) {
            return null;
        }

        LecturerDto lecturerDto = new LecturerDto();

        lecturerDto.setId(lecturer.getId());

        if (lecturer.getUserId() != null) {
            User user = lecturer.getUserId();
            lecturerDto.setUserIdId(user.getId());
            lecturerDto.setUserIdLastName(user.getLastName());
            lecturerDto.setUserIdFirstName(user.getFirstName());
            lecturerDto.setUserIdUsername(user.getUsername());
            lecturerDto.setUserIdAvatar(user.getAvatar());
            lecturerDto.setUserIdPublicId(user.getPublicId());
            lecturerDto.setUserIdEmail(user.getEmail());
            lecturerDto.setUserRoleIdName(user.getUserRoleId().getName());
            lecturerDto.setCountCourse(courseLecturerService.countCourseLecturersByLecturer(lecturer.getId()));
        }

        return lecturerDto;
    }

//    public Lecturer toEntity(LecturerDto lecturerDto) {
//        if (lecturerDto == null) {
//            return null;
//        }
//
//        Lecturer lecturer = new Lecturer();
//
//        lecturer.setId(lecturerDto.getId());
//
//        // Set the User if userIdId is provided
//        if (lecturerDto.getUserIdId() != null) {
//            try {
//
//                User user = userService.getUserById(lecturerDto.getUserIdId());
//
//            } catch (Exception e) {
//                // Log or handle exception if user not found
//            }
//        }
//
//        // Set isActive to true by default for new lecturers
//        lecturer.setIsActive(true);
//
//        return lecturer;
//    }

//    public Lecturer partialUpdate(LecturerDto lecturerDto, Lecturer lecturer) {
//        if (lecturerDto == null) {
//            return lecturer;
//        }
//
//        if (lecturerDto.getId() != null) {
//            lecturer.setId(lecturerDto.getId());
//        }
//
//        // Update User if needed and if UserService is available
//        if (lecturerDto.getUserIdId() != null && lecturer.getUserId() != null
//                && !lecturerDto.getUserIdId().equals(lecturer.getUserId().getId())) {
//            try {
//                User user = userService.getUserById(lecturerDto.getUserIdId());
//                lecturer.setUserId(user);
//            } catch (Exception e) {
//                // Log or handle exception if user not found
//            }
//        }
//
//        return lecturer;
//    }
}
