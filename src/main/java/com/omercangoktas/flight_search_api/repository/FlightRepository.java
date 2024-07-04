package com.omercangoktas.flight_search_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.omercangoktas.flight_search_api.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    boolean existsByArrivalAirport_AirportId(Long airportId);
    @Query("SELECT f FROM Flight f WHERE f.departureAirport.id = :departureAirportId AND f.arrivalAirport.id = :arrivalAirportId AND f.departureDateTime >= :departureDateTime")
    List<Flight> findFlights(@Param("departureAirportId") Long departureAirportId, 
                            @Param("arrivalAirportId") Long arrivalAirportId, 
                            @Param("departureDateTime") LocalDateTime departureDateTime);

    @Query("SELECT f FROM Flight f WHERE f.departureAirport.city = :departureCity AND f.arrivalAirport.city = :arrivalCity AND f.departureDateTime BETWEEN :departureStartDateTime AND :departureEndDateTime")
    List<Flight> findOneWayFlights(@Param("departureCity") String departureCity,
                                    @Param("arrivalCity") String arrivalCity,
                                    @Param("departureStartDateTime") LocalDateTime departureStartDateTime,
                                    @Param("departureEndDateTime") LocalDateTime departureEndDateTime);

    @Query("SELECT f FROM Flight f WHERE f.departureAirport.city = :departureCity AND f.arrivalAirport.city = :arrivalCity AND f.departureDateTime BETWEEN :departureStartDateTime AND :departureEndDateTime AND EXISTS (SELECT 1 FROM Flight rf WHERE rf.departureAirport.city = :arrivalCity AND rf.arrivalAirport.city = :departureCity AND rf.departureDateTime BETWEEN :returnStartDateTime AND :returnEndDateTime)")
    List<Flight> findRoundTripFlights(@Param("departureCity") String departureCity,
                                        @Param("arrivalCity") String arrivalCity,
                                        @Param("departureStartDateTime") LocalDateTime departureStartDateTime,
                                        @Param("departureEndDateTime") LocalDateTime departureEndDateTime,
                                        @Param("returnStartDateTime") LocalDateTime returnStartDateTime,
                                        @Param("returnEndDateTime") LocalDateTime returnEndDateTime);
}
