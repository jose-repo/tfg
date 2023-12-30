package com.udima.tfg.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Util {
  /**
   * Retrieves the contents of a URL and returns it as a string.
   *
   * @param url the URL to retrieve the contents from
   * @return the contents of the URL as a string
   */
  public static String stream(URL url, String baseName) {
    String cachedJson = getCachedJson(baseName);
    if (cachedJson != null) {

      return cachedJson;
    }
    try (InputStream input = url.openStream()) {
      InputStreamReader isr = new InputStreamReader(input);
      BufferedReader reader = new BufferedReader(isr);
      StringBuilder json = new StringBuilder();
      int c;
      while ((c = reader.read()) != -1) {
        json.append((char) c);
      }
      saveCacheFile(json.toString(), baseName);
      return json.toString();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Converts a JSON string to a List of objects of the specified type.
   *
   * @param json the JSON string to be converted
   * @param tClass the class of the objects in the List
   * @return a List of objects of the specified type
   * @throws JsonProcessingException if an error occurs during JSON processing
   */
  public static List<?> jsonToObjectList(String json, Class<?> tClass)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        json, objectMapper.getTypeFactory().constructCollectionType(List.class, tClass));
  }

  /**
   * Returns a Predicate that filters distinct elements by a key extracted from each element.
   *
   * @param <T> the type of elements to filter
   * @param keyExtractor the function to extract the key from an element
   * @return a Predicate that filters distinct elements
   */
  public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

  public static void saveCacheFile(String json, String name) {
    ObjectMapper objectMapper = new ObjectMapper();
    LocalDate date = LocalDate.now();
    try {
      objectMapper.writeValue(
          new File(
              System.getProperty("java.io.tmpdir")
                  + "\\"
                  + name
                  + date.getYear()
                  + date.getMonth()
                  + date.getDayOfMonth()
                  + ".json"),
          json);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String getCachedJson(String name) {
    LocalDate date = LocalDate.now();
    ObjectMapper mapper = new ObjectMapper();
    try {
      String content = new String(
          Files.readAllBytes(
              Paths.get(
                  System.getProperty("java.io.tmpdir")
                      + "\\"
                      + name
                      + date.getYear()
                      + date.getMonth()
                      + date.getDayOfMonth()
                      + ".json")));
      JsonNode jsonNode = mapper.readTree(content);
      return jsonNode.asText();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
