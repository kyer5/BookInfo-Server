package spring.univ_board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.univ_board.controller.dto.request.LoginRequest;
import spring.univ_board.controller.dto.request.SignUpRequest;
import spring.univ_board.controller.dto.response.LoginResponse;
import spring.univ_board.domain.User;
import spring.univ_board.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public LoginResponse login(final LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        User user = userRepository.findByEmail(email).orElseThrow();
        return checkPassword(password, user);
    }

    @Transactional
    public Long signUp(final SignUpRequest signUpRequest) {
        User signUpUser = User.singUp(signUpRequest);
        User savedUser = userRepository.save(signUpUser);
        return savedUser.getId();
    }

    private LoginResponse checkPassword(String password, User user) {
        if (password.equals(user.getPassword())) {
            log.info("Login successful.");
            return new LoginResponse(user.getId());
        } else {
            log.info("Password incorrect.");
            return null;
        }
    }
}
