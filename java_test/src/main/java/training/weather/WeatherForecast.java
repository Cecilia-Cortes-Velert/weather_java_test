package training.weather;

import org.json.JSONObject;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class WeatherForecast {

	private final GeocodingService geocodingService;
	private final MeteoService meteoService;
	private final WeatherDataProcessor weatherDataProcessor;

	public WeatherForecast(GeocodingService geocodingService, MeteoService meteoService, WeatherDataProcessor weatherDataProcessor) {
		this.geocodingService = geocodingService;
		this.meteoService = meteoService;
		this.weatherDataProcessor = weatherDataProcessor;
	}

	public Optional<String> getCityWeather(String city, LocalDate date) throws IOException {
		LocalDate targetDate = Optional.ofNullable(date).orElse(LocalDate.now());

		return targetDate.isBefore(LocalDate.now().plusDays(7))
				? geocodingService.getCoordinates(city)
				.flatMap(coordinates -> weatherDataProcessor.getWeatherDescriptionForCoordinates(coordinates, targetDate, meteoService))
				: Optional.empty();
	}
}


