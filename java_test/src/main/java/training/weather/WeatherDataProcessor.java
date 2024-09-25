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

        int dateIndex = findDateIndex(dailyResults, formattedDate);

        if (dateIndex != -1) {
            int weatherCode = weatherCodeResults.getInt(dateIndex);
            return Optional.of(ForecastEnum.getEnumByCode(weatherCode).getDescription());
        }

        return Optional.empty();
    }

    public int findDateIndex(JSONArray dailyResults, String formattedDate) {
        return IntStream.range(0, dailyResults.length())
                .filter(i -> dailyResults.getString(i).equals(formattedDate))
                .findFirst()
                .orElse(-1);
    }
}

