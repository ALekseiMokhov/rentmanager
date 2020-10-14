package com.senla.carservice.dto.mappers;

import com.senla.carservice.entity.user.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role asRole(String role) {
        return Role.valueOf(role);
    }

    public String asString(Role role) {
        return String.valueOf(role);
    }
}
