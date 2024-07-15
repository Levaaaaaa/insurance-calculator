package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMedicalStatusErrorGenerator {
    @Autowired
    private static ValidationErrorFactory errorFactory;

    public static ValidationErrorDTO generateErrorCode21() {
        return errorFactory.buildError("ERROR_CODE_21");
    }
}
