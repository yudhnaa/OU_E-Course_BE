package com.ou.services.impl;

import com.ou.pojo.User;
import com.ou.repositories.UserRepository;
import com.ou.services.LocalizationService;
import com.ou.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Fetch user from the database
        User user = userRepo.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        localizationService.getMessage("user.notFound", LocaleContextHolder.getLocale())));

        // Map roles to GrantedAuthority
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRoleId().getName()));

        // Return UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
//                user.getIsActive(),
//                true, // accountNonExpired
//                true, // credentialsNonExpired
//                true, // accountNonLocked
                authorities
        );
    }
}
