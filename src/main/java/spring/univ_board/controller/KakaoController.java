package spring.univ_board.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.univ_board.controller.dto.ResponseDto;
import spring.univ_board.dto.KakaoDto;
import spring.univ_board.service.KakaoService;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/login")
    public ResponseDto<KakaoDto> kakaoLogin(HttpServletRequest request) throws Exception {
        KakaoDto kakaoDto = kakaoService.getKakaoInfo(request.getParameter("code"));
        return ResponseDto.of(kakaoDto, "You have successfully logged in with your Kakao account.");
    }
}
