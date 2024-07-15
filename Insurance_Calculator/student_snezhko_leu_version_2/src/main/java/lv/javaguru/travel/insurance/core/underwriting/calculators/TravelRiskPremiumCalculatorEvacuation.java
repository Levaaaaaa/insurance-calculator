package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.underwriting.calculators.evacuation.TEPremiumCalculatorComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
class TravelRiskPremiumCalculatorEvacuation implements TravelRiskPremiumCalculator {
    @Autowired
    private List<TEPremiumCalculatorComponent> calculators;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        if (agreement == null || person == null) {
            return BigDecimal.ZERO;
        }

        return calculators.stream().map(
                calculator -> calculator.calculatePremium(agreement, person)
        ).reduce(BigDecimal::multiply).get();
    }

    @Override
    public String getIc() {
        return "TRAVEL_EVACUATION";
    }
}
