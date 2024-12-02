package spring.univ_board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PaymentsResponse {

    private String address;
    private int itemTotalPrice;
    private int deliverFee = 3000;
    private int paymentsTotalPrice;

    @Builder

    public PaymentsResponse(String address, int itemTotalPrice, int paymentsTotalPrice) {
        this.address = address;
        this.itemTotalPrice = itemTotalPrice;
        this.paymentsTotalPrice = paymentsTotalPrice;
    }
}
