package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInformationResponse {

    private String email;
    private String nickname;
    private String phone;

    @Builder
    public UserInformationResponse(String email, String nickname, String phone) {
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
    }
}
