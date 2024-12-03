package spring.univ_board.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.univ_board.controller.dto.ResponseDto;
import spring.univ_board.controller.dto.request.LoginRequest;
import spring.univ_board.controller.dto.request.SignUpRequest;
import spring.univ_board.controller.dto.response.LoginResponse;
import spring.univ_board.controller.dto.response.SignUpResponse;
import spring.univ_board.controller.dto.response.UserInformationResponse;
import spring.univ_board.domain.User;
import spring.univ_board.service.KakaoService;
import spring.univ_board.service.UserService;

import javax.security.sasl.AuthenticationException;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final KakaoService kakaoService;
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseDto<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) throws AuthenticationException {
        LoginResponse loginResponse = userService.login(loginRequest);
        User user = userService.findUserById(loginResponse.getUserId());
        session.setAttribute("user", user);
        return ResponseDto.of(loginResponse, "You have successfully logged in.");
    }

    @GetMapping("/login/kakao")
    public String kakaoLogin() {
        String kakaoUrl = kakaoService.getKakaoLogin();
        return "redirect:" + kakaoUrl;
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseDto<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        Long accountId = userService.signUp(signUpRequest);
        log.info("accountId = " + accountId);
        SignUpResponse signUpResponse = new SignUpResponse(accountId);
        return ResponseDto.of(signUpResponse, "A new user account has been created.");
    }

    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        UserInformationResponse userInformationResponse = userService.getUserInformation(user);
        model.addAttribute("userInformationResponse", userInformationResponse);
        return "user/mypage";
    }
}
