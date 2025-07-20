package com.udea.airlineskbt.controller;

import com.udea.airlineskbt.exception.InvalidRating;
import com.udea.airlineskbt.exception.ModelNotFoundException;
import com.udea.airlineskbt.model.Flight;
import com.udea.airlineskbt.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
@CrossOrigin("*")
public class FlightController {
    @Autowired
    FlightService flightService;

    @PostMapping("/save")
    public long save(@RequestBody Flight flight) throws InvalidRating {
        if (flight.getRating() > 5) {
            throw new InvalidRating("Id should be less than or equal 5");
        }
        flightService.save(flight);
        return flight.getIdFlight();
    }

    @GetMapping("/listAll")
    public Iterable<Flight> listAllFlights(){
        return flightService.list();
    }

    @GetMapping("/list/{id}")
    public Flight listFlightById(@PathVariable("id") long id){
        Optional<Flight> flight = flightService.listId(id);
        if (flight.isPresent()){
            return flight.get();
        }
        throw new ModelNotFoundException("ID the Flight invalid");
    }

    @GetMapping("/topFlights")
    public ResponseEntity<List<Flight>> viewBestFlights() throws RuntimeException {
        List<Flight> list = flightService.viewBestFlight();
        return ResponseEntity.ok(list);
    }

    @PutMapping
    public Flight updateFlight(@RequestBody Flight flight) throws RuntimeException {
        return flightService.update(flight);
    }

    @DeleteMapping("/{id}")
    public String deleteFlight(@PathVariable("id") long id){
        return flightService.delete(id);
    }
}
