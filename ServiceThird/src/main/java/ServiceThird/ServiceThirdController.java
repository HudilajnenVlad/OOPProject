package ServiceThird;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@RestController
public class ServiceThirdController {
    @Autowired
    ThirdServiceImpl thirdService;
    @GetMapping("/report")
    public String getReport()
    {
        return thirdService.getReport();
    }

}
