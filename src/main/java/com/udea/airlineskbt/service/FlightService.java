package com.udea.airlineskbt.service;

import com.udea.airlineskbt.dao.IFlightDAO;
import com.udea.airlineskbt.exception.FlightNotFoundException;
import com.udea.airlineskbt.exception.ModelNotFoundException;
import com.udea.airlineskbt.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private IFlightDAO flightDAO;

    public Flight save(Flight f){
        return flightDAO.save(f);
    }

    public String delete(Long id){
        flightDAO.deleteById(id);
        return "Flight deleted";
    }

    public Iterable<Flight> list(){
        return flightDAO.findAll();
    }

    public Optional<Flight> listId(Long id){
        return flightDAO.findById(id);
    }

    public Flight update(Flight f) throws ModelNotFoundException {
        Optional<Flight> existingFlight = flightDAO.findById(f.getIdFlight());
        if(existingFlight.isEmpty()){
            throw new ModelNotFoundException("ID the Flight invalid");
        }
        Flight flight = existingFlight.get();
        flight.setNombreAvion(f.getNombreAvion());
        flight.setNumeroVuelo(f.getNumeroVuelo());
        flight.setOrigen(f.getOrigen());
        flight.setDestino(f.getDestino());
        flight.setCapacidad(f.getCapacidad());
        flight.setRating(f.getRating());
        flight.setPlanvuelo(f.getPlanvuelo());
        flight.setCumplido(f.getCumplido());
        return flightDAO.save(flight);
    }

    public List<Flight> viewBestFlight() throws FlightNotFoundException{
        List<Flight> bestFlights = flightDAO.viewBestFlight();
        if (!bestFlights.isEmpty()) {
            return bestFlights;
        } else {
            throw new FlightNotFoundException("No Flight found with rating >= 4");
        }
    }
}
