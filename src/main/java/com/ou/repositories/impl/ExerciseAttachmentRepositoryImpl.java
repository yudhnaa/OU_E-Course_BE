package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.ExerciseAttachment;
import com.ou.repositories.ExerciseAttachmentRepository;
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
public class ExerciseAttachmentRepositoryImpl implements ExerciseAttachmentRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public ExerciseAttachment addExerciseAttachment(ExerciseAttachment exerciseAttachment) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(exerciseAttachment);
        session.flush(); // Ensure ID is generated and available
        return exerciseAttachment;
    }

    @Override
    public ExerciseAttachment updateExerciseAttachment(ExerciseAttachment exerciseAttachment) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(exerciseAttachment);
        return exerciseAttachment;
    }

    @Override
    public boolean deleteExerciseAttachment(Integer exerciseAttachmentId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        ExerciseAttachment exerciseAttachment = session.get(ExerciseAttachment.class, exerciseAttachmentId);
        if (exerciseAttachment != null) {
            session.delete(exerciseAttachment);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExerciseAttachment> getExerciseAttachmentById(Integer exerciseAttachmentId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        ExerciseAttachment exerciseAttachment = session.get(ExerciseAttachment.class, exerciseAttachmentId);
        return Optional.ofNullable(exerciseAttachment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttachment> getExerciseAttachments(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseAttachment> query = builder.createQuery(ExerciseAttachment.class);
        Root<ExerciseAttachment> root = query.from(ExerciseAttachment.class);
        query.select(root);
        
        Query<ExerciseAttachment> q = session.createQuery(query);
        
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
    public long countExerciseAttachments() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ExerciseAttachment> root = query.from(ExerciseAttachment.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttachment> searchExerciseAttachments(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseAttachment> query = builder.createQuery(ExerciseAttachment.class);
        Root<ExerciseAttachment> root = query.from(ExerciseAttachment.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<ExerciseAttachment> q = session.createQuery(query);
        
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
        Root<ExerciseAttachment> root = query.from(ExerciseAttachment.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<ExerciseAttachment> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            if (filters.containsKey("exerciseId")) {
                predicates.add(builder.equal(root.get("exerciseId").get("id"), Integer.valueOf(filters.get("exerciseId"))));
            }
            
            if (filters.containsKey("attachmentId")) {
                predicates.add(builder.equal(root.get("attachmentId").get("id"), Integer.valueOf(filters.get("attachmentId"))));
            }
        }
        
        return predicates;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttachment> getExerciseAttachmentsByExercise(Integer exerciseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseAttachment> query = builder.createQuery(ExerciseAttachment.class);
        Root<ExerciseAttachment> root = query.from(ExerciseAttachment.class);
        
        query.where(builder.equal(root.get("exerciseId").get("id"), exerciseId));
        
        Query<ExerciseAttachment> q = session.createQuery(query);
        
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
    public long countExerciseAttachmentsByExercise(Integer exerciseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ExerciseAttachment> root = query.from(ExerciseAttachment.class);
        query.where(builder.equal(root.get("exerciseId").get("id"), exerciseId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttachment> getExerciseAttachmentsByAttachment(Integer attachmentId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ExerciseAttachment> query = builder.createQuery(ExerciseAttachment.class);
        Root<ExerciseAttachment> root = query.from(ExerciseAttachment.class);
        
        query.where(builder.equal(root.get("attachmentId").get("id"), attachmentId));
        
        Query<ExerciseAttachment> q = session.createQuery(query);
        
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
    public long countExerciseAttachmentsByAttachment(Integer attachmentId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ExerciseAttachment> root = query.from(ExerciseAttachment.class);
        query.where(builder.equal(root.get("attachmentId").get("id"), attachmentId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    public long countExerciseAttachmentsByLesson(Integer lessonId) {
        Session session = sessionFactory.getObject().getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);

        Root<ExerciseAttachment> root = query.from(ExerciseAttachment.class);

        query.where(builder.equal(root.get("exerciseId").get("lessonId").get("id"), lessonId));
        query.select(builder.count(root));

        return session.createQuery(query).getSingleResult();
    }
}
