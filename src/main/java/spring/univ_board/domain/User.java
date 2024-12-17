package spring.univ_board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import spring.univ_board.controller.dto.request.KakaoLoginRequest;
import spring.univ_board.controller.dto.request.SignUpRequest;
import spring.univ_board.domain.value.AccountType;

@Entity
@Getter
public class User extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    protected User() {
    }

    @Builder
    public User(String email, String nickname, String password, String phone, AccountType accountType) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.accountType = accountType;
    }

    public static User singUp(SignUpRequest signUpRequest) {
        return User.builder()
                .email(signUpRequest.getEmail())
                .nickname(signUpRequest.getNickname())
                .password(signUpRequest.getPassword())
                .phone(signUpRequest.getPhone())
                .accountType(AccountType.COMMON)
                .build();
    }

    public static User kakaoLogin(KakaoLoginRequest kakaoLoginRequest) {
        return User.builder()
                .email(kakaoLoginRequest.getEmail())
                .nickname(kakaoLoginRequest.getNickname())
                .accountType(AccountType.KAKAO)
                .build();
    }

    public void updatePassword(String currentPassword, String newPassword, String newPasswordCheck) throws IllegalAccessException {
        if (!this.password.equals(currentPassword)) {
            throw new IllegalAccessException("Password incorrect.");
        }
        if(!newPassword.equals(newPasswordCheck)) {
            throw new IllegalAccessException("New password check incorrect.");
        }
        this.password = newPassword;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }
}