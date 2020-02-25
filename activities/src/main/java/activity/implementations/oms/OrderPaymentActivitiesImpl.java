package activity.implementations.oms;

import activity.contract.oms.OrderPaymentActivities;

public class OrderPaymentActivitiesImpl implements OrderPaymentActivities {

  private static final String PAYMENT_SUCCESSFUL = "PAYMENT_SUCCESS";

  @Override
  public boolean requestAuthorization(String orderNo) {
    System.out.println("[OC AW] Started requestAuthorization activity :: orderNo = " + orderNo);
    System.out.printf("Invoking requestAuthorization service here...");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("[OC AW] Completed requestAuthorization activity :: orderNo = " + orderNo );
    return true;
  }

  @Override
  public String chargeAuthorization(String orderNo) {

    System.out.println("[OC AW] Started chargeAuthorization activity :: orderNo = " + orderNo);
    System.out.printf("Invoking chargeAuthorization service here...");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("[OC AW] Completed chargeAuthorization activity :: orderNo = " + orderNo );
    return PAYMENT_SUCCESSFUL;
  }
}
