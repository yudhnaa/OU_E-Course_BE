package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.PaymentReceiptCourse;
import com.ou.repositories.PaymentReceiptCourseRepository;
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
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class PaymentReceiptCourseRepositoryImpl implements PaymentReceiptCourseRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public PaymentReceiptCourse addPaymentReceiptCourse(PaymentReceiptCourse paymentReceiptCourse) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(paymentReceiptCourse);
        session.flush();
        return paymentReceiptCourse;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentReceiptCourse> getPaymentReceiptCourseById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        PaymentReceiptCourse paymentReceiptCourse = session.get(PaymentReceiptCourse.class, id);
        return Optional.ofNullable(paymentReceiptCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentReceiptCourse> getPaymentReceiptCoursesByPaymentReceipt(Integer paymentReceiptId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentReceiptCourse> query = builder.createQuery(PaymentReceiptCourse.class);
        Root<PaymentReceiptCourse> root = query.from(PaymentReceiptCourse.class);

        query.where(builder.equal(root.get("paymentReceipt").get("id"), paymentReceiptId));

        Query<PaymentReceiptCourse> q = session.createQuery(query);

        // Apply pagination
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
    public List<PaymentReceiptCourse> getPaymentReceiptCoursesByCourse(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentReceiptCourse> query = builder.createQuery(PaymentReceiptCourse.class);
        Root<PaymentReceiptCourse> root = query.from(PaymentReceiptCourse.class);

        query.where(builder.equal(root.get("course").get("id"), courseId));

        Query<PaymentReceiptCourse> q = session.createQuery(query);

        // Apply pagination
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
    public Optional<PaymentReceiptCourse> getPaymentReceiptCourseByReceiptAndCourse(Integer paymentReceiptId, Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentReceiptCourse> query = builder.createQuery(PaymentReceiptCourse.class);
        Root<PaymentReceiptCourse> root = query.from(PaymentReceiptCourse.class);

        query.where(
            builder.and(
                builder.equal(root.get("paymentReceipt").get("id"), paymentReceiptId),
                builder.equal(root.get("course").get("id"), courseId)
            )
        );

        try {
            PaymentReceiptCourse result = session.createQuery(query).getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentReceiptCourse> getAllPaymentReceiptCourses(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentReceiptCourse> query = builder.createQuery(PaymentReceiptCourse.class);
        Root<PaymentReceiptCourse> root = query.from(PaymentReceiptCourse.class);
        query.select(root);

        Query<PaymentReceiptCourse> q = session.createQuery(query);

        // Apply pagination
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
    public boolean deletePaymentReceiptCourse(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        PaymentReceiptCourse paymentReceiptCourse = session.get(PaymentReceiptCourse.class, id);
        if (paymentReceiptCourse != null) {
            session.remove(paymentReceiptCourse);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePaymentReceiptCourseByReceiptAndCourse(Integer paymentReceiptId, Integer courseId) {
        Optional<PaymentReceiptCourse> paymentReceiptCourseOpt = getPaymentReceiptCourseByReceiptAndCourse(
                paymentReceiptId, courseId);
        
        if (paymentReceiptCourseOpt.isPresent()) {
            Session session = sessionFactory.getObject().getCurrentSession();
            session.remove(paymentReceiptCourseOpt.get());
            return true;
        }
        
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Long countPaymentReceiptCoursesByPaymentReceipt(Integer paymentReceiptId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<PaymentReceiptCourse> root = query.from(PaymentReceiptCourse.class);
        
        query.where(builder.equal(root.get("paymentReceipt").get("id"), paymentReceiptId));
        query.select(builder.count(root));
        
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Long countPaymentReceiptCoursesByCourse(Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<PaymentReceiptCourse> root = query.from(PaymentReceiptCourse.class);
        
        query.where(builder.equal(root.get("course").get("id"), courseId));
        query.select(builder.count(root));
        
        return session.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAllPaymentReceiptCourses() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<PaymentReceiptCourse> root = query.from(PaymentReceiptCourse.class);
        
        query.select(builder.count(root));
        
        return session.createQuery(query).getSingleResult();
    }
}
