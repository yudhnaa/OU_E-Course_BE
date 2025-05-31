package com.ou.mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ou.dto.CourseWithProgressDto;
import com.ou.dto.LecturerDto;
import com.ou.pojo.Course;
import com.ou.dto.CourseDto;
import com.ou.pojo.Lecturer;
import com.ou.services.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {


    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private CourseLecturerService courseLecturerService;
    @Autowired
    private LecturerMapper lecturerMapper;
    @Autowired
    private CourseRateService courseRateService;

    public CourseDto toDto(Course course) {
        if ( course == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String description = null;
        String image = null;
        LocalDateTime dateStart = null;
        LocalDateTime dateEnd = null;
        BigDecimal price = null;
        Long studentCount = null;

        id = course.getId();
        name = course.getName();
        description = course.getDescription();
        image = course.getImage();
        dateStart = course.getDateStart();
        dateEnd = course.getDateEnd();
        price = course.getPrice();
        studentCount = courseStudentService.countCourseStudentsByCourse(course.getId());

        Integer categoryIdId = null;
        String categoryIdName = null;
        List<LecturerDto> lecturers = null;
        double averageRate = 0;

        categoryIdId = course.getCategoryId().getId();
        categoryIdName = course.getCategoryId().getName();
        lecturers = courseLecturerService.getLecturersByCourseId(course.getId(), null).stream()
                .map(lecturerMapper::toDto).toList();
        averageRate = courseRateService.calculateAverageRate(course.getId());

        CourseDto courseDto = new CourseDto( id, name, description, image, dateStart, dateEnd, categoryIdId, categoryIdName, studentCount, price);
        courseDto.setLecturers(lecturers);
        courseDto.setAverageRate(averageRate);

        return courseDto;
    }

    public CourseWithProgressDto toDtoWithProgress(Course course, Double progress) {
        if (course == null) {
            return null;
        }

        CourseDto baseDto = toDto(course); // reuse method đã có

        CourseWithProgressDto dto = new CourseWithProgressDto();
        BeanUtils.copyProperties(baseDto, dto); // sao chép toàn bộ property từ CourseDto sang CourseWithProgressDto

        dto.setProgress(progress);
        return dto;
    }
//    public Course partialUpdate(CourseDto courseDto, Course course) {
//        if ( courseDto == null ) {
//            return course;
//        }
//
//        if ( courseDto.getPrice() != null ) {
//            course.setPrice( courseDto.getPrice() );
//        }
//        if ( courseDto.getId() != null ) {
//            course.setId( courseDto.getId() );
//        }
//        if ( courseDto.getName() != null ) {
//            course.setName( courseDto.getName() );
//        }
//        if ( courseDto.getDescription() != null ) {
//            course.setDescription( courseDto.getDescription() );
//        }
//        if ( courseDto.getImage() != null ) {
//            course.setImage( courseDto.getImage() );
//        }
//        if ( courseDto.getDateStart() != null ) {
//            course.setDateStart( courseDto.getDateStart() );
//        }
//        if ( courseDto.getDateEnd() != null ) {
//            course.setDateEnd( courseDto.getDateEnd() );
//        }
//
//        return course;
//    }
    
//    public Course toEntity(CourseDto courseDto) {
//        if ( courseDto == null ) {
//            return null;
//        }
//
//        Course course = new Course();
//
//        course.setPrice( courseDto.getPrice() );
//        course.setId( courseDto.getId() );
//        course.setName( courseDto.getName() );
//        course.setDescription( courseDto.getDescription() );
//        course.setImage( courseDto.getImage() );
//        course.setDateStart( courseDto.getDateStart() );
//        course.setDateEnd( courseDto.getDateEnd() );
//
//        return course;
//    }
}
