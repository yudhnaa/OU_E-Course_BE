package com.ou.repositories.impl;

import com.ou.pojo.ExerciseAttemptAnswer;
import com.ou.repositories.ExerciseAttemptAnswerRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class ExerciseAttemptAnswerRepositoryImpl implements ExerciseAttemptAnswerRepository {
    @Autowired
    private LocalSessionFactoryBean factory;


    @Override
    public ExerciseAttemptAnswer addExerciseAttemptAnswer(ExerciseAttemptAnswer exerciseAttemptAnswer) {
        return null;
    }

    @Override
    public List<ExerciseAttemptAnswer> getExerciseAttemptAnswers(Map<String, String> params) {
        return List.of();
    }

    @Override
    public List<ExerciseAttemptAnswer> searchExerciseAttemptAnswers(Map<String, String> filters, Map<String, String> params) {
        return List.of();
    }

    @Override
    public Optional<ExerciseAttemptAnswer> getExerciseAttemptAnswerById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<ExerciseAttemptAnswer> getExerciseAttemptAnswersByAttemptId(Integer attemptId, Map<String, String> params) {
        Session session = factory.getObject().getCurrentSession();
        if (attemptId == null || attemptId <= 0) {
            throw new IllegalArgumentException("Invalid exercise attempt ID");
        }
        return session.createQuery("FROM ExerciseAttemptAnswer WHERE attemptId.id = :attemptId", ExerciseAttemptAnswer.class)
                .setParameter("attemptId", attemptId)
                .getResultList();
    }

    @Override
    public List<ExerciseAttemptAnswer> getExerciseAttemptAnswersByUserId(Integer userId, Map<String, String> params) {
        return List.of();
    }

    @Override
    public ExerciseAttemptAnswer updateExerciseAttemptAnswer(ExerciseAttemptAnswer exerciseAttemptAnswer) {
        Session session = factory.getObject().getCurrentSession();
        session.update(exerciseAttemptAnswer);
        return exerciseAttemptAnswer;
    }

    @Override
    public boolean deleteExerciseAttemptAnswer(Integer id) {
        return false;
    }

    @Override
    public long countExerciseAttemptAnswers() {
        return 0;
    }

    @Override
    public long countExerciseAttemptAnswersByAttemptId(Integer attemptId) {
        return 0;
    }

    @Override
    public long countExerciseAttemptAnswersByUserId(Integer userId) {
        return 0;
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        return 0;
    }
}
