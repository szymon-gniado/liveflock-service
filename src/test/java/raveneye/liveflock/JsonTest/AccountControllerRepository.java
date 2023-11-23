package raveneye.liveflock.JsonTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class AccountControllerRepository {

    @Autowired
    private JacksonTester<raveneye.liveflock.entity.Account> json;

    // Declare test account
    raveneye.liveflock.entity.Account entity = raveneye.liveflock.TestEntities.Account.SHOULD_PASS.getAccount();

    @Test
    public void accountSerializationTest() throws IOException {

        //Make account into a json
        JsonContent<raveneye.liveflock.entity.Account> account_json = json.write(entity);

        // Compare  account_json and it's values to account.json
        assertThat(account_json).isEqualToJson("account.json");
        assertThat(account_json).hasJsonPathStringValue("@.email");
        assertThat(account_json).extractingJsonPathStringValue("@.email")
                .isEqualTo("test@e.mail");

        assertThat(account_json).hasJsonPathStringValue("@.username");
        assertThat(account_json).extractingJsonPathStringValue("@.username")
                .isEqualTo("test_username");

        assertThat(account_json).hasJsonPathStringValue("@.display_name");
        assertThat(account_json).extractingJsonPathStringValue("@.display_name")
                .isEqualTo("test_display_name");

        assertThat(account_json).hasJsonPathStringValue("@.avatar");
        assertThat(account_json).extractingJsonPathStringValue("@.avatar")
                .isEqualTo("test/path");

    }

    @Test
    public void accountDeserializationTest() throws IOException {

        // Declare expected json response
        String expected_json = """
                {
                  "email": "test@e.mail",
                  "username": "test_username",
                  "display_name": "test_display_name",
                  "avatar": "test/path"
                }
                """;
        // Parse expected json into Account object
        raveneye.liveflock.entity.Account expected = json.parseObject(expected_json);

        // Compare expected_account values to test account ones
        assertThat(expected.email()).isEqualTo(entity.email());
        assertThat(expected.username()).isEqualTo(entity.username());
        assertThat(expected.display_name()).isEqualTo(entity.display_name());
        assertThat(expected.avatar()).isEqualTo(entity.avatar());

    }
}