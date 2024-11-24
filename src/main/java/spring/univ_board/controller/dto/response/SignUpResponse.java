package spring.univ_board.controller.dto.response;

import lombok.Getter;

@Getter
public class SignUpResponse {

    private final Long accountId;

    public SignUpResponse(Long accountId) {
        this.accountId = accountId;
    }
}
