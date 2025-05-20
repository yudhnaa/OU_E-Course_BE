package com.ou.services;

import com.ou.pojo.Attachment;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Service interface for Attachment entity operations
 * @author yudhna
 */
public interface AttachmentService {
    // Create operations
    Attachment addAttachment(Attachment attachment) throws IOException;
    
    // Read operations with pagination
    List<Attachment> getAttachments(Map<String, String> params);
    List<Attachment> searchAttachments(Map<String, String> filters, Map<String, String> params);
    
    // Individual retrieval operations
    Attachment getAttachmentById(Integer id);
    Attachment getAttachmentByName(String name);
    Attachment getAttachmentByLink(String link);
    
    // cannnot update attachment. cause attachment is immutable, create new attachment and delete the old one
//    Attachment updateAttachment(Attachment attachment);
    
    // Delete operation
    boolean deleteAttachment(Integer id) throws IOException;
    
    // Count methods for pagination
    long countAttachments();
    long countSearchResults(Map<String, String> filters);
}
