package org.semyonq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static String url = "https://fake-json-api.mock.beeceptor.com/users";

    public static void main(String[] args) {
        System.out.println("Запуск программы");

        List<User> users = new ArrayList<>();
        try {
            HttpService httpService = new HttpService(url);
            String[] res = httpService.readData();
            System.out.println("Данные получены. Идёт преобразование в объекты...");
            for(String json : res)
                users.add(new User().toUser(json));
        } catch (BadURLException e) {
            System.out.println("Ошибка подключения к хосту: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Невозможно прочитать данные: " + e.getMessage());
        } catch (BadKeyException e) {
            System.out.println("Ошибка: Ожидались другие данные");
        }

        System.out.println("Полученные пользователи:");
        for (int i = 0; i < users.size(); i++)
            System.out.println(i + 1 + ". " + users.get(i));

        System.out.println("Завершение программы");
    }
}