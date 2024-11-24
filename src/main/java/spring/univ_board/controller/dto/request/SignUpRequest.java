package spring.univ_board.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
    @Email
    private String email;

    @NotBlank(message = "닉네임은 비어있을 수 없습니다.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상, 10자 이하여야 합니다.")
    @Pattern(regexp = "^[가-힣a-zA-z0-9]+$", message = "닉네임은 한글, 영문, 숫자만 가능합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    @Size(min = 6, max = 16, message = "비밀번호는 6자 이상, 16자 이하여야 합니다.")
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*~])[a-zA-Z0-9!@#$%^&*~]+$",
            message = "비밀번호는 알파벳, 숫자, 특수문자를 모두 포함해야 합니다."
    )
    private String password;

    @NotBlank(message = "비밀번호 확인은 비어있을 수 없습니다.")
    @Size(min = 6, max = 16, message = "비밀번호 확인은 6자 이상, 16자 이하여야 합니다.")
    private String passwordCheck;

    @NotBlank(message = "전화번호는 비어있을 수 없습니다.")
    @Size(min = 7, max = 15, message = "전화번호는 7자 이상, 15자 이하여야 합니다.")
    @Pattern(regexp = "^[0-9]+$", message = "전화번호는 숫자만 가능합니다.")
    private String phone;

    protected SignUpRequest() {
    }

    @Builder
    protected SignUpRequest(String email, String nickname, String password, String passwordCheck, String phone) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.phone = phone;
    }
}
