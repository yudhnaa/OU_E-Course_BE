package com.ou.repositories;

import com.ou.pojo.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> getAllQuestions();
    Question getQuestionById(Integer id);
    Question addQuestion(Question question);
    Question updateQuestion(Question question);
    boolean deleteQuestion(Integer id);
    List<Question> getQuestionsByExercise(Integer exerciseId);
    List<Question> getQuestionsByType(Integer questionTypeId);
    List<Question> getQuestionsByTest(Integer testId);
}
