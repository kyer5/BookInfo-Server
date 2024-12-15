package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeListResponse {

    private String isbn;
    private String imageURL;
    private String title;
    private String price;

    @Builder
    public LikeListResponse(String isbn, String imageURL, String title, String price) {
        this.isbn = isbn;
        this.imageURL = imageURL;
        this.title = title;
        this.price = price;
    }
}
