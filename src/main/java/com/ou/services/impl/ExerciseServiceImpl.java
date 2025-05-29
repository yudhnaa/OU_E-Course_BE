package com.ou.services.impl;

import com.ou.pojo.Exercise;
import com.ou.pojo.Lecturer;
import com.ou.pojo.User;
import com.ou.repositories.ExerciseRepository;
import com.ou.repositories.LecturerRepository;
import com.ou.services.ExerciseService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepositoryImpl;

    @Autowired
    private LocalizationService localizationService;

    @Autowired
    private LecturerRepository lecturerRepositoryimpl;

    @Override
    public Exercise createExercise(Exercise exercise) throws Exception {
        if(!isValidExercise(exercise)) {
            throw new IllegalArgumentException(localizationService.getMessage("exercise.invalid", LocaleContextHolder.getLocale()));
        }

        return exerciseRepositoryImpl.createExercise(exercise);
    }

    @Override
    public List<Exercise> getExercises(Map<String, String> params) {
        return exerciseRepositoryImpl.getExercises(params);
    }

    @Override
    public List<Exercise> searchExercises(Map<String, String> filters, Map<String, String> params) {
        return List.of();
    }

    @Override
    public Optional<Exercise> getExerciseById(Integer id) throws Exception {
        return exerciseRepositoryImpl.getExerciseById(id);
    }

    @Override
    public Exercise getExerciseByIdWithPermissionsCheck(Integer exerciseId, User user) {
        Optional<Exercise> optionalExercise = exerciseRepositoryImpl.getExerciseById(exerciseId);

        if (optionalExercise.isEmpty()) {
            throw new IllegalArgumentException(localizationService.getMessage("exercise.notfound", LocaleContextHolder.getLocale()));
        }

        if(user.getUserRoleId().getName().contains("LECTURER")) {
            Lecturer lecturer = lecturerRepositoryimpl.getLecturerByUserId(user.getId())
                    .orElseThrow(() -> new AccessDeniedException(localizationService.getMessage("lecturer.notFound", LocaleContextHolder.getLocale())));
            boolean isLecturerOfExercise = exerciseRepositoryImpl.isCreatorOfExercise(lecturer.getId(), exerciseId);
            if (!isLecturerOfExercise) {
                throw new AccessDeniedException(localizationService.getMessage("exercise.access.denied", LocaleContextHolder.getLocale()));
            }
        }
        return optionalExercise.get();
    }

    @Override
    public List<Exercise> getExercisesByCourse(Integer courseId, Map<String, String> params) {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException(localizationService.getMessage("exercise.course.invalid", LocaleContextHolder.getLocale()));
        }
        return exerciseRepositoryImpl.getExercisesByCourse(courseId, params);
    }

    @Override
    public List<Exercise> getExercisesByLesson(Integer lessonId, Map<String, String> params) {
        if (lessonId == null || lessonId <= 0) {
            throw new IllegalArgumentException(localizationService.getMessage("exercise.lesson.invalid", LocaleContextHolder.getLocale()));
        }
        return exerciseRepositoryImpl.getExercisesByLesson(lessonId, params);
    }

    @Override
    public List<Exercise> getExercisesByCreator(Integer userId, Map<String, String> params) {
        return List.of();
    }

    @Override
    public Exercise updateExercise(Exercise exercise) throws Exception {
        if (!isValidExercise(exercise)) {
            throw new IllegalArgumentException(localizationService.getMessage("exercise.invalid", LocaleContextHolder.getLocale()));
        }

        if (exercise.getId() == null || exercise.getId() <= 0) {
            throw new IllegalArgumentException(localizationService.getMessage("exercise.id.invalid", LocaleContextHolder.getLocale()));
        }

        return exerciseRepositoryImpl.updateExercise(exercise);
    }

    @Override
    public boolean deleteExercise(Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(localizationService.getMessage("exercise.id.invalid", LocaleContextHolder.getLocale()));
        }
        return exerciseRepositoryImpl.deleteExercise(id);
    }

    @Override
    public long countExercises() {
        return exerciseRepositoryImpl.countExercises();
    }

    @Override
    public long countExercisesByCourse(Integer courseId) {
        return exerciseRepositoryImpl.countExercisesByCourse(courseId);
    }

    @Override
    public long countExercisesByLesson(Integer lessonId) {
        return exerciseRepositoryImpl.countExercisesByLesson(lessonId);
    }

    @Override
    public long countExercisesByCreator(Integer userId) {
        return 0;
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        if (filters == null || filters.isEmpty()) {
            throw new IllegalArgumentException(localizationService.getMessage("exercise.filters.invalid", LocaleContextHolder.getLocale()));
        }
        return exerciseRepositoryImpl.countSearchResults(filters);
    }

    @Override
    public boolean isValidExercise(Exercise exercise) throws Exception {
        if (exercise == null) {
            throw new IllegalArgumentException("Exercise cannot be null");
        }
        if (exercise.getName() == null || exercise.getName().isEmpty()) {
            throw new IllegalArgumentException("Exercise title cannot be empty");
        }
        if(exercise.getCreatedByUserId() == null || exercise.getCreatedByUserId().getUserId().getId() <= 0) {
            throw new IllegalArgumentException("Invalid user ID for exercise creator");
        }
        if(exercise.getCourseId() == null || exercise.getCourseId().getId() <= 0) {
            throw new IllegalArgumentException("Invalid course ID for exercise");
        }
        if(exercise.getLessonId() == null || exercise.getLessonId().getId() <= 0) {
            throw new IllegalArgumentException("Invalid lesson ID for exercise");
        }
        if(exercise.getDurationMinutes() <= 0) {
            throw new IllegalArgumentException("Exercise duration must be greater than zero");
        }
        if(exercise.getMaxScore() == null || exercise.getMaxScore().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Exercise max score must be greater than zero");
        }

        // Add more validation rules as needed
        return true;
    }
}
