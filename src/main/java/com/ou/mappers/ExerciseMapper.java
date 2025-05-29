package com.ou.mappers;

import com.ou.pojo.Course;
import com.ou.pojo.Exercise;
import com.ou.dto.ExerciseDto;
import com.ou.pojo.Lesson;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    public Exercise toEntity(ExerciseDto exerciseDto) {
        if ( exerciseDto == null ) {
            return null;
        }

        Exercise exercise = new Exercise();

        exercise.setLessonId( exerciseDtoToLesson( exerciseDto ) );
        exercise.setCourseId( exerciseDtoToCourse( exerciseDto ) );
        exercise.setId( exerciseDto.getId() );
        exercise.setName( exerciseDto.getName() );
        exercise.setDurationMinutes( exerciseDto.getDurationMinutes() );
        exercise.setMaxScore( exerciseDto.getMaxScore() );

        return exercise;
    }

    public ExerciseDto toDto(Exercise exercise) {
        if ( exercise == null ) {
            return null;
        }

        ExerciseDto exerciseDto = new ExerciseDto();

        exerciseDto.setLessonIdOrderIndex( exerciseLessonIdOrderIndex( exercise ) );
        exerciseDto.setLessonIdName( exerciseLessonIdName( exercise ) );
        exerciseDto.setLessonIdId( exerciseLessonIdId( exercise ) );
        exerciseDto.setCourseIdId( exerciseCourseIdId( exercise ) );
        exerciseDto.setId( exercise.getId() );
        exerciseDto.setName( exercise.getName() );
        exerciseDto.setDurationMinutes( exercise.getDurationMinutes() );
        exerciseDto.setMaxScore( exercise.getMaxScore() );

        return exerciseDto;
    }

    public Exercise partialUpdate(ExerciseDto exerciseDto, Exercise exercise) {
        if ( exerciseDto == null ) {
            return exercise;
        }

        if ( exercise.getLessonId() == null ) {
            exercise.setLessonId( new Lesson() );
        }
        exerciseDtoToLesson1( exerciseDto, exercise.getLessonId() );
        if ( exercise.getCourseId() == null ) {
            exercise.setCourseId( new Course() );
        }
        exerciseDtoToCourse1( exerciseDto, exercise.getCourseId() );
        if ( exerciseDto.getId() != null ) {
            exercise.setId( exerciseDto.getId() );
        }
        if ( exerciseDto.getName() != null ) {
            exercise.setName( exerciseDto.getName() );
        }
        exercise.setDurationMinutes( exerciseDto.getDurationMinutes() );
        if ( exerciseDto.getMaxScore() != null ) {
            exercise.setMaxScore( exerciseDto.getMaxScore() );
        }

        return exercise;
    }

    protected Lesson exerciseDtoToLesson(ExerciseDto exerciseDto) {
        if ( exerciseDto == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setOrderIndex( exerciseDto.getLessonIdOrderIndex() );
        lesson.setName( exerciseDto.getLessonIdName() );
        lesson.setId( exerciseDto.getLessonIdId() );

        return lesson;
    }

    protected Course exerciseDtoToCourse(ExerciseDto exerciseDto) {
        if ( exerciseDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( exerciseDto.getCourseIdId() );

        return course;
    }

    private int exerciseLessonIdOrderIndex(Exercise exercise) {
        Lesson lessonId = exercise.getLessonId();
        if ( lessonId == null ) {
            return 0;
        }
        return lessonId.getOrderIndex();
    }

    private String exerciseLessonIdName(Exercise exercise) {
        Lesson lessonId = exercise.getLessonId();
        if ( lessonId == null ) {
            return null;
        }
        return lessonId.getName();
    }

    private Integer exerciseLessonIdId(Exercise exercise) {
        Lesson lessonId = exercise.getLessonId();
        if ( lessonId == null ) {
            return null;
        }
        return lessonId.getId();
    }

    private Integer exerciseCourseIdId(Exercise exercise) {
        Course courseId = exercise.getCourseId();
        if ( courseId == null ) {
            return null;
        }
        return courseId.getId();
    }

    protected void exerciseDtoToLesson1(ExerciseDto exerciseDto, Lesson mappingTarget) {
        if ( exerciseDto == null ) {
            return;
        }

        mappingTarget.setOrderIndex( exerciseDto.getLessonIdOrderIndex() );
        if ( exerciseDto.getLessonIdName() != null ) {
            mappingTarget.setName( exerciseDto.getLessonIdName() );
        }
        if ( exerciseDto.getLessonIdId() != null ) {
            mappingTarget.setId( exerciseDto.getLessonIdId() );
        }
    }

    protected void exerciseDtoToCourse1(ExerciseDto exerciseDto, Course mappingTarget) {
        if ( exerciseDto == null ) {
            return;
        }

        if ( exerciseDto.getCourseIdId() != null ) {
            mappingTarget.setId( exerciseDto.getCourseIdId() );
        }
    }
}
