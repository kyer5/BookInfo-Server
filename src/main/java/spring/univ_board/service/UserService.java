package spring.univ_board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.univ_board.controller.dto.request.SignUpRequest;
import spring.univ_board.domain.User;
import spring.univ_board.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long signUp(final SignUpRequest signUpRequest) {
        User signUpUser = User.singUp(signUpRequest);
        User savedUser = userRepository.save(signUpUser);
        return savedUser.getId();
    }
}
