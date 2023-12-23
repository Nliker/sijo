package com.backend.jwt;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.backend.errorcode.JwtErrorCode;
import com.backend.exception.JwtException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.DefaultClaims;
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

    public String create() throws Exception{
    	Claims claims=new DefaultClaims();
		claims.put("company", "naepojesijo");
		System.out.println(SECRET);
		return Jwts.builder()
				.addClaims(claims)
				.signWith(SignatureAlgorithm.HS256,SECRET)
				.setExpiration(expiresAt())
				.compact();
    }
    
    private Date expiresAt()  {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 한달 24 * 31
        cal.add(Calendar.HOUR, expireHour);
        return cal.getTime();
    }
    
    public void validate(String token) throws Exception{
    	System.out.println(token);
    	if (token==null) {
    		throw new JwtException(JwtErrorCode.JwtReqired.getCode(),JwtErrorCode.JwtReqired.getDescription());
    	}
    	try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		}catch(SecurityException | SignatureException | MalformedJwtException e) {
			throw new JwtException(JwtErrorCode.InvaldJwtSignature.getCode(),JwtErrorCode.InvaldJwtSignature.getDescription());
		}catch(ExpiredJwtException e) {
			throw new JwtException(JwtErrorCode.ExpiredJwt.getCode(),JwtErrorCode.ExpiredJwt.getDescription());
		}catch(UnsupportedJwtException e) {
			throw new JwtException(JwtErrorCode.UnsupportedJwt.getCode(),JwtErrorCode.UnsupportedJwt.getDescription());
		}catch(IllegalArgumentException e) {
			throw new JwtException(JwtErrorCode.IllegalArgumentException.getCode(),JwtErrorCode.IllegalArgumentException.getDescription());
		}
    }
}