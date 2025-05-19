package com.ou.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.pojo.Attachment;
import com.ou.pojo.Lesson;
import com.ou.pojo.LessonAttachment;
import com.ou.repositories.LessonRepository;
import com.ou.services.AttachmentService;
import com.ou.services.LessonAttachmentService;
import com.ou.services.LessonService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private LocalizationService localizationService;

    @Autowired
    private LessonAttachmentService lessonAttachmentService;

    @Autowired
    private AttachmentService attachmentService;

    @Override
    public Lesson createLesson(Lesson lesson) throws Exception {
        // Validate lesson data
        validateLessonData(lesson);
        
        // Additional business logic validations
        if (lesson.getId() != null) {
            throw new Exception("Cannot create a lesson with a predefined ID");
        }
        
        // Check for required relationships
        if (lesson.getCourseId() == null) {
            throw new Exception("Course is required for a lesson");
        }
        
        if (lesson.getLessonTypeId() == null) {
            throw new Exception("Lesson type is required");
        }
        
        if (lesson.getUserUploadId() == null) {
            throw new Exception("User who uploads the lesson is required");
        }

        if (!lesson.getThumbnailImage().isEmpty()){
            String imageUrl = uploadImageToCloudinary(lesson.getThumbnailImage());
            lesson.setImage(imageUrl);
        }
        Lesson newLesson = lessonRepository.addLesson(lesson);

        if (lesson.getLessonAttachments() != null && newLesson != null) {
            for (MultipartFile file : lesson.getLessonAttachments()) {
                if (!file.isEmpty()) {
                    Attachment attachment = new Attachment();
                    attachment.setName(file.getOriginalFilename());
                    attachment.setFile(file);
                    Attachment savedAttachment = attachmentService.addAttachment(attachment);

                    LessonAttachment lessonAttachment = new LessonAttachment();
                    lessonAttachment.setLessonId(newLesson);
                    lessonAttachment.setAttachmentId(savedAttachment);
                    lessonAttachmentService.createLessonAttachment(lessonAttachment);
                }
            }
        }

        return newLesson;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessons(Map<String, String> params) {
        return lessonRepository.getLessons(params);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Lesson> getLessonById(Integer id) {
        if (id == null || id <= 0) {
            return Optional.empty();
        }
        return lessonRepository.getLessonById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsByName(String name, Map<String, String> params) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Lesson name cannot be empty");
        }
        return lessonRepository.getLessonsByName(name, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsByCourse(Integer courseId, Map<String, String> params) {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Invalid course ID");
        }
        return lessonRepository.getLessonsByCourse(courseId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsByType(Integer typeId, Map<String, String> params) {
        if (typeId == null || typeId <= 0) {
            throw new IllegalArgumentException("Invalid lesson type ID");
        }
        return lessonRepository.getLessonsByType(typeId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsByUploadUser(Integer userId, Map<String, String> params) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        return lessonRepository.getLessonsByUploadUser(userId, params);
    }

    @Override
    public Lesson updateLesson(Lesson lesson) throws Exception {
        // Validate lesson exists
        if (lesson.getId() == null) {
            throw new Exception("Cannot update lesson without ID");
        }
        
        Optional<Lesson> existingLesson = lessonRepository.getLessonById(lesson.getId());
        if (existingLesson.isEmpty()) {
            throw new Exception("Lesson with ID " + lesson.getId() + " not found");
        }
        
        // Validate lesson data
        validateLessonData(lesson);
        
        // Check for required relationships
        if (lesson.getCourseId() == null) {
            throw new Exception("Course is required for a lesson");
        }
        
        if (lesson.getLessonTypeId() == null) {
            throw new Exception("Lesson type is required");
        }
        
        if (lesson.getUserUploadId() == null) {
            throw new Exception("User who uploads the lesson is required");
        }
        
        return lessonRepository.updateLesson(lesson);
    }

    @Override
    public boolean deleteLesson(Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid lesson ID");
        }
        
        // Check if lesson exists
        Optional<Lesson> existingLesson = lessonRepository.getLessonById(id);
        if (existingLesson.isEmpty()) {
            throw new Exception("Lesson with ID " + id + " not found");
        }
        
        // Additional business logic before deletion could go here
        // For example, check if there are dependencies that would prevent deletion
        Lesson lesson = existingLesson.get();
        
        if (lesson.getExerciseSet() != null && !lesson.getExerciseSet().isEmpty()) {
            throw new Exception("Cannot delete lesson with associated exercises");
        }

        if (lesson.getLessonAttachmentSet() != null && !lesson.getLessonAttachmentSet().isEmpty()) {
            throw new Exception("Cannot delete lesson with associated attachments. Remove attachments first.");
        }
        
        if (lesson.getLessonStudentSet() != null && !lesson.getLessonStudentSet().isEmpty()) {
            throw new Exception("Cannot delete lesson that has been assigned to students");
        }
        
        return lessonRepository.deleteLesson(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> searchLessons(Map<String, String> filters, Map<String, String> params) {
        return lessonRepository.searchLessons(filters, params);
    }

    @Override
    @Transactional(readOnly = true)
    public long countLessons(String locale) {
        return lessonRepository.countLessons(locale);
    }

    @Override
    @Transactional(readOnly = true)
    public long countLessonsByCourse(Integer courseId) {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Invalid course ID");
        }
        return lessonRepository.countLessonsByCourse(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countLessonsByType(Integer typeId) {
        if (typeId == null || typeId <= 0) {
            throw new IllegalArgumentException("Invalid lesson type ID");
        }
        return lessonRepository.countLessonsByType(typeId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countLessonsByUploadUser(Integer userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        return lessonRepository.countLessonsByUploadUser(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        return lessonRepository.countSearchResults(filters);
    }
    
    // Helper methods for validation
    private void validateLessonData(Lesson lesson) throws Exception {
        if (lesson == null) {
            throw new Exception("Lesson cannot be null");
        }
        
        // Name validation
        if (lesson.getName() == null || lesson.getName().trim().isEmpty()) {
            throw new Exception("Lesson name cannot be empty");
        }
        
        if (lesson.getName().length() > 50) {
            throw new Exception("Lesson name cannot exceed 50 characters");
        }
        
        // Embed link validation
        if (lesson.getEmbedLink() == null || lesson.getEmbedLink().trim().isEmpty()) {
            throw new Exception("Embed link cannot be empty");
        }
        
        if (lesson.getEmbedLink().length() > 255) {
            throw new Exception("Embed link cannot exceed 255 characters");
        }
        
        // Description validation (optional field)
        if (lesson.getDescription() != null && lesson.getDescription().length() > 65535) {
            throw new Exception("Description is too long");
        }
    }

    private String uploadImageToCloudinary(MultipartFile file) throws IOException {
        try {
            Map res = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            return res.get("secure_url").toString();
        } catch (IOException ex) {
            throw  new IOException(localizationService.getMessage("cloudinary.upload.error", LocaleContextHolder.getLocale()));
        }
    }
}
