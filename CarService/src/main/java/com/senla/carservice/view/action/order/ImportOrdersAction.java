package com.senla.carservice.view.action.order;

import com.senla.carservice.domain.entities.garage.Place;
import com.senla.carservice.domain.entities.master.IMaster;
import com.senla.carservice.domain.entities.order.Order;
import util.Calendar;
import util.csv.CsvOrderParser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ImportOrdersAction extends AbstractOrderAction {
    @Override
    public void execute() {
        try {
            List <Order> orderList = CsvOrderParser.load();
            for (Order order : orderList) {
                controller.loadOrder( order );
            }

       /*     List <List <String>> res = intermediateList.stream()
                    .map( s -> Arrays.asList( s.split( "," ) ) )
                    .collect( Collectors.toList() );

            for (List <String> list : res) {
                controller.addOrder( LocalDate.parse( list.get( 0 ) ), LocalDate.parse( list.get( 1 ) )
                        , iMasterFormat(list.subList( 3,list.size() ) ),controller., UUID.fromString( list.get( 2 ) ) );
            }
            System.out.println( res.size() + " masters were loaded from file!" );
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        } catch (IOException e) {
            e.printStackTrace();
        }

 /*   private List <IMaster> iMasterFormat(List<String>input) {
        List <IMaster> res = new ArrayList <>();
        input.substring( 1, input.length() - 1 );
        System.out.println( input );
controller.addMaster(
                list.get( 0 ), Double.parseDouble( list.get( 1 ) ), new Calendar()
                , Speciality.valueOf( list.get( 2 ) ), UUID.fromString( list.get( 3 ) ) );

        return res;
    }*/
    }
}
