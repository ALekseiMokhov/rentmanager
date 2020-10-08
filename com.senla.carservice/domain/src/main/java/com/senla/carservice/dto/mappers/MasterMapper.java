package com.senla.carservice.dto.mappers;


import com.senla.carservice.dto.*;
import com.senla.carservice.dto.ElectricianMasterDto;
import com.senla.carservice.dto.ReshaperMasterDto;
import com.senla.carservice.entity.master.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UuidMapper.class, SpecialityMapper.class})

public interface MasterMapper {
    MasterMapper INSTANCE = Mappers.getMapper(MasterMapper.class);

    MechanicMasterDto mechanicToDto(Mechanic mechanic);

    ElectricianMasterDto electricianToDto(Electrician electrician);

    ReshaperMasterDto reshaperToDto(Reshaper reshaper);

    PainterMasterDto painterToDto(Painter painter);

    /*reverse mapping*/
    Mechanic mechanicFromDto(MechanicMasterDto mechanicDto);

    Electrician electricianFromDto(ElectricianMasterDto electricianDto);

    Reshaper reshaperFromDto(ReshaperMasterDto reshaperDto);

    Painter painterFromDto(PainterMasterDto painterDto);



}
