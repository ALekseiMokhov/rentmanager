package ru.rambler.alexeimohov.controller;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.service.interfaces.IOrderService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{startDateTime}/{cardNumber}")
    public ResponseEntity createOrder(@PathVariable LocalDateTime startDateTime, @CreditCardNumber @PathVariable long cardNumber,
                                      @Valid @RequestBody UserDto userDto, @Valid @RequestBody VehicleDto vehicleDto) {
        OrderDto created = new OrderDto();
        created.setUserDto( userDto );
        created.setHasValidSubscription( userDto.getHasValidSubscription() );
        created.setStartTime( String.valueOf( startDateTime ) );
        created.setVehicleDto( vehicleDto );
        created.setCreditCardNumber( String.valueOf( cardNumber ) );
        created.setBlockedFunds( vehicleDto.getRentPrice() );

        orderService.saveOrUpdate( created );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable long id) {
        return orderService.getById( id );
    }

    @PutMapping("/")
    public ResponseEntity updateOrder(@RequestBody OrderDto dto) {
        orderService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.OK );
    }


    @PutMapping("/finish/{id}")
    public ResponseEntity finishOrder(@PathVariable long id) {
        orderService.finish( id );
        return new ResponseEntity( HttpStatus.OK );
    }


    @PutMapping("/cancel/{id}")
    public ResponseEntity cancelOrder(@PathVariable long id) {
        orderService.cancel( id );
        return new ResponseEntity( HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable long id) {
        orderService.remove( id );
        return new ResponseEntity( HttpStatus.OK );
    }

    @GetMapping("/")
    public List <OrderDto> getAllOrders() {
        return orderService.getAll();
    }
}
