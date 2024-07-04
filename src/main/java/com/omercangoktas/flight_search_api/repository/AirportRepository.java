package com.omercangoktas.flight_search_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omercangoktas.flight_search_api.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long>{
    // public Airport getAirportById
}
