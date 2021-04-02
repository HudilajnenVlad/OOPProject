package ServiceThird;

import ServiceFirst.Schedule;
import ServiceFirst.Ship.Ship;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.TreeSet;

public class Convertor {
    public void toJSON(TreeSet<Ship> schedule, String path) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(schedule);
        System.out.println(json);
        FileOutputStream outputStream = new FileOutputStream(path);
        outputStream.write(json.getBytes(StandardCharsets.UTF_8));
    }
    public Schedule fromJSON(String path) throws IOException {
        Gson gson = new Gson();
        FileInputStream fileInputStream = new FileInputStream(path);
        StringBuilder sb = new StringBuilder();
        int i;
        while ((i = fileInputStream.read())!=-1)
        {
            sb.append((char)i);
        }

        return gson.fromJson(sb.toString(), Schedule.class);
    }
}
