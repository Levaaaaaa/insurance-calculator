package lv.javaguru.travel.insurance.core.repositories.calculation.evacuation;

import lv.javaguru.travel.insurance.core.domain.calculate.evacuation.TEMedicalCoefficientDomain;
import lv.javaguru.travel.insurance.core.repositories.calculate.evacuation.TEMedicalCoefficientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TEMedicalCoefficientRepositoryTest {
    @Autowired
    private TEMedicalCoefficientRepository repository;

    @Test
    public void isNotNull() {
        assertNotNull(repository);
    }

    @Test
    public void perfectlyHealthyStatusTest() {
        executeAndCompare("PERFECTLY_HEALTHY", BigDecimal.ONE);
    }

    @Test
    public void slightlyIllStatusTest() {
        executeAndCompare("SLIGHTLY_ILL", BigDecimal.valueOf(2L));
    }

    @Test
    public void illStatusTest() {
        executeAndCompare("ILL", BigDecimal.valueOf(3L));
    }

    @Test
    public void HardlyIllStatusTest() {
        executeAndCompare("HARDLY_ILL", BigDecimal.valueOf(4L));
    }
    private void executeAndCompare(String ic, BigDecimal expectedCoefficient) {
        Optional<TEMedicalCoefficientDomain> optional = repository.findByIc(ic);
        assertTrue(optional.isPresent());
        assertEquals(expectedCoefficient.setScale(2, RoundingMode.HALF_UP), optional.get().getCoefficient());
    }
}
