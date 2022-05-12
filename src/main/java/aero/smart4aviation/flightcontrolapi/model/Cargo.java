package aero.smart4aviation.flightcontrolapi.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "cargo")
public class Cargo extends Freight{

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cargo cargo = (Cargo) o;
        return getId() != null && Objects.equals(getId(), cargo.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
