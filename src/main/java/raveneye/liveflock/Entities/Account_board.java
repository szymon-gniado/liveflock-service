package raveneye.liveflock.Entities;

import org.springframework.data.annotation.Id;

public record Account_board(
        @Id
        Long account,
        @Id
        Long board,
        boolean owner,
        boolean editor
) {
}
