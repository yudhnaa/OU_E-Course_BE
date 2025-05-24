package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.Lesson;
import com.ou.repositories.LessonRepository;
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
public class LessonRepositoryImpl implements LessonRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Lesson addLesson(Lesson lesson) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(lesson);
        session.flush(); // Ensure ID is generated and available
        return lesson;
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(lesson);
        return lesson;
    }

    @Override
    public boolean deleteLesson(Integer lessonId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Lesson lesson = session.get(Lesson.class, lessonId);
        if (lesson != null) {
            session.delete(lesson);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Lesson> getLessonById(Integer lessonId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Lesson lesson = session.get(Lesson.class, lessonId);
        return Optional.ofNullable(lesson);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessons(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
        Root<Lesson> root = query.from(Lesson.class);
        query.select(root);
        
        Query<Lesson> q = session.createQuery(query);
        
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
    public long countLessons(String locale) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Lesson> root = query.from(Lesson.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsByName(String name, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
        Root<Lesson> root = query.from(Lesson.class);
        
        query.where(builder.like(root.get("name"), String.format("%%%s%%", name)));
        
        Query<Lesson> q = session.createQuery(query);
        
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
    public List<Lesson> getLessonsByCourse(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
        Root<Lesson> root = query.from(Lesson.class);

        // Build predicates
        List<Predicate> predicates = buildSearchPredicates(builder, root, params);

        predicates.add(builder.equal(root.get("courseId").get("id"), courseId));

        query.where(builder.and(predicates.toArray(new Predicate[0])));
        
        Query<Lesson> q = session.createQuery(query);
        
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
    public long countLessonsByCourse(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Lesson> root = query.from(Lesson.class);

        // Build predicates
        List<Predicate> predicates = buildSearchPredicates(builder, root, params);

        predicates.add(builder.equal(root.get("courseId").get("id"), courseId));

        query.where(builder.and(predicates.toArray(new Predicate[0])));
        query.select(builder.count(root));

        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsByType(Integer typeId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
        Root<Lesson> root = query.from(Lesson.class);
        
        query.where(builder.equal(root.get("lessonTypeId").get("id"), typeId));
        
        Query<Lesson> q = session.createQuery(query);
        
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
    public long countLessonsByType(Integer typeId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Lesson> root = query.from(Lesson.class);
        query.where(builder.equal(root.get("lessonTypeId").get("id"), typeId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsByUploadUser(Integer userId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
        Root<Lesson> root = query.from(Lesson.class);
        
        query.where(builder.equal(root.get("userUploadId").get("id"), userId));
        
        Query<Lesson> q = session.createQuery(query);
        
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
    public long countLessonsByUploadUser(Integer userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Lesson> root = query.from(Lesson.class);
        query.where(builder.equal(root.get("userUploadId").get("id"), userId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> searchLessons(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
        Root<Lesson> root = query.from(Lesson.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<Lesson> q = session.createQuery(query);
        
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
        Root<Lesson> root = query.from(Lesson.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<Lesson> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            if (filters.containsKey("name")) {
                predicates.add(builder.like(root.get("name"), String.format("%%%s%%", filters.get("name"))));
            }

            if (filters.containsKey("description")) {
                predicates.add(builder.like(root.get("description"), String.format("%%%s%%", filters.get("description"))));
            }

            if (filters.containsKey("embedLink")) {
                predicates.add(builder.like(root.get("embedLink"), String.format("%%%s%%", filters.get("embedLink"))));
            }

            if (filters.containsKey("courseId")) {
                predicates.add(builder.equal(root.get("courseId").get("id"), Integer.valueOf(filters.get("courseId"))));
            }

            if (filters.containsKey("lessonTypeId")) {
                predicates.add(builder.equal(root.get("lessonTypeId").get("id"), Integer.valueOf(filters.get("lessonTypeId"))));
            }

            if (filters.containsKey("userUploadId")) {
                predicates.add(builder.equal(root.get("userUploadId").get("id"), Integer.valueOf(filters.get("userUploadId"))));
            }
        }

        return predicates;
    }
}
