package raveneye.liveflock.JsonTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import raveneye.liveflock.TestEntities.Posts;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class Post {

    @Autowired
    private JacksonTester<raveneye.liveflock.entity.Post> json;

    // Declare test account
    raveneye.liveflock.entity.Post entity = Posts.SHOULD_PASS.getPost();
    
    @Test
    public void accountSerializationTest() throws IOException {

        //Make account into a json
        JsonContent<raveneye.liveflock.entity.Post> account_json = json.write(entity);

        // Compare  account_json and it's values to account.json
        assertThat(account_json).isEqualToJson("post.json");
        
        assertThat(account_json).hasJsonPathNumberValue("@.author");
        assertThat(account_json).extractingJsonPathNumberValue("@.author")
                .isEqualTo(1);

        assertThat(account_json).hasJsonPathStringValue("@.title");
        assertThat(account_json).extractingJsonPathStringValue("@.title")
                .isEqualTo("Test Title");

        assertThat(account_json).hasJsonPathStringValue("@.content");
        assertThat(account_json).extractingJsonPathStringValue("@.content")
                .isEqualTo("Test content");

        assertThat(account_json).hasJsonPathNumberValue("@.parent");
        assertThat(account_json).extractingJsonPathNumberValue("@.parent")
                .isEqualTo(1);

    }

    @Test
    public void accountDeserializationTest() throws IOException {

        // Declare expected json response
        String expected_json = """
                {
                  "author": 1,
                  "title": "Test Title",
                  "content": "Test content",
                  "parent": 1
                }
                """;
        // Parse expected json into Account object
        raveneye.liveflock.entity.Post expeced = json.parseObject(expected_json);

        // Compare expected_account values to test account ones
        assertThat(expeced.author()).isEqualTo(entity.author());
        assertThat(expeced.title()).isEqualTo(entity.title());
        assertThat(expeced.content()).isEqualTo(entity.content());
        assertThat(expeced.parent()).isEqualTo(entity.parent());

    }
}