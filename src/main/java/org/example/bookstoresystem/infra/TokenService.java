package org.example.bookstoresystem.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.example.bookstoresystem.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserModel user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("api-books")
                    .withSubject(user.getUsername())
                    .withExpiresAt(generateExpiresAt())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException jwtCreationException){
            throw new RuntimeException("token not created.");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("api-books")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException jwtVerificationException){
            throw new RuntimeException("");
        }
    }

    private Instant generateExpiresAt(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
