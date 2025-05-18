package com.ou.services;

import com.ou.pojo.Lecturer;

import java.util.Optional;

public interface LecturerService {
    Optional<Lecturer> getLecturerById(Integer id);
}
