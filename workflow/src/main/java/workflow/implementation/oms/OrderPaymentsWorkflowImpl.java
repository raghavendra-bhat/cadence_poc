package workflow.implementation.oms;

import activity.contract.oms.OrderPaymentActivities;
import com.uber.cadence.workflow.Workflow;
import java.time.Duration;
import workflow.contract.oms.OrderPaymentsWorkflow;

public class OrderPaymentsWorkflowImpl implements OrderPaymentsWorkflow {

  private static final long DELAY_IN_SECONDS = 5;
  /**
   * Activity stub implements activity interface and proxies calls to it to Cadence activity invocations. Because activities are reentrant, only a single stub can be used for
   * multiple activity invocations.
   */
  private final OrderPaymentActivities activities = Workflow.newActivityStub(OrderPaymentActivities.class);

  /**
   * Payment processing workflow that just gets authorization and charges given payment method
   * Start -> Request Authorization -> Charge Authorization -> End
   * @param orderNo
   * @return
   */
  @Override
  public String startPaymentsProcessing(String orderNo) {

    System.out.println("[PMT WFW] Started startPaymentsProcessing orderNo = " + orderNo+"\n");
    activities.requestAuthorization(orderNo);

    //Simulate processing time
    Workflow.sleep(Duration.ofSeconds(DELAY_IN_SECONDS));

    System.out.println("[PMT WFW] Started startPaymentsProcessing orderNo = " + orderNo+"\n");
    String chargeStatus = activities.chargeAuthorization(orderNo);

    System.out.println("[PMT WFW] Completed the startPaymentsProcessing for : orderNo = " + orderNo +"\n");
    return chargeStatus;
  }
}
