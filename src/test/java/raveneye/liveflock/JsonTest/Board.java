package raveneye.liveflock.JsonTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class Board {

    @Autowired
    private JacksonTester<raveneye.liveflock.entity.Board> json;

    // Declare test account
    raveneye.liveflock.entity.Board entity = raveneye.liveflock.TestEntities.Board.SHOULD_PASS.getBoard();
    
    @Test
    public void accountSerializationTest() throws IOException {

        //Make account into a json
        JsonContent<raveneye.liveflock.entity.Board> account_json = json.write(entity);

        // Compare  account_json and it's values to account.json
        assertThat(account_json).isEqualToJson("board.json");

        assertThat(account_json).hasJsonPathStringValue("@.title");
        assertThat(account_json).extractingJsonPathStringValue("@.title")
                .isEqualTo("Test Title");

        assertThat(account_json).hasJsonPathStringValue("@.description");
        assertThat(account_json).extractingJsonPathStringValue("@.description")
                .isEqualTo("Test description");

        assertThat(account_json).hasJsonPathStringValue("@.avatar");
        assertThat(account_json).extractingJsonPathStringValue("@.avatar")
                .isEqualTo("test/path");

    }

    @Test
    public void accountDeserializationTest() throws IOException {

        // Declare expected json response
        String expected_json = """
                {
                  "title": "Test Title",
                  "description": "Test description",
                  "avatar": "test/path"
                }
                """;
        // Parse expected json into Account object
        raveneye.liveflock.entity.Board expeced = json.parseObject(expected_json);

        // Compare expected_account values to test account ones
        assertThat(expeced.title()).isEqualTo(entity.title());
        assertThat(expeced.description()).isEqualTo(entity.description());
        assertThat(expeced.avatar()).isEqualTo(entity.avatar());

    }
}