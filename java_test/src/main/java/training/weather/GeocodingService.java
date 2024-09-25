package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;

public class GeocodingService {

    private static final String GEOCODE_URL = "https://geocode.xyz/";

    public Optional<Coordinates> getCoordinates(String city) throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(GEOCODE_URL + city + "?json=1"));

        String response = request.execute().parseAsString();
        JSONObject locationJson = new JSONObject(response);

        return Optional.of(locationJson)
                .filter(json -> json.has("longt") && json.has("latt"))
                .map(json -> new Coordinates(json.getString("latt"), json.getString("longt")));
    }
}
