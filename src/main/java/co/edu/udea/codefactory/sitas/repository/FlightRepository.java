package co.edu.udea.codefactory.sitas.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.udea.codefactory.sitas.model.Airport;
import co.edu.udea.codefactory.sitas.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> { // Cambia Long a UUID
    List<Flight> findByDepartureDateEqualsAndDepartureAirportEqualsAndArrivalAirportEquals(LocalDate departureDate, Airport departureAirport, Airport arrivalAirport);

    Optional<Flight> findById(UUID id); // Esto es correcto
}
