package com.senla.carservice.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String id;
    private String status;

    private String dateBooked;

    private String startOfExecution;
    private String finishOfExecution;
}
