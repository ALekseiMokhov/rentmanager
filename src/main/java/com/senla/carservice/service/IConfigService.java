package com.senla.carservice.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("configService")
public interface IConfigService {
    void loadDefaultProps();

    void loadCustomProps(String propName);

    void write(String k, String v);

    String get(String s);
}
