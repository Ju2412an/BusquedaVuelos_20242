package co.edu.udea.codefactory.sitas.repository;

import co.edu.udea.codefactory.sitas.model.Airport;
import co.edu.udea.codefactory.sitas.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureDateEqualsAndDepartureAirportEqualsAndArrivalAirportEquals(LocalDate departureDate, Airport departureAirport, Airport arrivalAirport);

    Optional<Flight> findById(UUID id);
}
