package com.ou.repositories.impl;

import com.ou.pojo.LessonType;
import com.ou.repositories.LessonTypeRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class LessonTypeRepositoryImpl implements LessonTypeRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public LessonType createLessonType(LessonType lessonType) {
        Session session = factory.getObject().getCurrentSession();
        session.save(lessonType);
        session.flush(); // Ensure ID is generated and available
        return lessonType;
    }

    @Override
    @Transactional(readOnly = true)
    public LessonType getLessonTypeById(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        return session.get(LessonType.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonType> getAllLessonTypes() {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LessonType> query = builder.createQuery(LessonType.class);
        Root<LessonType> root = query.from(LessonType.class);
        query.select(root);

        Query<LessonType> q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LessonType> getLessonTypeByName(String name) {
        Session session = factory.getObject().getCurrentSession();
        try {
            Query<LessonType> query = session.createNamedQuery("LessonType.findByName", LessonType.class);
            query.setParameter("name", name);
            return Optional.ofNullable(query.uniqueResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public LessonType updateLessonType(LessonType lessonType) {
        Session session = factory.getObject().getCurrentSession();
        session.update(lessonType);
        return lessonType;
    }

    @Override
    public boolean deleteLessonType(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        LessonType lessonType = session.get(LessonType.class, id);
        if (lessonType != null) {
            session.delete(lessonType);
            return true;
        }
        return false;
    }
}
