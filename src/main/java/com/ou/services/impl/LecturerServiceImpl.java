package com.ou.services.impl;

import com.ou.pojo.Lecturer;
import com.ou.repositories.LecturerRepository;
import com.ou.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LecturerServiceImpl implements LecturerService {
    @Autowired
    private LecturerRepository lecturerRepositoryImpl;

    @Override
    public Optional<Lecturer> getLecturerById(Integer id) {
        return lecturerRepositoryImpl.getLecturerById(id);
    }
}
