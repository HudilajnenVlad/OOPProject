package ServiceFirst;

import ServiceFirst.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ServiceFirstController {
    @Autowired
    FirstServiceImpl firstService;
    @GetMapping("/schedule")
    public String getSchedule() {
        return firstService.getSchedule();
    }


}
