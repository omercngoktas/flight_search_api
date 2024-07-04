package com.omercangoktas.flight_search_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.omercangoktas.flight_search_api.model.Flight;
import com.omercangoktas.flight_search_api.repository.FlightRepository;

@Service
public class FlightService {
    
    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // get all flights from repository
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // get flight by given id
    public Flight getFlightById(Long flightId) throws Exception {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if(optionalFlight.isPresent()) {
            return optionalFlight.get();
        } else {
            throw new Exception("Flight not found with id " + flightId);
        }
    }   

    // create new flight based on given flight
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // update flight with given id and new values
    public Flight updateFlight(Long flightId, Flight flight) throws Exception {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if(optionalFlight.isPresent()) {
            flight.setFlightId(flightId);
            return flightRepository.save(flight);
        } else {
            throw new Exception("Flight not found with id " + flightId);
        }
    }

    // delete flight by given id
    public void deleteFlight(Long flightId) throws Exception {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        if(optionalFlight.isPresent()) {
            flightRepository.deleteById(flightId);
        } else {
            throw new Exception("Flight not found with id " + flightId);
        }
    }

    public List<Flight> searchFlights(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDateTime) {
        return flightRepository.findFlights(departureAirportId, arrivalAirportId, departureDateTime);
    }
}
