package com.ou.mappers;

import com.ou.dto.AttachmentDto;
import com.ou.pojo.Attachment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Component
public class AttachmentMapper {


    public AttachmentDto toDto(Attachment attachment) {
        if ( attachment == null ) {
            return null;
        }

        AttachmentDto attachmentDto = new AttachmentDto();

        attachmentDto.setDescription( attachment.getDescription() );
        attachmentDto.setId( attachment.getId() );
        attachmentDto.setLink( attachment.getLink() );
        attachmentDto.setName( attachment.getName() );

        return attachmentDto;
    }

//    public Attachment partialUpdate(AttachmentDto attachmentDto, Attachment attachment) {
//        if ( attachmentDto == null ) {
//            return attachment;
//        }
//
//        if ( attachmentDto.getId() != null ) {
//            attachment.setId( attachmentDto.getId() );
//        }
//        if ( attachmentDto.getName() != null ) {
//            attachment.setName( attachmentDto.getName() );
//        }
//        if ( attachmentDto.getLink() != null ) {
//            attachment.setLink( attachmentDto.getLink() );
//        }
//        if ( attachmentDto.getDescription() != null ) {
//            attachment.setDescription( attachmentDto.getDescription() );
//        }
//
//        return attachment;
//    }
//
//
//    public Attachment toEntity(AttachmentDto attachmentDto) {
//        if ( attachmentDto == null ) {
//            return null;
//        }
//
//        Attachment attachment = new Attachment();
//
//        attachment.setId( attachmentDto.getId() );
//        attachment.setName( attachmentDto.getName() );
//        attachment.setLink( attachmentDto.getLink() );
//        attachment.setDescription( attachmentDto.getDescription() );
//
//        return attachment;
//    }
}
