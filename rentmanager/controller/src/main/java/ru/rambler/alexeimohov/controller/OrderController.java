package ru.rambler.alexeimohov.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.service.interfaces.IOrderService;

import java.util.List;
 /*
 *
 * Controller @linked to IOrderService
 * @method getAll filters orders for authenticated users showing them their order dto's only*/
@RestController
@Slf4j
@RequestMapping("/orders")
public class OrderController {

    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrUpdate( orderDto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    @PreAuthorize( "hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')" )
    public OrderDto getById(@PathVariable long id) {
        return orderService.getById( id );
    }

    @PutMapping("/")
    @PreAuthorize( "hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')" )
    public ResponseEntity updateOrder(@RequestBody OrderDto dto) {
        orderService.saveOrUpdate( dto );
        log.debug( "order been saved! User: " + dto.getUserDto().getUsername() );
        return new ResponseEntity( HttpStatus.OK );
    }


    @PutMapping("/finish/{id}")
    @PreAuthorize( "hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')" )
    public ResponseEntity finishOrder(@PathVariable long id) {
        orderService.finish( id );
        return new ResponseEntity( HttpStatus.OK );
    }


    @PutMapping("/cancel/{id}")
    @PreAuthorize( "hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')" )
    public ResponseEntity cancelOrder(@PathVariable long id) {
        orderService.cancel( id );
        return new ResponseEntity( HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize( "hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')" )
    public ResponseEntity deleteOrder(@PathVariable long id) {
        orderService.remove( id );
        return new ResponseEntity( HttpStatus.OK );
    }

    @GetMapping("/")
    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.userDto.username == authentication.principal.username")
    public List <OrderDto> getAllOrders() {
        return orderService.getAll();
    }
}
