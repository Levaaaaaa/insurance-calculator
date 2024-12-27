package lv.javaguru.travel.insurance.core.domain.calculate.evacuation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "travel_evacuation_age_coefficient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TEAgeCoefficientDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "age_from", nullable = false)
    private Integer ageFrom;

    @Column(name = "age_to", nullable = false)
    private Integer ageTo;

    @Column(name = "coefficient", nullable = false, precision = 10, scale = 2)
    private BigDecimal coefficient;
}
