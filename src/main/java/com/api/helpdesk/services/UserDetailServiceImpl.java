package com.api.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.api.helpdesk.errors.AppError;
import com.api.helpdesk.domains.User;
import com.api.helpdesk.repositories.UserRepository;
import com.api.helpdesk.security.UserSpringSecurity;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws AppError {
        Optional<User> user = this.repository.findByEmail(email);
        if (user.isPresent()) {
            return new UserSpringSecurity(
                user.get().getId(),
                user.get().getEmail(),
                user.get().getPassword(),
                user.get().getProfiles());
        }
        throw new AppError("User not found", 404, "Entity not found");
    }

}
