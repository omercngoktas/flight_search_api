package com.omercangoktas.flight_search_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.omercangoktas.flight_search_api.exception.NotFoundException;
import com.omercangoktas.flight_search_api.model.Airport;
import com.omercangoktas.flight_search_api.repository.AirportRepository;
import com.omercangoktas.flight_search_api.repository.FlightRepository;

@Service
public class AirportService {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    public AirportService(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    // return all airports from airportRepository
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    // get airport with given id
    public Airport getAirportById(Long airportId) throws Exception {
        Optional<Airport> airport = airportRepository.findById(airportId);
        if(airport.isPresent()) {
            return airport.get();
        } else {
            throw new Exception("Airport not found with id " + airportId);
        }
    }

    // create new airport
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public void deleteAirportById(Long airportId) {
        // Check if the airport exists
        if (!airportRepository.existsById(airportId)) {
            throw new NotFoundException("Airport not found with id: " + airportId);
        }
        // Check if there are flights referencing this airport
        if (flightRepository.existsByArrivalAirport_AirportId(airportId)) {
            throw new DataIntegrityViolationException("Cannot delete airport as it is referenced by one or more flights.");
        }
        airportRepository.deleteById(airportId);
    }

    // update the airport with given id
    public Airport updateAirport(Long airportId, Airport airport) throws Exception {
        Optional<Airport> optionalAirport = airportRepository.findById(airportId);
        if(optionalAirport.isPresent()) {
            airport.setAirportId(airportId);
            return airportRepository.save(airport);
        } else {
            throw new Exception("Airport not found with id " + airportId);
        }
    }

}
