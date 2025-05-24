package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.Attachment;
import com.ou.repositories.AttachmentRepository;
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
public class AttachmentRepositoryImpl implements AttachmentRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Attachment addAttachment(Attachment attachment) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(attachment);
        session.flush();
        return attachment;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Attachment> getAttachments(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Attachment> query = builder.createQuery(Attachment.class);
        Root<Attachment> root = query.from(Attachment.class);
        query.select(root);

        Query<Attachment> q = session.createQuery(query);

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
    public List<Attachment> searchAttachments(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Attachment> query = builder.createQuery(Attachment.class);
        Root<Attachment> root = query.from(Attachment.class);

        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);

        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }

        Query<Attachment> q = session.createQuery(query);

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
    public Optional<Attachment> getAttachmentById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Attachment attachment = session.get(Attachment.class, id);
        return Optional.ofNullable(attachment);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Attachment> getAttachmentByName(String name) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            Query<Attachment> query = session.createNamedQuery("Attachment.findByName", Attachment.class);
            query.setParameter("name", name);
            return Optional.ofNullable(query.uniqueResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Attachment> getAttachmentByLink(String link) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            Query<Attachment> query = session.createNamedQuery("Attachment.findByLink", Attachment.class);
            query.setParameter("link", link);
            return Optional.ofNullable(query.uniqueResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Attachment updateAttachment(Attachment attachment) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(attachment);
        return attachment;
    }

    @Override
    public boolean deleteAttachment(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Attachment attachment = session.get(Attachment.class, id);
        if (attachment != null) {
            session.delete(attachment);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public long countAttachments() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Attachment> root = query.from(Attachment.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Attachment> root = query.from(Attachment.class);

        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);

        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }

        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<Attachment> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            if (filters.containsKey("name")) {
                predicates.add(builder.like(root.get("name"), String.format("%%%s%%", filters.get("name"))));
            }

            if (filters.containsKey("link")) {
                predicates.add(builder.like(root.get("link"), String.format("%%%s%%", filters.get("link"))));
            }

            if (filters.containsKey("description")) {
                predicates.add(builder.like(root.get("description"), String.format("%%%s%%", filters.get("description"))));
            }
        }

        return predicates;
    }
}
