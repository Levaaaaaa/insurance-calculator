package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonMedicalStatusValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory errorFactory;
    
    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person, List<String> selectedRisks) {
        if (selectedRisks != null
                && selectedRisks.contains("TRAVEL_EVACUATION")
                && person != null
                &&
                (
                        person.getPersonMedicalStatus() == null
                )
        )
        {
            return Optional.of(errorFactory.buildError("ERROR_CODE_20"));
        }
        return Optional.empty();
    }
}
