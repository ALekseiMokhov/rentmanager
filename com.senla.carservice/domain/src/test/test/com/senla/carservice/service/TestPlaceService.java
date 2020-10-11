package com.senla.carservice.service;

import com.senla.carservice.dto.PlaceDto;
import com.senla.carservice.dto.mappers.PlaceMapper;
import com.senla.carservice.entity.garage.Place;
import com.senla.carservice.repository.interfaces.IGenericRepository;
import com.senla.carservice.repository.jpa.PlaceJpaRepository;
import com.senla.carservice.util.calendar.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestPlaceService {
    private final IGenericRepository mockRepo
            = Mockito.mock(PlaceJpaRepository.class);
    @InjectMocks
    private PlaceService placeService;
    @Mock
    private PlaceMapper mapper;
    private PlaceDto placeDto;
    private Place place;
    private UUID id;

    @BeforeEach
    public void init() {
        place = new Place(new Calendar());
        id = UUID.randomUUID();
        place.setId(id);
        placeDto = this.mapper.placeToDto(place);

        when(mockRepo.getById(id)).thenReturn(place);

    }


    @Test
    void givenNumberOfPlacesShouldBeAdded() {
        /*when*/
        placeService.addPlaces(22);
        /*then*/
        verify(mockRepo, times(22)).save(any(Place.class));
    }

    @Test
    void givenDateShouldReturnFreePlaceDto() {
        /*when*/
        placeService.getFreePlaceDtoForDate(LocalDate.now());
        /*then*/
        verify(mockRepo, times(1)).findAll();
    }

    @Test
    void givenIdANdDateShouldReturnIsPlaceBooked() {
        /*given*/
        when(mockRepo.getById(id)).thenReturn(place);
        /*when*/
        Boolean res = placeService.isPlaceSetForDate(id, LocalDate.now());
        /*then*/
        Assertions.assertEquals(false, res);
    }

    @Test
    void givenDateShouldSetPlaceForTheDate() {
        this.placeService.setPlaceForDate(id, LocalDate.now());

        Assertions.assertEquals(
                true, this.place.getCalendar().isDateBooked(LocalDate.now()));

    }

    @Test
    void givenDateShouldSetPlaceFree() {
        LocalDate date = LocalDate.now();
        place.getCalendar().setDateForBooking(date);
        this.placeService.setPlaceFree(id, date);


        Assertions.assertEquals(false, this.place.getCalendar().isDateBooked(date));

    }

    @Test
    void shouldReturnTrueIfPlaceIsPresent() {
        Boolean result = this.placeService.isPresent(id);

        Assertions.assertEquals(true, result);

    }

    @Test
    void verifyRepositorySavePlace() {
        when(mapper.dtoToPlace(placeDto)).thenReturn(place);
        this.placeService.savePlace(placeDto);

        verify(mockRepo, times(1)).update(place);
    }


    @Test
    void shouldThrowExceptionIfNoFreePlacesForTheDate() {
        this.placeService.setPlaceForDate(id, LocalDate.now());

        Assertions.assertThrows(RuntimeException.class, () -> this.placeService.getFreePlaceDto(LocalDate.now()));

    }


    @Test
    void verifyRepositoryDeletePlace() {
        this.placeService.deletePlace(id);

        verify(mockRepo, times(1)).delete(id);
    }


}
