package com.ou.mappers;

import com.ou.dto.AttachmentDto;
import com.ou.dto.LessonDto;
import com.ou.dto.LessonWithAttachmentsDto;
import com.ou.pojo.Lesson;

import com.ou.pojo.LessonStudent;
import com.ou.services.ExerciseAttachmentService;
import com.ou.services.ExerciseService;
import com.ou.services.LessonAttachmentService;
import com.ou.services.LessonStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LessonMapper {
    private final LessonAttachmentService lessonAttachmentService;
    private final ExerciseAttachmentService exerciseAttachmentService;
    private final AttachmentMapper attachmentMapper;
    private final ExerciseService exerciseService;
    private final LessonStudentService lessonStudentService;

    public LessonMapper(LessonAttachmentService lessonAttachmentService, ExerciseAttachmentService exerciseAttachmentService, AttachmentMapper attachmentMapper, ExerciseService exerciseService, LessonStudentService lessonStudentService) {
        this.lessonAttachmentService = lessonAttachmentService;
        this.exerciseAttachmentService = exerciseAttachmentService;
        this.attachmentMapper = attachmentMapper;
        this.exerciseService = exerciseService;
        this.lessonStudentService = lessonStudentService;
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
//        lessonDto.setCountExercise(exerciseAttachmentService.countExerciseAttachmentsByLesson(lesson.getId()));
        lessonDto.setCountExercise(exerciseService.countExercisesByLesson(lesson.getId()));



        return lessonDto;
    }

    public LessonWithAttachmentsDto toDtoWithExtraData(Lesson lesson, Integer ... studentId) {
        if ( lesson == null ) {
            return null;
        }

        LessonDto lessonDto = toDto(lesson);

        lessonDto.setCourseIdId(lesson.getCourseId().getId());
        lessonDto.setCourseIdName(lesson.getCourseId().getName());
        lessonDto.setLessonTypeIdId(lesson.getLessonTypeId().getId());
        lessonDto.setLessonTypeIdName(lesson.getLessonTypeId().getName());
        lessonDto.setUserUploadIdId(lesson.getUserUploadId().getId());
        lessonDto.setUserUploadIdUsername(lesson.getUserUploadId().getUsername());
        lessonDto.setOrderIndex(lesson.getOrderIndex());

        LessonWithAttachmentsDto lessonWithAttachmentDto = new LessonWithAttachmentsDto();
        BeanUtils.copyProperties(lessonDto, lessonWithAttachmentDto);

        lessonWithAttachmentDto.setIsLearned(false);
        if ( studentId != null && studentId.length > 0 ) {
            Optional<LessonStudent> lessonStudent = lessonStudentService.getLessonStudentByLessonAndStudent(lesson.getId(), studentId[0]);

            if (lessonStudent.isPresent() && lessonStudent.get().getIsLearn() != null && lessonStudent.get().getIsLearn()) {
                lessonWithAttachmentDto.setIsLearned(true);
            }
        }

        List<AttachmentDto> attachmentDtos = lessonAttachmentService.getAttachmentsByLesson(lesson.getId()).stream().map(attachmentMapper::toDto).toList();
        lessonWithAttachmentDto.setAttachmentDtos(attachmentDtos);


        return lessonWithAttachmentDto;
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
