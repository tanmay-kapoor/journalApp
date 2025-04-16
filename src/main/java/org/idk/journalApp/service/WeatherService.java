package org.idk.journalApp.service;

import org.idk.journalApp.api.response.WeatherResponse;
import org.idk.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

  @Value("${weather.api.key}")
  private String apiKey;

  @Autowired
  private AppCache appCache;

  @Autowired
  private RestTemplate restTemplate;

  public WeatherResponse getWeather(String city) {
    String finalUrl = appCache.APP_CACHE
            .get("weather_api")
            .replace("<city>", city)
            .replace("<apiKey>", apiKey);

    ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);
    return response.getBody();
  }

//  public String postRequest() {
//    HttpHeaders headers = new HttpHeaders();
//    headers.set("key", "value");
//
//    User user = User.builder().username("journal").password("password").build();
//    HttpEntity<User> request = new HttpEntity<>(user, headers);
//
//    ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
//    return response.getBody();
//  }
}
