package com.ou.repositories.impl;

import com.ou.pojo.Question;
import com.ou.pojo.TestQuestion;
import com.ou.repositories.QuestionRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class QuestionRepositoryImpl implements QuestionRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Question> getAllQuestions() {
        return List.of();
    }

    @Override
    public Question getQuestionById(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        return session.get(Question.class, id);
    }

    @Override
    public Question addQuestion(Question question) {
        Session session = factory.getObject().getCurrentSession();
        session.persist(question);
        return question;
    }

    @Override
    public Question updateQuestion(Question question) {
        Session session = factory.getObject().getCurrentSession();
        session.merge(question);
        return question;
    }

    @Override
    public boolean deleteQuestion(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        Question question = session.get(Question.class, id);
        if (question != null) {
            session.delete(question);
            return true;
        }
        return false;
    }

    @Override
    public List<Question> getQuestionsByExercise(Integer exerciseId) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);
        query.select(root)
                .where(builder.equal(root.get("exerciseId").get("id"), exerciseId));

        return session.createQuery(query).getResultList();
    }

    @Override
    public List<Question> getQuestionsByType(Integer questionTypeId) {
        return List.of();
    }

    @Override
    public List<Question> getQuestionsByTest(Integer testId) {
        Session session = factory.getObject().getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        Root<TestQuestion> root = query.from(TestQuestion.class);

        // Join tới entity Question
        Join<TestQuestion, Question> questionJoin = root.join("questionId");

        // Thêm điều kiện testId
        query.select(questionJoin)
                .where(builder.equal(root.get("testId").get("id"), testId))
                .distinct(true);

        return session.createQuery(query).getResultList();
    }


}
