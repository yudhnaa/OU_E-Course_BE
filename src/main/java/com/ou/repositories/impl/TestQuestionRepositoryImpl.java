package com.ou.repositories.impl;

import com.ou.pojo.Question;
import com.ou.pojo.Test;
import com.ou.pojo.TestQuestion;
import com.ou.repositories.TestQuestionRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TestQuestionRepositoryImpl implements TestQuestionRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addQuestionToTest(Integer testId, Integer questionId) {
        Session session = factory.getObject().getCurrentSession();
        // Lấy đối tượng Test và Question từ database
        Test test = session.get(Test.class, testId);
        Question question = session.get(Question.class, questionId);

        if (test == null || question == null) {
            return false;
        }

        // Tạo mới TestQuestion với các đối tượng liên quan
        TestQuestion testQuestion = new TestQuestion();
        testQuestion.setTestId(test);
        testQuestion.setQuestionId(question);

        // Lưu vào database
        session.persist(testQuestion);
        return true;
    }

    @Override
    public boolean removeQuestionFromTest(Integer testId, Integer questionId) {
        Session session = factory.getObject().getCurrentSession();
        String hql = "DELETE FROM TestQuestion tq WHERE tq.testId.id = :testId AND tq.questionId.id = :questionId";
        Query query = session.createQuery(hql);
        query.setParameter("testId", testId);
        query.setParameter("questionId", questionId);
        int result = query.executeUpdate();
        return result > 0;
    }


}
