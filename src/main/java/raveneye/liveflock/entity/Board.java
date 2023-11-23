package raveneye.liveflock.entity;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public record Board(
        @Id
        Long id,
        String title,
        String description,
        LocalDate creation_date,
        String avatar
) {
}
