package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.CourseLecturer;
import com.ou.pojo.Lecturer;
import com.ou.repositories.CourseLecturerRepository;
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
public class CourseLecturerRepositoryImpl implements CourseLecturerRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public CourseLecturer addCourseLecturer(CourseLecturer courseLecturer) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(courseLecturer);
        session.flush(); // Ensure ID is generated and available
        return courseLecturer;
    }

    @Override
    public CourseLecturer updateCourseLecturer(CourseLecturer courseLecturer) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(courseLecturer);
        return courseLecturer;
    }

    @Override
    public boolean deleteCourseLecturer(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CourseLecturer courseLecturer = session.get(CourseLecturer.class, id);
        if (courseLecturer != null) {
            session.delete(courseLecturer);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseLecturer> getCourseLecturerById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CourseLecturer courseLecturer = session.get(CourseLecturer.class, id);
        return Optional.ofNullable(courseLecturer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseLecturer> getCourseLecturers(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseLecturer> query = builder.createQuery(CourseLecturer.class);
        Root<CourseLecturer> root = query.from(CourseLecturer.class);
        query.select(root);
        
        Query<CourseLecturer> q = session.createQuery(query);
        
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
    public List<Lecturer> getLecturersByCourseId(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lecturer> query = builder.createQuery(Lecturer.class);
        Root<CourseLecturer> root = query.from(CourseLecturer.class);

        Join<CourseLecturer, Lecturer> lecturerJoin = root.join("lecturerId", JoinType.INNER);
        query.select(lecturerJoin)
             .where(builder.equal(root.get("courseId").get("id"), courseId));

        Query<Lecturer> q = session.createQuery(query);

//        // Process pagination parameters
//        if (params != null) {
//            int page = Integer.parseInt(params.getOrDefault("page", "1"));
//            int pageSize = Integer.parseInt(params.getOrDefault("pageSize", String.valueOf(PAGE_SIZE)));
//            int start = (page - 1) * pageSize;
//            q.setMaxResults(pageSize);
//            q.setFirstResult(start);
//        }
        return q.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourseLecturers() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseLecturer> root = query.from(CourseLecturer.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseLecturer> searchCourseLecturers(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseLecturer> query = builder.createQuery(CourseLecturer.class);
        Root<CourseLecturer> root = query.from(CourseLecturer.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<CourseLecturer> q = session.createQuery(query);
        
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
        Root<CourseLecturer> root = query.from(CourseLecturer.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    public boolean existsByCourseIdAndLecturerId(int courseId, int lecturerId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseLecturer> query = builder.createQuery(CourseLecturer.class);
        Root<CourseLecturer> root = query.from(CourseLecturer.class);

        query.where(
            builder.equal(root.get("courseId").get("id"), courseId),
            builder.equal(root.get("lecturerId").get("id"), lecturerId)
        );

        List<CourseLecturer> results = session.createQuery(query).getResultList();

        return !results.isEmpty();
    }

    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<CourseLecturer> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            if (filters.containsKey("courseId")) {
                predicates.add(builder.equal(root.get("courseId").get("id"), Integer.valueOf(filters.get("courseId"))));
            }
            
            if (filters.containsKey("lecturerId")) {
                predicates.add(builder.equal(root.get("lecturerId").get("id"), Integer.valueOf(filters.get("lecturerId"))));
            }
        }
        
        return predicates;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseLecturer> getCourseLecturersByCourse(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseLecturer> query = builder.createQuery(CourseLecturer.class);
        Root<CourseLecturer> root = query.from(CourseLecturer.class);
        
        query.where(builder.equal(root.get("courseId").get("id"), courseId));
        
        Query<CourseLecturer> q = session.createQuery(query);
        
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
    public long countCourseLecturersByCourse(Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseLecturer> root = query.from(CourseLecturer.class);
        query.where(builder.equal(root.get("courseId").get("id"), courseId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseLecturer> getCourseLecturersByLecturer(Integer lecturerId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseLecturer> query = builder.createQuery(CourseLecturer.class);
        Root<CourseLecturer> root = query.from(CourseLecturer.class);
        
        query.where(builder.equal(root.get("lecturerId").get("id"), lecturerId));
        
        Query<CourseLecturer> q = session.createQuery(query);
        
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
    public long countCourseLecturersByLecturer(Integer lecturerId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseLecturer> root = query.from(CourseLecturer.class);
        query.where(builder.equal(root.get("lecturerId").get("id"), lecturerId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
}
