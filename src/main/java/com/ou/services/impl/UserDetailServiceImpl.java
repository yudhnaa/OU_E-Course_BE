package com.ou.services.impl;

import com.ou.pojo.CustomUserDetails;
import com.ou.pojo.User;
import com.ou.repositories.UserRepository;
import com.ou.services.LocalizationService;
import com.ou.services.UserDetailService;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
@Transactional
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private LocalizationService localizationService;

    @Qualifier("springSecurityFilterChain")
    @Autowired
    private Filter filter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database
        User user = userRepo.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        localizationService.getMessage("user.notFound", LocaleContextHolder.getLocale())));

        //use CustomUserDetails. Do this in CustomUserDetails.java
//        // Map roles to GrantedAuthority
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority(user.getUserRoleId().getName()));

        // Return UserDetails object
        return new CustomUserDetails(user);
    }
}