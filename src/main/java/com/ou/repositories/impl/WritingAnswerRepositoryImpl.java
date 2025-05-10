package com.ou.repositories.impl;


import com.ou.pojo.WritingAnswer;
import com.ou.repositories.WritingAnswerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class WritingAnswerRepositoryImpl implements WritingAnswerRepository {
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
