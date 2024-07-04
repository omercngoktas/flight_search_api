package com.omercangoktas.flight_search_api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omercangoktas.flight_search_api.model.Flight;
import com.omercangoktas.flight_search_api.service.FlightSearchService;

@RestController
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    @GetMapping("/flights/search")
    public List<Flight> searchFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam LocalDate departureDate,
            @RequestParam(required = false) LocalDate returnDate) {
        return flightSearchService.searchFlights(departureCity, arrivalCity, departureDate, returnDate);
    }
}
