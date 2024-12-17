package spring.univ_board.controller.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequest {

    private String currentPassword;

    @Size(min = 6, max = 16, message = "비밀번호는 6자 이상, 16자 이하여야 합니다.")
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*~])[a-zA-Z0-9!@#$%^&*~]+$",
            message = "비밀번호는 알파벳, 숫자, 특수문자를 모두 포함해야 합니다."
    )
    private String newPassword;

    @Size(min = 6, max = 16, message = "비밀번호 확인은 6자 이상, 16자 이하여야 합니다.")
    private String newPasswordCheck;

    @Size(min = 2, max = 10, message = "닉네임은 2자 이상, 10자 이하여야 합니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]+$", message = "닉네임은 한글, 영문, 숫자만 가능합니다.")
    private String nickname;

    @Size(min = 7, max = 15, message = "전화번호는 7자 이상, 15자 이하여야 합니다.")
    @Pattern(regexp = "^[0-9]+$", message = "전화번호는 숫자만 가능합니다.")
    private String phone;
}