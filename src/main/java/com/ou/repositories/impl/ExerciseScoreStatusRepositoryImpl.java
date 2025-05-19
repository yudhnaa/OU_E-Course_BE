package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.ExerciseScoreStatus;
import com.ou.repositories.ExerciseScoreStatusRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class ExerciseScoreStatusRepositoryImpl implements ExerciseScoreStatusRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public ExerciseScoreStatus addExerciseScoreStatus(ExerciseScoreStatus status) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(status);
        session.flush(); // Ensure ID is generated and available
        return status;
    }

    @Override
    public ExerciseScoreStatus updateExerciseScoreStatus(ExerciseScoreStatus status) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(status);
        return status;
    }

    @Override
    public boolean deleteExerciseScoreStatus(Integer statusId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        ExerciseScoreStatus status = session.get(ExerciseScoreStatus.class, statusId);
        if (status != null) {
            session.delete(status);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExerciseScoreStatus> getExerciseScoreStatusById(Integer statusId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        ExerciseScoreStatus status = session.get(ExerciseScoreStatus.class, statusId);
        return Optional.ofNullable(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseScoreStatus> getExerciseScoreStatuses(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseScoreStatus> query = builder.createQuery(ExerciseScoreStatus.class);
        Root<ExerciseScoreStatus> root = query.from(ExerciseScoreStatus.class);
        query.select(root);
        
        Query<ExerciseScoreStatus> q = session.createQuery(query);
        
        // Process pagination parameters
        if (params != null) {
            int page = Integer.parseInt(params.getOrDefault("page", "1"));
            int pageSize = Integer.parseInt(params.getOrDefault("pageSize", String.valueOf(PAGE_SIZE)));
            int start = (page - 1) * pageSize;
            q.setMaxResults(pageSize);
            q.setFirstResult(start);
        }
        
        return q.getResultList();
    }
    
    @Override
    @Transactional(readOnly = true)
    public long countExerciseScoreStatuses() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ExerciseScoreStatus> root = query.from(ExerciseScoreStatus.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseScoreStatus> searchExerciseScoreStatuses(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseScoreStatus> query = builder.createQuery(ExerciseScoreStatus.class);
        Root<ExerciseScoreStatus> root = query.from(ExerciseScoreStatus.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<ExerciseScoreStatus> q = session.createQuery(query);
        
        // Process pagination parameters
        if (params != null) {
            int page = Integer.parseInt(params.getOrDefault("page", "1"));
            int pageSize = Integer.parseInt(params.getOrDefault("pageSize", String.valueOf(PAGE_SIZE)));
            int start = (page - 1) * pageSize;
            q.setMaxResults(pageSize);
            q.setFirstResult(start);
        }
        
        return q.getResultList();
    }
    
    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ExerciseScoreStatus> root = query.from(ExerciseScoreStatus.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<ExerciseScoreStatus> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            if (filters.containsKey("name")) {
                predicates.add(builder.like(root.get("name"), String.format("%%%s%%", filters.get("name"))));
            }
            
            if (filters.containsKey("description")) {
                predicates.add(builder.like(root.get("description"), String.format("%%%s%%", filters.get("description"))));
            }
        }
        
        return predicates;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExerciseScoreStatus> getExerciseScoreStatusByName(String name) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            Query<ExerciseScoreStatus> query = session.createNamedQuery("ExerciseScoreStatus.findByName", ExerciseScoreStatus.class);
            query.setParameter("name", name);
            return Optional.ofNullable(query.uniqueResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
