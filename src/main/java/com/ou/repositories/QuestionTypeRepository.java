package com.ou.repositories;

import java.util.List;

public interface QuestionTypeRepository {
    List<QuestionTypeRepository> getAllQuestionTypes();
    List<QuestionTypeRepository> getQuestionTypeById(Integer id);
    QuestionTypeRepository createQuestionType(String name, String description);
    QuestionTypeRepository updateQuestionType(Integer id, String name, String description);
    boolean deleteQuestionType(Integer id);
}
