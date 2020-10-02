package com.senla.carservice.dto;

import com.senla.carservice.entity.order.OrderStatus;
import lombok.Data;

import javax.persistence.Enumerated;
import javax.persistence.Version;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class OrderDto {
    private String id;
    private String status;
    private String dateBooked;
    private String startOfExecution;
    private String finishOfExecution;
}
