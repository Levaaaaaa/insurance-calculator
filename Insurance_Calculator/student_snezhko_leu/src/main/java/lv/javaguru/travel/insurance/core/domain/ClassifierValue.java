package lv.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="classifier_values")
public class ClassifierValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="classifier_id", nullable = false)
    private Classifier classifier;

    @Column(name="ic", nullable = false)
    private String ic;

    @Column(name="description", nullable = false)
    private String description;
}
