package com.senla.carservice.dto.mappers;


import com.senla.carservice.dto.ElectricianDto;
import com.senla.carservice.dto.MechanicDto;
import com.senla.carservice.dto.PainterDto;
import com.senla.carservice.dto.ReshaperDto;
import com.senla.carservice.entity.master.Electrician;
import com.senla.carservice.entity.master.Mechanic;
import com.senla.carservice.entity.master.Painter;
import com.senla.carservice.entity.master.Reshaper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UuidMapper.class, SpecialityMapper.class})

public interface MasterMapper {
    MasterMapper INSTANCE = Mappers.getMapper(MasterMapper.class);

    MechanicDto mechanicToDto(Mechanic mechanic);

    ElectricianDto electricianToDto(Electrician electrician);

    ReshaperDto reshaperToDto(Reshaper reshaper);

    PainterDto painterToDto(Painter painter);

    /*reverse mapping*/
    Mechanic mechanicFromDto(MechanicDto mechanicDto);

    Electrician electricianFromDto(ElectricianDto electricianDto);

    Reshaper reshaperFromDto(ReshaperDto reshaperDto);

    Painter painterFromDto(PainterDto painterDto);

}
