package ru.rambler.alexeimohov.service.interfaces;

import com.vividsolutions.jts.io.ParseException;
import org.springframework.stereotype.Service;
import ru.rambler.alexeimohov.dto.RentPointDto;

import java.util.List;

@Service
public interface IRentPointService {

    RentPointDto getById(Long id);

    void saveOrUpdate(RentPointDto dto) throws ParseException;

    List <RentPointDto> getAll();

    List <RentPointDto> getPointsByValue();

    void remove(Long id);

    RentPointDto getByCoordinate(Double x, Double y);


}
