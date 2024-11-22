package spring.univ_board.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KakaoDto {

    private long id;
    private String nickname;
}
