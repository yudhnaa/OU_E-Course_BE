package com.ou.repositories.impl;

import com.ou.pojo.Question;
import com.ou.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class QuestionRepositoryImpl implements QuestionRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Question> getAllQuestions() {
        return List.of();
    }

    @Override
    public Question getQuestionById(Integer id) {
        return null;
    }

    @Override
    public Question addQuestion(Question question) {
        return null;
    }

    @Override
    public Question updateQuestion(Question question) {
        return null;
    }

    @Override
    public boolean deleteQuestion(Integer id) {
        return false;
    }

    @Override
    public List<Question> getQuestionsByExercise(Integer exerciseId) {
        return List.of();
    }

    @Override
    public List<Question> getQuestionsByType(Integer questionTypeId) {
        return List.of();
    }
}
