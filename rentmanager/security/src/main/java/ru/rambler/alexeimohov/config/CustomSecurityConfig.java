package ru.rambler.alexeimohov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.rambler.alexeimohov.jwt.ITokenProvider;
import ru.rambler.alexeimohov.jwt.JwtAuthenticationFilter;
import ru.rambler.alexeimohov.service.interfaces.IUserService;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.rambler.alexeimohov")
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {
    private ITokenProvider tokenProvider;

    private IUserService userService;

    private AuthenticationEntryPoint unauthorizedHandler;

    public CustomSecurityConfig(ITokenProvider tokenProvider, IUserService userService, AuthenticationEntryPoint unauthorizedHandler) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter( tokenProvider );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )
                .and()
                .exceptionHandling()
                .authenticationEntryPoint( unauthorizedHandler )
                .and()
                .addFilterBefore( jwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class )

                .authorizeRequests()
                .antMatchers( "/", "/users/signup/**", "/users/signin/**" ).permitAll()
                .antMatchers(  "/cards/**", "users/**" ).hasAuthority( "ROLE_ADMIN" )
                .antMatchers( "/rentpoints/**", "/addresses/**","/subscriptions/**", "/vehicles/**", "/messages/**" ).hasAnyAuthority( "ROLE_ADMIN", "ROLE_MANAGER" )
                .antMatchers( "/orders/**" ) .hasAnyAuthority( "ROLE_ADMIN", "ROLE_MANAGER","ROLE_USER" )
                .anyRequest().authenticated()

        ;

    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService( userService )
                .passwordEncoder( passwordEncoder() );
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
