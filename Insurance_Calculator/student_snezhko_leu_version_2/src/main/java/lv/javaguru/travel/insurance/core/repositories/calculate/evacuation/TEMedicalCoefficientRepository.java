package lv.javaguru.travel.insurance.core.repositories.calculate.evacuation;

import lv.javaguru.travel.insurance.core.domain.calculate.evacuation.TEMedicalCoefficientDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TEMedicalCoefficientRepository extends JpaRepository<TEMedicalCoefficientDomain, Long> {
    public Optional<TEMedicalCoefficientDomain> findByIc(String ic);
}
