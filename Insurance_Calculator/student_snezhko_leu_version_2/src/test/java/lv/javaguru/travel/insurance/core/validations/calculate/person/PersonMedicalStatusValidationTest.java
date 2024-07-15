package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.TE_PERSON_MEDICAL_STATUS;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PersonMedicalStatusValidationTest {
    @InjectMocks
    private PersonMedicalStatusValidation validation;

    @Mock
    private static ValidationErrorFactory errorFactory;

    private static final String errorCode = "ERROR_CODE_20";
    private static final String description = "Field personMedicalStatus must not be empty!";

    private PersonDTO person;
    private List<String> selectedRisks;

    @BeforeEach
    public void init() {
        selectedRisks = List.of("TRAVEL_EVACUATION");
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));
    }

    @Test
    public void correctStatusWithEvacuationRiskTest() {
        person = PersonDTOBuilder.createPersonDTO().withPersonMedicalStatus(TE_PERSON_MEDICAL_STATUS.ILL).build();
        //selectedRisks = List.of("TRAVEL_EVACUATION");

        Optional<ValidationErrorDTO> optionalError = validation.validate(person, selectedRisks);
        assertTrue(optionalError.isEmpty());
//        assertEquals(errorCode, optionalError.get().getErrorCode());
//        assertEquals(description, optionalError.get().getDescription());
    }

    @Test
    public void PersonIsNullEvacuationRiskTest() {
        //selectedRisks = List.of("TRAVEL_EVACUATION");

        Optional<ValidationErrorDTO> optionalError = validation.validate(null, selectedRisks);
        assertTrue(optionalError.isEmpty());
    }

    @Test
    public void SelectedRisksIsNullEvacuationRiskTest() {
        //selectedRisks = List.of("TRAVEL_EVACUATION");

        Optional<ValidationErrorDTO> optionalError = validation.validate(person, null);
        assertTrue(optionalError.isEmpty());
    }

    @Test
    public void StatusIsNullEvacuationRiskTest() {
        person = PersonDTOBuilder.createPersonDTO().withPersonMedicalStatus(null).build();
        //selectedRisks = List.of("TRAVEL_EVACUATION")
        Optional<ValidationErrorDTO> optionalError = validation.validate(person, selectedRisks);
        assertTrue(optionalError.isPresent());
        assertEquals(errorCode, optionalError.get().getErrorCode());
        assertEquals(description, optionalError.get().getDescription());
    }


    @Test
    public void StatusIsNullCancellationRiskTest() {
        person = PersonDTOBuilder.createPersonDTO().withPersonMedicalStatus(null).build();
        selectedRisks = List.of("TRAVEL_CANCELLATION");
        Optional<ValidationErrorDTO> optionalError = validation.validate(person, selectedRisks);
        assertTrue(optionalError.isEmpty());
    }
}
