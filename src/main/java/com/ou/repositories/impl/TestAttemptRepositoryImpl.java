package com.ou.repositories.impl;

import com.ou.pojo.Test;
import com.ou.pojo.TestAttempt;
import com.ou.repositories.TestAttemptRepository;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.query.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.ou.configs.WebApplicationSettings.PAGE_SIZE;

@Repository
@Transactional
public class TestAttemptRepositoryImpl implements TestAttemptRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<TestAttempt> getAllTestAttemptsByTestId(Integer testId, Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TestAttempt> query = cb.createQuery(TestAttempt.class);
        Root<TestAttempt> root = query.from(TestAttempt.class);

        // Tạo list predicates và thêm điều kiện testId vào
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("testId").get("id"), testId));

        // Thêm các predicates khác từ params
        List<Predicate> additionalPredicates = buildPredicates(cb, root, params);
        predicates.addAll(additionalPredicates);

        // Áp dụng tất cả predicates cùng lúc
        query.where(cb.and(predicates.toArray(new Predicate[0])));
        query.orderBy(cb.desc(root.get("submittedAt")));

        Query<TestAttempt> q = session.createQuery(query);

        // Pagination logic...
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
    public List<TestAttempt> getTestAttemptsByStudentId(Integer studentId, Map<String, String> params) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TestAttempt> query = builder.createQuery(TestAttempt.class);
        Root<TestAttempt> root = query.from(TestAttempt.class);

        List<Predicate> predicates = buildPredicates(builder, root, params);
        predicates.add(builder.equal(root.get("userId").get("id"), studentId));
        query.where(builder.and(predicates.toArray(new Predicate[0])));
        query.orderBy(builder.desc(root.get("submittedAt")));

        Query<TestAttempt> q = session.createQuery(query);

        if (params != null && params.containsKey("page")) {
            int page = Integer.parseInt(params.getOrDefault("page", "1"));
            int pageSize = Integer.parseInt(params.getOrDefault("pageSize", String.valueOf(PAGE_SIZE)));
            int start = (page - 1) * pageSize;
            q.setMaxResults(pageSize);
            q.setFirstResult(start);
        }

        return q.getResultList();

    }


    @Override
    public long countTestAttemptsByTestId(Integer testId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<TestAttempt> root = query.from(TestAttempt.class);
        query.where(cb.equal(root.get("testId").get("id"), testId));
        query.select(cb.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    public Optional<TestAttempt> getTestAttemptById(Integer id) {
        Session session = this.factory.getObject().getCurrentSession();
        TestAttempt testAttempt = session.get(TestAttempt.class, id);
        return Optional.ofNullable(testAttempt);
    }

    @Override
    public TestAttempt addTestAttempt(TestAttempt testAttempt) {
        if (testAttempt == null || testAttempt.getTestId() == null || testAttempt.getTestId().getId() <= 0) {
            throw new IllegalArgumentException("Invalid test attempt data");
        }
        Session session = this.factory.getObject().getCurrentSession();
        session.persist(testAttempt);
        return testAttempt;
    }

    @Override
    public boolean deleteTestAttemptById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid test attempt ID");
        }
        Session session = this.factory.getObject().getCurrentSession();
        TestAttempt testAttempt = session.get(TestAttempt.class, id);
        if (testAttempt != null) {
            session.delete(testAttempt);
            return true;
        }
        return false;
    }

    @Override
    public TestAttempt updateTestAttempt(TestAttempt testAttempt) {
        if (testAttempt == null || testAttempt.getId() == null || testAttempt.getId() <= 0) {
            throw new IllegalArgumentException("Invalid test attempt data");
        }
        Session session = this.factory.getObject().getCurrentSession();
        session.merge(testAttempt);
        return testAttempt;
    }

    private List<Predicate> buildPredicates(CriteriaBuilder builder, Root<TestAttempt> root, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();

        if(filters != null){
            if(filters.containsKey("totalScore")){
                predicates.add(builder.equal(root.get("totalScore"), filters.get("totalScore")));
            }

            if(filters.containsKey("studentId")){
                predicates.add(builder.equal(root.get("studentId").get("id"), Integer.parseInt(filters.get("studentId"))));
            }

            if(filters.containsKey("startedAt")){
                predicates.add(builder.greaterThanOrEqualTo(root.get("startedAt"), filters.get("startedAt")));
            }

            if(filters.containsKey("submittedAt")){
                predicates.add(builder.lessThanOrEqualTo(root.get("submittedAt"), filters.get("submittedAt")));
            }

            if(filters.containsKey("testId")){
                predicates.add(builder.equal(root.get("testId").get("id"), Integer.parseInt(filters.get("testId"))));
            }
        }

        return predicates;
    }
}
