package com.ou.repositories;

import com.ou.pojo.QuestionType;

import java.util.List;

public interface QuestionTypeRepository {
    List<QuestionType> getAllQuestionTypes();
    QuestionType getQuestionTypeById(Integer id);
    QuestionType createQuestionType(String name, String description);
    QuestionType updateQuestionType(Integer id, String name, String description);
    boolean deleteQuestionType(Integer id);
}
