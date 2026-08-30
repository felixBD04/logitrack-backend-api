package com.logitrack.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // la llave maestra debe tener por lo menos 256 bits o mejor dicho 32 caracteres
    private static final String SECRET_KEY = "EstaEsUnaLlaveMaestraSuperSeguraParaElProyectoLogiTrack";

    private Key getSignInKey(){
        // convierte el texto en una llave encriptada
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    //creamos el token y lo asociamos con alguien
    public String generarToken(String username){
        return Jwts.builder()
                .setSubject(username) // el propietario del token
                .setIssuedAt(new Date(System.currentTimeMillis())) // la fecha y hora de la creacion del token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // expira en 24 horas
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // se firma con nuestra llave y un algoritmo seguro
                .compact();
    }

    public String extraerUsername(String token){
        return extraerClaim(token, Claims::getSubject);
    }

    public boolean esTokenValido(String token, UserDetails userDetails){
        final String username = extraerUsername(token);
        return (username.equals(userDetails.getUsername())) && !esTokenExpirado(token); // si la fecha es valida y si el usuario concuierda el token es valido
    }

    //revisamos que la fecha del token siga vijente
    private boolean esTokenExpirado(String token) {
        return extraerExpiration(token).before(new Date());
    }

    //extraemos la fecha del token
    private Date extraerExpiration(String token) {
        return extraerClaim(token, Claims::getExpiration);
    }

    // metodo interno para poder desarmar el token y leerlo por partes
    public <T> T extraerClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extraerTodasLasClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extraerTodasLasClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
