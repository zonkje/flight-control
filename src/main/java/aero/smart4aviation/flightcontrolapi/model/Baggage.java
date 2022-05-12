package aero.smart4aviation.flightcontrolapi.model;

import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "baggage")
public class Baggage extends Freight{

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Baggage baggage = (Baggage) o;
        return getId() != null && Objects.equals(getId(), baggage.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
