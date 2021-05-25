package ServiceThird;

import entity.Ship.Ship;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.TreeSet;

public class Convertor {
    public void toJSON(TreeSet<Ship> schedule, String path) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(schedule);
        FileOutputStream outputStream = new FileOutputStream(path);
        outputStream.write(json.getBytes(StandardCharsets.UTF_8));

    }

    public String toJSON(Report report) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(report);
        return json;

    }

    public Ship[] fromJSON(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Ship[].class);
    }
}
