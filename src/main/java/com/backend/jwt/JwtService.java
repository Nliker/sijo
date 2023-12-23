package com.backend.jwt;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.issuer}")
    private String ISSUER;

    @Value("${jwt.secret}")
    private String SECRET;
    
    @Value("${jwt.expireHour}")
    private int expireHour;

    public String create() {
        JWTCreator.Builder b = JWT.create();
        b.withIssuer(ISSUER);
        b.withClaim("company", "naepojesijo");
        b.withExpiresAt(expiresAt());
        return b.sign(Algorithm.HMAC256(SECRET));
    }
    
    private Date expiresAt()  {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 한달 24 * 31
        cal.add(Calendar.HOUR, expireHour);
        return cal.getTime();
    }
}