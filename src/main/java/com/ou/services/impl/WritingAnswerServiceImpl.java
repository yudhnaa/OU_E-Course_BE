package com.ou.services.impl;


import com.ou.pojo.Question;
import com.ou.pojo.WritingAnswer;
import com.ou.repositories.WritingAnswerRepository;
import com.ou.services.WritingAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WritingAnswerServiceImpl implements WritingAnswerService {
    @Autowired
    private WritingAnswerRepository writingAnswerRepository;

    @Override
    public List<WritingAnswer> getAllWritingAnswers() {
        return writingAnswerRepository.getAllWritingAnswers();
    }

    @Override
    public WritingAnswer getWritingAnswerById(Integer id) {
        return writingAnswerRepository.getWritingAnswerById(id);
    }

    @Override
    public boolean addWritingAnswer(Question question, String answer) {
        if (answer == null || answer.isEmpty())
            return false;
        WritingAnswer writingAnswer = new WritingAnswer();
        writingAnswer.setContent(answer);
        writingAnswer.setQuestionId(question);
        writingAnswerRepository.addWritingAnswer(writingAnswer);
        if (writingAnswer.getId() != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateWritingAnswer(Integer questionId, Integer answerId, String answer) {
        if (answer == null || answer.isEmpty())
            return false;
        WritingAnswer writingAnswer = writingAnswerRepository.getWritingAnswerById(answerId);
        writingAnswer.setContent(answer);
        return writingAnswerRepository.updateWritingAnswer(writingAnswer);
    }

    @Override
    public boolean deleteWritingAnswer(Integer id) {
        return writingAnswerRepository.deleteWritingAnswer(id);
    }

    @Override
    public WritingAnswer getWritingAnswerByQuestionId(Integer questionId) {
        return writingAnswerRepository.getWritingAnswerByQuestionId(questionId);
    }


}
