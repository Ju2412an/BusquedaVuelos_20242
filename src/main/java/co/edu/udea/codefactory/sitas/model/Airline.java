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
public class Airline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "airline_code", unique = true)
    private String airlineCode;

    @Column(name = "airline_name", unique = true)
    private String airlineName;

    @Column(name = "headquarters", unique = true)
    private String headquarters;

    @Column(name = "founding_date", unique = true)
    private String foundingDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "airline")
    private Set<Flight> flights;

    public Airline() {
    }

    public Airline(UUID uuid, String airlineCode, String airlineName, String headquarters, String foundingDate, Set<Flight> flights) {
        this.uuid = uuid;
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.headquarters = headquarters;
        this.foundingDate = foundingDate;
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airline airline)) return false;
        return Objects.equals(uuid, airline.uuid) && Objects.equals(airlineCode, airline.airlineCode) && Objects.equals(airlineName, airline.airlineName) && Objects.equals(headquarters, airline.headquarters) && Objects.equals(foundingDate, airline.foundingDate) && Objects.equals(flights, airline.flights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, airlineCode, airlineName, headquarters, foundingDate, flights);
    }

    @Override
    public String toString() {
        return "Airline{" +
                "uuid=" + uuid +
                ", airlineCode='" + airlineCode + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", headquarters='" + headquarters + '\'' +
                ", foundingDate='" + foundingDate + '\'' +
                ", flights=" + flights +
                '}';
    }
}
