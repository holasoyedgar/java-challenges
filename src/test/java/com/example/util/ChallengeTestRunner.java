package com.example.util;

import com.google.gson.*;
import org.junit.jupiter.api.Assertions;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class ChallengeTestRunner<I, O> {
    private final String challengeName;
    private final Class<I> inputClass;
    private final Class<O> expectedClass;
    private final Function<I, O> solutionFunction;
    private final Gson gson;

    public ChallengeTestRunner(String challengeName, Class<I> inputClass, Class<O> expectedClass, Function<I, O> solutionFunction) {
        this.challengeName = challengeName;
        this.inputClass = inputClass;
        this.expectedClass = expectedClass;
        this.solutionFunction = solutionFunction;

        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
    }

    public void runTest(String testCaseName) {
        String inputPath = challengeName + "/" + testCaseName + "_input.json";
        String expectedPath = challengeName + "/" + testCaseName + "_expected.json";

        I input = loadJsonFile(inputPath, inputClass);
        O expected = loadJsonFile(expectedPath, expectedClass);

        O actual = solutionFunction.apply(input);

        // Nivelación de aserciones: Si es un array, comparamos el contenido. Si no, comparación estándar.
        if (expected != null && expected.getClass().isArray()) {
            Assertions.assertArrayEquals((Object[]) expected, (Object[]) actual, "Falló el caso de prueba: " + testCaseName);
        } else {
            Assertions.assertEquals(expected, actual, "Falló el caso de prueba: " + testCaseName);
        }
    }

    private <T> T loadJsonFile(String filePath, Class<T> clazz) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
            Assertions.assertNotNull(is, "No se encontró el archivo: " + filePath);
            try (InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                return gson.fromJson(reader, clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error cargando JSON: " + filePath, e);
        }
    }

    private static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
        @Override
        public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
    }

    public static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }
}