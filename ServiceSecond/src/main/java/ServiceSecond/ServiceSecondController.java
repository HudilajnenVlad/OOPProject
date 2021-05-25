package ServiceSecond;

    import entity.Convertor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.client.RestTemplate;
    import org.springframework.web.server.ResponseStatusException;

    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.FileWriter;
    import java.io.IOException;


@RestController
public class ServiceSecondController {
    @Autowired
    private SecondServiceImpl secondService;

    @GetMapping("/json")
    public String getScheduleWithFileName()
    {
     return secondService.getSchedule();
    }

    @GetMapping("/json/{filename}")
    public String getScheduleWithFileName(@PathVariable(value = "filename") String filename)
    {
        return secondService.getScheduleWithFileName(filename);
    }

    @PostMapping(path = "/report")
    public String addReport(@RequestBody String path){
        return secondService.addReport(path);
    }

}
