package spring.univ_board.api;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class NaverApiService {

    private final String CLIENT_ID = "nIwTTBRmq7Fwp1TqB1yo";
    private final String CLIENT_SECRET = "2IyFHrl8kR";
    private final String API_URL = "https://openapi.naver.com/v1/search/book.json?query=";

    public String getApiUrl() {
        return API_URL;
    }

    public JSONObject getBookInformation(String apiURL) throws IOException {
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

}
