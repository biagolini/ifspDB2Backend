package com.loja.jogos.ecommerce.security;

import com.loja.jogos.ecommerce.entity.User;
import com.loja.jogos.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> usuario = repository.findByEmail(email);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("User/Password not valid");
    }
}
