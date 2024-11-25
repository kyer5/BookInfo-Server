package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class BookListResponse {


    private String title;
    private String author;
    private String imageURL;
    private String discount;
    private String publisher;
    private String isbn;
    private String pubdate;

    @Builder
    public BookListResponse(String title, String author, String imageURL,
                            String discount, String publisher, String isbn, String pubdate) {
        this.title = title;
        this.author = author;
        this.imageURL = imageURL;
        this.discount = discount;
        this.publisher = publisher;
        this.isbn = isbn;
        this.pubdate = pubdate;
    }
}
