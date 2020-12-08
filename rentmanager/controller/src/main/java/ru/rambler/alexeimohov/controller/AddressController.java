package ru.rambler.alexeimohov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.AddressDto;
import ru.rambler.alexeimohov.service.interfaces.IAddressService;

import javax.validation.Valid;
import java.util.List;
 /*
 * Controller @linked to IAddressService.
 * @linked to RentPointController.
 * Persists and updates both Address and RentPoint entities.
 * @see ru.rambler.alexeimohov.entities.Address in domain module */
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private IAddressService addressService;

    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/")
    public ResponseEntity saveAdress(@Valid @RequestBody AddressDto dto) {
        addressService.saveOrUpdate( dto );
        return new ResponseEntity( "Address saved", HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    public AddressDto getAddressById(@PathVariable long id) {
        return addressService.getById( id );
    }

    @GetMapping("/point/{id}")
    public AddressDto getAddressByPointId(@PathVariable long id) {
        return addressService.getAddressByPointId( id );
    }

    @PutMapping("/")
    public ResponseEntity getAddressById(@Validated @RequestBody AddressDto dto) {
        addressService.saveOrUpdate( dto );
        return new ResponseEntity( "Address updated", HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress(@PathVariable long id) {
        addressService.remove( id );
        return new ResponseEntity( "Address removed " + id, HttpStatus.OK );
    }

    @GetMapping("/")
    public List <AddressDto> getAll() {
        return addressService.getAll();
    }

    @GetMapping("/from/{city}")
    public List <AddressDto> getAllByCity(@PathVariable String city) {
        return addressService.getAddressesByCity( city );
    }
}
