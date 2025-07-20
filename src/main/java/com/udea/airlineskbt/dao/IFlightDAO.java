package com.udea.airlineskbt.dao;

import com.udea.airlineskbt.model.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IFlightDAO extends CrudRepository<Flight, Long> {
    @Query("from Flight f where f.rating >= 4 AND f.cumplido = true")
    public List<Flight> viewBestFlight();

}
