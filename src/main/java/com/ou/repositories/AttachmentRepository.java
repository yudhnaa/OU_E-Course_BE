package com.ou.repositories;

import com.ou.pojo.Attachment;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repository interface for Attachment entity operations
 * @author yudhna
 */

public interface AttachmentRepository {
    // Create operations
    Attachment addAttachment(Attachment attachment);
    
    // Read operations with pagination
    List<Attachment> getAttachments(Map<String, String> params);
    List<Attachment> searchAttachments(Map<String, String> filters, Map<String, String> params);
    
    // Individual retrieval operations (using Optional to handle null cases)
    Optional<Attachment> getAttachmentById(Integer id);
    Optional<Attachment> getAttachmentByName(String name);
    Optional<Attachment> getAttachmentByLink(String link);
    
    // Update operation
    Attachment updateAttachment(Attachment attachment);
    
    // Delete operation
    boolean deleteAttachment(Integer id);
    
    // Count methods for pagination
    long countAttachments();
    long countSearchResults(Map<String, String> filters);
}
