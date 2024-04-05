package com.project.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.Airline;

import java.util.random.RandomGenerator;

import java.io.File;
import java.io.IOException;

import static javafx.collections.FXCollections.observableArrayList;

public class AirlineFactory {
    public static Airline generate() {
        RandomGenerator random = RandomGenerator.of("L128X256MixRandom");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode airlineData;
        try {
            JsonNode airlines = objectMapper.readTree(new File("src/main/resources/com/project/api/airlines.json"));
            airlineData = airlines.get(random.nextInt(airlines.size()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Airline(String.valueOf(airlineData.get("name")), random.nextDouble(2));
    }

}
