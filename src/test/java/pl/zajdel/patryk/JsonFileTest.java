package pl.zajdel.patryk;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;
class JsonFileTest {

    @Test
    public void shouldReturnEmptyOptionalIfFileDoesNotExist() {
        // given
        String path = "src/main/resources/examples/NonExistentFile.json";

        // when
        var result = JsonFile.fromPath(path);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnNonEmptyOptionalIfFileExists() {
        // given
        String path = "src/main/resources/examples/ValidPolicy.json";

        // when
        var result = JsonFile.fromPath(path);

        // then
        assertTrue(result.isPresent());
    }

    @Test
    public void shouldReturnFile() {
        // given
        String path = "src/main/resources/examples/ValidPolicy.json";
        var jsonFile = JsonFile.fromPath(path).get();

        // when
        var result = jsonFile.getFile();

        // then
        assertTrue(result.exists());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfFileDoesNotHaveJsonExtension() {
        // given
        String path = "src/main/resources/examples/InvalidPolicy.txt";

        // when
        try {
            JsonFile.fromPath(path);
        } catch (IllegalArgumentException e) {
            // then
            assertTrue(e.getMessage().contains("The file must have a .json extension"));
        }
    }
}