import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)

public class AuthorizationTest {


    @Autowired
    FilterChainProxy filterChainProxy;
    @Autowired
    TestService testService;

    @Test
    public void getMessageUnauthenticated() {
        testService.getMessage();
        /*Assertions.assertThrows( AuthenticationException.class,()-> testService.getMessage() );*/
    }

    @Test
    @WithMockUser
    public void getMessageAuthenticated() {

    }

    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    static class ContextConfiguration {

        @Bean
        public TestService testService() {

            return new TestService();
        }

        @Bean
        public FilterChainProxy filterChainProxy() {
            return new FilterChainProxy();
        }
    }
}
