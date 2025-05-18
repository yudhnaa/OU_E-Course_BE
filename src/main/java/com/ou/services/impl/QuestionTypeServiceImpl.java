package com.ou.services.impl;

import com.ou.repositories.QuestionTypeRepository;
import com.ou.services.QuestionTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
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
