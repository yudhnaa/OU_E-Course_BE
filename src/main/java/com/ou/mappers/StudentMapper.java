package com.ou.mappers;

import com.ou.dto.StudentDto;
import com.ou.pojo.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto toDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDto studentDto = new StudentDto();

        studentDto.setId( student.getId() );
        studentDto.setUserIdLastName(student.getUserId().getLastName());
        studentDto.setUserIdFirstName(student.getUserId().getFirstName());
        studentDto.setUserIdBirthday(student.getUserId().getBirthday());
        studentDto.setUserIdUsername(student.getUserId().getUsername());
        studentDto.setUserIdEmail(student.getUserId().getEmail());
        studentDto.setUserRoleIdId(student.getUserId().getUserRoleId().getId());
        studentDto.setUserRoleIdName(student.getUserId().getUserRoleId().getName());
        studentDto.setUserIdAvatar(student.getUserId().getAvatar());

        return studentDto;
    }

    
//
//    public Student toEntity(StudentDto studentDto) {
//        if ( studentDto == null ) {
//            return null;
//        }
//
//        Student student = new Student();
//
//        student.setId( studentDto.getId() );
//
//        return student;
//    }
}
