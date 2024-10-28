package co.edu.udea.codefactory.sitas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Airport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "airport_code", unique = true)
    private String airportCode;

    @Column(name = "airport_name", unique = true)
    private String airportName;

    @Column(name = "airport_city")
    private String airportCity;

    @Column(name = "airport_country")
    private String airportCountry;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "departureAirport")
    private Set<Flight> departureFlights;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrivalAirport")
    private Set<Flight> arrivalFlights;

    public Airport() {
    }

    public Airport(UUID uuid, String airportCode, String airportName, String airportCity, String airportCountry, Double latitude, Double longitude, Set<Flight> departureFlights, Set<Flight> arrivalFlights) {
        this.uuid = uuid;
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.airportCity = airportCity;
        this.airportCountry = airportCountry;
        this.latitude = latitude;
        this.longitude = longitude;
        this.departureFlights = departureFlights;
        this.arrivalFlights = arrivalFlights;
    }

    public Airport(UUID uuid, String jfk, String s) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport)) return false;
        return Objects.equals(uuid, airport.uuid) && Objects.equals(airportCode, airport.airportCode) && Objects.equals(airportName, airport.airportName) && Objects.equals(airportCity, airport.airportCity) && Objects.equals(airportCountry, airport.airportCountry) && Objects.equals(latitude, airport.latitude) && Objects.equals(longitude, airport.longitude) && Objects.equals(departureFlights, airport.departureFlights) && Objects.equals(arrivalFlights, airport.arrivalFlights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, airportCode, airportName, airportCity, airportCountry, latitude, longitude, departureFlights, arrivalFlights);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "uuid=" + uuid +
                ", airportCode='" + airportCode + '\'' +
                ", airportName='" + airportName + '\'' +
                ", airportCity='" + airportCity + '\'' +
                ", airportCountry='" + airportCountry + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", departureFlights=" + departureFlights +
                ", arrivalFlights=" + arrivalFlights +
                '}';
    }
}
