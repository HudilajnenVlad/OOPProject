package ServiceFirst;

import ServiceFirst.entity.Schedule;
import org.springframework.stereotype.Service;

@Service
public class FirstServiceImpl implements FirstService {


    @Override
    public String getSchedule() {
        Schedule schedule = new Schedule();
        schedule.generateSchedule();
        return schedule.toJSON();
    }
}
