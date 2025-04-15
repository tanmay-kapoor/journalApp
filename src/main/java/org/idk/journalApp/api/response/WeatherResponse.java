package org.idk.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
  private Current current;

  @Getter
  @Setter
  public class Current {

    private int temperature;

    @JsonProperty("weather_descriptions")
    private List<String> weatherDescriptions;

    @JsonProperty("feelslike")
    private int feelsLike;
  }
}