package com.ou.services;

import com.ou.pojo.WritingAnswer;

import java.util.List;

public interface WritingAnswerService {
    List<WritingAnswer> getAllWritingAnswers();
    WritingAnswer getWritingAnswerById(Integer id);
    WritingAnswer addWritingAnswer(WritingAnswer answer);
    WritingAnswer updateWritingAnswer(WritingAnswer answer);
    boolean deleteWritingAnswer(Integer id);
}
