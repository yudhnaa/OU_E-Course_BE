package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.CourseCertificate;
import com.ou.repositories.CourseCertificateRepository;
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
public class CourseCertificateRepositoryImpl implements CourseCertificateRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public CourseCertificate addCourseCertificate(CourseCertificate certificate) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.save(certificate);
        session.flush(); // Ensure ID is generated and available
        return certificate;
    }

    @Override
    public CourseCertificate updateCourseCertificate(CourseCertificate certificate) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.update(certificate);
        return certificate;
    }

    @Override
    public boolean deleteCourseCertificate(Integer certificateId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CourseCertificate certificate = session.get(CourseCertificate.class, certificateId);
        if (certificate != null) {
            session.delete(certificate);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourseCertificate> getCourseCertificateById(Integer certificateId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CourseCertificate certificate = session.get(CourseCertificate.class, certificateId);
        return Optional.ofNullable(certificate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseCertificate> getCourseCertificates(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseCertificate> query = builder.createQuery(CourseCertificate.class);
        Root<CourseCertificate> root = query.from(CourseCertificate.class);
        query.select(root);
        
        Query<CourseCertificate> q = session.createQuery(query);
        
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
    public List<CourseCertificate> searchCourseCertificates(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseCertificate> query = builder.createQuery(CourseCertificate.class);
        Root<CourseCertificate> root = query.from(CourseCertificate.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<CourseCertificate> q = session.createQuery(query);
        
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
    public List<CourseCertificate> getCertificatesByCourseStudentId(Integer courseStudentId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CourseCertificate> query = builder.createQuery(CourseCertificate.class);
        Root<CourseCertificate> root = query.from(CourseCertificate.class);
        
        query.where(builder.equal(root.get("courseStudentId").get("id"), courseStudentId));
        
        Query<CourseCertificate> q = session.createQuery(query);
        
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
    public long countCourseCertificates() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseCertificate> root = query.from(CourseCertificate.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long countCertificatesByCourseStudentId(Integer courseStudentId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseCertificate> root = query.from(CourseCertificate.class);
        query.where(builder.equal(root.get("courseStudentId").get("id"), courseStudentId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<CourseCertificate> root = query.from(CourseCertificate.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<CourseCertificate> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            if (filters.containsKey("downloadLink")) {
                predicates.add(builder.like(root.get("downloadLink"), String.format("%%%s%%", filters.get("downloadLink"))));
            }
            
            if (filters.containsKey("courseStudentId")) {
                predicates.add(builder.equal(root.get("courseStudentId").get("id"), Integer.valueOf(filters.get("courseStudentId"))));
            }
        }
        
        return predicates;
    }
}
