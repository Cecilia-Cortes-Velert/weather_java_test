package training.weather;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class WeatherForecastTest {

	private WeatherForecast weatherForecast;
	private MeteoService mockMeteoService;
	private GeocodingService mockGeocodingService;
	private WeatherDataProcessor weatherDataProcessor;
	private Coordinates coordinates;
	private LocalDate targetDate;

	@Before
	public void setUp() throws IOException {
		mockMeteoService = Mockito.mock(MeteoService.class);
		mockGeocodingService = Mockito.mock(GeocodingService.class);
		weatherDataProcessor = new WeatherDataProcessor();
		weatherForecast = new WeatherForecast();
		targetDate = LocalDate.now();
		coordinates = new Coordinates("40.4168", "-3.7038");
		when(mockGeocodingService.getCoordinates("Madrid"))
				.thenReturn(Optional.of(coordinates));
	}

	@Test
	public void testGetWeatherDescriptionForCoordinates_IOException() throws IOException {
		when(mockMeteoService.getWeatherData(coordinates.getLatitude(), coordinates.getLongitude()))
				.thenThrow(new IOException("Simulated IOException"));
		Optional<String> result = weatherDataProcessor.getWeatherDescriptionForCoordinates(coordinates, LocalDate.now(), mockMeteoService);
		assertFalse("Expected Optional to be empty due to IOException", result.isPresent());
	}

	@Test
	public void testGetCityWeather_SuccessfulForecast() throws IOException {
		Optional<String> forecast = weatherForecast.getCityWeather("Madrid", targetDate);
		assertTrue("Forecast should be present", forecast.isPresent());
		forecast.ifPresent(System.out::println);
	}

	@Test
	public void testGetCityWeather_EmptyForecast() throws IOException {
		LocalDate futureDate = targetDate.plusDays(10);
		Optional<String> forecast = weatherForecast.getCityWeather("Madrid", futureDate);
		assertFalse("Forecast should be empty for dates beyond the forecast range", forecast.isPresent());
	}

	@Test
	public void testGetCityWeather_InvalidCity() throws IOException {
		Optional<String> forecast = weatherForecast.getCityWeather("InvalidCityName123", targetDate);
		assertFalse("Forecast should be empty for an invalid city", forecast.isPresent());
	}

}


