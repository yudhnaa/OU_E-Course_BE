package com.ou.mappers;

import com.ou.dto.StudentSignUpFormDto;
import com.ou.pojo.Student;
import com.ou.pojo.User;
import com.ou.pojo.UserRole;
import java.time.LocalDate;

import com.ou.services.UserRoleService;
import org.springframework.stereotype.Component;

@Component
public class StudentSignUpFormMapper {

    private final UserRoleService userRoleService;

    public StudentSignUpFormMapper(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public Student partialUpdate(StudentSignUpFormDto studentSignUpFormDto, Student student) {
        if ( studentSignUpFormDto == null ) {
            return student;
        }

        if ( student.getUserId() == null ) {
            student.setUserId( new User() );
        }
        studentSignUpFormDtoToUser( studentSignUpFormDto, student.getUserId() );
        if ( studentSignUpFormDto.getId() != null ) {
            student.setId( studentSignUpFormDto.getId() );
        }

        return student;
    }

    public StudentSignUpFormDto toDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentSignUpFormDto studentSignUpFormDto = new StudentSignUpFormDto();

        studentSignUpFormDto.setUserIdUserRoleIdId( studentUserIdUserRoleIdId( student ) );
        studentSignUpFormDto.setUserIdEmail( studentUserIdEmail( student ) );
        studentSignUpFormDto.setUserIdPassword( studentUserIdPassword( student ) );
        studentSignUpFormDto.setUserIdUsername( studentUserIdUsername( student ) );
        studentSignUpFormDto.setUserIdBirthday( studentUserIdBirthday( student ) );
        studentSignUpFormDto.setUserIdFirstName( studentUserIdFirstName( student ) );
        studentSignUpFormDto.setUserIdLastName( studentUserIdLastName( student ) );
        studentSignUpFormDto.setUserIdId( studentUserIdId( student ) );
        studentSignUpFormDto.setId( student.getId() );

        return studentSignUpFormDto;
    }

    public Student toEntity(StudentSignUpFormDto studentSignUpFormDto) {
        if ( studentSignUpFormDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setUserId( studentSignUpFormDtoToUser1( studentSignUpFormDto ) );
        student.setId( studentSignUpFormDto.getId() );

        return student;
    }

    protected void studentSignUpFormDtoToUserRole(StudentSignUpFormDto studentSignUpFormDto, UserRole mappingTarget) {
        if ( studentSignUpFormDto == null ) {
            return;
        }

        if ( studentSignUpFormDto.getUserIdUserRoleIdId() != null ) {
            mappingTarget.setId( studentSignUpFormDto.getUserIdUserRoleIdId() );
        }
    }

    protected void studentSignUpFormDtoToUser(StudentSignUpFormDto studentSignUpFormDto, User mappingTarget) {
        if ( studentSignUpFormDto == null ) {
            return;
        }

        if ( mappingTarget.getUserRoleId() == null ) {
            mappingTarget.setUserRoleId( new UserRole() );
        }
        studentSignUpFormDtoToUserRole( studentSignUpFormDto, mappingTarget.getUserRoleId() );
        if ( studentSignUpFormDto.getUserIdEmail() != null ) {
            mappingTarget.setEmail( studentSignUpFormDto.getUserIdEmail() );
        }
        if ( studentSignUpFormDto.getUserIdPassword() != null ) {
            mappingTarget.setPassword( studentSignUpFormDto.getUserIdPassword() );
        }
        if ( studentSignUpFormDto.getUserIdUsername() != null ) {
            mappingTarget.setUsername( studentSignUpFormDto.getUserIdUsername() );
        }
        if ( studentSignUpFormDto.getUserIdBirthday() != null ) {
            mappingTarget.setBirthday( studentSignUpFormDto.getUserIdBirthday() );
        }
        if ( studentSignUpFormDto.getUserIdFirstName() != null ) {
            mappingTarget.setFirstName( studentSignUpFormDto.getUserIdFirstName() );
        }
        if ( studentSignUpFormDto.getUserIdLastName() != null ) {
            mappingTarget.setLastName( studentSignUpFormDto.getUserIdLastName() );
        }
        if ( studentSignUpFormDto.getUserIdId() != null ) {
            mappingTarget.setId( studentSignUpFormDto.getUserIdId() );
        }
    }

    private Integer studentUserIdUserRoleIdId(Student student) {
        User userId = student.getUserId();
        if ( userId == null ) {
            return null;
        }
        UserRole userRoleId = userId.getUserRoleId();
        if ( userRoleId == null ) {
            return null;
        }
        return userRoleId.getId();
    }

    private String studentUserIdEmail(Student student) {
        User userId = student.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getEmail();
    }

    private String studentUserIdPassword(Student student) {
        User userId = student.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getPassword();
    }

    private String studentUserIdUsername(Student student) {
        User userId = student.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getUsername();
    }

    private LocalDate studentUserIdBirthday(Student student) {
        User userId = student.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getBirthday();
    }

    private String studentUserIdFirstName(Student student) {
        User userId = student.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getFirstName();
    }

    private String studentUserIdLastName(Student student) {
        User userId = student.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getLastName();
    }

    private Integer studentUserIdId(Student student) {
        User userId = student.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getId();
    }

    protected UserRole studentSignUpFormDtoToUserRole1(StudentSignUpFormDto studentSignUpFormDto) {
        if ( studentSignUpFormDto == null ) {
            return null;
        }

        UserRole userRole = new UserRole();

        userRole.setId( studentSignUpFormDto.getUserIdUserRoleIdId() );

        return userRole;
    }

    protected User studentSignUpFormDtoToUser1(StudentSignUpFormDto studentSignUpFormDto) {
        if ( studentSignUpFormDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserRoleId( studentSignUpFormDtoToUserRole1( studentSignUpFormDto ) );
        user.setEmail( studentSignUpFormDto.getUserIdEmail() );
        user.setPassword( studentSignUpFormDto.getUserIdPassword() );
        user.setUsername( studentSignUpFormDto.getUserIdUsername() );
        user.setBirthday( studentSignUpFormDto.getUserIdBirthday() );
        user.setFirstName( studentSignUpFormDto.getUserIdFirstName() );
        user.setLastName( studentSignUpFormDto.getUserIdLastName() );
        user.setId( studentSignUpFormDto.getUserIdId() );
        user.setAvatarFile(studentSignUpFormDto.getAvatarFile());

        return user;
    }
}
