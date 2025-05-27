package com.ou.services;

import com.ou.pojo.Question;
import com.ou.pojo.WritingAnswer;

import java.util.List;

public interface WritingAnswerService {
    List<WritingAnswer> getAllWritingAnswers();
    WritingAnswer getWritingAnswerById(Integer id);
    boolean addWritingAnswer(Question question, String answer);
    boolean updateWritingAnswer(Integer questionId, Integer answerId, String answer);
    boolean deleteWritingAnswer(Integer id);
    WritingAnswer getWritingAnswerByQuestionId(Integer questionId);
}
