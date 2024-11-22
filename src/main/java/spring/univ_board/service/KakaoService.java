package spring.univ_board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.univ_board.repository.KakaoRepository;

@RequiredArgsConstructor // 의존성 주입
@Service
public class KakaoService {

    private final KakaoRepository kakaoRepository;
}
