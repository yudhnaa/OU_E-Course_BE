package com.ou.services.impl;

import com.ou.exceptions.NotFoundException;
import com.ou.pojo.LessonType;
import com.ou.repositories.LessonTypeRepository;
import com.ou.services.LessonTypeService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class LessonTypeServiceImpl implements LessonTypeService {

    @Autowired
    private LessonTypeRepository lessonTypeRepo;
    
    @Autowired
    private LocalizationService localizationService;

    @Override
    public LessonType getLessonTypeById(int id) {
        LessonType lessonType = lessonTypeRepo.getLessonTypeById(id);
        if (lessonType == null) {
            throw new NotFoundException(localizationService.getMessage("lessonType.notFound", LocaleContextHolder.getLocale()));
        }
        return lessonType;
    }

    @Override
    public LessonType addLessonType(LessonType lessonType) {
        return lessonTypeRepo.createLessonType(lessonType);
    }

    @Override
    public LessonType updateLessonType(LessonType lessonType) {
        LessonType existingLessonType = getLessonTypeById(lessonType.getId());
        return lessonTypeRepo.updateLessonType(lessonType);
    }

    @Override
    public boolean deleteLessonType(int id) {
        getLessonTypeById(id);
        return lessonTypeRepo.deleteLessonType(id);
    }

    @Override
    public List<LessonType> getLessonTypes() {

        return lessonTypeRepo.getAllLessonTypes();
    }

    @Override
    public List<LessonType> searchLessonTypes() {
        return lessonTypeRepo.getAllLessonTypes();
    }

    @Override
    public Optional<LessonType> getLessonTypeByName(String name) {
        return lessonTypeRepo.getLessonTypeByName(name);
    }

    @Override
    public long countLessonTypes() {
        return lessonTypeRepo.getAllLessonTypes().size();
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        return lessonTypeRepo.getAllLessonTypes().size();
    }
}
