package com.ou.services.impl;

import com.ou.pojo.Question;
import com.ou.repositories.QuestionRepository;
import com.ou.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions() {
        return List.of();
    }

    @Override
    public Question getQuestionById(Integer id) {
        return questionRepository.getQuestionById(id);
    }

    @Override
    public Question addQuestion(Question question) {
        if (question == null)
            return null;
        questionRepository.addQuestion(question);
        if (question.getId() == null) {
            return null;
        }
        return question;
    }

    @Override
    public Question updateQuestion(Question question) {
        return null;
    }

    @Override
    public boolean deleteQuestion(Integer id) {
        return questionRepository.deleteQuestion(id);
    }

    @Override
    public List<Question> getQuestionsByExercise(Integer exerciseId) {
        return questionRepository.getQuestionsByExercise(exerciseId);
    }

    @Override
    public List<Question> getQuestionsByType(Integer questionTypeId) {
        return List.of();
    }

    @Override
    public List<Question> getQuestionsByTest(Integer testId) {
        return questionRepository.getQuestionsByTest(testId);
    }

    @Override
    public List<Question> getQuestionsByCourse(Integer courseId) {
        return questionRepository.getQuestionsByCourse(courseId);
    }

}
