package com.senla.carservice.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public interface IMenuController {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

    void run() throws IOException;
}
