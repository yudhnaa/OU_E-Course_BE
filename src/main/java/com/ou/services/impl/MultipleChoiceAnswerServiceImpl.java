package com.ou.services.impl;


import com.ou.pojo.MultipleChoiceAnswer;
import com.ou.pojo.Question;
import com.ou.repositories.MultipleChoiceAnswerRepository;
import com.ou.services.MultipleChoiceAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MultipleChoiceAnswerServiceImpl implements MultipleChoiceAnswerService {
    @Autowired
    private MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;


    @Override
    public List<MultipleChoiceAnswer> getAllMultipleChoiceAnswers() {
        return multipleChoiceAnswerRepository.getAllMultipleChoiceAnswers();
    }

    @Override
    public MultipleChoiceAnswer getMultipleChoiceAnswerById(Integer id) {
        return multipleChoiceAnswerRepository.getMultipleChoiceAnswerById(id);
    }

    @Override
    public boolean addMultipleChoiceAnswer(Question question,List<String> options, Integer correctAnswer) {
        if (options == null || options.size() == 0)
            return false;
        for(int i = 0; i < options.size(); i++) {
            String content = options.get(i).toString();
            boolean isCorrect = (i == correctAnswer);

            MultipleChoiceAnswer answer = new MultipleChoiceAnswer();
            answer.setContent(content);
            answer.setIsCorrect(isCorrect);
            answer.setQuestionId(question);
            multipleChoiceAnswerRepository.addMultipleChoiceAnswer(answer);
            if (answer.getId() == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateMultipleChoiceAnswer(Integer questionId, List<Integer> answers, List<String> options,Integer correctAnswer) {
        if (answers == null || options == null || answers.size() != options.size())
            return false;
        for(int i = 0; i < answers.size(); i++) {
            Integer answerId = answers.get(i);
            String content = options.get(i).toString();
            boolean isCorrect = (i == correctAnswer);

            MultipleChoiceAnswer answer = getMultipleChoiceAnswerById(answerId);
            if (answer != null) {
                answer.setContent(content);
                answer.setIsCorrect(isCorrect);
                answer.setQuestionId(new Question(questionId));
                multipleChoiceAnswerRepository.updateMultipleChoiceAnswer(answer);
            } else {
                MultipleChoiceAnswer newAnswer = new MultipleChoiceAnswer();
                newAnswer.setContent(content);
                newAnswer.setIsCorrect(isCorrect);
                newAnswer.setQuestionId(new Question(questionId));
                multipleChoiceAnswerRepository.addMultipleChoiceAnswer(newAnswer);
            }
        }
        return true;
    }

    @Override
    public boolean deleteMultipleChoiceAnswer(Integer id) {
        return multipleChoiceAnswerRepository.deleteMultipleChoiceAnswer(id);
    }

    @Override
    public List<MultipleChoiceAnswer> getAnswersByQuestionId(Integer questionId) {
        return multipleChoiceAnswerRepository.getAnswersByQuestionId(questionId);
    }

    @Override
    public MultipleChoiceAnswer getCorrectAnswer(Integer questionId) {
        return multipleChoiceAnswerRepository.getCorrectAnswer(questionId);
    }
}
