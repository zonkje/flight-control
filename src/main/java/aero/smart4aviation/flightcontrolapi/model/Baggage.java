package aero.smart4aviation.flightcontrolapi.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "baggage")
public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "weight_unit")
    private String weightUnit;

    @Column(name = "pieces")
    private Integer pieces;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Baggage baggage = (Baggage) o;
        return id != null && Objects.equals(id, baggage.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
