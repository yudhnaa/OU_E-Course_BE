package com.ou.services.impl;

import com.ou.pojo.Attachment;
import com.ou.repositories.AttachmentRepository;
import com.ou.services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;
    
    @Override
    public Attachment addAttachment(Attachment attachment) {
        // Validate attachment input
        if (attachment == null) {
            throw new IllegalArgumentException("Attachment cannot be null");
        }
        if (attachment.getName() == null || attachment.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Attachment name cannot be empty");
        }
        if (attachment.getLink() == null || attachment.getLink().trim().isEmpty()) {
            throw new IllegalArgumentException("Attachment link cannot be empty");
        }
        
        // Check if attachment with same name already exists
        Optional<Attachment> existingAttachment = attachmentRepository.getAttachmentByName(attachment.getName());
        if (existingAttachment.isPresent()) {
            throw new IllegalStateException("Attachment with name '" + attachment.getName() + "' already exists");
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
            throw new IllegalArgumentException("Attachment ID cannot be null");
        }
        
        return attachmentRepository.getAttachmentById(id)
                .orElseThrow(() -> new NoSuchElementException("Attachment not found with id: " + id));
    }
    
    @Override
    public Attachment getAttachmentByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Attachment name cannot be empty");
        }
        
        return attachmentRepository.getAttachmentByName(name)
                .orElseThrow(() -> new NoSuchElementException("Attachment not found with name: " + name));
    }
    
    @Override
    public Attachment getAttachmentByLink(String link) {
        if (link == null || link.trim().isEmpty()) {
            throw new IllegalArgumentException("Attachment link cannot be empty");
        }
        
        return attachmentRepository.getAttachmentByLink(link)
                .orElseThrow(() -> new NoSuchElementException("Attachment not found with link: " + link));
    }
    
    @Override
    public Attachment updateAttachment(Attachment attachment) {
        // Validate attachment input
        if (attachment == null) {
            throw new IllegalArgumentException("Attachment cannot be null");
        }
        if (attachment.getId() == null) {
            throw new IllegalArgumentException("Attachment ID cannot be null for update operation");
        }
        if (attachment.getName() == null || attachment.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Attachment name cannot be empty");
        }
        if (attachment.getLink() == null || attachment.getLink().trim().isEmpty()) {
            throw new IllegalArgumentException("Attachment link cannot be empty");
        }
        
        // Check if attachment exists
        attachmentRepository.getAttachmentById(attachment.getId())
                .orElseThrow(() -> new NoSuchElementException("Cannot update. Attachment not found with id: " + attachment.getId()));
        
        // Check if updating the name to one that already exists (excluding this attachment)
        Optional<Attachment> existingAttachment = attachmentRepository.getAttachmentByName(attachment.getName());
        if (existingAttachment.isPresent() && !existingAttachment.get().getId().equals(attachment.getId())) {
            throw new IllegalStateException("Cannot update. Another attachment with name '" + attachment.getName() + "' already exists");
        }
        
        return attachmentRepository.updateAttachment(attachment);
    }
    
    @Override
    public boolean deleteAttachment(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Attachment ID cannot be null for delete operation");
        }
        
        // Check if attachment exists before attempting to delete
        attachmentRepository.getAttachmentById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot delete. Attachment not found with id: " + id));
        
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
