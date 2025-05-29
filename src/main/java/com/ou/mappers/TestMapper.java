package com.ou.mappers;

import com.ou.dto.TestDto;
import com.ou.pojo.Course;
import com.ou.pojo.Test;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Component
public class TestMapper {

    
    public Test toEntity(TestDto testDto) {
        if ( testDto == null ) {
            return null;
        }

        Test test = new Test();

        test.setCourseId( testDtoToCourse( testDto ) );
        test.setId( testDto.getId() );
        test.setName( testDto.getName() );
        test.setDescription( testDto.getDescription() );
        test.setDurationMinutes( testDto.getDurationMinutes() );
        test.setCreatedAt( testDto.getCreatedAt() );
        test.setMaxScore( testDto.getMaxScore() );

        return test;
    }

    
    public TestDto toDto(Test test) {
        if ( test == null ) {
            return null;
        }

        TestDto testDto = new TestDto();

        testDto.setCourseIdId( testCourseIdId( test ) );
        testDto.setId( test.getId() );
        testDto.setName( test.getName() );
        testDto.setDescription( test.getDescription() );
        testDto.setDurationMinutes( test.getDurationMinutes() );
        testDto.setCreatedAt( test.getCreatedAt() );
        testDto.setMaxScore( test.getMaxScore() );

        return testDto;
    }

    
    public Test partialUpdate(TestDto testDto, Test test) {
        if ( testDto == null ) {
            return test;
        }

        if ( test.getCourseId() == null ) {
            test.setCourseId( new Course() );
        }
        testDtoToCourse1( testDto, test.getCourseId() );
        if ( testDto.getId() != null ) {
            test.setId( testDto.getId() );
        }
        if ( testDto.getName() != null ) {
            test.setName( testDto.getName() );
        }
        if ( testDto.getDescription() != null ) {
            test.setDescription( testDto.getDescription() );
        }
        test.setDurationMinutes( testDto.getDurationMinutes() );
        if ( testDto.getCreatedAt() != null ) {
            test.setCreatedAt( testDto.getCreatedAt() );
        }
        if ( testDto.getMaxScore() != null ) {
            test.setMaxScore( testDto.getMaxScore() );
        }

        return test;
    }

    protected Course testDtoToCourse(TestDto testDto) {
        if ( testDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( testDto.getCourseIdId() );

        return course;
    }

    private Integer testCourseIdId(Test test) {
        Course courseId = test.getCourseId();
        if ( courseId == null ) {
            return null;
        }
        return courseId.getId();
    }

    protected void testDtoToCourse1(TestDto testDto, Course mappingTarget) {
        if ( testDto == null ) {
            return;
        }

        if ( testDto.getCourseIdId() != null ) {
            mappingTarget.setId( testDto.getCourseIdId() );
        }
    }
}
