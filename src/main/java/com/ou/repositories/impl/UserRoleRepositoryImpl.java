package com.ou.repositories.impl;

import com.ou.pojo.UserRole;
import com.ou.repositories.UserRoleRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserRoleRepositoryImpl implements UserRoleRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<UserRole> getAllUserRoles() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRole> query = builder.createQuery(UserRole.class);
        Root<UserRole> root = query.from(UserRole.class);
        query.select(root);
        
        return session.createQuery(query).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserRole getUserRoleById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.get(UserRole.class, id);
    }

    @Override
    public UserRole addUserRole(UserRole userRole) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(userRole);
        session.flush(); // Ensure ID is generated and available
        return userRole;
    }

    @Override
    public UserRole updateUserRole(UserRole userRole) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(userRole);
        return userRole;
    }

    @Override
    public boolean deleteUserRole(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        UserRole userRole = session.get(UserRole.class, id);
        if (userRole != null) {
            session.remove(userRole);
            return true;
        }
        return false;
    }
    
    // Additional helper methods
    
    @Transactional(readOnly = true)
    public UserRole getUserRoleByName(String name) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            Query<UserRole> query = session.createNamedQuery("UserRole.findByName", UserRole.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
