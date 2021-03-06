package aero.smart4aviation.flightcontrolapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @Column(name = "flight_id", nullable = false)
    private Long flightId;

    @Column(name = "flight_number")
    private Long flightNumber;

    @Column(name = "departure_airport_iata_code")
    private String departureAirportIATACode;

    @Column(name = "arrival_airport_iata_code")
    private String arrivalAirportIATACode;

    @Column(name = "departure_date")
    private OffsetDateTime departureDate;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinColumn(name = "flight_id")
    Collection<Baggage> baggage;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinColumn(name = "flight_id")
    Collection<Cargo> cargo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Flight flight = (Flight) o;
        return flightId != null && Objects.equals(flightId, flight.flightId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
