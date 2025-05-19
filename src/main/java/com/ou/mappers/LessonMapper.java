package com.ou.mappers;

import com.ou.dto.LessonDto;
import com.ou.pojo.Lesson;

import com.ou.services.ExerciseAttachmentService;
import com.ou.services.LessonAttachmentService;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    private final LessonAttachmentService lessonAttachmentService;
    private final ExerciseAttachmentService exerciseAttachmentService;

    public LessonMapper(LessonAttachmentService lessonAttachmentService, ExerciseAttachmentService exerciseAttachmentService) {
        this.lessonAttachmentService = lessonAttachmentService;
        this.exerciseAttachmentService = exerciseAttachmentService;
    }


//    public Lesson partialUpdate(LessonDto lessonDto, Lesson lesson) {
//        if ( lessonDto == null ) {
//            return lesson;
//        }
//
//        if ( lessonDto.getId() != null ) {
//            lesson.setId( lessonDto.getId() );
//        }
//        if ( lessonDto.getName() != null ) {
//            lesson.setName( lessonDto.getName() );
//        }
//        if ( lessonDto.getEmbedLink() != null ) {
//            lesson.setEmbedLink( lessonDto.getEmbedLink() );
//        }
//        if ( lessonDto.getDescription() != null ) {
//            lesson.setDescription( lessonDto.getDescription() );
//        }
//        if ( lessonDto.getImage() != null ) {
//            lesson.setImage( lessonDto.getImage() );
//        }
//
//        return lesson;
//    }

    public LessonDto toDto(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonDto lessonDto = new LessonDto();

        lessonDto.setDescription( lesson.getDescription() );
        lessonDto.setEmbedLink( lesson.getEmbedLink() );
        lessonDto.setId(lesson.getId());
        lessonDto.setLessonTypeIdName(lesson.getLessonTypeId().getName());
        lessonDto.setUserUploadIdFirstName(lesson.getUserUploadId().getFirstName());
        lessonDto.setUserUploadIdLastName(lesson.getUserUploadId().getLastName());
        lessonDto.setImage( lesson.getImage() );
        lessonDto.setName( lesson.getName() );
        lessonDto.setCountAttachment(lessonAttachmentService.countLessonAttachmentsByLesson(lesson.getId()));
        lessonDto.setCountExercise(exerciseAttachmentService.countExerciseAttachmentsByExercise(lesson.getId()));

        return lessonDto;
    }

    public Lesson toEntity(LessonDto lessonDto) {
        if ( lessonDto == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setId( lessonDto.getId() );
        lesson.setName( lessonDto.getName() );
        lesson.setEmbedLink( lessonDto.getEmbedLink() );
        lesson.setDescription( lessonDto.getDescription() );
        lesson.setImage( lessonDto.getImage() );

        return lesson;
    }
}
