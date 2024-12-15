package spring.univ_board.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "likes")
public class Like extends BaseEntity {

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "item_isbn")
    private String itemIsbn;

    protected Like() {
    }

    @Builder
    public Like(User user, String itemIsbn) {
        this.user = user;
        this.itemIsbn = itemIsbn;
    }
}
