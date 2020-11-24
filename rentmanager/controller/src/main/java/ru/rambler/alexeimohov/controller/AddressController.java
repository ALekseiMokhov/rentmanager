package ru.rambler.alexeimohov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.AddressDto;
import ru.rambler.alexeimohov.service.interfaces.IAddressService;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    private IAddressService addressService;

    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/")
    public ResponseEntity saveAdress(@RequestBody  AddressDto dto){
        addressService.saveOrUpdate( dto );
        return new ResponseEntity("Address saved", HttpStatus.CREATED )  ;
    }

    @GetMapping( "/{id}")
    public AddressDto getAddressById(@PathVariable long id){
      return addressService.getById(id  );
    }
    @PutMapping( "/{id}")
    public ResponseEntity getAddressById(@RequestBody  AddressDto dto){
       addressService.saveOrUpdate(dto  );
       return new ResponseEntity( "Address updated", HttpStatus.OK ) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress (@PathVariable long id){
        addressService.remove( id );
        return new ResponseEntity("Address removed " + id, HttpStatus.OK )  ;
    }

    public List <AddressDto>getAll(){
        return addressService.getAll();
    }
}
