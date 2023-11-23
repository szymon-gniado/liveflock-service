package raveneye.liveflock.TestEntities;

import java.time.LocalDate;

public enum Account {
    SHOULD_PASS(
            1L,
            "test@e.mail",
            "test_username",
            "test_password",
            "test_display_name",
            LocalDate.of(2003,11,20),
            "test/path"
    );

    private final raveneye.liveflock.entity.Account account;

    Account(
            Long id,
            String email,
            String username,
            String password,
            String display_name,
            LocalDate creation_date,
            String avatar
    ) {
        this.account = new raveneye.liveflock.entity.Account(
                id,
                email,
                username,
                password,
                display_name,
                creation_date,
                avatar
        );
    };

    public raveneye.liveflock.entity.Account getAccount() {
        return account;
    }
}
