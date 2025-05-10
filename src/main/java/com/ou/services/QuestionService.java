package com.ou.services;

import com.ou.pojo.Question;

import java.util.List;

public interface QuestionService{
    List<Question> getAllQuestions();
    Question getQuestionById(Integer id);
    Question addQuestion(Question question);
    Question updateQuestion(Question question);
    boolean deleteQuestion(Integer id);
    List<Question> getQuestionsByExercise(Integer exerciseId);
    List<Question> getQuestionsByType(Integer questionTypeId);
}
