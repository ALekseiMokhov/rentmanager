package com.senla.carservice.domain.service;

import java.io.IOException;

public interface IConfigService {
    void loadDefaultProps();

    void loadCustomProps(String propName);

    void write(String k, String v);

    String get(String s);
}
