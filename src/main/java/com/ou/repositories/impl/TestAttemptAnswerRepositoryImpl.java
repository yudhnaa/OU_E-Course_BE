package com.ou.repositories.impl;

import com.ou.pojo.TestAttemptAnswer;
import com.ou.repositories.TestAttemptAnswerRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class TestAttemptAnswerRepositoryImpl implements TestAttemptAnswerRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<TestAttemptAnswer> getTestAttemptAnswersByTestAttemptId(Integer testAttemptId, Map<String, String> params) {
        if (testAttemptId == null || testAttemptId <= 0) {
            throw new IllegalArgumentException("Invalid test attempt ID");
        }

        Session session = factory.getObject().getCurrentSession();
        return session.createQuery("FROM TestAttemptAnswer t WHERE t.attemptId.id = :testAttemptId", TestAttemptAnswer.class)
                .setParameter("testAttemptId", testAttemptId)
                .getResultList();
    }


    @Override
    public TestAttemptAnswer updateTestAttemptAnswer(TestAttemptAnswer testAttemptAnswer) {
        if (testAttemptAnswer == null || testAttemptAnswer.getId() == null || testAttemptAnswer.getId() <= 0) {
            throw new IllegalArgumentException("Invalid test attempt answer");
        }

        Session session = factory.getObject().getCurrentSession();
        session.merge(testAttemptAnswer);
        return testAttemptAnswer;
    }


}
