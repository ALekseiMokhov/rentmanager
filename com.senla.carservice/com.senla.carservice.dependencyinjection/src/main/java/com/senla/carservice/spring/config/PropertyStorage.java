package com.senla.carservice.spring.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class PropertyStorage {

    @Value("#{new Boolean('${garage.admin.mode}')}")
    private Boolean isGarageModificationPermitted;
    @Value("#{new Boolean('${master.admin.mode}')}")
    private Boolean isMasterModificationPermitted;
    @Value("#{new Boolean('${order.admin.mode}')}")
    private Boolean isOrderModificationPermitted;
    @Value("#{new Boolean('${json.admin.mode}')}")
    private Boolean isJsonPersistenceAllowed;



}
