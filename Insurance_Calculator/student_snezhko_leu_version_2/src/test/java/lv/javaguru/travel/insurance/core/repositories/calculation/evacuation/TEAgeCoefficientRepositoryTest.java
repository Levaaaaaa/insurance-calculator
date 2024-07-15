package lv.javaguru.travel.insurance.core.repositories.calculation.evacuation;

import lv.javaguru.travel.insurance.core.domain.calculate.cancellation.TCAgeCoefficientDomain;
import lv.javaguru.travel.insurance.core.domain.calculate.evacuation.TEAgeCoefficientDomain;
import lv.javaguru.travel.insurance.core.repositories.calculate.evacuation.TEAgeCoefficientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TEAgeCoefficientRepositoryTest {
    @Autowired
    private TEAgeCoefficientRepository repository;

    @Test
    public void isNotNull() {
        assertNotNull(repository);
    }

    @Test
    public void TEAgeCoefficientFor4YearsOldTest() {
        executeAndCompare(4, BigDecimal.valueOf(5L));
    }

    @Test
    public void For11YearOldTest() {
        executeAndCompare(11, BigDecimal.valueOf(10L));
    }

    @Test
    public void For18YearOldTest() {
        executeAndCompare(18, BigDecimal.valueOf(20L));
    }

    @Test
    public void For40YearOldTest() {
        executeAndCompare(40, BigDecimal.valueOf(30L));
    }

    @Test
    public void For130YearOldTest() {
        executeAndCompare(130, BigDecimal.valueOf(50L));
    }

    private void executeAndCompare(int age, BigDecimal expectedCoefficient) {
        Optional<TEAgeCoefficientDomain> optional = repository.findAgeCoefficient(age);
        assertTrue("", optional.isPresent());
        assertEquals("", expectedCoefficient.setScale(2, RoundingMode.HALF_UP), optional.get().getCoefficient());
    }
}
