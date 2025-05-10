package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.User;
import com.ou.pojo.UserRole;
import com.ou.repositories.UserRepository;
import jakarta.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Locale;

import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    
    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public User addUser(User user) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.save(user);
        session.flush(); // Ensure ID is generated and available
        return user;
    }

    @Override
    public User updateUser(User user) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.update(user);
        return user;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        User user = session.get(User.class, userId);
        if (user != null) {
            session.delete(user);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Integer userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        User user = session.get(User.class, userId);
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        
        Query<User> q = session.createQuery(query);
        
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
    public long countUsers(String locale) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<User> root = query.from(User.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> searchUsers(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<User> q = session.createQuery(query);
        
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
        Root<User> root = query.from(User.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<User> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            if (filters.containsKey("username")) {
                predicates.add(builder.like(root.get("username"), String.format("%%%s%%", filters.get("username"))));
            }
            
            if (filters.containsKey("email")) {
                predicates.add(builder.like(root.get("email"), String.format("%%%s%%", filters.get("email"))));
            }
            
            if (filters.containsKey("firstName")) {
                predicates.add(builder.like(root.get("firstName"), String.format("%%%s%%", filters.get("firstName"))));
            }
            
            if (filters.containsKey("lastName")) {
                predicates.add(builder.like(root.get("lastName"), String.format("%%%s%%", filters.get("lastName"))));
            }
            
            if (filters.containsKey("isActive")) {
                predicates.add(builder.equal(root.get("isActive"), Boolean.valueOf(filters.get("isActive"))));
            }
            
            if (filters.containsKey("userRoleId")) {
                predicates.add(builder.equal(root.get("userRoleId").get("id"), Integer.valueOf(filters.get("userRoleId"))));
            }
        }
        
        return predicates;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByUsername(String username) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            Query<User> query = session.createNamedQuery("User.findByUsername", User.class);
            query.setParameter("username", username);
            return Optional.ofNullable(query.uniqueResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            Query<User> query = session.createNamedQuery("User.findByEmail", User.class);
            query.setParameter("email", email);
            return Optional.ofNullable(query.uniqueResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getActiveUsers(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        
        query.where(builder.equal(root.get("isActive"), true));
        
        Query<User> q = session.createQuery(query);
        
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
    public long countActiveUsers(String locale) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("isActive"), true));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersByRole(Integer roleId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        
        query.where(builder.equal(root.get("userRoleId").get("id"), roleId));
        
        Query<User> q = session.createQuery(query);
        
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
    public long countUsersByRole(Integer roleId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("userRoleId").get("id"), roleId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
}
