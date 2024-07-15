package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.TE_PERSON_MEDICAL_STATUS;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.calculators.evacuation.TEAgeCoefficientCalculator;
import lv.javaguru.travel.insurance.core.underwriting.calculators.evacuation.TEMedicalCoefficientCalculator;
import lv.javaguru.travel.insurance.core.underwriting.calculators.evacuation.TEPremiumCalculatorComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static lv.javaguru.travel.insurance.core.validations.calculate.integration.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TravelRiskPremiumCalculatorEvacuationTest {
    @InjectMocks
    TravelRiskPremiumCalculatorEvacuation calculator;

    @Mock
    private List<TEPremiumCalculatorComponent> calculators;

    @Mock
    private TEAgeCoefficientCalculator ageCoefficientCalculator;

    @Mock
    private TEMedicalCoefficientCalculator medicalCoefficientCalculator;


    private AgreementDTO agreement;
    private PersonDTO person;

    private static final BigDecimal ageCoefficientValue = BigDecimal.valueOf(20L);
    private static final BigDecimal medicalCoefficientValue = BigDecimal.valueOf(3L);

    @Test
    public void calculatePremiumIntegrationTest() {
        int age = 18;
        TE_PERSON_MEDICAL_STATUS medicalStatus = TE_PERSON_MEDICAL_STATUS.ILL;
        PersonDTO person = createPersonDTO().withBirthDate(generateBirthDate(age)).build();

        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(createDate("2050-09-11"))
                .withDateTo(createDate("2050-09-12"))
                .withSelectedRisks("TRAVEL_EVACUATION")
                .build();

        calculators = List.of(ageCoefficientCalculator, medicalCoefficientCalculator);
        when(ageCoefficientCalculator.calculatePremium(agreement, person)).thenReturn(ageCoefficientValue);
        when(medicalCoefficientCalculator.calculatePremium(agreement, person)).thenReturn(medicalCoefficientValue);

        ReflectionTestUtils.setField(calculator, "calculators", calculators);

        assertEquals(BigDecimal.valueOf(60L), calculator.calculatePremium(agreement, person));
    }

    private Date generateBirthDate(int age) {
        Date birthDate = new Date();
        birthDate.setYear(birthDate.getYear() - age);
        return birthDate;
    }
}
