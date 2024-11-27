package spring.univ_board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoDto {

    private long id;
    private String nickname;

    @Builder
    public KakaoDto(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
