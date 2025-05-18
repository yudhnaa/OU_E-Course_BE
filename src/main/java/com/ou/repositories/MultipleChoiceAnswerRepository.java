package com.ou.repositories;

import com.ou.pojo.MultipleChoiceAnswer;

import java.util.List;

public interface MultipleChoiceAnswerRepository{
    List<MultipleChoiceAnswer> getAllMultipleChoiceAnswers();
    MultipleChoiceAnswer getMultipleChoiceAnswerById(Integer id);
    MultipleChoiceAnswer addMultipleChoiceAnswer(MultipleChoiceAnswer answer);
    MultipleChoiceAnswer updateMultipleChoiceAnswer(MultipleChoiceAnswer answer);
    boolean deleteMultipleChoiceAnswer(Integer id);
    List<MultipleChoiceAnswer> getAnswersByQuestionId(Integer questionId);
    List<MultipleChoiceAnswer> getCorrectAnswers(Integer questionId);
}
