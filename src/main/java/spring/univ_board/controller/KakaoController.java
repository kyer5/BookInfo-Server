package spring.univ_board.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.univ_board.service.KakaoService;

@Controller
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/login")
    public String kakaoLogin(HttpServletRequest request) throws Exception {
        kakaoService.getKakaoInfo(request.getParameter("code"));
        return "redirect:/";
    }
}