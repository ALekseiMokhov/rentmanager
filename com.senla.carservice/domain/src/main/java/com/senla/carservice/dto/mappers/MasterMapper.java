package com.senla.carservice.dto.mappers;


import com.senla.carservice.dto.MasterDto;
import com.senla.carservice.entity.master.Master;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UuidMapper.class, SpecialityMapper.class}, componentModel = "spring")

public interface MasterMapper {
    MasterMapper INSTANCE = Mappers.getMapper(MasterMapper.class);

    MasterDto masterToDto(Master master);

    /*reverse mapping*/
    Master masterFromDto(MasterDto masterDto);


    List<MasterDto> mastersToDto(List<Master> masters);

    List<Master> dtoToMasters(List<MasterDto> masterdto);


}
