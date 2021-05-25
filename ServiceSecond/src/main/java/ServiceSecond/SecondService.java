package ServiceSecond;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface SecondService {
    public String getSchedule();
    public String getScheduleWithFileName(String filename);
    public String addReport( String path);
}
