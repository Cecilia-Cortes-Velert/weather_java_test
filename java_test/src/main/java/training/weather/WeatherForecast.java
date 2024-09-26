package training.weather;

import org.json.JSONObject;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class WeatherForecast {

	private final GeocodingService geocodingService;
	private final MeteoService meteoService;
	private final WeatherDataProcessor weatherDataProcessor;

	public WeatherForecast() {
		this.geocodingService = new GeocodingService();
		this.meteoService = new MeteoService();
		this.weatherDataProcessor = new WeatherDataProcessor();
	}

	public Optional<String> getCityWeather(String city, LocalDate date) throws IOException {
		LocalDate targetDate = Optional.ofNullable(date).orElse(LocalDate.now());

		return targetDate.isBefore(LocalDate.now().plusDays(7))
				? geocodingService.getCoordinates(city)
				.flatMap(coordinates -> weatherDataProcessor.getWeatherDescriptionForCoordinates(coordinates, targetDate, meteoService))
				: Optional.empty();
	}
}


