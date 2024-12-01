package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class BookListResponse {

    private String imageURL;
    private String title;
    private String author;
    private String publisher;
    private String pubdate;
    private String price;
    private String isbn;

    @Builder
    public BookListResponse(String imageURL, String title, String author,
                            String publisher, String pubdate, String price, String isbn) {
        this.imageURL = imageURL;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.price = price;
        this.isbn = isbn;
    }
}
