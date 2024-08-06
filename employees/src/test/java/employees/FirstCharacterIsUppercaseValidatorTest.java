package employees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FirstCharacterIsUppercaseValidatorTest {

    @Test
    void valid() {
        // Given
        var validator = new FirstCharacterIsUppercaseValidator();
        // When
        var result = validator.isValid("John Doe", null);
        // Then
        assertTrue(result);
    }

    @Test
    void lowercaseName() {
        assertFalse(new FirstCharacterIsUppercaseValidator()
                .isValid( "john doe", null));
    }
}
