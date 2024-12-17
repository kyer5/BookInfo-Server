package spring.univ_board.controller.dto.response;

import lombok.Getter;

@Getter
public class ReviewCreateResponse {

    private Long reviewId;

    public ReviewCreateResponse(Long reviewId) {
        this.reviewId = reviewId;
    }
}
