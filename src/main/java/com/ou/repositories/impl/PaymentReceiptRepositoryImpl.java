package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.PaymentReceipt;
import com.ou.repositories.PaymentReceiptRepository;
import jakarta.persistence.NoResultException;
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
public class PaymentReceiptRepositoryImpl implements PaymentReceiptRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public PaymentReceipt addPaymentReceipt(PaymentReceipt paymentReceipt) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(paymentReceipt);
        session.flush();
        return paymentReceipt;
    }

    @Override
    public PaymentReceipt updatePaymentReceipt(PaymentReceipt paymentReceipt) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(paymentReceipt);
        return paymentReceipt;
    }

    @Override
    public boolean deletePaymentReceipt(Long id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        PaymentReceipt paymentReceipt = session.get(PaymentReceipt.class, id);
        if (paymentReceipt != null) {
            session.remove(paymentReceipt);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentReceipt> getPaymentReceiptById(Long id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        PaymentReceipt paymentReceipt = session.get(PaymentReceipt.class, id);
        return Optional.ofNullable(paymentReceipt);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentReceipt> getPaymentReceiptByReceiptId(String receiptId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentReceipt> query = builder.createQuery(PaymentReceipt.class);
        Root<PaymentReceipt> root = query.from(PaymentReceipt.class);

        query.where(builder.equal(root.get("receiptId"), receiptId));

        try {
            PaymentReceipt result = session.createQuery(query).getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentReceipt> getPaymentReceipts(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentReceipt> query = builder.createQuery(PaymentReceipt.class);
        Root<PaymentReceipt> root = query.from(PaymentReceipt.class);
        query.select(root);

        // Apply sorting by creation date (most recent first)
        query.orderBy(builder.desc(root.get("createdAt")));

        Query<PaymentReceipt> q = session.createQuery(query);

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
    public List<PaymentReceipt> searchPaymentReceipts(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentReceipt> query = builder.createQuery(PaymentReceipt.class);
        Root<PaymentReceipt> root = query.from(PaymentReceipt.class);

        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);

        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }

        // Apply sorting by creation date (most recent first)
        query.orderBy(builder.desc(root.get("createdAt")));

        Query<PaymentReceipt> q = session.createQuery(query);

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

    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<PaymentReceipt> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            if (filters.containsKey("receiptId")) {
                predicates.add(builder.like(root.get("receiptId"),
                        String.format("%%%s%%", filters.get("receiptId"))));
            }

            if (filters.containsKey("status")) {
                predicates.add(builder.equal(root.get("status"), filters.get("status")));
            }

            if (filters.containsKey("studentId")) {
                predicates.add(builder.equal(root.get("student").get("id"),
                        Long.valueOf(filters.get("studentId"))));
            }

            if (filters.containsKey("paymentMethod")) {
                predicates.add(builder.equal(root.get("paymentMethod"), filters.get("paymentMethod")));
            }
        }

        return predicates;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentReceipt> getPaymentReceiptsByStudent(Long studentId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentReceipt> query = builder.createQuery(PaymentReceipt.class);
        Root<PaymentReceipt> root = query.from(PaymentReceipt.class);

        query.where(builder.equal(root.get("student").get("id"), studentId));
        query.orderBy(builder.desc(root.get("createdAt")));

        Query<PaymentReceipt> q = session.createQuery(query);

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
    public List<PaymentReceipt> getPaymentReceiptsByStatus(String status, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentReceipt> query = builder.createQuery(PaymentReceipt.class);
        Root<PaymentReceipt> root = query.from(PaymentReceipt.class);

        query.where(builder.equal(root.get("status"), status));
        query.orderBy(builder.desc(root.get("createdAt")));

        Query<PaymentReceipt> q = session.createQuery(query);

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
    public long countPaymentReceipts() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<PaymentReceipt> root = query.from(PaymentReceipt.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long countPaymentReceiptsByStudent(Long studentId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<PaymentReceipt> root = query.from(PaymentReceipt.class);
        query.where(builder.equal(root.get("student").get("id"), studentId));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long countPaymentReceiptsByStatus(String status) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<PaymentReceipt> root = query.from(PaymentReceipt.class);
        query.where(builder.equal(root.get("status"), status));
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<PaymentReceipt> root = query.from(PaymentReceipt.class);

        List<Predicate> predicates = buildSearchPredicates(builder, root, filters);

        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }

        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }
}
