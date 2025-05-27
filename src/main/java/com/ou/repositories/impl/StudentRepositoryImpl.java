package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.CourseStudent;
import com.ou.pojo.Student;
import com.ou.pojo.User;
import com.ou.repositories.StudentRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Student addStudent(Student student) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(student);
        session.flush(); // Ensure ID is generated and available
        return student;
    }

    @Override
    public Student createStudentFromUser(User user) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Student student = new Student();
        student.setUserId(user);
        student.setTestAttemptSet(new HashSet<>());
        student.setLessonStudentSet(new HashSet<>());
        student.setCourseStudentSet(new HashSet<>());

        session.persist(student);
        session.flush();
        return student;
    }

    @Override
    public Optional<Student> getStudentById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Student student = session.get(Student.class, id);
        return Optional.ofNullable(student);
    }

    @Override
    public Optional<Student> getStudentByUserId(Integer userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        query.where(builder.equal(root.get("userId").get("id"), userId));

        try {
            Student student = session.createQuery(query).getSingleResult();
            return Optional.of(student);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Student> getStudents(Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        query.select(root);

        Query<Student> q = session.createQuery(query);

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
    public List<Student> searchStudents(Map<String, String> filters, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        Join<Student, User> userJoin = root.join("userId");

        List<Predicate> predicates = buildSearchPredicates(builder, root, userJoin, filters);

        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }

        Query<Student> q = session.createQuery(query);

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
    public List<Student> getStudentsByCourse(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        Join<Student, CourseStudent> courseStudentJoin = root.join("courseStudentSet");

        query.where(builder.equal(courseStudentJoin.get("courseId").get("id"), courseId));
        query.distinct(true);

        Query<Student> q = session.createQuery(query);

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
    public List<Student> getStudentsNotInCourse(Integer courseId, Map<String, String> params) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        // Subquery to get all student IDs enrolled in the course
        Subquery<Integer> subquery = query.subquery(Integer.class);
        Root<CourseStudent> subRoot = subquery.from(CourseStudent.class);
        subquery.select(subRoot.get("studentId").get("id"))
                .where(builder.equal(subRoot.get("courseId").get("id"), courseId));

        // Main query to get students not in the subquery results
        query.where(builder.not(root.get("id").in(subquery)));

        Query<Student> q = session.createQuery(query);

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
    public Student updateStudent(Student student) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.merge(student);
        return student;
    }

    @Override
    public boolean deleteStudent(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.delete(student);
            return true;
        }
        return false;
    }

    @Override
    public long countStudents() {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Student> root = query.from(Student.class);
        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Student> root = query.from(Student.class);
        Join<Student, User> userJoin = root.join("userId");

        List<Predicate> predicates = buildSearchPredicates(builder, root, userJoin, filters);

        // Apply predicates if any
        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[0])));
        }

        query.select(builder.count(root));
        return session.createQuery(query).getSingleResult();
    }

    @Override
    public long countStudentsByCourse(Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Student> root = query.from(Student.class);
        Join<Student, CourseStudent> courseStudentJoin = root.join("courseStudentSet");

        query.where(builder.equal(courseStudentJoin.get("courseId").get("id"), courseId));
        query.select(builder.countDistinct(root));

        return session.createQuery(query).getSingleResult();
    }

    @Override
    public long countStudentsNotInCourse(Integer courseId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Student> root = query.from(Student.class);

        // Subquery to get all student IDs enrolled in the course
        Subquery<Integer> subquery = query.subquery(Integer.class);
        Root<CourseStudent> subRoot = subquery.from(CourseStudent.class);
        subquery.select(subRoot.get("studentId").get("id"))
                .where(builder.equal(subRoot.get("courseId").get("id"), courseId));

        // Main query to count students not in the subquery results
        query.where(builder.not(root.get("id").in(subquery)));
        query.select(builder.count(root));

        return session.createQuery(query).getSingleResult();
    }

    private List<Predicate> buildSearchPredicates(CriteriaBuilder builder, Root<Student> root,
                                               Join<Student, User> userJoin, Map<String, String> filters) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            String name = filters.get("name");
            if (name != null && !name.trim().isEmpty()) {
                String pattern = "%" + name.trim().toLowerCase() + "%";
                Predicate usernameLike = builder.like(builder.lower(userJoin.get("username")), pattern);
                Predicate firstNameLike = builder.like(builder.lower(userJoin.get("firstName")), pattern);
                Predicate lastNameLike = builder.like(builder.lower(userJoin.get("lastName")), pattern);
                predicates.add(builder.or(usernameLike, firstNameLike, lastNameLike));
            }

            if (filters.containsKey("email")) {
                predicates.add(builder.like(userJoin.get("email"),
                        String.format("%%%s%%", filters.get("email"))));
            }

            if (filters.containsKey("userId")) {
                predicates.add(builder.equal(userJoin.get("id"),
                        Integer.valueOf(filters.get("userId"))));
            }

            if (filters.containsKey("notInCourse")) {
                Integer courseId = Integer.valueOf(filters.get("notInCourse"));

                // Create a subquery for students enrolled in the course
                Subquery<Integer> subquery = builder.createQuery().subquery(Integer.class);
                Root<CourseStudent> subRoot = subquery.from(CourseStudent.class);
                subquery.select(subRoot.get("studentId").get("id"))
                        .where(builder.equal(subRoot.get("courseId").get("id"), courseId));

                // Add predicate to exclude students in the subquery results
                predicates.add(builder.not(root.get("id").in(subquery)));
            }
        }

        return predicates;
    }
}
