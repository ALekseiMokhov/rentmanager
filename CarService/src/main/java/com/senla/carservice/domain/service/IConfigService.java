package com.senla.carservice.domain.service;

import java.io.IOException;

public interface IConfigService {
    void readAll();

    void read(String s);

    void write(String k, String v);

    String get(String s);
}
