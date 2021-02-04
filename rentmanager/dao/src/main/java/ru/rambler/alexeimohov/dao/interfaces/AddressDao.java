package ru.rambler.alexeimohov.dao.interfaces;

import ru.rambler.alexeimohov.entities.Address;

import java.util.List;

public interface AddressDao extends IGenericDao<Address>  {

    List<Address> findAllSortedByCity(String cityName);
}
