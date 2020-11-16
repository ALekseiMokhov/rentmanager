package ru.rambler.alexeimohov.dto.mappers.interfaces;

import java.util.List;

public interface GenericMapper<S, DTO> {

    DTO toDto(S s);

    S fromDto(DTO dto);

    List <S> listFromDto(List <DTO> dto);

    List <DTO> listToDto(List <S> entities);
}
