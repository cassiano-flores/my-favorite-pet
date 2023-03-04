package br.com.cwi.myFavoritePet.security;

import br.com.cwi.myFavoritePet.security.domain.Users;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class UsersSecurity implements UserDetails {

    private static final String SPRING_PERMISSION_PREFIX = "ROLE_";

    private Long id;
    private String email;
    private String password;
    private boolean active;
    private List<SimpleGrantedAuthority> permissions;

    public UsersSecurity(Users usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.active = usuario.isActive();
        this.permissions = convertPermissions(usuario);
    }

    private List<SimpleGrantedAuthority> convertPermissions(Users usuario) {
        return usuario.getPermissions().stream()
                .map(permissao -> new SimpleGrantedAuthority(SPRING_PERMISSION_PREFIX + permissao.getName()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
