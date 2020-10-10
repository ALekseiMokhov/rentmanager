package com.senla.carservice.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TestOrderService {
/*
    private final IGenericRepository mockRepo =
            Mockito.mock(OrderJpaRepository.class);
    @Mock
    private MasterService mockMasterService;
    @Mock
    private PlaceService mockPlaceService;
    @Mock
    private PlaceMapper placeMapper;
    @Mock
    private MasterMapper masterMapper;
    @InjectMocks
    private OrderService orderService;

    private Place place;
    private Master reshaper;
    private Set<Speciality> required;
    private UUID id;
    private UUID idExp;

    @BeforeEach
    void init() {

        place = new Place(new Calendar());
        reshaper = new Master("Sergei", 3.6, new Calendar(), Speciality.RESHAPER);
        Master painter = new Master("Andrew", 22, new Calendar(), Speciality.PAINTER);
        List<Master> masterList = new ArrayList<>();
        masterList.add(reshaper);

        List<Order> orderList = new ArrayList<>();
        required = new HashSet<>();
        required.add(Speciality.RESHAPER);

        Order testOrder = new Order(LocalDate.now(), LocalDate.of(2020, 9, 23), place, masterList);
        id = testOrder.getId();

        List<Master> expensiveMasters = new ArrayList<>();
        expensiveMasters.add(reshaper);
        expensiveMasters.add(painter);

        Order expensiveOrder = new Order(LocalDate.now(), LocalDate.of(2040, 4, 21), place, expensiveMasters);
        idExp = expensiveOrder.getId();

        orderList.add(expensiveOrder);
        orderList.add(testOrder);

        doNothing().when(mockRepo).save(any(Order.class));
        when(mockRepo.getById(id)).thenReturn(testOrder);
        when(mockRepo.getById(idExp)).thenReturn(expensiveOrder);
        when(mockRepo.findAll()).thenReturn(orderList);

    }

    @Test
    void verifyRepositoryAddOrderAndServicesSetData() {
        when(mockPlaceService.getFreePlace(any(LocalDate.class))).thenReturn(place);
        when(mockMasterService.getFreeBySpeciality(any(LocalDate.class), any(Speciality.class))).thenReturn(reshaper);

        this.orderService.addOrder(LocalDate.now(), LocalDate.of(2020, 9, 24), required);

        verify(mockRepo, times(1)).save(any(Order.class));
        verify(mockPlaceService, times(1)).getFreePlace(LocalDate.of(2020, 9, 24));
        verify(mockMasterService, times(required.size())).getFreeBySpeciality(any(LocalDate.class), any(Speciality.class));
    }


    @Test
    void verifyRepositoryDeleteOrder() {
        this.orderService.deleteOrder(id);

        verify(mockRepo, times(1)).delete(id);
    }

    @Test
    void givenDateAndIdShouldSetNewDateOfExecution() {
        when(mockPlaceService.getFreePlace(any(LocalDate.class))).thenReturn(place);
        when(mockMasterService.getFreeBySpeciality(any(LocalDate.class), any(Speciality.class))).thenReturn(reshaper);
        doNothing().when(mockMasterService).setMasterForDate(any(UUID.class), any(LocalDate.class));
        InOrder inOrder = inOrder(mockPlaceService, mockMasterService, mockRepo);

        LocalDate newDate = LocalDate.of(2030, 12, 31);
        this.orderService.shiftOrderExecutionDate(id, newDate);

        verify(mockPlaceService, times(1)).getFreePlace(newDate);
        verify(mockMasterService, times(required.size())).getFreeBySpeciality(newDate, Speciality.RESHAPER);

        inOrder.verify(mockRepo).getById(id);
        inOrder.verify(mockPlaceService).getFreePlace(newDate);
        inOrder.verify(mockMasterService).getFreeBySpeciality(newDate, Speciality.RESHAPER);
        inOrder.verify(mockMasterService).setMasterForDate(any(UUID.class), any(LocalDate.class));

        Assertions.assertEquals(this.orderService.findOrderById(id).getStartOfExecution(), newDate);
    }

    @Test
    void shouldSetNewMasters() {
        when(mockMasterService.getFreeBySpeciality(any(LocalDate.class), any(Speciality.class))).thenReturn(reshaper);
        InOrder inOrder = inOrder(mockMasterService, mockRepo);

        this.orderService.setNewMasters(id);

        inOrder.verify(mockRepo).getById(id);
        inOrder.verify(mockMasterService, times(required.size())).getFreeBySpeciality(any(LocalDate.class), any(Speciality.class));
    }

    @Test
    void shouldChangeStatusToCancelled() {
        this.orderService.cancelOrder(id);

        Assertions.assertEquals(this.orderService.findOrderById(id).getStatus(), OrderStatus.CANCELLED);
    }

    @Test
    void shouldChangeStatusToCompleted() {
        this.orderService.completeOrder(id);

        Assertions.assertNotEquals(this.orderService.findOrderById(id).getStatus(), OrderStatus.CANCELLED);
    }


    @Test
    void shouldGetOrdersSortedByPrice() {
        List<Order> result = this.orderService.getOrdersByPrice(OrderStatus.MANAGED);

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(25.6, result.get(1).getTotalPrice());
    }

    @Test
    void shouldGetOrdersSortedByBookedDate() {
        List<Order> result = this.orderService.getOrdersByBookedDate(OrderStatus.MANAGED);

        Assertions.assertEquals(LocalDate.of(2040, 4, 21), result.get(0).getStartOfExecution());
    }


    @Test
    void givenPeriodShouldGetOrdersForPeriod() {
        this.orderService.completeOrder(id);
        this.orderService.completeOrder(idExp);
        List<Order> result = this.orderService.getOrdersForPeriod(LocalDate.of(2020, 1, 1), LocalDate.of(2100, 1, 1));

        Assertions.assertEquals(2, result.size());
    }*/
}
