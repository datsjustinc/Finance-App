// Utility class for reading and writing JSON files

import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class DataPersistenceManager
{

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Loads a JSON file into an object
    public static <T> T loadData(String filename, Class<T> clazz) throws IOException
    {
        try (Reader reader = new FileReader(filename))
        {
            return gson.fromJson(reader, clazz);
        }
    }

    // Loads a list of objects from a JSON file
    public static <T> List<T> loadListData(String filename, TypeToken<List<T>> typeToken) throws IOException
    {
        try (Reader reader = new FileReader(filename))
        {
            return gson.fromJson(reader, typeToken.getType());
        }
    }

    // Saves an object to a JSON file
    public static void saveData(String filename, Object data) throws IOException
    {
        try (Writer writer = new FileWriter(filename))
        {
            gson.toJson(data, writer);
        }
    }

}