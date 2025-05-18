package com.ou.repositories;

import com.ou.pojo.Lecturer;

import java.util.Optional;

public interface LecturerRepository {
    Optional<Lecturer> getLecturerById(Integer id);
}
