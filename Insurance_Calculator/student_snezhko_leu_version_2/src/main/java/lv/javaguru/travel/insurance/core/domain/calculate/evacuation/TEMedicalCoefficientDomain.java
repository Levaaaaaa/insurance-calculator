package lv.javaguru.travel.insurance.core.domain.calculate.evacuation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "travel_evacuation_medical_coefficient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TEMedicalCoefficientDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ic", nullable = false)
    private String ic;

    @Column(name = "coefficient", nullable = false)
    private BigDecimal coefficient;
}
