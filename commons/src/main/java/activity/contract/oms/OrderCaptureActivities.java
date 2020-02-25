package activity.contract.oms;

import com.uber.cadence.activity.ActivityMethod;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface OrderCaptureActivities {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 25)
  String createOrder(String orderNo, String orderJson);

  default String getDate(){
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    String stringDate = sdf.format(new Date());
    return stringDate + " ------- : ";
  }

}
