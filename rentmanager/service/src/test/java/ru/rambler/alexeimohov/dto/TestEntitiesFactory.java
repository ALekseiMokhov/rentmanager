package ru.rambler.alexeimohov.dto;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import ru.rambler.alexeimohov.entities.*;
import ru.rambler.alexeimohov.entities.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
Utility class to populate test entities*/
public class TestEntitiesFactory {
    static User getUser() {
        User user = new User();
        user.setId(547469l);
        user.setUsername("Sergei");
        user.setPassword("tu4hd845cY23L");
        user.setSubscription(new Subscription(1l, user, 22.9, false, LocalDate.now(), LocalDate.now(),
                LocalDate.of(2033, 11, 16)));
        user.setHasValidSubscription(true);
        user.setPrivilege(Privilege.EXCLUSIVE);
        user.setRole(Role.ROLE_USER);
        user.addMessage(new Message(1l, "Hi there!", user, LocalDateTime.now()));
        user.addMessage(new Message(2l, "Second mssage", user, LocalDateTime.now()));
        user.addMessage(new Message(3l, "The last message", user, LocalDateTime.now()));
        return user;
    }

    static UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId("2");
        userDto.setUsername("Alexander");
        userDto.setPassword("4f837t873T");
        userDto.setPhoneNumber("89192223341");
        userDto.setPrivilege("NEWBIE");
        userDto.setRole("ROLE_USER");
        userDto.setEmail("Alex@gmail.com");
        return userDto;
    }

    static Vehicle getVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle = new Vehicle();
        vehicle.setId(1l);
        vehicle.setType(VehicleType.SCOOTER);
        vehicle.setIsChildish(true);
        vehicle.setIsHumanPowered(true);
        vehicle.setRentPrice(13.4);
        vehicle.setFinePrice(12.0);
        vehicle.setModelName("S1");
        vehicle.setRentPoint(getRentPoint());
        return vehicle;
    }

    static VehicleDto getVehicleDto() {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setIsChildish("true");
        vehicleDto.setModelName("S1");
        vehicleDto.setRentPrice("3.5");
        vehicleDto.setFinePrice("22.9");
        vehicleDto.setIsHumanPowered("true");
        vehicleDto.setType("SCOOTER");
        vehicleDto.setId("43");
        vehicleDto.setRentPoint(getRentPointDto());
        return vehicleDto;
    }

    static Subscription getSubscription() {
        Subscription subscription = new Subscription();
        subscription.setId(223l);
        subscription.setPrice(43.9);
        subscription.setStartDate(LocalDate.now());
        subscription.setExpirationDate(LocalDate.of(2040, 12, 31));
        subscription.setUser(getUser());
        return subscription;
    }

    static SubscriptionDto getSubscriptionDto() {
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setId("46363");
        subscriptionDto.setUser(getUserDto());
        subscriptionDto.setStartDate("2020-10-10");
        subscriptionDto.setExpirationDate("2040-12-31");
        subscriptionDto.setPrice("54.0");
        return subscriptionDto;
    }

    static RentPoint getRentPoint() {
        RentPoint rentPoint = new RentPoint();
        rentPoint = new RentPoint();
        rentPoint.setId(856l);
        rentPoint.setPointName("Main point");
        rentPoint.setType(PointType.SECOND_LINE);
        rentPoint.setCoordinate(new GeometryFactory().createPoint(new Coordinate(334, 58)));

        return rentPoint;
    }

    static RentPointDto getRentPointDto() {
        RentPointDto rentPointDto = new RentPointDto();
        rentPointDto = new RentPointDto();
        rentPointDto.setId("576474");
        rentPointDto.setPointName("Susan McCassey");
        rentPointDto.setCoordinate("POINT (222 111)");
        return rentPointDto;
    }

    static Order getOrder() {
        Order order = new Order();
        order.setId(3856856l);
        order.setVehicle(getVehicle());
        order.setHasValidSubscription(true);
        order.setUser(getUser());
        order.setBlockedFunds(4.0);
        order.setStatus(OrderStatus.IN_RENT);
        return order;
    }

    static OrderDto getOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId("29572");
        orderDto.setBlockedFunds("23.8");
        orderDto.setCreditCardNumber("5555982510856625");
        orderDto.setUserDto(getUserDto());
        orderDto.setCreationTime("2018-12-30T19:34:50.63");
        orderDto.setHasValidSubscription("true");
        orderDto.setVehicleDto(getVehicleDto());
        return orderDto;
    }

    static Message getMessage() {
        Message message = new Message();
        message.setId(7475l);
        message.setText("Hi there!");
        message.setUser(getUser());
        return message;
    }

    static MessageDto getMessageDto() {
        MessageDto messageDto = new MessageDto();
        messageDto.setId("2352");
        messageDto.setDateTimeOfSending(String.valueOf(LocalDateTime.now()));
        messageDto.setText("Hi again there!It's longer now!");
        messageDto.setUserId(getUserDto().getId());
        return messageDto;
    }

    static Card getCard() {
        Card card = new Card();
        card.setId(345l);
        card.setAvailableFunds(3333.4);
        card.setCreditCardNumber(4444_4444_4444_4444l);
        card.setUser(getUser());
        card.setExpirationDate(LocalDate.of(2040, 12, 31));
        return card;

    }

    static CardDto getCardDto() {
        CardDto cardDto = new CardDto();
        cardDto.setId("325");
        cardDto.setUserId(String.valueOf(getUser().getId()));
        cardDto.setCreditCardNumber("1111111111111111");
        cardDto.setAvailableFunds("232");
        cardDto.setExpirationDate("2040-12-31");
        return cardDto;
    }

    static Address getAddress() {
        Address address = new Address();
        address.setId(1l);
        address.setRentPoint(getRentPoint());
        address.setCity("Moscow");
        address.setStreet("Tverskaya");
        address.setBuildingNumber(232);
        return address;
    }

    static AddressDto getAddressDto() {
        AddressDto addressDto = new AddressDto();
        addressDto.setId("693");
        addressDto.setCity("Oryol");
        addressDto.setStreet("Lenina");
        addressDto.setBuildingNumber("1");
        return addressDto;
    }


}
