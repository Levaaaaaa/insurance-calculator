package lv.javaguru.travel.insurance.core.underwriting.calculators.evacuation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.calculate.evacuation.TEAgeCoefficientDomain;
import lv.javaguru.travel.insurance.core.repositories.calculate.evacuation.TEAgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.CalculateAgeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TEAgeCoefficientCalculator implements TEPremiumCalculatorComponent{
    @Autowired
    private TEAgeCoefficientRepository ageCoefficientRepository;

    @Autowired
    private CalculateAgeUtil calculateAgeUtil;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreementDTO, PersonDTO person) {
        Optional<TEAgeCoefficientDomain> optional =
                ageCoefficientRepository.findAgeCoefficient(
                        calculateAgeUtil.calculateAge(person.getPersonBirthDate())
                );

        return optional.isPresent() ? optional.get().getCoefficient() : BigDecimal.ONE;
    }
}
