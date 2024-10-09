package org.example;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int mode = 0;

        System.out.println("Выберите режим работы:\nВведите 1 для поиска температуры на данный момент\nВведите 2 для поиска средней температуры на интервале");

        while (!Arrays.asList(new Integer[]{1, 2})
                .contains(sc.hasNextInt() ? mode = sc.nextInt() : sc.next())) {
            System.out.println("Введите 1 или 2");
        }

        switch (mode) {
            case 1:
                modeOne(sc);
                break;
            case 2:
                modeTwo(sc);
                break;
        }
    }

    public static void modeOne(Scanner sc) {
        float lat = setLat(sc);
        float lon = setLon(sc);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("https://api.weather.yandex.ru/v2/forecast?lat=%s&lon=%s", lat, lon)))
                .header("X-Yandex-Weather-Key", "ae492382-dbbd-422e-afd5-f450a3d1985b")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Body: " + response.body());
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            System.out.println("Temp:" + jsonObject.get("fact").getAsJsonObject().get("temp").getAsString());
        } catch (Exception e) {
            System.err.println("Error making HTTP request: " + e.getMessage());
        }
    }

    public static void modeTwo(Scanner sc) {
        float lat = setLat(sc);
        float lon = setLon(sc);

        long daysCount;
        System.out.println("Введите лимит - кол-во дней для подсчёта средней температуры (максимум 7)");

        while (true) {
            try {
                if (sc.hasNextInt()) {
                    daysCount = sc.nextInt();
                    if (daysCount > 7 || daysCount <= 0) {
                        throw new Exception();
                    } else {
                        break;
                    }
                } else throw new Exception();
            } catch (Exception e) {
                System.out.println("Введите число от 1 до 7");
            }
        }

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("https://api.weather.yandex.ru/v2/forecast?lat=%s&lon=%s", lat, lon)))
                .header("X-Yandex-Weather-Key", "ae492382-dbbd-422e-afd5-f450a3d1985b")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Body: " + response.body());
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray forecasts = jsonObject.getAsJsonArray("forecasts");
            float avgTemp = 0;
            int i;

            for (i = 0; i < forecasts.size() && i < daysCount; i++) {
                JsonObject partsOfDay = forecasts.get(i).getAsJsonObject().get("parts").getAsJsonObject();
                JsonObject dayInfo = partsOfDay.get("day").getAsJsonObject();
                JsonObject eveningInfo = partsOfDay.get("evening").getAsJsonObject();
                JsonObject morningInfo = partsOfDay.get("morning").getAsJsonObject();
                JsonObject nightInfo = partsOfDay.get("night").getAsJsonObject();

                avgTemp += (float) (dayInfo.get("temp_avg").getAsInt() + eveningInfo.get("temp_avg").getAsInt() + morningInfo.get("temp_avg").getAsInt() + nightInfo.get("temp_avg").getAsInt()) / 4;
            }

            System.out.println("Avg Temp:" + avgTemp / i);
        } catch (Exception e) {
            System.err.println("Error making HTTP request: " + e.getMessage());
        }
    }

    public static float setLat(Scanner sc) {
        System.out.println("Введите широту, например, 54,54");
        while (true) {
            try {
                if (sc.hasNextFloat()) return sc.nextFloat();
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("Введите число равное широте (для дробной с запятой)");
                sc.next();
            }
        }
    }

    public static float setLon(Scanner sc) {
        System.out.println("Введите долготу, например, 27,27");
        while (true) {
            try {
                if (sc.hasNextFloat()) return sc.nextFloat();
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("Введите число равное долготе (для дробной с запятой)");
                sc.next();
            }
        }
    }
}