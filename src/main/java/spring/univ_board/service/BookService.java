package spring.univ_board.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.univ_board.api.NaverApiService;
import spring.univ_board.controller.dto.response.BookDetailsResponse;
import spring.univ_board.controller.dto.response.BookListResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BookService {

    private final NaverApiService naverApiService;

    public List<BookListResponse> getBookList(String keyword) throws IOException {
        String apiUrl = naverApiService.getApiUrl();
        String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
        String apiURL = apiUrl + encodedKeyword;

        JSONObject jsonObject = naverApiService.getBookInformation(apiURL);
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        List<BookListResponse> bookListResponses = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject bookInformation = jsonArray.getJSONObject(i);
            String image = bookInformation.getString("image");
            String title = bookInformation.getString("title");
            String author = bookInformation.getString("author");
            String publisher = bookInformation.getString("publisher");
            String pubdate = bookInformation.getString("pubdate");
            String price = bookInformation.getString("discount");
            String isbn = bookInformation.getString("isbn");

            bookListResponses.add(BookListResponse.builder()
                    .imageURL(image)
                    .title(title)
                    .author(author)
                    .publisher(publisher)
                    .pubdate(pubdate)
                    .price(price)
                    .isbn(isbn)
                    .build());
        }
        return bookListResponses;
    }

    public BookDetailsResponse getBookDetails(String isbnKey) throws IOException {
        String encodedIsbn = URLEncoder.encode(isbnKey, "UTF-8");
        String apiURL = naverApiService.getApiUrl() + encodedIsbn;

        JSONObject jsonObject = naverApiService.getBookInformation(apiURL);
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        JSONObject bookInformation = jsonArray.getJSONObject(0);

        String image = bookInformation.getString("image");
        String title = bookInformation.getString("title");
        String author = bookInformation.getString("author");
        String publisher = bookInformation.getString("publisher");
        String pubdate = bookInformation.getString("pubdate");
        String price = bookInformation.getString("discount");
        String description = bookInformation.getString("description");
        String isbn = bookInformation.getString("isbn");

        BookDetailsResponse bookDetailsResponse = BookDetailsResponse.builder()
                .imageURL(image)
                .title(title)
                .author(author)
                .publisher(publisher)
                .pubdate(pubdate)
                .price(price)
                .description(description)
                .isbn(isbn)
                .build();

        return bookDetailsResponse;
    }
}
