package com.ou.repositories.impl;

import com.ou.pojo.Lecturer;
import com.ou.repositories.LecturerRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class LecturerRepositoryImpl implements LecturerRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public Optional<Lecturer> getLecturerById(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        Lecturer lecturer = session.get(Lecturer.class, id);
        if (lecturer != null) {
            return Optional.of(lecturer);
        } else {
            return Optional.empty();
        }
    }
}
