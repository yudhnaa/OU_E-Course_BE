package com.ou.repositories.impl;


import com.ou.pojo.MultipleChoiceAnswer;
import com.ou.repositories.MultipleChoiceAnswerRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MultipleChoiceAnswerRepositoryImpl implements MultipleChoiceAnswerRepository {
    @Autowired
    private LocalSessionFactoryBean factory;


    @Override
    public List<MultipleChoiceAnswer> getAllMultipleChoiceAnswers() {
        return List.of();
    }

    @Override
    public MultipleChoiceAnswer getMultipleChoiceAnswerById(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        return session.get(MultipleChoiceAnswer.class, id);
    }

    @Override
    public MultipleChoiceAnswer addMultipleChoiceAnswer(MultipleChoiceAnswer answer) {
        Session session = factory.getObject().getCurrentSession();
        session.persist(answer);
        return answer;
    }

    @Override
    public MultipleChoiceAnswer updateMultipleChoiceAnswer(MultipleChoiceAnswer answer) {
        Session session = factory.getObject().getCurrentSession();
        session.merge(answer);
        return answer;
    }

    @Override
    public boolean deleteMultipleChoiceAnswer(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        MultipleChoiceAnswer answer = session.get(MultipleChoiceAnswer.class, id);
        if (answer != null) {
            session.delete(answer);
            return true;
        }
        return false;
    }

    @Override
    public List<MultipleChoiceAnswer> getAnswersByQuestionId(Integer questionId) {
        Session session = factory.getObject().getCurrentSession();
        return session.createQuery(
                        "FROM MultipleChoiceAnswer a WHERE a.questionId.id = :questionId",
                        MultipleChoiceAnswer.class)
                .setParameter("questionId", questionId)
                .getResultList();
    }

    @Override
    public MultipleChoiceAnswer getCorrectAnswer(Integer questionId) {
        Session session = factory.getObject().getCurrentSession();
        try {
            return session.createQuery(
                            "FROM MultipleChoiceAnswer a WHERE a.questionId.id = :questionId AND a.isCorrect = true",
                            MultipleChoiceAnswer.class)
                    .setParameter("questionId", questionId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
