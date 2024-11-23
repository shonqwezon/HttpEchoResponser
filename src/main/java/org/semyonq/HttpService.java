package org.semyonq;

import java.io.IOException;
import java.net.*;

public class HttpService {
    private final URL url;

    public HttpService(String server_address) throws BadURLException {
        try {
            url = new URI(server_address).toURL();
            if (!isAvailable())
                throw new UnavailableHostException("Хост недоступен");
        } catch (URISyntaxException | IOException e) {
            throw new BadURLException("Некорректный адрес");
        }
    }

    private boolean isAvailable() throws IOException {
        HttpURLConnection connection = null;
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
}
