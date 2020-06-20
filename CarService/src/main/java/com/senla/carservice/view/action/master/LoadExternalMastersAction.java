package com.senla.carservice.view.action.master;

import com.senla.carservice.domain.entities.master.Speciality;
import util.Calendar;
import util.csv.CsvMasterParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LoadExternalMastersAction extends AbstractMasterAction {
    @Override
    public void execute() {
        try {
            List <String> intermediateList = CsvMasterParser.load();

            List <List <String>> res = intermediateList.stream()
                    .map( s -> Arrays.asList( s.split( "," ) ) )
                    .collect( Collectors.toList() );

            for (List <String> list : res) {
                controller.addMaster(
                        list.get( 0 ), Double.parseDouble( list.get( 1 ) ), new Calendar()
                        , Speciality.valueOf( list.get( 2 ) ), UUID.fromString( list.get( 3 ) ) );
            }
            System.out.println(res.size()+ " masters were loaded from file!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
