package training.weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.IntStream;

public class WeatherDataProcessor {

    public String formatTargetDate(LocalDate targetDate) {
        return targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Optional<String> getWeatherDescriptionForDate(JSONObject weatherData, String formattedDate) {
        JSONArray dailyResults = weatherData.getJSONObject("daily").getJSONArray("time");
        JSONArray weatherCodeResults = weatherData.getJSONObject("daily").getJSONArray("weathercode");

        return IntStream.range(0, dailyResults.length())
                .filter(i -> formattedDate.equals(dailyResults.getString(i)))
                .mapToObj(i -> ForecastEnum.getEnumByCode(weatherCodeResults.getInt(i)))
                .map(ForecastEnum::getDescription)
                .findFirst();
    }
}

