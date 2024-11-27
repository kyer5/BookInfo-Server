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

import javax.security.sasl.AuthenticationException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public LoginResponse login(final LoginRequest loginRequest) throws AuthenticationException {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        User user = userRepository.findByEmail(email).orElseThrow();
        validatePassword(password, user);
        return new LoginResponse(user.getId());
    }

    @Transactional
    public Long signUp(final SignUpRequest signUpRequest) {
        User signUpUser = User.singUp(signUpRequest);
        User savedUser = userRepository.save(signUpUser);
        return savedUser.getId();
    }

    private void validatePassword(String password, User user) throws AuthenticationException {
        if (password.equals(user.getPassword())) {
            log.info("Login successful.");
        } else {
            log.info("Password incorrect.");
            throw new AuthenticationException("Password incorrect.");
        }
    }
}
