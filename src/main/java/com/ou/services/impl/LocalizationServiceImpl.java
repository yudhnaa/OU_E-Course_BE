package com.ou.services.impl;

import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String key, Locale locale) {
        Locale localeObj = locale != null ? locale: LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, localeObj);
    }
}
