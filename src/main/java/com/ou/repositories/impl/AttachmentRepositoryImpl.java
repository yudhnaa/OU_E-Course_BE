package com.ou.repositories.impl;

import com.ou.configs.WebApplicationSettings;
import com.ou.pojo.Attachment;
import com.ou.repositories.AttachmentRepository;
import com.ou.repositories.TemplateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class AttachmentRepositoryImpl implements AttachmentRepository {

    private static final int PAGE_SIZE = WebApplicationSettings.PAGE_SIZE;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Attachment addAttachment(Attachment attachment) {
        return null;
    }

    @Override
    public List<Attachment> getAttachments(Map<String, String> params) {
        return List.of();
    }

    @Override
    public List<Attachment> searchAttachments(Map<String, String> filters, Map<String, String> params) {
        return List.of();
    }

    @Override
    public Optional<Attachment> getAttachmentById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Attachment> getAttachmentByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Attachment> getAttachmentByLink(String link) {
        return Optional.empty();
    }

    @Override
    public Attachment updateAttachment(Attachment attachment) {
        return null;
    }

    @Override
    public boolean deleteAttachment(Integer id) {
        return false;
    }

    @Override
    public long countAttachments() {
        return 0;
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        return 0;
    }
}
