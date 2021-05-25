package ServiceSecond;

import entity.Convertor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class SecondServiceImpl implements SecondService{

    @Override
    public String getSchedule() {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("http://localhost:8080/schedule", String.class);
        try {
            FileWriter fileWriter = new FileWriter("arrival.json");
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    @Override
    public String getScheduleWithFileName(String filename) {
        String res;
        try {
            res = new Convertor().fromJSON(filename);
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }
        return res;
    }

    @Override
    public String addReport(String path) {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("http://localhost:8095/report", String.class);
        
        try {
            File file = new File(path);
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
