package spring.univ_board.controller.dto.response;

import lombok.Getter;

@Getter
public class UserUpdateResponse {

    private Long userId;

    public UserUpdateResponse(Long userId) {
        this.userId = userId;
    }
}
