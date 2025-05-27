package com.ou.services;

import com.ou.pojo.QuestionType;
import com.ou.repositories.QuestionTypeRepository;

import java.util.List;

public interface QuestionTypeService {
    List<QuestionType> getAllQuestionTypes();
    QuestionType getQuestionTypeById(Integer id);
    QuestionType createQuestionType(String name, String description);
    QuestionType updateQuestionType(Integer id, String name, String description);
    boolean deleteQuestionType(Integer id);
}
