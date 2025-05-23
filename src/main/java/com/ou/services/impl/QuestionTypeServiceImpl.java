package com.ou.services.impl;

import com.ou.pojo.QuestionType;
import com.ou.repositories.QuestionTypeRepository;
import com.ou.services.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Override
    public List<QuestionType> getAllQuestionTypes() {
        return questionTypeRepository.getAllQuestionTypes();
    }

    @Override
    public QuestionType getQuestionTypeById(Integer id) {
        return questionTypeRepository.getQuestionTypeById(id);
    }

    @Override
    public QuestionType createQuestionType(String name, String description) {
        return null;
    }

    @Override
    public QuestionType updateQuestionType(Integer id, String name, String description) {
        return null;
    }

    @Override
    public boolean deleteQuestionType(Integer id) {
        return false;
    }
}
