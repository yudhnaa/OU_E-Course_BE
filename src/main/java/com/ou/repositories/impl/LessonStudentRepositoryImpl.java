package com.ou.repositories.impl;

import com.ou.pojo.LessonStudent;
import com.ou.repositories.LessonStudentRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.List;

@Repository
@Transactional
public class LessonStudentRepositoryImpl implements LessonStudentRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public LessonStudent updateLessonStudent(LessonStudent lessonStudent) {
        Session session = factory.getObject().getCurrentSession();
        try {
            session.merge(lessonStudent);
            return lessonStudent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LessonStudent> getAllLessonStudents() {
        Session session = factory.getObject().getCurrentSession();
        try {
            return session.createQuery("FROM LessonStudent", LessonStudent.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Return an empty list in case of an error
        }
    }

    @Override
    public List<LessonStudent> findByLessonId(Integer lessonId) {
        Session session = factory.getObject().getCurrentSession();
        Query<LessonStudent> query = session.createQuery(
                "SELECT ls FROM LessonStudent ls WHERE ls.lessonId.id = :lessonId",
                LessonStudent.class);
        query.setParameter("lessonId", lessonId);
        return query.getResultList();
    }

    @Override
    public List<LessonStudent> findByStudentId(Integer studentId) {
        Session session = factory.getObject().getCurrentSession();
        Query<LessonStudent> query = session.createQuery(
                "SELECT ls FROM LessonStudent ls WHERE ls.studentId.id = :studentId",
                LessonStudent.class);
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }

    @Override
    public List<LessonStudent> findByLearningStatus(Boolean isLearn) {
        Session session = factory.getObject().getCurrentSession();
        Query<LessonStudent> query = session.createQuery(
                "SELECT ls FROM LessonStudent ls WHERE ls.isLearn = :isLearn",
                LessonStudent.class);
        query.setParameter("isLearn", isLearn);
        return query.getResultList();
    }

    @Override
    public boolean isLessonCompleted(Integer lessonId, Integer studentId) {
        List<LessonStudent> lessonStudents = findByLessonId(lessonId);
        if (lessonStudents.isEmpty()) {
            return false;
        }

        return lessonStudents.stream()
                .anyMatch(ls -> ls.getStudentId().getId().equals(studentId) && Boolean.TRUE.equals(ls.getIsLearn()));
    }

    @Override
    public LessonStudent findByLessonIdAndStudentId(Integer lessonId, Integer studentId) {
        Session session = factory.getObject().getCurrentSession();
        Query<LessonStudent> query = session.createQuery(
                "SELECT ls FROM LessonStudent ls WHERE ls.lessonId.id = :lessonId AND ls.studentId.id = :studentId",
                LessonStudent.class);
        query.setParameter("lessonId", lessonId);
        query.setParameter("studentId", studentId);

        List<LessonStudent> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
