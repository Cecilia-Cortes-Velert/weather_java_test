package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.json.JSONObject;

import java.io.IOException;

public class MeteoService {

    private static final String METEO_URL = "https://api.open-meteo.com/v1/forecast?daily=weathercode&current_weather=true&timezone=Europe%2FBerlin";

    public JSONObject getWeatherData(String latitude, String longitude) throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(METEO_URL + "&latitude=" + latitude + "&longitude=" + longitude));

        String response = request.execute().parseAsString();
        return new JSONObject(response);
    }
}

