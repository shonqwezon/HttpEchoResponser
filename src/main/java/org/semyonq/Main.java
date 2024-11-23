package org.semyonq;

public class Main {
    private final static String url = "https://fake-json-api.mock.beeceptor.com/";

    public static void main(String[] args) {
        try {
            HttpService httpService = new HttpService(url);
        } catch (BadURLException e) {
            System.out.println("Ошибка подключения к хосту: " + e.getMessage());
        }
    }
}