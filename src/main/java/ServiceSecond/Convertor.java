package ServiceSecond;

import ServiceFirst.Ship.Ship;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.TreeSet;

public class Convertor {
    public void toJSON(TreeSet<Ship> schedule) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(schedule);
        System.out.println(json);
        FileOutputStream outputStream = new FileOutputStream("schedule.json");
        outputStream.write(json.getBytes(StandardCharsets.UTF_8));
    }
}
