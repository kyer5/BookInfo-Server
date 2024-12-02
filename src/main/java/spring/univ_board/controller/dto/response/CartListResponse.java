package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartListResponse {

    private String isbn;
    private String imageURL;
    private String title;
    private int count;
    private String price;

    @Builder
    public CartListResponse(String isbn, String imageURL, String title, int count, String price) {
        this.isbn = isbn;
        this.imageURL = imageURL;
        this.title = title;
        this.count = count;
        this.price = price;
    }
}
