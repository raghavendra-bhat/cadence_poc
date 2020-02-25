package activity.implementations.oms;

import activity.contract.GreetingActivities;
import activity.contract.oms.OrderCaptureActivities;
import com.uber.cadence.client.WorkflowClient;
import utilties.Constants;
import workflow.contract.JapaneseGreetingWorkflow;

public class OrderCaptureActivitiesImpl implements OrderCaptureActivities {

  @Override
  public String createOrder(String orderNo, String orderJson) {
    System.out.println("[OC AW] Started createOrder activity :: orderNo = " + orderNo + ", orderJson = " + orderJson);
    System.out.printf("Invoking createOrder service here...");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("[OC AW] Completed createOrder activity :: orderNo = " + orderNo + ", orderJson = " + orderJson);
    return orderNo;
  }
}
