package com.ou.repositories;

import com.ou.pojo.WritingAnswer;

import java.util.List;

public interface WritingAnswerRepository {
    List<WritingAnswer> getAllWritingAnswers();
    WritingAnswer getWritingAnswerById(Integer id);
    WritingAnswer addWritingAnswer(WritingAnswer answer);
    boolean updateWritingAnswer(WritingAnswer answer);
    boolean deleteWritingAnswer(Integer id);
    WritingAnswer getWritingAnswerByQuestionId(Integer questionId);

}
