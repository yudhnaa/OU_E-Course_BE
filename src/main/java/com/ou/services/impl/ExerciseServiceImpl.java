package com.ou.services.impl;

import com.ou.pojo.Exercise;
import com.ou.repositories.ExerciseRepository;
import com.ou.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepositoryImpl;

    @Override
    public Exercise createExercise(Exercise exercise) throws Exception {
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
    public List<Exercise> getExercisesByCourse(Integer courseId, Map<String, String> params) {
        return exerciseRepositoryImpl.getExercisesByCourse(courseId, params);
    }

    @Override
    public List<Exercise> getExercisesByLesson(Integer lessonId, Map<String, String> params) {
        return exerciseRepositoryImpl.getExercisesByLesson(lessonId, params);
    }

    @Override
    public List<Exercise> getExercisesByCreator(Integer userId, Map<String, String> params) {
        return List.of();
    }

    @Override
    public Exercise updateExercise(Exercise exercise) throws Exception {
        return exerciseRepositoryImpl.updateExercise(exercise);
    }

    @Override
    public boolean deleteExercise(Integer id) throws Exception {
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
        return 0;
    }
}
