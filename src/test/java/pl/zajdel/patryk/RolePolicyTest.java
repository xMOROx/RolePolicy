package pl.zajdel.patryk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class RolePolicyTest {
    private final String schemaName = "RolePolicy.json";

    @Mock
    private JsonFile jsonFile;

    @Test
    public void shouldReturnTrueIfJsonMeetsTheSchema() throws IOException {
        // given
        Mockito.when(jsonFile.getFile()).thenReturn(new File("src/main/resources/examples/ValidPolicy.json"));
        // when
        boolean result = RolePolicy.verifyJsonFormat(jsonFile, schemaName);

        // then
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfPolicyNameIsLongerThan128Characters() throws IOException {
        // given
        Mockito.when(jsonFile.getFile()).thenReturn(new File("src/main/resources/examples/InvalidPolicyName.json"));
        // when
        boolean result = RolePolicy.verifyJsonFormat(jsonFile, schemaName);

        // then
        assertFalse(result);
    }

    @Test
    public void shouldReturnTheInstanceIfJsonMeetsTheSchema() throws IOException {
        // given
        Mockito.when(jsonFile.getFile()).thenReturn(new File("src/main/resources/examples/ValidPolicy.json"));

        // when
        RolePolicy rolePolicy = RolePolicy.fromJsonWithGivenSchema(jsonFile, schemaName);

        // then
        assertInstanceOf(RolePolicy.class, rolePolicy);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfJsonDoesNotMeetTheSchema() {
        // given
        Mockito.when(jsonFile.getFile()).thenReturn(new File("src/main/resources/examples/InvalidPolicyName.json"));

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> RolePolicy.fromJsonWithGivenSchema(jsonFile, schemaName));
    }

    @Test
    public void shouldReturnTrueIfResourcesAreValid() throws IOException {
        // given
        Mockito.when(jsonFile.getFile()).thenReturn(new File("src/main/resources/examples/ValidPolicy.json"));
        RolePolicy rolePolicy = RolePolicy.fromJsonWithGivenSchema(jsonFile, schemaName);

        // when
        boolean result = rolePolicy.verifyResources();

        // then
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfResourcesDefineAsStringHasAsterisk() throws IOException {
        // given
        Mockito.when(jsonFile.getFile()).thenReturn(new File("src/main/resources/examples/PolicyResourcesHaveAsteriskInStringField.json"));
        RolePolicy rolePolicy = RolePolicy.fromJsonWithGivenSchema(jsonFile, schemaName);

        // when
        boolean result = rolePolicy.verifyResources();

        // then
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfResourcesDefineAsArrayHasAsterisk() throws IOException {
        // given
        Mockito.when(jsonFile.getFile()).thenReturn(new File("src/main/resources/examples/PolicyResourcesHaveAsteriskInArrayField.json"));
        RolePolicy rolePolicy = RolePolicy.fromJsonWithGivenSchema(jsonFile, schemaName);

        // when
        boolean result = rolePolicy.verifyResources();

        // then
        assertFalse(result);
    }
}