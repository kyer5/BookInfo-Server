package spring.univ_board.controller.dto;

import lombok.Getter;

@Getter
public class DefaultResponseData<T> implements ResponseData<T> {

    private final T body;

    protected DefaultResponseData(T body) {
        this.body = body;
    }
}
