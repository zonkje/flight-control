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
@Table(name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "weight_unit")
    private String weightUnit;

    @Column(name = "pieces")
    private Integer pieces;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cargo cargo = (Cargo) o;
        return id != null && Objects.equals(id, cargo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
