package spring.univ_board.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.univ_board.domain.MsgEntity;
import spring.univ_board.api.dto.KakaoDto;
import spring.univ_board.api.KakaoAuthApiService;

@RestController // RESTful 웹 서비스의 컨트롤러
@RequiredArgsConstructor
@RequestMapping("kakao")
public class KakaoController {

    private final KakaoAuthApiService kakaoAuthApiService;

    @GetMapping("/login")
    public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception {
        KakaoDto kakaoInfo = kakaoAuthApiService.getKakaoInfo(request.getParameter("code"));

        return ResponseEntity.ok()
                .body(new MsgEntity("Success", kakaoInfo));
        // HTTP 응답 생성, OK 응답을 반환하며, 본문(body)으로 MsgEntity 객체를 담고 있음
    }
}
