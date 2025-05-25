package com.ou.services.impl;

import com.ou.exceptions.NotFoundException;
import com.ou.helpers.CloudinaryHelper;
import com.ou.pojo.Attachment;
import com.ou.repositories.AttachmentRepository;
import com.ou.services.AttachmentService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private LocalizationService localizationService;

    @Autowired
    private CloudinaryHelper cloudinaryHelper;
    @Override
    public Attachment addAttachment(Attachment attachment) throws IOException {
        // Validate attachment input
        if (attachment == null) {
            throw new IllegalArgumentException(localizationService.getMessage("attachment.null", LocaleContextHolder.getLocale()));
        }
        if (attachment.getName() == null || attachment.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(localizationService.getMessage("attachment.name.empty", LocaleContextHolder.getLocale()));
        }

        // Upload file to Cloudinary if provided
        if (attachment.getFile() != null && !attachment.getFile().isEmpty()) {
            Map<String, String> imageUrl = cloudinaryHelper.uploadMultipartFile(attachment.getFile());
            attachment.setLink(imageUrl.get("url"));
            attachment.setPublicId(imageUrl.get("publicId"));
        } else if (attachment.getLink() == null || attachment.getLink().trim().isEmpty()) {
            throw new IllegalArgumentException(localizationService.getMessage("attachment.link.empty", LocaleContextHolder.getLocale()));
        }

        return attachmentRepository.addAttachment(attachment);
    }
    
    @Override
    public List<Attachment> getAttachments(Map<String, String> params) {
        return attachmentRepository.getAttachments(params);
    }
    
    @Override
    public List<Attachment> searchAttachments(Map<String, String> filters, Map<String, String> params) {
        return attachmentRepository.searchAttachments(filters, params);
    }
    
    @Override
    public Attachment getAttachmentById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException(localizationService.getMessage("attachment.id.null", LocaleContextHolder.getLocale()));
        }
        
        return attachmentRepository.getAttachmentById(id)
                .orElseThrow(() -> new NotFoundException(
                    localizationService.getMessage("attachment.notFound", LocaleContextHolder.getLocale())
                ));
    }
    
    @Override
    public Attachment getAttachmentByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(localizationService.getMessage("attachment.name.empty", LocaleContextHolder.getLocale()));
        }
        
        return attachmentRepository.getAttachmentByName(name)
                .orElseThrow(() -> new NotFoundException(
                    localizationService.getMessage("attachment.name.notFound", LocaleContextHolder.getLocale())
                ));
    }
    
    @Override
    public Attachment getAttachmentByLink(String link) {
        if (link == null || link.trim().isEmpty()) {
            throw new IllegalArgumentException(localizationService.getMessage("attachment.link.empty", LocaleContextHolder.getLocale()));
        }
        
        return attachmentRepository.getAttachmentByLink(link)
                .orElseThrow(() -> new NotFoundException(
                    localizationService.getMessage("attachment.link.notFound", LocaleContextHolder.getLocale())
                ));
    }
    
//    @Override
//    public Attachment updateAttachment(Attachment attachment) {
//        // Validate attachment input
//        if (attachment == null) {
//            throw new IllegalArgumentException(localizationService.getMessage("attachment.null", LocaleContextHolder.getLocale()));
//        }
//        if (attachment.getId() == null) {
//            throw new IllegalArgumentException(localizationService.getMessage("attachment.id.null", LocaleContextHolder.getLocale()));
//        }
//        if (attachment.getName() == null || attachment.getName().trim().isEmpty()) {
//            throw new IllegalArgumentException(localizationService.getMessage("attachment.name.empty", LocaleContextHolder.getLocale()));
//        }
//
//        // Check if attachment exists
//        Attachment existingAttachment = getAttachmentById(attachment.getId());
//
//        // Upload new file if provided
//        MultipartFile file = attachment.getFile();
//        if (file != null && !file.isEmpty()) {
//            try {
//
//                Map<String, String> imageUrl = cloudinaryHelper.uploadFile(file);
//                attachment.setLink(imageUrl.get("url"));
//                attachment.setPublicId(imageUrl.get("publicId"));
//
//
//            } catch (IOException e) {
//                throw new RuntimeException(localizationService.getMessage("cloudinary.upload.error", LocaleContextHolder.getLocale()), e);
//            }
//        } else if (attachment.getLink() == null || attachment.getLink().trim().isEmpty()) {
//            // Keep existing link if new link is not provided
//            attachment.setLink(existingAttachment.getLink());
//        }
//
//        // Check for name uniqueness if name is changed
//        if (!attachment.getName().equals(existingAttachment.getName())) {
//            Optional<Attachment> attachmentWithSameName = attachmentRepository.getAttachmentByName(attachment.getName());
//            if (attachmentWithSameName.isPresent() && !attachmentWithSameName.get().getId().equals(attachment.getId())) {
//                throw new IllegalArgumentException(localizationService.getMessage("attachment.name.duplicate", LocaleContextHolder.getLocale()));
//            }
//        }
//
//        return attachmentRepository.updateAttachment(attachment);
//    }
    
    @Override
    public boolean deleteAttachment(Integer id) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException(localizationService.getMessage("attachment.id.null", LocaleContextHolder.getLocale()));
        }

        // Check if attachment exists before attempting to delete
        Attachment cur = getAttachmentById(id);

        cloudinaryHelper.deleteFile(cur.getPublicId());
        
        return attachmentRepository.deleteAttachment(id);
    }
    
    @Override
    public long countAttachments() {
        return attachmentRepository.countAttachments();
    }
    
    @Override
    public long countSearchResults(Map<String, String> filters) {
        return attachmentRepository.countSearchResults(filters);
    }
}
