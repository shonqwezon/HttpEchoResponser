package org.semyonq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class HttpService {
    private final URL url;

    public HttpService(String server_address) throws BadURLException {
        System.out.println("Проверка соединения с " + server_address);
        try {
            url = new URI(server_address).toURL();
            if (!isAvailable())
                throw new UnavailableHostException("Хост недоступен");
        } catch (URISyntaxException | IOException e) {
            throw new BadURLException("Некорректный адрес");
        }
        System.out.println("Адрес доступен!");
    }

    private boolean isAvailable() throws IOException {
        HttpURLConnection connection = null;
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public String[] readData() throws IOException {
        System.out.println("Чтение данных с сервера");
        InputStream in = url.openStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
            response.append(line);

        String json = response.toString();
        json = json.substring(1, json.length() - 1).trim();
        json = json.replace("{", "").trim();
        return json.split("},");
    }
}
