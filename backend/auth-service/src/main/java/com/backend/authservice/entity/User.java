package com.backend.authservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    String username;
    @Column(name = "password_hash")
    String passwordHash;
    @Column(name = "full_name")
    String fullname;
    @Column(name = "avatar_url")
    String avatarUrl;
    String email;
    @Column(name = "phone_number")
    String phonenumber;
    @Column(name = "create_at")
    LocalDateTime createAt;
    @Column(name = "is_delete", nullable = false)
    boolean isDeleted = false;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getRolePermissions().stream()
                .map(p -> (GrantedAuthority) () -> p.getRole().getDescription())
                .collect(Collectors.toList());
    }
    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isDeleted;
    }
}