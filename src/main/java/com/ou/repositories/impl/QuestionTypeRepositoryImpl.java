package com.ou.repositories.impl;


import com.ou.repositories.QuestionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class QuestionTypeRepositoryImpl implements QuestionTypeRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<QuestionTypeRepository> getAllQuestionTypes() {
        return List.of();
    }

    @Override
    public List<QuestionTypeRepository> getQuestionTypeById(Integer id) {
        return List.of();
    }

    @Override
    public QuestionTypeRepository createQuestionType(String name, String description) {
        return null;
    }

    @Override
    public QuestionTypeRepository updateQuestionType(Integer id, String name, String description) {
        return null;
    }

    @Override
    public boolean deleteQuestionType(Integer id) {
        return false;
    }
}
