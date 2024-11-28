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
    private String discount;
    private String description;
    private String isbn;

    @Builder
    public BookDetailsResponse(String imageURL, String title, String author, String publisher,
                               String pubdate, String discount, String description, String isbn) {
        this.imageURL = imageURL;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.discount = discount;
        this.description = description;
        this.isbn = isbn;
    }


}
