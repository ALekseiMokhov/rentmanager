import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.service.interfaces.IOrderService;
import ru.rambler.alexeimohov.service.interfaces.IUserService;
import ru.rambler.alexeimohov.service.interfaces.IVehicleService;
import ru.rambler.alexeimohov.spring.PersistenceConfig;

import java.time.LocalDateTime;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext( new String[]{ "ru.rambler.alexeimohov" } );
        ((AnnotationConfigApplicationContext) context).register( PersistenceConfig.class );
        /*Arrays.stream( context.getBeanDefinitionNames() ).forEach( log::info );*/

        IOrderService service = context.getBean( IOrderService.class );
        CardDto cardDto = new CardDto();
        cardDto.setAvailableFunds( "8000" );
        cardDto.setBlockedFunds( "1000" );
        cardDto.setCreditCardNumber( "4319094710988297" );

        UserDto userDto = new UserDto();
        userDto.setFullName( "Uasya" );
        userDto.setEmail( "madfrogNS@yandex.ru" );
        userDto.setPhoneNumber( "223344" );



        IUserService userService = context.getBean( IUserService.class );
        userService.saveOrUpdate( userDto );
        userService.addCreditCard( 1, cardDto );


        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setRentPrice( "0.023" );
        IVehicleService vehicleService =  context.getBean( IVehicleService.class );
        vehicleService.saveOrUpdate( vehicleDto );

        OrderDto dto = new OrderDto();
        dto.setCreditCardNumber( "4319094710988297" );
        dto.setVehicleDto( vehicleService.getById( 1l ) );
        dto.setUserDto( userService.getById( 1l ) );
        dto.setStartTime( String.valueOf( LocalDateTime.of( 2020, 10, 10, 1, 1, 1, 1 ) ) );
        service.saveOrUpdate( dto);

        service.finish( 1l );

    }
}
