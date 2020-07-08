package com.senla.carservice.domain.service;

import dependency.injection.annotations.components.Component;

@Component
public interface IConfigService {
    void loadDefaultProps();

    void loadCustomProps(String propName);

    void write(String k, String v);

    String get(String s);
}
