package ru.rambler.alexeimohov.spring;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
public class AppControllerAdvice {

    @ExceptionHandler( AccessDeniedException.class )
    public ResponseEntity  handleAccessDeniedException(Exception e, WebRequest request) {
        log.debug( "Handling Exception: " + e );

        return ResponseEntity.status( HttpStatus.FORBIDDEN).body( "Access denied! "+e.getMessage() );
    }

    @ExceptionHandler( ExpiredJwtException.class)
    public ResponseEntity  handleExpiredJwtException(Exception e, WebRequest request) {
        log.debug( "Handling Exception: " + e );

        return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "Jwt token has been expired! "+e.getMessage() );
    }
    @ExceptionHandler( javax.validation.ConstraintViolationException.class)
    public ResponseEntity  handleConstraintViolationException(Exception e, WebRequest request) {
        log.debug( "Handling Exception: " + e );

        return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "DB constraint has been violated! "+e.getMessage() );
    }

    @ExceptionHandler( MailException.class)
    public ResponseEntity  handleMailException(Exception e, WebRequest request) {
        log.debug( "Handling Exception: " + e );

        return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( "Message wasn't sent! "+e.getMessage() );
    }

    @ExceptionHandler({ SQLException.class,IllegalArgumentException.class,NullPointerException.class})
    public ResponseEntity handleInternalException(Exception e, WebRequest request){

        return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( e.getMessage() ) ;
    }

    @ExceptionHandler({ RuntimeException.class})
    public ResponseEntity handleRuntimeException(Exception e, WebRequest request){

        return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( e.getMessage() ) ;
    }

}
