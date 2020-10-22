package com.senla.carservice.dto.mappers.interfaces;


import com.senla.carservice.dto.MasterDto;
import com.senla.carservice.dto.mappers.SpecialityMapper;
import com.senla.carservice.dto.mappers.UuidMapper;
import com.senla.carservice.entity.master.Master;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {UuidMapper.class, SpecialityMapper.class}, componentModel = "spring")

public interface MasterMapper {
    /*MasterMapper INSTANCE = Mappers.getMapper(MasterMapper.class);*/

    MasterDto masterToDto(Master master);

    /*reverse mapping*/
    Master masterFromDto(MasterDto masterDto);


    List<MasterDto> mastersToDto(List<Master> masters);

    List<Master> dtoToMasters(List<MasterDto> masterdto);


}
