package com.ou.controllers.restController;

import com.ou.dto.LessonWithAttachmentsDto;
import com.ou.helpers.PaginationHelper;
import com.ou.mappers.AttachmentMapper;
import com.ou.mappers.LessonAttachmentMapper;
import com.ou.mappers.LessonMapper;
import com.ou.pojo.CustomUserDetails;
import com.ou.pojo.Lesson;
import com.ou.pojo.Student;
import com.ou.services.AttachmentService;
import com.ou.services.LessonAttachmentService;
import com.ou.services.LessonService;
import com.ou.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/secure")
public class RestCourseLearningController {

    private final LessonService lessonService;
    private final LessonMapper lessonMapper;
    private final PaginationHelper paginationHelper;
    private final AttachmentService attachmentService;
    private final LessonAttachmentService lessonAttachmentService;
    private final LessonAttachmentMapper lessonAttachmentMapper;
    private final AttachmentMapper attachmentMapper;
    private final StudentService studentService;

    public RestCourseLearningController(LessonService lessonService, LessonMapper lessonMapper, PaginationHelper paginationHelper, AttachmentService attachmentService, LessonAttachmentService lessonAttachmentService, LessonAttachmentMapper lessonAttachmentMapper, AttachmentMapper attachmentMapper, StudentService studentService) {
        this.lessonService = lessonService;
        this.lessonMapper = lessonMapper;
        this.paginationHelper = paginationHelper;
        this.attachmentService = attachmentService;
        this.lessonAttachmentService = lessonAttachmentService;
        this.lessonAttachmentMapper = lessonAttachmentMapper;
        this.attachmentMapper = attachmentMapper;
        this.studentService = studentService;
    }

    @GetMapping("/{courseId}/lessons")
    public ResponseEntity<List<LessonWithAttachmentsDto>> getCourseLessons(
            @PathVariable("courseId") Integer courseId,
            @RequestParam Map<String, String> params,
            @AuthenticationPrincipal CustomUserDetails principal
    ) {
        Student student = studentService.getStudentByUserId(principal.getUser().getId());

        Map<String, String> filters = new HashMap<>();
        filters.putIfAbsent("pageSize", "9999"); // default fetch all

        List<Lesson> lessons = lessonService.getLessonsByCourse(courseId, filters);

        if (lessons.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<LessonWithAttachmentsDto> response = lessons.stream()
                .map( lesson ->
                        lessonMapper.toDtoWithExtraData(lesson, student.getId())
                ).toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
