package spring.univ_board.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.univ_board.api.NaverApiService;
import spring.univ_board.controller.dto.request.AddToCartRequest;
import spring.univ_board.controller.dto.response.AddToCartResponse;
import spring.univ_board.controller.dto.response.CartListResponse;
import spring.univ_board.controller.dto.response.PaymentsResponse;
import spring.univ_board.domain.Cart;
import spring.univ_board.domain.User;
import spring.univ_board.repository.CartRepository;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final NaverApiService naverApiService;

    @Transactional
    public AddToCartResponse addItem(User user, AddToCartRequest addToCartRequest) {
        Cart cart = cartRepository.findByUserAndItemIsbn(user, addToCartRequest.getIsbn());
        if (cart != null) {
            cart.increaseCount(addToCartRequest.getItemCount());
        } else {
            cart = Cart.builder()
                    .user(user)
                    .itemIsbn(addToCartRequest.getIsbn())
                    .itemCount(addToCartRequest.getItemCount())
                    .build();
        }
        cartRepository.save(cart);
        return new AddToCartResponse(cart.getId());
    }

    public List<CartListResponse> getCartList(User user) throws IOException {
        List<Cart> carts = cartRepository.findByUser(user);
        List<CartListResponse> cartListResponses = new ArrayList<>();
        int totalPrice = 0;
        for (int i = 0; i < carts.size(); i++) {
            String itemIsbn = carts.get(i).getItemIsbn();
            System.out.println("itemIsbn = " + itemIsbn);
            String encodedIsbn = URLEncoder.encode(itemIsbn, "UTF-8");
            String apiURL = naverApiService.getApiUrl() + encodedIsbn;
            JSONObject jsonObject = naverApiService.getBookInformation(apiURL);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            JSONObject bookInformation = jsonArray.getJSONObject(0);

            int price = Integer.parseInt(bookInformation.getString("discount"));
            totalPrice += price;

            cartListResponses.add(CartListResponse.builder()
                    .imageURL(bookInformation.getString("image"))
                    .title(bookInformation.getString("title"))
                    .count(1)
                    .price(bookInformation.getString("discount"))
                    .build());
        }
        return cartListResponses;
    }

    public PaymentsResponse getPaymentsInformation(List<CartListResponse> cartListResponses) {
        int itemTotalPrice = 0;
        for (int i = 0; i < cartListResponses.size(); i++) {
            itemTotalPrice += Integer.parseInt(cartListResponses.get(i).getPrice());
        }
        return PaymentsResponse.builder()
                .address(null)
                .itemTotalPrice(itemTotalPrice)
                .paymentsTotalPrice(itemTotalPrice + 3000)
                .build();
    }
}
