package com.omercangoktas.flight_search_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;


import com.omercangoktas.flight_search_api.model.Airport;
import com.omercangoktas.flight_search_api.model.Flight;

@Service
public class MockFlightService {

    private final AirportService airportService;

    @Autowired
    public MockFlightService(AirportService airportService) {
        this.airportService = airportService;
    }

    public List<Flight> getFlightsFromMockAPI() throws Exception {
        try {
            return generateMockFlights();
        } catch (Exception e) {
            throw new Exception("Error while generating mock flights", e);
        }
    }

    // create random flights
    private List<Flight> generateMockFlights() throws Exception {
        List<Flight> flights = new ArrayList<>();
        List<Airport> airports = airportService.getAllAirports();
        int countOfAirports = airports.size();
        
        if (countOfAirports < 2) {
            throw new Exception("Insufficient number of airports to generate flights");
        }

        for (int i = 0; i < 10; i++) {
            long departureAirportId = (long) (Math.random() * countOfAirports) + 1;
            long arrivalAirportId = (long) (Math.random() * countOfAirports) + 1;

            // Ensure departure and arrival airports are different
            while (arrivalAirportId == departureAirportId) {
                arrivalAirportId = (long) (Math.random() * countOfAirports) + 1;
            }

            LocalDateTime departureDateTime = generateRandomDepartureDateTime();
            LocalDateTime arrivalDateTime = departureDateTime.plusHours((long) (Math.random() * 8) + 1);

            Airport departureAirport = airportService.getAirportById(departureAirportId);
            Airport arrivalAirport = airportService.getAirportById(arrivalAirportId);
            Double price = 100 + (Math.random() * 900);

            flights.add(new Flight(departureAirport, arrivalAirport, departureDateTime, arrivalDateTime, price));
        }
        return flights;
    }

    public LocalDateTime generateRandomDepartureDateTime() {
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 23, 59);

        long startEpochSecond = startDate.toEpochSecond(java.time.ZoneOffset.UTC);
        long endEpochSecond = endDate.toEpochSecond(java.time.ZoneOffset.UTC);

        long randomEpochSecond = ThreadLocalRandom.current().nextLong(startEpochSecond, endEpochSecond);
        return LocalDateTime.ofEpochSecond(randomEpochSecond, 0, java.time.ZoneOffset.UTC);
    }

}


// INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
// SELECT 1, 1, 2, '2024-07-05', '2024-07-05', 250.0 
// WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 1);