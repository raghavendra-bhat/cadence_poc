package activity.implementations.oms;

import activity.contract.oms.OrderDecisionFulfilmentActivities;

public class OrderDecisioningFulfilmentActivitiesImpl implements OrderDecisionFulfilmentActivities {

  private static final String SCHEDULED_ORDER = "ORDER_SCHEDULED";
  private static final String SHIPPED_ORDER = "ORDER_SHIPPED";


  @Override
  public String scheduleOrder(String orderNo) {
    System.out.println("[OC AW] Started scheduleOrder activity :: orderNo = " + orderNo);
    System.out.printf("Invoking scheduleOrder service or drop message to queue for agent here...");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("[OC AW] Completed requestAuthorization activity :: orderNo = " + orderNo );
    return SCHEDULED_ORDER;
  }

  @Override
  public String shipOrder(String orderNo) {
    System.out.println("[OC AW] Started shipOrder activity :: orderNo = " + orderNo);
    System.out.printf("Invoking shipOrder service here...");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("[OC AW] Completed shipOrder activity :: orderNo = " + orderNo );
    return SHIPPED_ORDER;
  }
}
