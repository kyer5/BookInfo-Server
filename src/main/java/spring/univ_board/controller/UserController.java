package spring.univ_board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.univ_board.api.KakaoAuthApiService;
import spring.univ_board.controller.dto.ResponseDto;
import spring.univ_board.controller.dto.request.SignUpRequest;
import spring.univ_board.controller.dto.response.SignUpResponse;
import spring.univ_board.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final KakaoAuthApiService kakaoAuthApiService;
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) { // 웹 페이지로 사용자를 리다이렉트하고, 해당 페이지에서는 Kakao 로그인 URL을 사용할 수 있도록 모델에 추가
        model.addAttribute("kakaoUrl", kakaoAuthApiService.getKakaoLogin());
        return "user/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseDto<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        Long accountId = userService.signUp(signUpRequest);
        log.info("accountId = " + accountId);
        SignUpResponse signUpResponse = new SignUpResponse(accountId);
        return ResponseDto.of(signUpResponse, "A new user account has been created.");
    }

}
