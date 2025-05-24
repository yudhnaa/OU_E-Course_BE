package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.Category;
import com.ou.repositories.CategoryRepository;
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
public class CategoryRepositoryImpl implements CategoryRepository {
    
    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Category addCategory(Category category) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(category);
        session.flush(); // Ensure ID is generated and available
        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(category);
        return category;
    }

    @Override
    public boolean deleteCategory(Integer categoryId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Category category = session.get(Category.class, categoryId);
        if (category != null) {
            session.delete(category);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> getCategoryById(Integer categoryId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Category category = session.get(Category.class, categoryId);
        return Optional.ofNullable(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategories(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.select(root);
        
        Query<Category> q = session.createQuery(query);
        
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
    public long countCategories() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Category> root = query.from(Category.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> searchCategories(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        Query<Category> q = session.createQuery(query);
        
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
        Root<Category> root = query.from(Category.class);
        
        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);
        
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }
        
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
    
    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<Category> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filters != null) {
            if (filters.containsKey("name")) {
                predicates.add(builder.like(root.get("name"), String.format("%%%s%%", filters.get("name"))));
            }
            
            if (filters.containsKey("description")) {
                predicates.add(builder.like(root.get("description"), String.format("%%%s%%", filters.get("description"))));
            }
        }
        
        return predicates;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> getCategoryByName(String name) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            Query<Category> query = session.createNamedQuery("Category.findByName", Category.class);
            query.setParameter("name", name);
            return Optional.ofNullable(query.uniqueResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
