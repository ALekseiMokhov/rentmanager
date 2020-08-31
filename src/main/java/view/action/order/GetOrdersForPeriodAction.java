package view.action.order;

import com.senla.carservice.controller.OrderController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;

public class GetOrdersForPeriodAction extends AbstractOrderAction {
    private LocalDate start;
    private LocalDate end;
    @Autowired
    private OrderController controller;
    @Override
    public void execute() {
        System.out.println( "Enter the beginning of period: " );

        try {
            start = LocalDate.parse( reader.readLine() );
        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "The Date should have proper format 'YYYY-MM-DD'" );
        }


        try {
            System.out.println( "Enter the end of the period: " );
            end = LocalDate.parse( reader.readLine() );

            System.out.println( "Orders for the chosen period are: " );
            controller.getOrdersForPeriod( start, end ).stream()
                    .forEach( System.out::println );

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( "The Date should have proper format 'YYYY-MM-DD'" );
        }

    }
}
