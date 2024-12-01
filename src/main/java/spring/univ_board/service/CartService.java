package spring.univ_board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.univ_board.controller.dto.request.AddToCartRequest;
import spring.univ_board.controller.dto.response.AddToCartResponse;
import spring.univ_board.domain.Cart;
import spring.univ_board.domain.User;
import spring.univ_board.repository.CartRepository;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

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
}
