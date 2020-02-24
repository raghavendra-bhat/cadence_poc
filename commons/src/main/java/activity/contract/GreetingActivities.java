package activity.contract;

import com.uber.cadence.activity.ActivityMethod;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface GreetingActivities {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 25)
  String composeGreeting(String greeting, String name, String language);

  default String getDate(){
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    String stringDate = sdf.format(new Date());
    return stringDate + " ------- : ";
  }

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 5)
  String triggerChildWorkflow(String name, String japanese);
}
