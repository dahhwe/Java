import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Класс для поиска статей на Википедии.
 */
public class WikipediaSearch {
    // URL для API Википедии, используемый для выполнения поисковых запросов
    private static final String WIKIPEDIA_API_URL = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=";
    // Logger для записи информации о работе программы
    private static final Logger LOGGER = Logger.getLogger(WikipediaSearch.class.getName());

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            // Считать введенные пользователем данные
            String searchQuery = readUserInput();

            // Сделать запрос к серверу
            JsonObject jsonObject = makeRequest(searchQuery);

            // Распарсить ответ
            JsonArray searchResults = parseResponse(jsonObject);

            // Вывести результат поиска
            displayResults(searchResults);

            // Открыть нужную страницу в браузере
            openSelectedPage(searchResults);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка: ", e);
        }
    }

    /**
     * Считывает поисковый запрос пользователя.
     *
     * @return поисковый запрос пользователя
     * @throws IOException если произошла ошибка при чтении ввода пользователя
     */
    private static String readUserInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите поисковый запрос: ");
        String searchQuery = reader.readLine();
        if (searchQuery.trim().isEmpty()) {
            throw new IllegalArgumentException("Поисковый запрос не может быть пустым.");
        }
        return searchQuery;
    }

    /**
     * Делает запрос к серверу Википедии.
     *
     * @param searchQuery поисковый запрос пользователя
     * @return объект JsonObject, содержащий ответ от сервера
     * @throws IOException если произошла ошибка при выполнении запроса
     */
    private static JsonObject makeRequest(String searchQuery) throws IOException {
        String encodedQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8);
        HttpURLConnection connection = (HttpURLConnection) new URL(WIKIPEDIA_API_URL + encodedQuery).openConnection();
        connection.setRequestMethod("GET");
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String jsonResponse = responseReader.lines().collect(Collectors.joining());
        return JsonParser.parseString(jsonResponse).getAsJsonObject();
    }

    /**
     * Распарсивает ответ от сервера.
     *
     * @param jsonObject объект JsonObject, содержащий ответ от сервера
     * @return массив JsonArray, содержащий результаты поиска
     */
    private static JsonArray parseResponse(JsonObject jsonObject) {
        JsonObject queryResult = jsonObject.getAsJsonObject("query");
        if (queryResult.has("search")) {
            return queryResult.getAsJsonArray("search");
        } else {
            throw new IllegalArgumentException("Ничего не было найдено.");
        }
    }

    /**
     * Выводит результаты поиска.
     *
     * @param searchResults массив JsonArray, содержащий результаты поиска
     */
    private static void displayResults(JsonArray searchResults) {
        if (searchResults.isEmpty()) {
            System.out.println("По вашему запросу ничего не было найдено.");
            return;
        }
        System.out.println("Результаты поиска:");
        for (int i = 0; i < searchResults.size(); i++) {
            JsonObject result = searchResults.get(i).getAsJsonObject();
            System.out.println((i + 1) + ". " + result.get("title").getAsString());
        }
    }

    /**
     * Запрашивает у пользователя номер статьи для открытия и открывает выбранную статью.
     *
     * @param searchResults массив JsonArray, содержащий результаты поиска
     * @throws IOException если произошла ошибка при чтении ввода пользователя
     */
    private static void openSelectedPage(JsonArray searchResults) throws IOException {
        if (searchResults.isEmpty()) {
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите номер статьи для открытия: ");
        int articleNumber = Integer.parseInt(reader.readLine()) - 1;
        if (articleNumber >= 0 && articleNumber < searchResults.size()) {
            JsonObject selectedResult = searchResults.get(articleNumber).getAsJsonObject();
            int pageId = selectedResult.get("pageid").getAsInt();
            openWikipediaPage(pageId);
        } else {
            throw new IllegalArgumentException("Некорректный выбор статьи.");
        }
    }

    /**
     * Открывает страницу Википедии с указанным идентификатором страницы.
     *
     * @param pageId идентификатор страницы Википедии
     */
    private static void openWikipediaPage(int pageId) {
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI("https://ru.wikipedia.org/w/index.php?curid=" + pageId);
            desktop.browse(uri);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Не удалось открыть страницу Википедии.", e);
        }
    }
}
