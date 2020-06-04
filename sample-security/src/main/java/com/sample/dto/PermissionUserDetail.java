package com.sample.dto;

import com.sample.mbg.model.Permission;
import com.sample.mbg.model.Role;
import com.sample.mbg.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PermissionUserDetail implements UserDetails {
    private User user;
    private List<Role> roles;
    private List<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> rolesAuthority = roles
                .stream()
                .filter(role -> role != null)
                .map(role -> new SimpleGrantedAuthority(role.getRoleKey()))
                .collect(Collectors.toList());
        List<GrantedAuthority> permissionsAuthority = permissions
                .stream()
                .filter(permission -> permission != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
        rolesAuthority.addAll(permissionsAuthority);
        return rolesAuthority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return true;
    }
}
