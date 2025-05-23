package com.ou.services.impl;

import com.ou.pojo.Lecturer;
import com.ou.repositories.LecturerRepository;
import com.ou.services.LecturerService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LecturerServiceImpl implements LecturerService {
    @Autowired
    private LecturerRepository lecturerRepositoryImpl;
    @Autowired
    private LocalizationService localizationService;

    @Override
    public Lecturer addLecturer(Lecturer lecturer) {
        return lecturerRepositoryImpl.addLecturer(lecturer);
    }

    @Override
    public List<Lecturer> getLecturers(Map<String, String> params) {
        return lecturerRepositoryImpl.getLecturers(params);
    }

    @Override
    public Optional<Lecturer> getLecturerById(Integer id) {
        return lecturerRepositoryImpl.getLecturerById(id);
    }

    @Override
    public Optional<Lecturer> getLecturerByUserId(Integer userId) {
        return lecturerRepositoryImpl.getLecturerByUserId(userId);
    }

    @Override
    public List<Lecturer> getActiveLecturers(Map<String, String> params) {
        return lecturerRepositoryImpl.getActiveLecturers(params);
    }

    @Override
    public Lecturer updateLecturer(Lecturer lecturer) {
        try {
            return lecturerRepositoryImpl.updateLecturer(lecturer);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteLecturer(Integer id) {
        return lecturerRepositoryImpl.deleteLecturer(id);
    }

    @Override
    public long countLecturers() {
        return lecturerRepositoryImpl.countLecturers();
    }

    @Override
    public long countLecturersByCourse(Integer courseId) {
        return lecturerRepositoryImpl.countLecturersByCourse(courseId);
    }

    @Override
    public long countActiveLecturers() {
        return lecturerRepositoryImpl.countActiveLecturers();
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        return lecturerRepositoryImpl.countSearchResults(filters);
    }
}
