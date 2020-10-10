package com.senla.carservice.dto.mappers;


import com.senla.carservice.dto.MasterDto;
import com.senla.carservice.entity.master.Master;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UuidMapper.class, SpecialityMapper.class})

public interface MasterMapper {
    MasterMapper INSTANCE = Mappers.getMapper(MasterMapper.class);

    MasterDto masterToDto(Master master);

    /*reverse mapping*/
    Master masterFromDto(MasterDto masterDto);




}
