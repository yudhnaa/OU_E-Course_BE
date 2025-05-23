package com.ou.repositories.impl;


import com.ou.pojo.Question;
import com.ou.pojo.WritingAnswer;
import com.ou.repositories.WritingAnswerRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class WritingAnswerRepositoryImpl implements WritingAnswerRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<WritingAnswer> getAllWritingAnswers() {
        return List.of();
    }

    @Override
    public WritingAnswer getWritingAnswerById(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        return session.get(WritingAnswer.class, id);
    }

    @Override
    public WritingAnswer addWritingAnswer(WritingAnswer answer) {
        Session session = factory.getObject().getCurrentSession();
        session.persist(answer);
        return answer;
    }

    @Override
    public boolean updateWritingAnswer(WritingAnswer answer) {
        Session session = factory.getObject().getCurrentSession();
        if (answer != null) {
            session.merge(answer);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteWritingAnswer(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        WritingAnswer answer = session.get(WritingAnswer.class, id);
        if (answer != null) {
            session.delete(answer);
            return true;
        }
        return false;
    }

    @Override
    public WritingAnswer getWritingAnswerByQuestionId(Integer questionId) {
        Session session = factory.getObject().getCurrentSession();

        // First get the Question object
        Question question = session.get(Question.class, questionId);
        if (question == null) {
            return null;
        }

        return session.createQuery("FROM WritingAnswer WHERE questionId = :question", WritingAnswer.class)
                .setParameter("question", question)
                .uniqueResult();
    }

}
