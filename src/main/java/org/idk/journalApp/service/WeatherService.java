package org.idk.journalApp.service;

import org.idk.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

  private static final String apiKey = "1bec392b4da97c9934211168e87141ac";

  private static final String URL = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

  @Autowired
  private RestTemplate restTemplate;

  public WeatherResponse getWeather(String city) {
    String finalUrl = URL.replace("CITY", city).replace("API_KEY", apiKey);

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
