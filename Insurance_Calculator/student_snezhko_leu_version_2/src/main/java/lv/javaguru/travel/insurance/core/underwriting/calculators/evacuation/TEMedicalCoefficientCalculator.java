package lv.javaguru.travel.insurance.core.underwriting.calculators.evacuation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.calculate.evacuation.TEMedicalCoefficientDomain;
import lv.javaguru.travel.insurance.core.repositories.calculate.evacuation.TEMedicalCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TEMedicalCoefficientCalculator implements TEPremiumCalculatorComponent{
    @Autowired
    private TEMedicalCoefficientRepository medCoeffRepository;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreementDTO, PersonDTO person) {
        Optional<TEMedicalCoefficientDomain> optional = medCoeffRepository.findByIc(person.getPersonMedicalStatus().name());
        return optional.isPresent()
                ? optional.get().getCoefficient()
                : BigDecimal.ONE;
    }
}
