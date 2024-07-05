package com.omercangoktas.flight_search_api.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.omercangoktas.flight_search_api.model.Flight;
import com.omercangoktas.flight_search_api.repository.FlightRepository;
import com.omercangoktas.flight_search_api.service.MockFlightService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FlightWatcher {
    
    private final FlightRepository flightRepository;
    private final MockFlightService mockFlightService;

    @Autowired
    public FlightWatcher(final FlightRepository flightRepository, final MockFlightService mockFlightService) {
        this.flightRepository = flightRepository;
        this.mockFlightService = mockFlightService;
    }

    // @Scheduled(cron = "0 0 0 * * *") // Her gün 00:00'da çalışacak
    @Scheduled(fixedDelay = 60 * 1000)
    public void watchFlights() {
        try {
            List<Flight> flights = mockFlightService.getFlightsFromMockAPI();
            log.info("Size of Flights from Mock API: {}", flights.size());
            flightRepository.saveAll(flights);
        } catch (Exception e) {
            log.error("Failed to fetch and save flights from Mock API", e);
        }
    }
}
