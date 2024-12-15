package spring.univ_board.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.univ_board.api.NaverApiService;
import spring.univ_board.controller.dto.request.LikeRequest;
import spring.univ_board.controller.dto.response.LikeListResponse;
import spring.univ_board.controller.dto.response.LikeResponse;
import spring.univ_board.domain.Like;
import spring.univ_board.domain.User;
import spring.univ_board.repository.LikeRepository;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final NaverApiService naverApiService;

    @Transactional
    public LikeResponse addItem(User user, LikeRequest likeRequest) {
        Like like = Like.builder()
                .user(user)
                .itemIsbn(likeRequest.getIsbn())
                .build();
        likeRepository.save(like);
        return new LikeResponse(like.getId());
    }

    public List<LikeListResponse> getLikeList(User user) throws IOException {
        List<Like> likes = likeRepository.findByUser(user);
        List<LikeListResponse> likeListResponses = new ArrayList<>();
        for (int i = 0; i < likes.size(); i++) {
            String itemIsbn = likes.get(i).getItemIsbn();
            String encodedIsbn = URLEncoder.encode(itemIsbn, "UTF-8");
            String apiURL = naverApiService.getApiUrl() + encodedIsbn;
            JSONObject jsonObject = naverApiService.getBookInformation(apiURL);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            JSONObject bookInformation = jsonArray.getJSONObject(0);

            likeListResponses.add(LikeListResponse.builder()
                    .imageURL(bookInformation.getString("image"))
                    .title(bookInformation.getString("title"))
                    .price(bookInformation.getString("discount"))
                    .build());
        }
        return likeListResponses;
    }

    public LikeResponse cancelLike(Long likeId) {
        likeRepository.deleteById(likeId);
        return new LikeResponse(likeId);
    }
}
