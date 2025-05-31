package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.Exercise;
import com.ou.repositories.ExerciseRepository;
import jakarta.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ExerciseRepositoryImpl implements ExerciseRepository {
    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Exercise createExercise(Exercise exercise) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(exercise);
        session.flush(); // Ensure ID is generated and available
        return exercise;
    }

    @Override
    public Exercise updateExercise(Exercise exercise) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(exercise);
        return exercise;
    }

    @Override
    public boolean deleteExercise(Integer exerciseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Exercise exercise = session.get(Exercise.class, exerciseId);
        if (exercise != null) {
            session.delete(exercise);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Exercise> getExerciseById(Integer exerciseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Exercise exercise = session.get(Exercise.class, exerciseId);
        return Optional.ofNullable(exercise);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getExercises(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Exercise> query = builder.createQuery(Exercise.class);
        Root<Exercise> root = query.from(Exercise.class);
        query.select(root);
        
        List<Predicate> predicates = buildSearchPredicates(builder,root,params);
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        Query<Exercise> q = session.createQuery(query);

        // Process pagination parameters
        if (params != null && params.containsKey("page")) {
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
    public long countExercises() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Exercise> root = query.from(Exercise.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> searchExercises(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Exercise> query = builder.createQuery(Exercise.class);
        Root<Exercise> root = query.from(Exercise.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<Exercise> q = session.createQuery(query);
        
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
        Root<Exercise> root = query.from(Exercise.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    public boolean isCreatorOfExercise(Integer lecturerId, Integer exerciseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Exercise> query = builder.createQuery(Exercise.class);
            Root<Exercise> root = query.from(Exercise.class);
            query.where(builder.and(
                builder.equal(root.get("id"), exerciseId),
                builder.equal(root.get("createdByUserId").get("id"), lecturerId)
            ));
            Exercise exercise = session.createQuery(query).getSingleResult();
            return exercise != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<Exercise> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            if (filters.containsKey("name")) {
                predicates.add(builder.like(root.get("name"), String.format("%%%s%%", filters.get("name"))));
            }
            
            if (filters.containsKey("durationMinutes")) {
                predicates.add(builder.equal(root.get("durationMinutes"), Integer.valueOf(filters.get("durationMinutes"))));
            }
            
            if (filters.containsKey("courseId")) {
                predicates.add(builder.equal(root.get("courseId").get("id"), Integer.valueOf(filters.get("courseId"))));
            }

            if (filters.containsKey("lessonId")) {
                predicates.add(builder.equal(root.get("lessonId").get("id"), Integer.valueOf(filters.get("lessonId"))));
            }
            
            if (filters.containsKey("createdByUserId")) {
                predicates.add(builder.equal(root.get("createdByUserId").get("id"), Integer.valueOf(filters.get("createdByUserId"))));
            }
        }
        
        return predicates;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getExercisesByCourse(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Exercise> query = builder.createQuery(Exercise.class);
        Root<Exercise> root = query.from(Exercise.class);
        
        query.where(builder.equal(root.get("courseId").get("id"), courseId));
        
        Query<Exercise> q = session.createQuery(query);
        
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
    public long countExercisesByCourse(Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Exercise> root = query.from(Exercise.class);
        query.where(builder.equal(root.get("courseId").get("id"), courseId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getExercisesByLesson(Integer lessonId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Exercise> query = builder.createQuery(Exercise.class);
        Root<Exercise> root = query.from(Exercise.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("lessonId").get("id"), lessonId));

        // Thêm các điều kiện tìm kiếm khác
        predicates.addAll(buildSearchPredicates(builder, root, params));

        // Gộp tất cả vào where
        query.where(builder.and(predicates.toArray(new Predicate[0])));

        Query<Exercise> q = session.createQuery(query);

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
    public long countExercisesByLesson(Integer lessonId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Exercise> root = query.from(Exercise.class);
        query.where(builder.equal(root.get("lessonId").get("id"), lessonId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> getExercisesByCreator(Integer userId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Exercise> query = builder.createQuery(Exercise.class);
        Root<Exercise> root = query.from(Exercise.class);
        
        query.where(builder.equal(root.get("createdByUserId").get("id"), userId));
        
        Query<Exercise> q = session.createQuery(query);
        
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
    public long countExercisesByCreator(Integer userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Exercise> root = query.from(Exercise.class);
        query.where(builder.equal(root.get("createdByUserId").get("id"), userId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

}
