package com.omercangoktas.flight_search_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omercangoktas.flight_search_api.exception.NotFoundException;
import com.omercangoktas.flight_search_api.model.Flight;
import com.omercangoktas.flight_search_api.service.FlightService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/flights")
public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // get all airports using service -> repository
    @GetMapping()
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping(value = "/{flightId}")
    public Flight getFlightById(@PathVariable Long flightId) throws Exception {
        return flightService.getFlightById(flightId);
    }

    // creating new flight
    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @PutMapping(value = "/{flightId}")
    public Flight updateFlight(@PathVariable Long flightId, @RequestBody Flight flight) throws Exception {
        return flightService.updateFlight(flightId, flight);
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightId) {
        try {
            flightService.deleteFlight(flightId);
            return ResponseEntity.ok("Flight with id " + flightId + " deleted successfully.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete flight with id " + flightId);
        }
    }
}
