package spring.univ_board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Cart extends BaseEntity {

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "item_isbn")
    private String itemIsbn;

    @Column(name = "item_count")
    private int itemCount;

    protected Cart() {
    }

    @Builder
    public Cart(User user, String itemIsbn, int itemCount) {
        this.user = user;
        this.itemIsbn = itemIsbn;
        this.itemCount = itemCount;
    }

    public void increaseCount(int itemCount) {
        this.itemCount += itemCount;
    }
}
