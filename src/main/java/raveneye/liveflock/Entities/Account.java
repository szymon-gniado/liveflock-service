package raveneye.liveflock.Entities;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.UUID;

public record Account(
        @Id
        Long id,
        String email,
        String username,
        String password,
        String display_name,
        LocalDate creation_date,
        String avatar
) {
}
