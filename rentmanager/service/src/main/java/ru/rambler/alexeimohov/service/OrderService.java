package ru.rambler.alexeimohov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.OrderDao;
import ru.rambler.alexeimohov.dto.OrderDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.OrderMapper;
import ru.rambler.alexeimohov.entities.Order;
import ru.rambler.alexeimohov.service.interfaces.IOrderService;

import java.util.List;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class OrderService implements IOrderService {

    private OrderDao orderDao;

    private OrderMapper orderMapper;

    public OrderService(OrderDao orderDao, OrderMapper orderMapper) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto getById(Long id) {
        return orderMapper.toDto( orderDao.findById( id ) );
    }

    @Override
    public void saveOrUpdate(OrderDto dto) {
        Order order = orderMapper.fromDto( dto );
        if(orderDao.findById( order.getId() )==null){
            orderDao.save( order );
        }
        else {
            orderDao.update( order );
        }
    }

    @Override
    public void remove(Long id) {
       orderDao.remove( id );
    }

    @Override
    public List <OrderDto> getAll() {
        return orderMapper.listToDto( orderDao.findAll() );
    }
}
