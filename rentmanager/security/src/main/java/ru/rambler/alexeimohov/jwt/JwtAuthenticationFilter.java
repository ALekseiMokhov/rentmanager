package ru.rambler.alexeimohov.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.service.interfaces.IUserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 /*
 * @linked to ITokenProvider filters incoming requests and grants authenticated user his authorities if any.
 * @linked to IUserService to retrieve User and UserMapper to convert DTO data.*/
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private ITokenProvider tokenProvider;

    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;

    public JwtAuthenticationFilter(ITokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException,
            IOException {
        try {
            String jwt = getJwtFromRequest( request );

            if (StringUtils.hasText( jwt ) && tokenProvider.validateToken( jwt )) {
                long userId = tokenProvider.getUserIdFromJWT( jwt );
                UserDetails userDetails = userMapper.fromDto( userService.getById( userId ) );
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities() );
                authentication.setDetails( new WebAuthenticationDetailsSource().buildDetails( request ) );

                SecurityContextHolder.getContext().setAuthentication( authentication );
                log.debug( "Granted authorities for " + userDetails.getUsername() + " are: " + userDetails.getAuthorities() );
            }
        } catch (Exception e) {
            log.debug( "Could not set user authentication in security context", e );
        }

        filterChain.doFilter( request, response );
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader( "Authorization" );
        if (StringUtils.hasText( bearerToken ) && bearerToken.startsWith( "Bearer " )) {
            return bearerToken.substring( 7, bearerToken.length() );
        }
        return null;
    }

}
