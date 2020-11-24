package ru.rambler.alexeimohov.controller;

import com.vividsolutions.jts.io.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.RentPointDto;
import ru.rambler.alexeimohov.service.interfaces.IRentPointService;

import java.util.List;

@RestController
@RequestMapping
public class RentPointController {

    private IRentPointService rentPointService;

    public RentPointController(IRentPointService rentPointService) {
        this.rentPointService = rentPointService;
    }

    @PostMapping("/")
    public ResponseEntity createRentPoint(@RequestBody RentPointDto dto) throws ParseException {
        rentPointService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.CREATED ) ;
    }

    @GetMapping("/{id}")
    public RentPointDto getById(@PathVariable long id){
              return rentPointService.getById( id ) ;
    }
    @GetMapping("/{x}/{y}")
    public RentPointDto getByCoordinate(@PathVariable double x,double y){
              return rentPointService.getByCoordinate( x ,y) ;
    }
    @PutMapping("/")
    public ResponseEntity updateRentPoint(@RequestBody RentPointDto dto) throws ParseException {
        rentPointService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.OK ) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRentPoint(@PathVariable long id) throws ParseException {
        rentPointService.remove( id );
        return new ResponseEntity( HttpStatus.OK ) ;
    }

    @GetMapping("/")
    public List <RentPointDto> getAllRentPoints(){
        return rentPointService.getAll();
    }

    @GetMapping("/sort")
    public List <RentPointDto> getPointsByValue(){
        return rentPointService.getPointsByValue();
    }

}
