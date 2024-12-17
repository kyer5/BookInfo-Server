package spring.univ_board.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "review")
public class Review extends BaseEntity{

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "item_isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    protected Review() {

    }

    @Builder
    public Review(User user, String isbn, String title, String content) {
        this.user = user;
        this.isbn = isbn;
        this.title = title;
        this.content = content;
    }
}
