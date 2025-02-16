import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.util.*;

public class Utils {

    public static List<String> readLines(String fileName, Class<?> clazz) {
        try (InputStream inputStream = clazz.getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().toList();
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Error reading the file: " + fileName, e);
        }
    }

    public static String readLine(String fileName, Class<?> clazz) {
        List<String> lines = readLines(fileName, clazz);

        if (lines.size() != 1) {
            throw new RuntimeException("Content is not one line in file: " + fileName);
        }

        return lines.get(0);
    }

    public static int readLineAsInt(String fileName, Class<?> clazz) {
        return Integer.valueOf(readLine(fileName, clazz));
    }

}
