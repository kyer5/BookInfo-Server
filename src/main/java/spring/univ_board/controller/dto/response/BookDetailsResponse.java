package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookDetailsResponse {

    private String imageURL;
    private String title;
    private String author;
    private String publisher;
    private String pubdate;
    private String price;
    private String description;
    private String isbn;

    @Builder
    public BookDetailsResponse(String imageURL, String title, String author, String publisher,
                               String pubdate, String price, String description, String isbn) {
        this.imageURL = imageURL;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
    }
}
