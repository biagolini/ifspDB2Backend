package com.loja.jogos.ecommerce.security;

import com.loja.jogos.ecommerce.entity.User;
import com.loja.jogos.ecommerce.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UserRepository repository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);
        if (valido) {
            autenticarCliente(token);
        }
        filterChain.doFilter(request, response);
    }

    // Metodo que vai pegar as informações do que estão no Payload do token (ou seja é a mensagem que enviamos dentro do token) onde falamos os dados do usuario e horario da requisição. Geramos um novo token com esses dados, e comparamos se os tokens são iguais.
    private void autenticarCliente(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        User usuario = repository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    // Metodo para pegar o token dentro do Header da requisição
    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }

}
