package spring.univ_board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.univ_board.controller.dto.request.ReviewCreateRequest;
import spring.univ_board.controller.dto.response.ReviewCreateResponse;
import spring.univ_board.controller.dto.response.ReviewListResponse;
import spring.univ_board.domain.Review;
import spring.univ_board.domain.User;
import spring.univ_board.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

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

    public List<ReviewListResponse> getReviewList(String isbn) {
        List<Review> reviews = reviewRepository.findByIsbn(isbn);
        List<ReviewListResponse> reviewListResponses = new ArrayList<>();
        for (int i = 0; i < reviews.size(); i++) {
            reviewListResponses.add(ReviewListResponse.builder()
                    .id(reviews.get(i).getId())
                    .title(reviews.get(i).getTitle())
                    .content(reviews.get(i).getContent())
                    .reviewer(reviews.get(i).getUser().getNickname())
                    .createdAt(reviews.get(i).getCreatedAt().toLocalDate())
                    .build());
        }
        return reviewListResponses;
    }
}
