package spring.univ_board.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.univ_board.controller.dto.response.BookListResponse;

import java.io.BufferedReader;
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

    public List<BookListResponse> getBookList(String keyword) throws Exception {
        String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
        String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + encodedKeyword;
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
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        List<BookListResponse> bookListResponses = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String title = object.getString("title");
            String author = object.getString("author");
            String image = object.getString("image");
            String discount = object.getString("discount");
            String publisher = object.getString("publisher");
            String isbn = object.getString("isbn");
            String pubdate = object.getString("pubdate");

            bookListResponses.add(BookListResponse.builder()
                    .title(title)
                    .author(author)
                    .imageURL(image)
                    .discount(discount)
                    .publisher(publisher)
                    .isbn(isbn)
                    .pubdate(pubdate)
                    .build());
        }
        return bookListResponses;
    }
}
