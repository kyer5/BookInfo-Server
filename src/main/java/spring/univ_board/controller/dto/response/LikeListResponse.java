package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeListResponse {

    private Long id;
    private String isbn;
    private String imageURL;
    private String title;
    private String price;

    @Builder
    public LikeListResponse(Long id, String isbn, String imageURL, String title, String price) {
        this.id = id;
        this.isbn = isbn;
        this.imageURL = imageURL;
        this.title = title;
        this.price = price;
    }
}
