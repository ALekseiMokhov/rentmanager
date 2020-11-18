import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.rambler.alexeimohov.dao.interfaces.UserDao;
import ru.rambler.alexeimohov.service.AddressService;
import ru.rambler.alexeimohov.service.interfaces.IAddressService;
import ru.rambler.alexeimohov.spring.PersistenceConfig;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext( new String[]{ "ru.rambler.alexeimohov" } );
        ((AnnotationConfigApplicationContext) context).register( PersistenceConfig.class );
        Arrays.stream( context.getBeanDefinitionNames() ).forEach( System.out::println );
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        IAddressService adressService = context.getBean( IAddressService.class ) ;
        adressService.getAddressesByCity( "Moscow" );

    }
}
