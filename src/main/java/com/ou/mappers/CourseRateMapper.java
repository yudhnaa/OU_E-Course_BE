package com.ou.mappers;

import com.ou.dto.CourseRateDto;
import com.ou.pojo.CourseRate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Component
public class CourseRateMapper{

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

//    public CourseRate toEntity(CourseRateDto courseRateDto) {
//        if ( courseRateDto == null ) {
//            return null;
//        }
//
//        CourseRate courseRate = new CourseRate();
//
//        courseRate.setId( courseRateDto.getId() );
//        courseRate.setRate( courseRateDto.getRate() );
//        courseRate.setComment( courseRateDto.getComment() );
//
//        return courseRate;
//    }
}
