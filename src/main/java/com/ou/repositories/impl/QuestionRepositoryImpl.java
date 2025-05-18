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
        return null;
    }

    @Override
    public Question addQuestion(Question question) {
        return null;
    }

    @Override
    public Question updateQuestion(Question question) {
        return null;
    }

    @Override
    public boolean deleteQuestion(Integer id) {
        return false;
    }

    @Override
    public List<Question> getQuestionsByExercise(Integer exerciseId) {
        return List.of();
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
