package com.backend.authservice.service;

import com.backend.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    System.out.println("🧪 DB password hash: " + user.getPasswordHash());
                    return user;
                })
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}