package ru.rambler.alexeimohov.service;

import com.vividsolutions.jts.io.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.RentPointDao;
import ru.rambler.alexeimohov.dto.RentPointDto;
import ru.rambler.alexeimohov.dto.mappers.RentPointMapper;
import ru.rambler.alexeimohov.entities.RentPoint;
import ru.rambler.alexeimohov.service.interfaces.IRentPointService;
import ru.rambler.alexeimohov.util.PointConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class RentPointService implements IRentPointService {
    RentPointDao rentPointDao;
    RentPointMapper mapper;

    public RentPointService(RentPointDao rentPointDao, RentPointMapper mapper) {
        this.rentPointDao = rentPointDao;
        this.mapper = mapper;
    }

    public void saveOrUpdateRentPoint(RentPointDto dto) throws ParseException {
        if (rentPointDao.getByCoordinate( PointConverter.fromDto( dto.getCoordinate() ) ) != null) {
            rentPointDao.update( mapper.fromDto( dto ) );
        } else {
            rentPointDao.save( mapper.fromDto( dto ) );
        }
    }

    public RentPointDto getById(Long id) {
        return mapper.toDto( rentPointDao.findById( id ) );
    }

    public void removeRentPoint(Long id) {
        rentPointDao.remove( id );
    }

    public List <RentPointDto> getAll() {
        return rentPointDao.findAll().stream()
                .map( p -> mapper.toDto( p ) )
                .collect( Collectors.toList() );
    }

    public List <RentPointDto> getPointsByValue() {
        return rentPointDao.findAll().stream()
                .sorted( RentPoint.pointValueComparator )
                .map( p -> mapper.toDto( p ) )
                .collect( Collectors.toList() );
    }
}
