package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInformationResponse {

    private String email;
    private String passwordCheck;
    private String newPassword;
    private String newPasswordCheck;
    private String nickname;
    private String phone;

    @Builder
    public UserInformationResponse(String email, String passwordCheck, String newPassword,
                                   String newPasswordCheck, String nickname, String phone) {
        this.email = email;
        this.passwordCheck = passwordCheck;
        this.newPassword = newPassword;
        this.newPasswordCheck = newPasswordCheck;
        this.nickname = nickname;
        this.phone = phone;
    }
}
