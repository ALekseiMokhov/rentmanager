package ru.rambler.alexeimohov.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.rambler.alexeimohov.entities.User;

import java.util.Date;

@Component("jwtProvider")

@Slf4j
public class JwtTokenProvider implements ITokenProvider {
    @Value("${jwt.token.secret}")
    private String code;
    @Value("${jwt.token.exp}")
    private long validTime;

    public String generateToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date( now.getTime() + this.validTime );

        return Jwts.builder()
                .setSubject( String.valueOf( user.getId() ) )
                .setIssuedAt( new Date() )
                .setExpiration( expiryDate )
                .signWith( SignatureAlgorithm.HS512, code )
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey( code )
                .parseClaimsJws( token )
                .getBody();

        return Long.parseLong( claims.getSubject() );
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey( code ).parseClaimsJws( token );
            return true;
        } catch (SignatureException ex) {
            log.error( "Invalid JWT signature" );
        } catch (MalformedJwtException ex) {
            log.error( "Invalid JWT token" );
        } catch (ExpiredJwtException ex) {
            log.error( "Expired JWT token" );
        } catch (UnsupportedJwtException ex) {
            log.error( "Unsupported JWT token" );
        } catch (IllegalArgumentException ex) {
            log.error( "JWT claims string is empty." );
        }
        return false;
    }
}
