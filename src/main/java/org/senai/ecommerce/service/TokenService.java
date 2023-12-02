package org.senai.ecommerce.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.Value;
import org.senai.ecommerce.entity.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String privado;

    public String gerarToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(privado);
            String token = JWT.create().withIssuer("auth-api").withSubject(usuario.getNomeUsuario()).withExpiresAt(gerarDataExpiraracao)).sign(algorithm);
            return token;
        } catch (JWTCreationException ex){
            throw new RuntimeException("Falha ao gerar o token de acesso");
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(privado);
            return JWT.require(algorithm).withIssuer("auth-api").build().verify(token).getSubject();
        } catch (JWTVerificationException ex){
            return "";
        }
    }

    public Instant gerarDataExpiracao(){
        return LocalTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
