package com.senla.carservice.service;

import com.senla.carservice.dto.PlaceDto;
import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.repository.jpa.PlaceJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestPlaceService {
    private final IGenericRepository mockRepo
            = Mockito.mock(PlaceJpaRepository.class);
    @InjectMocks
    private PlaceService placeService;
    private PlaceDto placeDto = new PlaceDto();
    private UUID id;

    @BeforeEach
    public void init() {
        placeDto = new PlaceDto();
        placeDto.setId(String.valueOf(UUID.randomUUID()));
        id = UUID.fromString(placeDto.getId());
        when(mockRepo.getById(id)).thenReturn(placeDto);
    }


    @Test()
    void shouldGetAllPlaceDto() {
        List<PlaceDto> list = new ArrayList<>();
        when(placeService.getPlaces()).thenReturn(list);

        Assertions.assertEquals(list, placeService.getPlaces());
    }

    @Test
    void givenNumberOfPlacesShouldBeAdded() {
        placeService.addPlaces(22);

        verify(mockRepo, times(22)).save(any(Place.class));
    }

    @Test
    void givenDateShouldReturnFreePlaces() {
        List<Place> list = new ArrayList<>();
        when(mockRepo.findAll()).thenReturn(list);

        placeService.getFreePlacesForDate(LocalDate.now());

        verify(mockRepo, times(1)).findAll();
    }

    @Test
    void givenIdANdDateShouldReturnIsPlaceBooked() {
        when(mockRepo.getById(id)).thenReturn(placeDto);
        Boolean res = placeService.isPlaceSetForDate(id, LocalDate.now());

        Assertions.assertEquals(false, res);
    }

    @Test
    void givenDateShouldSetPlaceForTheDate() {
        when(mockRepo.getById(id)).thenReturn(placeDto);
        placeService.setPlaceForDate(id, LocalDate.now());

        Assertions.assertEquals(
                true, placeService.getPlaceById(id).getCalendar().isDateBooked(LocalDate.now()));

    }

    @Test
    void givenDateShouldSetPlaceFree() {
        LocalDate date = LocalDate.now();
        this.placeService.setPlaceForDate(id, date);

        Assertions.assertEquals(true, placeService.getPlaceById(id).getCalendar().isDateBooked(LocalDate.now()));

    }

    @Test
    void shouldReturnTrueIfPlaceIsPresent() {
        Boolean result = this.placeService.isPresent(id);

        Assertions.assertEquals(true, result);

    }

    @Test
    void verifyRepositorySavePlace() {
        this.placeService.savePlace(placeDto);

        verify(mockRepo, times(1)).save(placeDto);
    }


    @Test
    void shouldThrowExceptionIfNoFreePlacesForTheDate() {
        this.placeService.setPlaceForDate(id, LocalDate.now());

        Assertions.assertThrows(RuntimeException.class, () -> this.placeService.getFreePlace(LocalDate.now()));

    }


    @Test
    void verifyRepositoryDeletePlace() {
        this.placeService.deletePlace(id);

        verify(mockRepo, times(1)).delete(id);
    }


}
