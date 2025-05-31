package com.ou.mappers;

import com.ou.dto.LessonAttachmentDto;
import com.ou.pojo.LessonAttachment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Component
public class LessonAttachmentMapper {


    public LessonAttachmentDto toDto(LessonAttachment lessonAttachment) {
        if ( lessonAttachment == null ) {
            return null;
        }

        Integer id = null;

        id = lessonAttachment.getId();

        Integer attachmentIdId = null;
        String attachmentIdLink = null;
        Integer lessonIdId = null;
        String lessonIdEmbedLink = null;

        LessonAttachmentDto lessonAttachmentDto = new LessonAttachmentDto( id, attachmentIdId, attachmentIdLink, lessonIdId, lessonIdEmbedLink );

        return lessonAttachmentDto;
    }
//
//    public LessonAttachment partialUpdate(LessonAttachmentDto lessonAttachmentDto, LessonAttachment lessonAttachment) {
//        if ( lessonAttachmentDto == null ) {
//            return lessonAttachment;
//        }
//
//        if ( lessonAttachmentDto.getId() != null ) {
//            lessonAttachment.setId( lessonAttachmentDto.getId() );
//        }
//
//        return lessonAttachment;
//    }
//
//    public LessonAttachment toEntity(LessonAttachmentDto lessonAttachmentDto) {
//        if ( lessonAttachmentDto == null ) {
//            return null;
//        }
//
//        LessonAttachment lessonAttachment = new LessonAttachment();
//
//        lessonAttachment.setId( lessonAttachmentDto.getId() );
//
//        return lessonAttachment;
//    }
}
