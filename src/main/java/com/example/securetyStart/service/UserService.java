package com.example.securetyStart.service;

import com.example.securetyStart.model.Role;
import com.example.securetyStart.model.User;
import com.example.securetyStart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;



    public User save(User user) {
        return userRepository.save(user);
    }



    public User create(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("User with this email already exists");
        }
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User with this username already exists");
        }
        return save(user);
    }


    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }


    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var user = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(user);
    }


    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}
