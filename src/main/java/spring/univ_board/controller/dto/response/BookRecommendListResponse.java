package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookRecommendListResponse {

    private String imageURL;
    private String title;

    @Builder
    public BookRecommendListResponse(String imageURL, String title) {
        this.imageURL = imageURL;
        this.title = title;
    }
}
