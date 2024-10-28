package co.edu.udea.codefactory.sitas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "flight_number", unique = true)
    private Long flightNumber;

    @JoinColumn(name = "airline")
    @ManyToOne
    private Airline airline;

    @JoinColumn(name = "departureAirport")
    @ManyToOne
    private Airport departureAirport;

    @JoinColumn(name = "arrivalAirport")
    @ManyToOne
    private Airport arrivalAirport;

    @Column(name = "departure_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date departureDate;

    @Column(name = "arrival_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date arrivalDate;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "seats_avalible")
    private Long seatsAvalible;

    @Column(name = "price")
    private double price;

    public Flight() {
    }

    public Flight(UUID id, Long flightNumber, Airline airline, Airport departureAirport, Airport arrivalAirport, Date departureDate, Date arrivalDate, Double distance, Long seatsAvalible, double price) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.distance = distance;
        this.seatsAvalible = seatsAvalible;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;
        return Objects.equals(id, flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, departureAirport.getAirportName());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", origin='" + departureAirport.getAirportName() + '\'' +
                ", destination='" + arrivalAirport.getAirportName() + '\'' +
                ", date=" + departureDate +
                '}';
    }
}
