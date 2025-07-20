package com.HJ.BankReclamation.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY="Pz&!9gU#qL3@8sV^dWf@2zMn!4xT7kLu";
    private static final long EXPIRATION_TIME=86400000;

    public String generateToken (Integer idC){
        return Jwts.builder()
                .setSubject(String.valueOf(idC))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();


    }

    public Integer getClientIdFromToken(String token){
        Claims claims= Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return Integer.parseInt(claims.getSubject());
    }

    public boolean validateToken (String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
