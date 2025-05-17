package com.ou.services;

import java.util.Locale;

public interface LocalizationService {
    String getMessage(String key, Locale locale);
}
