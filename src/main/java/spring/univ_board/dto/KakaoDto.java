package spring.univ_board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoDto {

    private String nickname;
    private String email;

    @Builder
    public KakaoDto(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }
}
