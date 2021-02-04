package ru.rambler.alexeimohov.service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.RentPointDao;
import ru.rambler.alexeimohov.dto.RentPointDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.RentPointMapper;
import ru.rambler.alexeimohov.entities.RentPoint;
import ru.rambler.alexeimohov.service.interfaces.IRentPointService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class RentPointService implements IRentPointService {
    @Autowired
    RentPointDao rentPointDao;
    @Autowired
    RentPointMapper mapper;

    public RentPointService(RentPointDao rentPointDao, RentPointMapper mapper) {
        this.rentPointDao = rentPointDao;
        this.mapper = mapper;
    }

    @Transactional(readOnly = false)
    public void saveOrUpdate(RentPointDto dto) throws ParseException {
        RentPoint rentPoint = mapper.fromDto(dto);
        if (rentPoint.getId() == null) {
            rentPointDao.save(rentPoint);
            log.debug("rent point saved " + dto.getPointName());
        } else {
            rentPointDao.update(mapper.fromDto(dto));
            log.debug("rent point updated " + dto.getPointName());
        }
    }

    public RentPointDto getById(Long id) {
        return mapper.toDto(rentPointDao.findById(id));
    }

    @Transactional(readOnly = false)
    public void remove(Long id) {
        rentPointDao.remove(id);
        log.info("rentpoint was deleted: " + id);
    }

    public List<RentPointDto> getAll() {
        return rentPointDao.findAll().stream()
                .map(p -> mapper.toDto(p))
                .collect(Collectors.toList());
    }

    @Override
    public RentPointDto getByCoordinate(Double x, Double y) {
        RentPoint retrieved = rentPointDao.getByCoordinate(new GeometryFactory().createPoint(new Coordinate(x, y)));
        log.debug("Rent point was retrieved by coordinate: " + "(" + x + "," + y + ")");
        return mapper.toDto(retrieved);

    }

    public List<RentPointDto> getPointsByValue() {
        return rentPointDao.findAll().stream()
                .sorted(RentPoint.pointValueComparator)
                .map(p -> mapper.toDto(p))
                .collect(Collectors.toList());
    }
}
