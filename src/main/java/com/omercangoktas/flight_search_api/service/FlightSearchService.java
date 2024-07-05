package com.omercangoktas.flight_search_api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.omercangoktas.flight_search_api.model.Flight;
import com.omercangoktas.flight_search_api.repository.FlightRepository;

@Service
public class FlightSearchService {

    private final FlightRepository flightRepository;

    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDate departureDate, LocalDate returnDate) {
        LocalDateTime departureStartDateTime = departureDate.atStartOfDay();
        LocalDateTime departureEndDateTime = departureDate.atTime(LocalTime.MAX);
        
        if(returnDate != null) {
            LocalDateTime returnStartDateTime = returnDate.atStartOfDay();
            LocalDateTime returnEndDateTime = returnDate.atTime(LocalTime.MAX);
            System.out.println("returnDate: " + returnDate);
            List<Flight> flights = flightRepository.searchFlightsWithReturn(departureCity, arrivalCity, departureStartDateTime, departureEndDateTime, returnStartDateTime, returnEndDateTime);
            System.out.println("flights: " + flights);
            return flightRepository.searchFlightsWithReturn(departureCity, arrivalCity, departureStartDateTime, departureEndDateTime, returnStartDateTime, returnEndDateTime);
        } else {
            return flightRepository.findOneWayFlights(departureCity, arrivalCity, departureStartDateTime, departureEndDateTime);
        }
    }
}
