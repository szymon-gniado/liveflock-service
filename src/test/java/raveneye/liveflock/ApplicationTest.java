package raveneye.liveflock;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import raveneye.liveflock.TestEntities.Account;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {
  @Autowired
  TestRestTemplate restTemplate;

  raveneye.liveflock.entity.Account account = Account.SHOULD_PASS.getAccount();

    // In case of request for account that doesn't exist return 404 NOT FOUND
	@Test
	void shouldReturnAccountWhenDataIsSaved() {

        ResponseEntity<String> response = restTemplate.getForEntity("/accounts/test_username", String.class);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String email = documentContext.read("$.email");
        String username = documentContext.read("$.username");
        String display_name = documentContext.read("$.display_name");
        String avatar = documentContext.read("$.avatar");
        assertThat(email).isEqualTo(account.email());
        assertThat(username).isEqualTo(account.username());
        assertThat(display_name).isEqualTo(account.display_name());
        assertThat(avatar).isEqualTo(account.avatar());
	}

    @Test
    void shouldNotReturnAccountWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/accounts/wrong_test_username", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    void shouldReturnURIofNewAccount() {
        raveneye.liveflock.entity.Account newAccount = new raveneye.liveflock.entity.Account(
                null,
                "new@e.mail",
                "new_username3",
                "new_password",
                "new_display_name",
                null,
                "new/path"
                );
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/accounts", newAccount, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewAccount = createResponse.getHeaders().getLocation();
        ResponseEntity<String> createdAccount = restTemplate.getForEntity(locationOfNewAccount.getPath(), String.class);
        assertThat(createdAccount.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
