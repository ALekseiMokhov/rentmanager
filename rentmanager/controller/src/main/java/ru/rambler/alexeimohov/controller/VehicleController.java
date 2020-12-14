package ru.rambler.alexeimohov.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.service.interfaces.IVehicleService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/*
 * Controller @linked to IVehicleService
 * */
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private IVehicleService vehicleService;

    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @PostMapping("/")
    public ResponseEntity createVehicle(@Valid @RequestBody VehicleDto dto) {
        vehicleService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @GetMapping("/{id}")
    public VehicleDto getById(@PathVariable long id) {
        return vehicleService.getById( id );

    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @PutMapping("/")
    public ResponseEntity updateVehicle(@Valid @RequestBody VehicleDto dto) {
        vehicleService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.OK );
    }

    @PatchMapping("/book/{id}/{date}")
    public ResponseEntity bookVehicle(@PathVariable long id, @PathVariable String date) {
        vehicleService.setDateForBooking( id, LocalDate.parse( date ) );
        return new ResponseEntity( HttpStatus.OK );

    }

    @PatchMapping("/cancel/{id}/{date}")
    public ResponseEntity cancelBooking(@PathVariable long id, @PathVariable String date) {
        vehicleService.cancelBooking( id, LocalDate.parse( date ) );
        return new ResponseEntity( HttpStatus.OK );

    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
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
    public List <VehicleDto> get(@PathVariable long id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date) {
        return vehicleService.getAllFreeFromPoint( id, date );

    }
    @GetMapping("/dates/{id}/")
    public Set <String> getDates(@PathVariable long id) {
        return vehicleService.getBookedDatesOfVehicle( id );

    }

}
