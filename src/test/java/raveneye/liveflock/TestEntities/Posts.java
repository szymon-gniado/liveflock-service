package raveneye.liveflock.TestEntities;

import raveneye.liveflock.entity.Post;

import java.time.LocalDate;

public enum Posts {
    SHOULD_PASS(
            1L,
            1L,
            "Test Title",
            "Test content",
            LocalDate.of(2003,11,20),
            1L
    );

    private final Post post;

    Posts(
            Long id,
            Long author,
            String title,
            String content,
            LocalDate creation_date,
            Long parent
    ) {
        this.post = new Post(
                id,
                author,
                title,
                content,
                creation_date,
                parent
        );
    };

    public Post getPost() {
        return this.post;
    }
}
