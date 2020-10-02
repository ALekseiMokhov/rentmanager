package com.senla.carservice.dto.mappers;


import com.senla.carservice.dto.*;
import com.senla.carservice.entity.master.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses ={ UuidMapper.class,SpecialityMapper.class})

public interface MasterMapper {
  public   MasterMapper INSTANCE = Mappers.getMapper(MasterMapper.class);

     MechanicDto mechanicToDto(Mechanic mechanic);
     ElectricianDto electricianToDto(Electrician electrician);
     ReshaperDto reshaperToDto(Reshaper reshaper);
     PainterDto painterToDto(Painter painter);

     Mechanic mechanicFromDto(MechanicDto mechanicDto);
     Electrician electricianFromDto(ElectricianDto electricianDto);
     Reshaper reshaperFromDto(ReshaperDto reshaperDto);
     Painter painterFromDto(PainterDto painterDto);

}
