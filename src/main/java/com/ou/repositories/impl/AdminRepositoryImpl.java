package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.Admin;
import com.ou.pojo.User;
import com.ou.repositories.AdminRepository;
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
public class AdminRepositoryImpl implements AdminRepository {
    
    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public Admin addAdmin(Admin admin) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(admin);
        session.flush();
        return admin;
    }
    
    @Override
    public Admin updateAdmin(Admin admin) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(admin);
        return admin;
    }
    
    @Override
    public boolean deleteAdmin(Integer adminId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Admin admin = session.get(Admin.class, adminId);
        if (admin != null) {
            session.remove(admin);
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Admin> getAdminById(Integer adminId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            Query<Admin> query = session.createNamedQuery("Admin.findById", Admin.class);
            query.setParameter("id", adminId);
            return Optional.ofNullable(query.uniqueResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Admin> getAdminByUserId(Integer userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        
        Join<Admin, User> userJoin = root.join("userId");
        query.where(builder.equal(userJoin.get("id"), userId));
        
        try {
            return Optional.ofNullable(session.createQuery(query).uniqueResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Admin> getAdmins(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        
        query.select(root);
        
        Query<Admin> q = session.createQuery(query);
        
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
    public long countAdmins() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Admin> root = query.from(Admin.class);
        
        query.select(builder.count(root));
        
        return session.createQuery(query).getSingleResult();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Admin> searchAdmins(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<Admin> q = session.createQuery(query);
        
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
        Root<Admin> root = query.from(Admin.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<Admin> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            Join<Admin, User> userJoin = root.join("userId", JoinType.INNER);
            
            if (filters.containsKey("username")) {
                predicates.add(builder.like(userJoin.get("username"), 
                        String.format("%%%s%%", filters.get("username"))));
            }
            
            if (filters.containsKey("email")) {
                predicates.add(builder.like(userJoin.get("email"), 
                        String.format("%%%s%%", filters.get("email"))));
            }
            
            if (filters.containsKey("firstName")) {
                predicates.add(builder.like(userJoin.get("firstName"), 
                        String.format("%%%s%%", filters.get("firstName"))));
            }
            
            if (filters.containsKey("lastName")) {
                predicates.add(builder.like(userJoin.get("lastName"), 
                        String.format("%%%s%%", filters.get("lastName"))));
            }
            
            if (filters.containsKey("userId")) {
                predicates.add(builder.equal(userJoin.get("id"), 
                        Integer.valueOf(filters.get("userId"))));
            }
        }
        
        return predicates;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Admin> getAdminsByCourse(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        
        Join<Object, Object> courseJoin = root.join("courseSet");
        query.where(builder.equal(courseJoin.get("id"), courseId));
        
        Query<Admin> q = session.createQuery(query);
        
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
    public long countAdminsByCourse(Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Admin> root = query.from(Admin.class);
        
        Join<Object, Object> courseJoin = root.join("courseSet");
        query.where(builder.equal(courseJoin.get("id"), courseId));
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
}
