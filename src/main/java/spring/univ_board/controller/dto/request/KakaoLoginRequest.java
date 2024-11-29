package spring.univ_board.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import spring.univ_board.domain.value.AccountType;

@Getter
public class KakaoLoginRequest {

    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
    private String email;

    @NotBlank(message = "닉네임은 비어있을 수 없습니다.")
    private String nickname;

    private AccountType accountType;

    protected KakaoLoginRequest() {
    }

    @Builder
    public KakaoLoginRequest(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
