package spring.univ_board.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.univ_board.controller.dto.response.BookDetailsResponse;
import spring.univ_board.controller.dto.response.BookListResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class BookService {

    private final String CLIENT_ID = "nIwTTBRmq7Fwp1TqB1yo";
    private final String CLIENT_SECRET = "2IyFHrl8kR";
    private final String API_URL = "https://openapi.naver.com/v1/search/book.json?query=";

    private JSONObject getBookInformation(String apiURL) throws IOException {
        URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
        connection.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);

        int responseCode = connection.getResponseCode();
        BufferedReader br;
        if (responseCode == 200) {
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        JSONObject jsonObject = new JSONObject(response.toString());
        return jsonObject;
    }

    public List<BookListResponse> getBookList(String keyword) throws IOException {
        String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
        String apiURL = API_URL + encodedKeyword;

        JSONObject jsonObject = getBookInformation(apiURL);
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        List<BookListResponse> bookListResponses = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject bookInformation = jsonArray.getJSONObject(i);
            String image = bookInformation.getString("image");
            String title = bookInformation.getString("title");
            String author = bookInformation.getString("author");
            String publisher = bookInformation.getString("publisher");
            String pubdate = bookInformation.getString("pubdate");
            String discount = bookInformation.getString("discount");
            String isbn = bookInformation.getString("isbn");

            bookListResponses.add(BookListResponse.builder()
                    .imageURL(image)
                    .title(title)
                    .author(author)
                    .publisher(publisher)
                    .pubdate(pubdate)
                    .discount(discount)
                    .isbn(isbn)
                    .build());
        }
        return bookListResponses;
    }

}
