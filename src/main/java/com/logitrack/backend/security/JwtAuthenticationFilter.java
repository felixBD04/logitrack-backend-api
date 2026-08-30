package com.logitrack.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Buscamos el header llamado "Authorization" en la petición
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // 2. Si no hay header o no empieza con "Bearer ", lo dejamos pasar al siguiente filtro
        // (Spring Security lo bloqueará más adelante si la ruta exige seguridad)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extraemos el token (quitando los primeros 7 caracteres de "Bearer ")
        jwt = authHeader.substring(7);

        // 4. Extraemos el usuario que viene en el token
        username = jwtService.extraerUsername(jwt);

        // 5. Si el token tiene un usuario y aún no está autenticado en este hilo...
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Buscamos al usuario en la base de datos
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Si el token es totalmente válido, le damos el acceso oficial a Spring Security
            if (jwtService.esTokenValido(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // ¡Se abren las puertas!
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continuamos con el flujo normal de la petición
        filterChain.doFilter(request, response);
    }
}