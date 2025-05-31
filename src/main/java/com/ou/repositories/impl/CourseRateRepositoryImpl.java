package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.CourseRate;
import com.ou.repositories.CourseRateRepository;
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
public class CourseRateRepositoryImpl implements CourseRateRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public CourseRate addCourseRate(CourseRate courseRate) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(courseRate);
        session.flush(); // Ensure ID is generated and available
        return courseRate;
    }

    @Override
    public CourseRate updateCourseRate(CourseRate courseRate) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(courseRate);
        return courseRate;
    }

    @Override
    public boolean deleteCourseRate(Integer courseRateId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CourseRate courseRate = session.get(CourseRate.class, courseRateId);
        if (courseRate != null) {
            session.delete(courseRate);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseRate> getCourseRateById(Integer courseRateId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CourseRate courseRate = session.get(CourseRate.class, courseRateId);
        return Optional.ofNullable(courseRate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseRate> getCourseRates(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseRate> query = builder.createQuery(CourseRate.class);
        Root<CourseRate> root = query.from(CourseRate.class);
        query.select(root);
        
        Query<CourseRate> q = session.createQuery(query);
        
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
    public long countCourseRates() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseRate> root = query.from(CourseRate.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseRate> searchCourseRates(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseRate> query = builder.createQuery(CourseRate.class);
        Root<CourseRate> root = query.from(CourseRate.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<CourseRate> q = session.createQuery(query);
        
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
        Root<CourseRate> root = query.from(CourseRate.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<CourseRate> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            if (filters.containsKey("rate")) {
                predicates.add(builder.equal(root.get("rate"), Double.valueOf(filters.get("rate"))));
            }
            
            if (filters.containsKey("minRate")) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("rate"), Double.valueOf(filters.get("minRate"))));
            }
            
            if (filters.containsKey("maxRate")) {
                predicates.add(builder.lessThanOrEqualTo(root.get("rate"), Double.valueOf(filters.get("maxRate"))));
            }
            
            if (filters.containsKey("comment")) {
                predicates.add(builder.like(root.get("comment"), String.format("%%%s%%", filters.get("comment"))));
            }
            
            if (filters.containsKey("courseId")) {
                predicates.add(builder.equal(root.get("courseId").get("id"), Integer.valueOf(filters.get("courseId"))));
            }
            
            if (filters.containsKey("studentId")) {
                predicates.add(builder.equal(root.get("studentId").get("id"), Integer.valueOf(filters.get("studentId"))));
            }
        }
        
        return predicates;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseRate> getCourseRatesByCourse(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseRate> query = builder.createQuery(CourseRate.class);
        Root<CourseRate> root = query.from(CourseRate.class);
        
        query.where(builder.equal(root.get("courseStudentId").get("courseId").get("id"), courseId));
        
        Query<CourseRate> q = session.createQuery(query);
        
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
    public long countCourseRatesByCourse(Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseRate> root = query.from(CourseRate.class);
        query.where(builder.equal(root.get("courseId").get("id"), courseId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseRate> getCourseRatesByStudent(Integer studentId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseRate> query = builder.createQuery(CourseRate.class);
        Root<CourseRate> root = query.from(CourseRate.class);
        
        query.where(builder.equal(root.get("studentId").get("id"), studentId));
        
        Query<CourseRate> q = session.createQuery(query);
        
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
    public double calculateAverageRate(Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Double> query = builder.createQuery(Double.class);
        Root<CourseRate> root = query.from(CourseRate.class);

        query.select(builder.avg(root.get("rate")));
        query.where(builder.equal(root.get("courseStudentId").get("courseId").get("id"), courseId));

        try {
            return session.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return 0.0;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long countCourseRatesByStudent(Integer studentId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseRate> root = query.from(CourseRate.class);
        query.where(builder.equal(root.get("studentId").get("id"), studentId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
}
