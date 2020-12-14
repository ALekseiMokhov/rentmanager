package ru.rambler.alexeimohov.controller;

import com.vividsolutions.jts.io.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.RentPointDto;
import ru.rambler.alexeimohov.service.interfaces.IRentPointService;

import java.util.List;

/*
Controller @linked to IRentPointService
 *  No POST method due to unidirectional association with Address. RentPoint persisted as a field of Address entity*/
@RestController
@RequestMapping("/rentpoints")
public class RentPointController {

    private IRentPointService rentPointService;

    public RentPointController(IRentPointService rentPointService) {
        this.rentPointService = rentPointService;
    }

    @GetMapping("/{id}")
    public RentPointDto getById(@PathVariable long id) {
        return rentPointService.getById( id );
    }

    @GetMapping("/coordinate")
    public RentPointDto getByCoordinate(@RequestParam double x, @RequestParam double y) {
        return rentPointService.getByCoordinate( x, y );
    }

    @PutMapping("/")
    public ResponseEntity updateRentPoint(@RequestBody RentPointDto dto) throws ParseException {
        rentPointService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.OK );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRentPoint(@PathVariable long id) throws ParseException {
        rentPointService.remove( id );
        return new ResponseEntity( HttpStatus.OK );
    }

    @GetMapping("/")
    public List <RentPointDto> getAllRentPoints() {
        return rentPointService.getAll();
    }

    @GetMapping("/sort")
    public List <RentPointDto> getPointsByValue() {
        return rentPointService.getPointsByValue();
    }

}
