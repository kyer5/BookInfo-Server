package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import spring.univ_board.domain.value.AccountType;

@Getter
public class UserInformationResponse {

    private String email;
    private String nickname;
    private String phone;
    private AccountType accountType;

    @Builder

    public UserInformationResponse(String email, String nickname, String phone, AccountType accountType) {
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.accountType = accountType;
    }
}
