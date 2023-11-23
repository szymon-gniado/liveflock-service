package raveneye.liveflock.entity;

import org.springframework.data.annotation.Id;

public record Board_post(
        @Id
        Long board,
        @Id
        Long post
) {
}
