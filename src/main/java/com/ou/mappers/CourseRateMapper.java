package com.ou.mappers;

import com.ou.dto.CourseRateDto;
import com.ou.pojo.CourseRate;
import javax.annotation.processing.Generated;

import com.ou.pojo.CourseStudent;
import com.ou.services.CourseStudentService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CourseRateMapper{
    private final CourseStudentService courseStudentService;

    public CourseRateMapper(CourseStudentService courseStudentService) {
        this.courseStudentService = courseStudentService;
    }

//    public CourseRate partialUpdate(CourseRateDto courseRateDto, CourseRate courseRate) {
//        if ( courseRateDto == null ) {
//            return courseRate;
//        }
//
//        if ( courseRateDto.getId() != null ) {
//            courseRate.setId( courseRateDto.getId() );
//        }
//        courseRate.setRate( courseRateDto.getRate() );
//        if ( courseRateDto.getComment() != null ) {
//            courseRate.setComment( courseRateDto.getComment() );
//        }
//
//        return courseRate;
//    }

    public CourseRateDto toDto(CourseRate courseRate) {
        if ( courseRate == null ) {
            return null;
        }

        CourseRateDto courseRateDto = new CourseRateDto();

        courseRateDto.setId( courseRate.getId() );
        courseRateDto.setRate( courseRate.getRate() );
        courseRateDto.setComment( courseRate.getComment() );
        courseRateDto.setCourseIdId(courseRate.getCourseStudentId().getCourseId().getId());
        courseRateDto.setStudentIdId(courseRate.getCourseStudentId().getStudentId().getId());
        courseRateDto.setStudentIdUsername(courseRate.getCourseStudentId().getStudentId().getUserId().getUsername());

        return courseRateDto;
    }

    public CourseRate toEntity(CourseRateDto courseRateDto,  Integer userId) {
        if ( courseRateDto == null ) {
            return null;
        }

        CourseRate courseRate = new CourseRate();

        courseRate.setId( courseRateDto.getId() );
        courseRate.setRate( courseRateDto.getRate() );
        courseRate.setComment( courseRateDto.getComment());
        Optional<CourseStudent> courseStudent = courseStudentService.getCourseStudentByCourseAndUser(courseRateDto.getCourseIdId(), userId);

        if (courseStudent.isEmpty()) {
            throw new IllegalArgumentException("Course student not found for courseId: " + courseRateDto.getCourseIdId() + " and userId: " + userId);
        }

        courseRate.setCourseStudentId(courseStudent.get());

        return courseRate;
    }
}
