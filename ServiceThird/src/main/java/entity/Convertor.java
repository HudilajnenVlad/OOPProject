package entity;

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
    public String fromJSON(String path) throws FileNotFoundException {
        FileReader fileReader = new FileReader(path);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true)
        {
            try {
                if ((i = fileReader.read()) == -1) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append((char)i);
        }
        return sb.toString();
    }
}
