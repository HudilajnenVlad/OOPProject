package ServiceThird;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class ThirdServiceImpl implements ThirdService{

    @Override
    public String getReport() {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("http://localhost:8090/json/arrival.json", String.class);
        Simulator simulator = new Simulator();
        String res = null;
        try {
            res = new Convertor().toJSON(simulator.serviceThird(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
