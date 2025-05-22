package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.Lecturer;
import com.ou.repositories.LecturerRepository;
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
public class LecturerRepositoryImpl implements LecturerRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Lecturer addLecturer(Lecturer lecturer) {
        Session session = factory.getObject().getCurrentSession();
        session.persist(lecturer);
        session.flush(); // Ensure ID is generated and available
        return lecturer;
    }

    @Override
    public Lecturer updateLecturer(Lecturer lecturer) {
        Session session = factory.getObject().getCurrentSession();
        session.merge(lecturer);
        return lecturer;
    }

    @Override
    public boolean deleteLecturer(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        Lecturer lecturer = session.get(Lecturer.class, id);
        if (lecturer != null) {
            session.delete(lecturer);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Lecturer> getLecturerById(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        Lecturer lecturer = session.get(Lecturer.class, id);
        return Optional.ofNullable(lecturer);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Lecturer> getLecturerByUserId(Integer userId) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lecturer> query = builder.createQuery(Lecturer.class);
        Root<Lecturer> root = query.from(Lecturer.class);

        query.where(builder.equal(root.get("userId").get("id"), userId));

        try {
            Lecturer result = session.createQuery(query).uniqueResult();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lecturer> getLecturers(Map<String, String> params) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lecturer> query = builder.createQuery(Lecturer.class);
        Root<Lecturer> root = query.from(Lecturer.class);
        query.select(root);

        List<Predicate> predicates = this.buildSearchPredicates(builder, root, params);

        query.where(builder.and(predicates.toArray(new Predicate[0])));

        Query<Lecturer> q = session.createQuery(query);

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

    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<Lecturer> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            if (filters.containsKey("isActive")) {
                predicates.add(builder.equal(root.get("isActive"), Boolean.valueOf(filters.get("isActive"))));
            }

            if (filters.containsKey("username")) {
                String keyword = "%" + filters.get("username").replace("_", "\\_").replace("%", "\\%") + "%";
                predicates.add(builder.like(root.get("userId").get("username"), keyword, '\\'));
            }

            if (filters.containsKey("email")) {
                String keyword = "%" + filters.get("email").replace("_", "\\_").replace("%", "\\%") + "%";
                predicates.add(builder.like(root.get("userId").get("email"), keyword, '\\'));
            }

            if (filters.containsKey("name")) {
                String keyword = "%" + filters.get("name").replace("_", "\\_").replace("%", "\\%") + "%";
                Predicate firstNameLike = builder.like(root.get("userId").get("firstName"), keyword, '\\');
                Predicate lastNameLike = builder.like(root.get("userId").get("lastName"), keyword, '\\');
                predicates.add(builder.or(firstNameLike, lastNameLike));
            }
        }

        return predicates;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Lecturer> getActiveLecturers(Map<String, String> params) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lecturer> query = builder.createQuery(Lecturer.class);
        Root<Lecturer> root = query.from(Lecturer.class);

        query.where(builder.equal(root.get("isActive"), true));

        Query<Lecturer> q = session.createQuery(query);

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
    public long countLecturers() {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Lecturer> root = query.from(Lecturer.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long countActiveLecturers() {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Lecturer> root = query.from(Lecturer.class);
        query.where(builder.equal(root.get("isActive"), true));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Lecturer> root = query.from(Lecturer.class);

        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);

        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }

        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
}
