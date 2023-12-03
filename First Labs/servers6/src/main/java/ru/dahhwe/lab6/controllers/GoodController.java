package ru.dahhwe.lab6.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/yes")
@RestController
public class GoodController {
    @GetMapping
    private JsonNode Mapping() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonString = "{"
                    + "\"name\":\"Алексей\","
                    + "\"age\":30,"
                    + "\"hobbies\":[\"программирование\",\"велоспорт\",\"чтение\"],"
                    + "\"isStudent\":false,"
                    + "\"contact\":{"
                    + "\"email\":\"alexey@example.com\","
                    + "\"phone\":\"+71234567890\""
                    + "}"
                    + "}";

            JsonNode rootNode = mapper.readTree(jsonString);
            System.out.println(rootNode.toPrettyString());
            return rootNode;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
