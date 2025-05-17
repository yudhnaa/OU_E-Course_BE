package com.ou.services.impl;

import com.ou.pojo.Course;
import com.ou.pojo.Exercise;
import com.ou.pojo.Lesson;
import com.ou.pojo.User;
import com.ou.repositories.CourseRepository;
import com.ou.repositories.ExerciseRepository;
import com.ou.repositories.LessonRepository;
import com.ou.repositories.UserRepository;
import com.ou.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private LessonRepository lessonRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Exercise createExercise(Exercise exercise) throws Exception {
        // Validate required fields
        validateExerciseFields(exercise);
        
        // Validate relationships
        validateRelationships(exercise, true);
        
        // Check if exercise with same name exists in the same lesson
        checkDuplicateExercise(exercise);
        
        return exerciseRepository.addExercise(exercise);
    }

    @Override
    public Exercise updateExercise(Exercise exercise) throws Exception {
        // Validate if exercise exists
        if (exercise.getId() == null) {
            throw new Exception("Exercise ID cannot be null for update operation");
        }
        
        Optional<Exercise> existingExercise = exerciseRepository.getExerciseById(exercise.getId());
        if (!existingExercise.isPresent()) {
            throw new Exception("Exercise with ID " + exercise.getId() + " not found");
        }
        
        // Validate required fields
        validateExerciseFields(exercise);
        
        // Validate relationships
        validateRelationships(exercise, false);
        
        // Check if update would create a duplicate
        checkDuplicateExerciseForUpdate(exercise);
        
        return exerciseRepository.updateExercise(exercise);
    }

    @Override
    public boolean deleteExercise(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Exercise ID cannot be null");
        }
        
        Optional<Exercise> existingExercise = exerciseRepository.getExerciseById(id);
        if (!existingExercise.isPresent()) {
            throw new Exception("Exercise with ID " + id + " not found");
        }
        
        // Additional checks could be added here
        // For example, check if there are any attempts for this exercise
        // If so, maybe we should not allow deletion
        
        return exerciseRepository.deleteExercise(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Exercise getExerciseById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Exercise ID cannot be null");
        }
        
        Optional<Exercise> exercise = exerciseRepository.getExerciseById(id);
        if (!exercise.isPresent()) {
            throw new Exception("Exercise with ID " + id + " not found");
        }
        
        return exercise.get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getExercises(Map<String, String> params) {
        return exerciseRepository.getExercises(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> searchExercises(Map<String, String> filters, Map<String, String> params) {
        return exerciseRepository.searchExercises(filters, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getExercisesByCourse(Integer courseId, Map<String, String> params) {
        return exerciseRepository.getExercisesByCourse(courseId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getExercisesByLesson(Integer lessonId, Map<String, String> params) {
        return exerciseRepository.getExercisesByLesson(lessonId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getExercisesByCreator(Integer userId, Map<String, String> params) {
        return exerciseRepository.getExercisesByCreator(userId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public long countExercises() {
        return exerciseRepository.countExercises();
    }

    @Override
    @Transactional(readOnly = true)
    public long countExercisesByCourse(Integer courseId) {
        return exerciseRepository.countExercisesByCourse(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countExercisesByLesson(Integer lessonId) {
        return exerciseRepository.countExercisesByLesson(lessonId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countExercisesByCreator(Integer userId) {
        return exerciseRepository.countExercisesByCreator(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        return exerciseRepository.countSearchResults(filters);
    }
    
    // Helper validation methods
    private void validateExerciseFields(Exercise exercise) throws Exception {
        if (exercise == null) {
            throw new Exception("Exercise cannot be null");
        }
        
        if (exercise.getName() == null || exercise.getName().trim().isEmpty()) {
            throw new Exception("Exercise name is required");
        }
        
        if (exercise.getName().length() > 50) {
            throw new Exception("Exercise name cannot exceed 50 characters");
        }
        
        if (exercise.getDurationMinutes() <= 0) {
            throw new Exception("Exercise duration must be greater than 0 minutes");
        }
    }
    
    private void validateRelationships(Exercise exercise, boolean isNew) throws Exception {
        // Validate Course
        if (exercise.getCourseId() == null || exercise.getCourseId().getId() == null) {
            throw new Exception("Course is required for an exercise");
        }

        // Ensure the course exists
        Optional<Course> course = courseRepository.getCourseById(exercise.getCourseId().getId());
        if (!course.isPresent()) {
            throw new Exception("Specified course does not exist");
        }

        // Validate Lesson
        if (exercise.getLessonId() == null || exercise.getLessonId().getId() == null) {
            throw new Exception("Lesson is required for an exercise");
        }

        // Ensure the lesson exists
        Optional<Lesson> lesson = lessonRepository.getLessonById(exercise.getLessonId().getId());
        if (!lesson.isPresent()) {
            throw new Exception("Specified lesson does not exist");
        }

        // Validate lesson belongs to the specified course
        if (!lesson.get().getCourseId().getId().equals(exercise.getCourseId().getId())) {
            throw new Exception("Specified lesson does not belong to the specified course");
        }

        // Validate User Creator
        if (exercise.getCreatedByUserId() == null || exercise.getCreatedByUserId().getId() == null) {
            throw new Exception("Creator user is required for an exercise");
        }

        // Ensure the user exists
        Optional<User> user = userRepository.getUserById(exercise.getCreatedByUserId().getId());
        if (!user.isPresent()) {
            throw new Exception("Specified creator user does not exist");
        }
    }
    
    private void checkDuplicateExercise(Exercise exercise) throws Exception {
        // Create a filter to check for exercises with same name in same lesson
        Map<String, String> filters = Map.of(
            "name", exercise.getName(),
            "lessonId", exercise.getLessonId().getId().toString()
        );
        
        long count = exerciseRepository.countSearchResults(filters);
        if (count > 0) {
            throw new Exception("An exercise with this name already exists in the specified lesson");
        }
    }
    
    private void checkDuplicateExerciseForUpdate(Exercise exercise) throws Exception {
        // Create a filter to check for exercises with same name in same lesson
        Map<String, String> filters = Map.of(
            "name", exercise.getName(),
            "lessonId", exercise.getLessonId().getId().toString()
        );
        
        List<Exercise> existing = exerciseRepository.searchExercises(filters, null);
        
        // Check if there's any exercise with the same name in the same lesson that is not the current one
        boolean hasDuplicate = existing.stream()
            .anyMatch(e -> !e.getId().equals(exercise.getId()));
            
        if (hasDuplicate) {
            throw new Exception("Another exercise with this name already exists in the specified lesson");
        }
    }
}
