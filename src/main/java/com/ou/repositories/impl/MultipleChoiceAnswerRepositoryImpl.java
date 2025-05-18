package com.ou.repositories.impl;


import com.ou.pojo.MultipleChoiceAnswer;
import com.ou.repositories.MultipleChoiceAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MultipleChoiceAnswerRepositoryImpl implements MultipleChoiceAnswerRepository {
    @Autowired
    private LocalSessionFactoryBean factory;


    @Override
    public List<MultipleChoiceAnswer> getAllMultipleChoiceAnswers() {
        return List.of();
    }

    @Override
    public MultipleChoiceAnswer getMultipleChoiceAnswerById(Integer id) {
        return null;
    }

    @Override
    public MultipleChoiceAnswer addMultipleChoiceAnswer(MultipleChoiceAnswer answer) {
        return null;
    }

    @Override
    public MultipleChoiceAnswer updateMultipleChoiceAnswer(MultipleChoiceAnswer answer) {
        return null;
    }

    @Override
    public boolean deleteMultipleChoiceAnswer(Integer id) {
        return false;
    }

    @Override
    public List<MultipleChoiceAnswer> getAnswersByQuestionId(Integer questionId) {
        return List.of();
    }

    @Override
    public List<MultipleChoiceAnswer> getCorrectAnswers(Integer questionId) {
        return List.of();
    }
}
