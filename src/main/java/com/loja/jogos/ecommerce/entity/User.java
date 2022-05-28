package com.loja.jogos.ecommerce.entity;

import com.loja.jogos.ecommerce.dto.UserForm;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "tblUser")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Long id;

    @Column(name = "dsName")
    private String name;

    @Column(name = "dsEmail")
    private String email;

    @Column(name = "dsPassword")
    private String password;

    @Column(name = "dsPasswordDateTimeLastEdition")
    private LocalDateTime lastPasswordUpdate;

    @Column(name = "idUserProfile")
    private Long idUserProfile;

    @Column(name = "idCustomer")
    private Long idCustomer;

    @Column(name = "stActive")
    private Boolean isActive;

    public User(UserForm form) {
        this.name = form.getName();
        this.email = form.getEmail();
        this.password = form.getPassword();
        this.idUserProfile = form.getIdUserProfile();
        this.idCustomer = form.getIdCustomer();
        this.isActive = true;
    }

    public void update(UserForm form) {
        this.name = form.getName();
        this.idUserProfile = form.getIdUserProfile();
    }

    public void selfUpdate(UserForm form) {
        this.name = form.getName();
    }

    public void updatePassword(UserForm form) {
        this.password = form.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

