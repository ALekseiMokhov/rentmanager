package ru.rambler.alexeimohov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.service.interfaces.IVehicleService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private IVehicleService vehicleService;

    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/")
    public ResponseEntity createVehicle(@Valid @RequestBody VehicleDto dto) {
        vehicleService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    public VehicleDto getById(@PathVariable long id) {
        return vehicleService.getById( id );

    }

    @PutMapping("/")
    public ResponseEntity updateVehicle(@Valid @RequestBody VehicleDto dto) {
        vehicleService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.OK );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteVehicle(@PathVariable long id) {
        vehicleService.remove( id );
        return new ResponseEntity( HttpStatus.OK );
    }

    @GetMapping("/")
    public List <VehicleDto> getAll() {
        return vehicleService.getAll();

    }

    @GetMapping("/childish")
    public List <VehicleDto> getAllChildish() {
        return vehicleService.getAllChildish();

    }

    @GetMapping("/muscular")
    public List <VehicleDto> get() {
        return vehicleService.getAllMuscular();

    }

    @GetMapping("/point/{id}")
    public List <VehicleDto> get(@PathVariable long id) {
        return vehicleService.getAllFromPoint( id );

    }

    @GetMapping("/point/{id}/{date}")
    public List <VehicleDto> get(@PathVariable long id, @PathVariable LocalDate date) {
        return vehicleService.getAllFreeFromPoint( id, date );

    }
}
