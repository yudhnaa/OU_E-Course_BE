package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.ExerciseAttempt;
import com.ou.repositories.ExerciseAttemptRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
public class ExerciseAttemptRepositoryImpl implements ExerciseAttemptRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public ExerciseAttempt addExerciseAttempt(ExerciseAttempt exerciseAttempt) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(exerciseAttempt);
        session.flush(); // Ensure ID is generated and available
        return exerciseAttempt;
    }

    @Override
    public ExerciseAttempt updateExerciseAttempt(ExerciseAttempt exerciseAttempt) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(exerciseAttempt);
        return exerciseAttempt;
    }

    @Override
    public boolean deleteExerciseAttempt(Integer exerciseAttemptId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        ExerciseAttempt exerciseAttempt = session.get(ExerciseAttempt.class, exerciseAttemptId);
        if (exerciseAttempt != null) {
            session.delete(exerciseAttempt);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExerciseAttempt> getExerciseAttemptById(Integer exerciseAttemptId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        ExerciseAttempt exerciseAttempt = session.get(ExerciseAttempt.class, exerciseAttemptId);
        return Optional.ofNullable(exerciseAttempt);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttempt> getExerciseAttempts(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseAttempt> query = builder.createQuery(ExerciseAttempt.class);
        Root<ExerciseAttempt> root = query.from(ExerciseAttempt.class);
        query.select(root);
        
        Query<ExerciseAttempt> q = session.createQuery(query);
        
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
    public long countExerciseAttempts() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ExerciseAttempt> root = query.from(ExerciseAttempt.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttempt> searchExerciseAttempts(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseAttempt> query = builder.createQuery(ExerciseAttempt.class);
        Root<ExerciseAttempt> root = query.from(ExerciseAttempt.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<ExerciseAttempt> q = session.createQuery(query);
        
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
        Root<ExerciseAttempt> root = query.from(ExerciseAttempt.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<ExerciseAttempt> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            if (filters.containsKey("totalScore")) {
                predicates.add(builder.equal(root.get("totalScore"), filters.get("totalScore")));
            }
            
            if (filters.containsKey("exerciseId")) {
                predicates.add(builder.equal(root.get("exerciseId").get("id"), Integer.valueOf(filters.get("exerciseId"))));
            }
            
            if (filters.containsKey("statusId")) {
                predicates.add(builder.equal(root.get("statusId").get("id"), Integer.valueOf(filters.get("statusId"))));
            }
            
            if (filters.containsKey("scoreByUserId")) {
                predicates.add(builder.equal(root.get("scoreByUserId").get("id"), Integer.valueOf(filters.get("scoreByUserId"))));
            }
            
            if (filters.containsKey("startDateFrom")) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("startedAt"), filters.get("startDateFrom")));
            }
            
            if (filters.containsKey("startDateTo")) {
                predicates.add(builder.lessThanOrEqualTo(root.get("startedAt"), filters.get("startDateTo")));
            }
            
            if (filters.containsKey("submittedDateFrom")) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("submittedAt"), filters.get("submittedDateFrom")));
            }
            
            if (filters.containsKey("submittedDateTo")) {
                predicates.add(builder.lessThanOrEqualTo(root.get("submittedAt"), filters.get("submittedDateTo")));
            }
        }
        
        return predicates;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttempt> getExerciseAttemptsByExerciseId(Integer exerciseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseAttempt> query = builder.createQuery(ExerciseAttempt.class);
        Root<ExerciseAttempt> root = query.from(ExerciseAttempt.class);
        
        query.where(builder.equal(root.get("exerciseId").get("id"), exerciseId));
        
        Query<ExerciseAttempt> q = session.createQuery(query);
        
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
    public long countExerciseAttemptsByExerciseId(Integer exerciseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ExerciseAttempt> root = query.from(ExerciseAttempt.class);
        query.where(builder.equal(root.get("exerciseId").get("id"), exerciseId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttempt> getExerciseAttemptsByScoreByUserId(Integer userId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseAttempt> query = builder.createQuery(ExerciseAttempt.class);
        Root<ExerciseAttempt> root = query.from(ExerciseAttempt.class);
        
        query.where(builder.equal(root.get("scoreByUserId").get("id"), userId));
        
        Query<ExerciseAttempt> q = session.createQuery(query);
        
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
    public long countExerciseAttemptsByScoreByUserId(Integer userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ExerciseAttempt> root = query.from(ExerciseAttempt.class);
        query.where(builder.equal(root.get("scoreByUserId").get("id"), userId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttempt> getExerciseAttemptsByStatusId(Integer statusId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseAttempt> query = builder.createQuery(ExerciseAttempt.class);
        Root<ExerciseAttempt> root = query.from(ExerciseAttempt.class);
        
        query.where(builder.equal(root.get("statusId").get("id"), statusId));
        
        Query<ExerciseAttempt> q = session.createQuery(query);
        
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
    public long countExerciseAttemptsByStatusId(Integer statusId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ExerciseAttempt> root = query.from(ExerciseAttempt.class);
        query.where(builder.equal(root.get("statusId").get("id"), statusId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
}
