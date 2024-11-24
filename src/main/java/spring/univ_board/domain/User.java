package spring.univ_board.domain;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import spring.univ_board.controller.dto.request.SignUpRequest;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class User extends BaseEntity {

    private String email;
    private String nickname;
    private String password;
    private String phone;

    @Builder
    public User(String email, String nickname, String password, String phone) {
        this();
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
    }

    public static User singUp(SignUpRequest signUpRequest) {
        return User.builder()
                .email(signUpRequest.getEmail())
                .nickname(signUpRequest.getNickname())
                .password(signUpRequest.getPassword())
                .phone(signUpRequest.getPhone())
                .build();
    }
}
