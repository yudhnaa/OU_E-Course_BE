package com.ou.repositories.impl;


import com.ou.pojo.QuestionType;
import com.ou.repositories.QuestionTypeRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class QuestionTypeRepositoryImpl implements QuestionTypeRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<QuestionType> getAllQuestionTypes() {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        var criteriaQuery = builder.createQuery(QuestionType.class);
        var root = criteriaQuery.from(QuestionType.class);
        criteriaQuery.select(root);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public QuestionType getQuestionTypeById(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        return session.get(QuestionType.class, id);
    }

    @Override
    public QuestionType createQuestionType(String name, String description) {
        return null;
    }

    @Override
    public QuestionType updateQuestionType(Integer id, String name, String description) {
        return null;
    }

    @Override
    public boolean deleteQuestionType(Integer id) {
        return false;
    }
}
