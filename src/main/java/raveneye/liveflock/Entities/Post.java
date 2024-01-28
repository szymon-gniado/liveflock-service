package raveneye.liveflock.Entities;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public record Post(
        @Id
        Long id,
        Long author,
        String title,
        String content,
        LocalDate creation_date,
        Long parent
) {
}
