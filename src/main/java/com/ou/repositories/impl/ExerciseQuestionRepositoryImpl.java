package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.ExerciseQuestion;
import com.ou.repositories.ExerciseQuestionRepository;
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
public class ExerciseQuestionRepositoryImpl implements ExerciseQuestionRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public ExerciseQuestion addExerciseQuestion(ExerciseQuestion exerciseQuestion) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.save(exerciseQuestion);
        session.flush(); // Ensure ID is generated and available
        return exerciseQuestion;
    }

    @Override
    public ExerciseQuestion updateExerciseQuestion(ExerciseQuestion exerciseQuestion) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.update(exerciseQuestion);
        return exerciseQuestion;
    }

    @Override
    public boolean deleteExerciseQuestion(Integer questionId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        ExerciseQuestion question = session.get(ExerciseQuestion.class, questionId);
        if (question != null) {
            session.delete(question);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExerciseQuestion> getExerciseQuestionById(Integer questionId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        ExerciseQuestion question = session.get(ExerciseQuestion.class, questionId);
        return Optional.ofNullable(question);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseQuestion> getExerciseQuestions(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseQuestion> query = builder.createQuery(ExerciseQuestion.class);
        Root<ExerciseQuestion> root = query.from(ExerciseQuestion.class);
        query.select(root);
        
        Query<ExerciseQuestion> q = session.createQuery(query);
        
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
    public long countExerciseQuestions() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ExerciseQuestion> root = query.from(ExerciseQuestion.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseQuestion> searchExerciseQuestions(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseQuestion> query = builder.createQuery(ExerciseQuestion.class);
        Root<ExerciseQuestion> root = query.from(ExerciseQuestion.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<ExerciseQuestion> q = session.createQuery(query);
        
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
        Root<ExerciseQuestion> root = query.from(ExerciseQuestion.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<ExerciseQuestion> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            // Filter by exercise ID
            if (filters.containsKey("exerciseId")) {
                predicates.add(builder.equal(root.get("exerciseId").get("id"), Integer.valueOf(filters.get("exerciseId"))));
            }
            
            // Add more filters as needed for ExerciseQuestion
        }
        
        return predicates;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseQuestion> getExerciseQuestionsByExerciseId(Integer exerciseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseQuestion> query = builder.createQuery(ExerciseQuestion.class);
        Root<ExerciseQuestion> root = query.from(ExerciseQuestion.class);
        
        query.where(builder.equal(root.get("exerciseId").get("id"), exerciseId));
        
        Query<ExerciseQuestion> q = session.createQuery(query);
        
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
    public long countExerciseQuestionsByExerciseId(Integer exerciseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ExerciseQuestion> root = query.from(ExerciseQuestion.class);
        query.where(builder.equal(root.get("exerciseId").get("id"), exerciseId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
}
