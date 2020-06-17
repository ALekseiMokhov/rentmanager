package com.senla.carservice.view.action.order;

import java.io.IOException;
import java.time.LocalDate;

public class GetOrdersForPeriodAction extends AbstractOrderAction {
    private LocalDate start;
    private LocalDate end;

    @Override
    public void execute() {
        System.out.println( "Enter the beginning of period: " );

        try {
            start = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "Enter the end of the period: " );

        try {
            end = LocalDate.parse( reader.readLine() );
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "Orders for the chosen period are: " );
        controller.getOrdersForPeriod( start, end ).stream()
                .forEach( System.out::println );

    }
}
