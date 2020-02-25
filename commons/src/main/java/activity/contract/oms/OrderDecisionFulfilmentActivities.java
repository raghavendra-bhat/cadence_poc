package activity.contract.oms;

import com.uber.cadence.activity.ActivityMethod;

public interface OrderDecisionFulfilmentActivities {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 25)
  String scheduleOrder(String orderNo);


  @ActivityMethod(scheduleToCloseTimeoutSeconds = 25)
  String shipOrder(String orderNo);
}
