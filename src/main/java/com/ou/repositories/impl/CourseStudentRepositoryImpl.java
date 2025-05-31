package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.CourseStudent;
import com.ou.pojo.Student;
import com.ou.pojo.User;
import com.ou.repositories.CourseStudentRepository;
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
public class CourseStudentRepositoryImpl implements CourseStudentRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public CourseStudent addCourseStudent(CourseStudent courseStudent) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(courseStudent);
        session.flush(); // Ensure ID is generated and available
        return courseStudent;
    }

    @Override
    public CourseStudent updateCourseStudent(CourseStudent courseStudent) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(courseStudent);
        return courseStudent;
    }

    @Override
    public boolean deleteCourseStudent(Integer courseStudentId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CourseStudent courseStudent = session.get(CourseStudent.class, courseStudentId);
        if (courseStudent != null) {
            session.delete(courseStudent);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseStudent> getCourseStudentById(Integer courseStudentId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CourseStudent courseStudent = session.get(CourseStudent.class, courseStudentId);
        return Optional.ofNullable(courseStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseStudent> getCourseStudents(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseStudent> query = builder.createQuery(CourseStudent.class);
        Root<CourseStudent> root = query.from(CourseStudent.class);
        query.select(root);
        
        Query<CourseStudent> q = session.createQuery(query);
        
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
    public long countCourseStudents() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseStudent> root = query.from(CourseStudent.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseStudent> searchCourseStudents(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseStudent> query = builder.createQuery(CourseStudent.class);
        Root<CourseStudent> root = query.from(CourseStudent.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<CourseStudent> q = session.createQuery(query);
        
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
        Root<CourseStudent> root = query.from(CourseStudent.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<CourseStudent> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            if (filters.containsKey("name")) {
                Join<CourseStudent, Student> studentJoin = root.join("studentId", JoinType.LEFT);

                Join<Student, User> userJoin =  studentJoin.join("userId", JoinType.LEFT);

                String keyword = "%" + filters.get("name") + "%";
                predicates.add(
                        builder.or(
                                builder.like(userJoin.get("firstName"), keyword),
                                builder.like(userJoin.get("lastName"), keyword)
                        )
                );
            }
            
            if (filters.containsKey("progress")) {
                predicates.add(builder.equal(root.get("progress"), Double.valueOf(filters.get("progress"))));
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
    public List<CourseStudent> getCourseStudentsByCourse(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseStudent> query = builder.createQuery(CourseStudent.class);
        Root<CourseStudent> root = query.from(CourseStudent.class);
        
        query.where(builder.equal(root.get("courseId").get("id"), courseId));
        
        Query<CourseStudent> q = session.createQuery(query);
        
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
    public long countCourseStudentsByCourse(Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseStudent> root = query.from(CourseStudent.class);
        query.where(builder.equal(root.get("courseId").get("id"), courseId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseStudent> getCourseStudentsByStudent(Integer studentId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseStudent> query = builder.createQuery(CourseStudent.class);
        Root<CourseStudent> root = query.from(CourseStudent.class);
        
        query.where(builder.equal(root.get("studentId").get("id"), studentId));
        
        Query<CourseStudent> q = session.createQuery(query);
        
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
    public List<CourseStudent> getCourseStudentsByUserId(Integer userId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseStudent> query = builder.createQuery(CourseStudent.class);
        Root<CourseStudent> root = query.from(CourseStudent.class);

        query.where(builder.equal(root.get("studentId").get("userId").get("id"), userId));

        Query<CourseStudent> q = session.createQuery(query);

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
    public long countCourseStudentsByStudent(Integer studentId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseStudent> root = query.from(CourseStudent.class);
        query.where(builder.equal(root.get("studentId").get("id"), studentId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<CourseStudent> getCourseStudentByCourseAndStudent(Integer courseId, Integer studentId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseStudent> query = builder.createQuery(CourseStudent.class);
        Root<CourseStudent> root = query.from(CourseStudent.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("courseId").get("id"), courseId));
        predicates.add(builder.equal(root.get("studentId").get("id"), studentId));
        
        query.where(builder.and(predicates.toArray(new Predicate[0])));
        
        try {
            return Optional.ofNullable(session.createQuery(query).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<CourseStudent> getCourseStudentByCourseAndUser(Integer courseId, Integer userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseStudent> query = builder.createQuery(CourseStudent.class);
        Root<CourseStudent> root = query.from(CourseStudent.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("courseId").get("id"), courseId));
        predicates.add(builder.equal(root.get("studentId").get("userId").get("id"), userId));

        query.where(builder.and(predicates.toArray(new Predicate[0])));

        try {
            return Optional.ofNullable(session.createQuery(query).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
