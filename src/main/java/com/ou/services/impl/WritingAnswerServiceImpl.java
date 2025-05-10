package com.ou.services.impl;


import com.ou.pojo.WritingAnswer;
import com.ou.services.WritingAnswerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WritingAnswerServiceImpl implements WritingAnswerService {
    @Override
    public List<WritingAnswer> getAllWritingAnswers() {
        return List.of();
    }

    @Override
    public WritingAnswer getWritingAnswerById(Integer id) {
        return null;
    }

    @Override
    public WritingAnswer addWritingAnswer(WritingAnswer answer) {
        return null;
    }

    @Override
    public WritingAnswer updateWritingAnswer(WritingAnswer answer) {
        return null;
    }

    @Override
    public boolean deleteWritingAnswer(Integer id) {
        return false;
    }
}
