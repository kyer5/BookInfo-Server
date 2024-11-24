package spring.univ_board.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import spring.univ_board.domain.KakaoUser;
import spring.univ_board.api.dto.KakaoDto;
import spring.univ_board.repository.KakaoRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor // 의존성 주입
public class KakaoAuthApiService {

    @Autowired
    private final KakaoRepository kakaoRepository;

    @Value("${kakao.client.id}")
    private String KAKAO_CLIENT_ID;

    @Value("${kakao.client.secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${kakao.redirect.url}")
    private String KAKAO_REDIRECT_URL;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com"; // 카카오 계정 인증을 위한 URI
    private final static String KAKAO_API_URI = "https://kapi.kakao.com"; // 카카오 API에 접근할 때 사용되는 URI

    public String getKakaoLogin() {
        return KAKAO_AUTH_URI + "/oauth/authorize"
                + "?client_id=" + KAKAO_CLIENT_ID
                + "&redirect_uri=" + KAKAO_REDIRECT_URL
                + "&response_type=code";
    }
    // https://kauth.kakao.com/oauth/logout?client_id=KAKAO_CLIENT_ID&redirect_uri=KAKAO_REDIRECT_URL&response_type=code

    public KakaoDto getKakaoInfo(String code) throws Exception {
        if (code == null) throw new Exception("Failed get authorization code"); // kakao 인증 코드가 null인 경우 예외 발생

        String accessToken = "";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded");
            // HTTP 요청에서 사용되는 콘텐츠 유형(Content-Type) : 웹 폼 데이터를 서버로 전송하는 데 주로 사용

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", KAKAO_CLIENT_ID);
            params.add("client_secret", KAKAO_CLIENT_SECRET);
            params.add("code", code);
            params.add("redirect_uri", KAKAO_REDIRECT_URL);
            // kakao API와의 통신을 위해 필요한 파라미터

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers); // HTTP 요청에 필요한 헤더와 바디 생성

            // RestTemplate을 사용하여 서버로 HTTP POST 요청을 보냄
            // exchange() 메소드로 api를 호출함
            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_AUTH_URI + "/oauth/token",
                    HttpMethod.POST,
                    httpEntity,
                    String.class // 응답 형식
            );

            // JSON 형식의 문자열을 Java 객체로 변환
            JSONParser jsonParser = new JSONParser();
            // HTTP 응답에서 JSON 문자열을 가져와 파싱
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

            accessToken = (String) jsonObj.get("access_token");
        } catch (Exception e) {
            throw new Exception("API call failed " + e.getMessage());
        }
        return getUserInfoWithToke(accessToken);
    }

    private KakaoDto getUserInfoWithToke(String accessToken) throws Exception {
        // HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader 담기
        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                KAKAO_API_URI + "/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        // Response 데이터 파싱
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());
        JSONObject account = (JSONObject) jsonObj.get("kakao_account");
        JSONObject profile = (JSONObject) account.get("profile");

        long id = (long) jsonObj.get("id"); // 사용자의 고유 ID는 "id" 키에서 추출
        String nickname = String.valueOf(profile.get("nickname"));

        KakaoUser kakaoUser = new KakaoUser();
        kakaoUser.setUsername(nickname);
        kakaoRepository.save(kakaoUser);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        // RequestContextHolder.currentRequestAttributes(): 현재 스레드에 바인딩된 요청 속성을 반환

        HttpSession session = request.getSession(); // 현재 요청에 대한 세션을 얻음
        session.setAttribute("member", kakaoUser);

        return KakaoDto.builder()
                .id(id)
                .nickname(nickname).build();
    }
}
