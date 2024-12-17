package spring.univ_board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.univ_board.controller.dto.request.ReviewCreateRequest;
import spring.univ_board.controller.dto.response.ReviewCreateResponse;
import spring.univ_board.domain.Review;
import spring.univ_board.domain.User;
import spring.univ_board.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewCreateResponse createReview(User user, ReviewCreateRequest reviewCreateRequest) {
        Review review = Review.builder()
                .user(user)
                .isbn(reviewCreateRequest.getIsbn())
                .title(reviewCreateRequest.getTitle())
                .content(reviewCreateRequest.getContent())
                .build();
        reviewRepository.save(review);
        return new ReviewCreateResponse(review.getId());
    }
}
