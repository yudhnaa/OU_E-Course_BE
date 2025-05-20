package com.ou.repositories.impl;


import com.ou.pojo.ExerciseAttachment;
import com.ou.pojo.LessonAttachment;
import com.ou.repositories.LessonAttachmentRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class LessonAttachmentRepositoryImpl implements LessonAttachmentRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public LessonAttachment createLessonAttachment(LessonAttachment lessonAttachment) {
        Session session = factory.getObject().getCurrentSession();
        try {
            session.persist(lessonAttachment);
            return lessonAttachment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public LessonAttachment getLessonAttachmentById(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        try {
            return session.get(LessonAttachment.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LessonAttachment> getAllLessonAttachments() {
        Session session = factory.getObject().getCurrentSession();
        try {
            return session.createQuery("FROM LessonAttachment", LessonAttachment.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Trả về danh sách rỗng nếu có lỗi
        }
    }

    @Override
    public List<LessonAttachment> getLessonAttachmentsByLesson(Integer lessonId) {
        Session session = factory.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<LessonAttachment> query = builder.createQuery(LessonAttachment.class);

            Root<LessonAttachment> root = query.from(LessonAttachment.class);
            query.select(root).where(builder.equal(root.get("lessonId").get("id"), lessonId));

            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public LessonAttachment updateLessonAttachment(LessonAttachment lessonAttachment) {
        Session session = factory.getObject().getCurrentSession();
        try {
            session.merge(lessonAttachment);
            return lessonAttachment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteLessonAttachment(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        try {
            LessonAttachment lessonAttachment = session.get(LessonAttachment.class, id);
            if (lessonAttachment != null) {
                session.delete(lessonAttachment);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long countLessonAttachmentsByLesson(Integer lessonId) {
        Session session = factory.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);

            Root<LessonAttachment> root = query.from(LessonAttachment.class);
            query.select(builder.count(root));
            query.where(builder.equal(root.get("lessonId").get("id"), lessonId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
