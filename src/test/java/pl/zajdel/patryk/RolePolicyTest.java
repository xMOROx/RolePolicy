package pl.zajdel.patryk;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RolePolicyTest {
    private final String schemaName = "role-policy-schema.json";

    @Test
    public void shouldReturnTrueIfJsonMeetsTheSchema() throws IOException {
        // given
        File jsonFile = new File("src/main/resources/validPolicy.json");
        // when
        boolean result = RolePolicy.verifyJsonFormat(jsonFile, schemaName);

        // then
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfPolicyNameIsLongerThan128Characters() throws IOException {
        // given
        File jsonFile = new File("src/main/resources/invalidPolicyName.json");

        // when
        boolean result = RolePolicy.verifyJsonFormat(jsonFile, schemaName);

        // then
        assertFalse(result);
    }
}