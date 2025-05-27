/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.formatters;

import com.ou.pojo.User;
import java.text.ParseException;
import java.util.Locale;

import com.ou.services.LocalizationService;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author yudhna
 */
@Component
public class UserFormatter implements Formatter<User> {

    @Autowired
    private UserService userService;

    @Autowired
    private LocalizationService localizationService;

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        try {
            Integer id = Integer.parseInt(text);

            User u = userService.getUserById(id);
            if (u == null) {
                throw new ParseException(localizationService.getMessage("user.notFound", locale), 0);
            }

            return u;
        } catch (NumberFormatException e) {
            throw new ParseException(localizationService.getMessage("user.invalid.userId", locale), 0);
        }
    }

    @Override
    public String print(User user, Locale locale) {
        return (user != null && user.getId() != null) ? String.valueOf(user.getId()) : "";
    }
}
