package activity.contract.oms;

import com.uber.cadence.activity.ActivityMethod;

public interface OrderPaymentActivities {

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 25)
  boolean requestAuthorization(String orderNo);

  @ActivityMethod(scheduleToCloseTimeoutSeconds = 25)
  String chargeAuthorization(String orderNo);
}
