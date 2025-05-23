package com.ou.services;

import com.ou.pojo.MultipleChoiceAnswer;
import com.ou.pojo.Question;

import java.util.List;

public interface MultipleChoiceAnswerService {
    List<MultipleChoiceAnswer> getAllMultipleChoiceAnswers();
    MultipleChoiceAnswer getMultipleChoiceAnswerById(Integer id);
    boolean addMultipleChoiceAnswer(Question question,List<String> options, Integer correctAnswer);
    boolean updateMultipleChoiceAnswer(Integer questionId, List<Integer> answers, List<String> options,Integer correctAnswer);
    boolean deleteMultipleChoiceAnswer(Integer id);
    List<MultipleChoiceAnswer> getAnswersByQuestionId(Integer questionId);
    MultipleChoiceAnswer getCorrectAnswer(Integer questionId);
}
