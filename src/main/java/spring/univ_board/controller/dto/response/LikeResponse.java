package spring.univ_board.controller.dto.response;

import lombok.Getter;

@Getter
public class LikeResponse {

    private Long likeId;

    public LikeResponse(Long likeId) {
        this.likeId = likeId;
    }
}
