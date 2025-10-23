package br.com.leandro.todolist.task;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Pegar autenticacao (usuario e senha)
        var authorization = request.getHeader("Authorization");

        // extraindo parte de texto - remover basic e pegar so o token
        // Basic bWFudW1jOjEyMzQ1 e ficar apenas bWFudW1jOjEyMzQ1 (base64)
        var authEncoded = authorization.substring("Basic".length()).trim();

        // Fazendo o decode do base64 (Converte para um array de bytes)
        byte[] authDecode = Base64.getDecoder().decode(authEncoded);

        // Convertendo array de bytes para uma String
        var authString = new String(authDecode);

        System.out.println("Authorization");
        System.out.println(authString);

        // Ao inves de mandar manuc:12345 vai mandar um em cada linha
        String[] credentials = authString.split(":");
        String username = credentials[0];
        String password = credentials[1];
        System.out.println("username: " + username);
        System.out.println("password: " + password);


        // Valida se o usuario existe

        // Valida senha

        // Segue viagem

        filterChain.doFilter(request, response);
    }
}
