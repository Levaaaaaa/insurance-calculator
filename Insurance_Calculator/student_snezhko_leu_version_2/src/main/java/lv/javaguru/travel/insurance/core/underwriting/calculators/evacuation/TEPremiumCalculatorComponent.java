package lv.javaguru.travel.insurance.core.underwriting.calculators.evacuation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;

import java.math.BigDecimal;

public interface TEPremiumCalculatorComponent {
    public BigDecimal calculatePremium(AgreementDTO agreementDTO, PersonDTO person);
}
