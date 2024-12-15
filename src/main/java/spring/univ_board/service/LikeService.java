package spring.univ_board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.univ_board.controller.dto.request.LikeRequest;
import spring.univ_board.controller.dto.response.LikeResponse;
import spring.univ_board.domain.Like;
import spring.univ_board.domain.User;
import spring.univ_board.repository.LikeRepository;


@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    @Transactional
    public LikeResponse addItem(User user, LikeRequest likeRequest) {
        Like like = Like.builder()
                .user(user)
                .itemIsbn(likeRequest.getIsbn())
                .build();
        likeRepository.save(like);
        return new LikeResponse(like.getId());
    }
}
