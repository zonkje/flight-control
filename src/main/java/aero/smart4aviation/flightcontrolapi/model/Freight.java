package aero.smart4aviation.flightcontrolapi.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Freight {

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

}
