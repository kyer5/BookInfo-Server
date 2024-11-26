package spring.univ_board.controller.dto.response;

import lombok.Getter;

@Getter
public class LoginResponse {

    private Long userId;

    public LoginResponse(Long userId) {
        this.userId = userId;
    }
}
