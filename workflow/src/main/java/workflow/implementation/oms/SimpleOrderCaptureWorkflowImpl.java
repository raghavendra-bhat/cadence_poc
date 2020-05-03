package workflow.implementation.oms;

import activity.contract.oms.OrderCaptureActivities;
import activity.contract.oms.OrderPaymentActivities;
import activity.contract.oms.OrderDecisionFulfilmentActivities;
import com.uber.cadence.workflow.CompletablePromise;
import com.uber.cadence.workflow.Workflow;
import java.time.Duration;
import workflow.contract.oms.SimpleOrderCaptureWorkflow;

public class SimpleOrderCaptureWorkflowImpl implements SimpleOrderCaptureWorkflow {

  /**
   * Activity stub implements activity interface and proxies calls to it to Cadence activity invocations. Because activities are reentrant, only a single stub can be used for
   * multiple activity invocations.
   */
  private final OrderCaptureActivities orderCaptureactivities = Workflow.newActivityStub(OrderCaptureActivities.class);

  private final OrderDecisionFulfilmentActivities decisionFulfilmentActivities = Workflow.newActivityStub(
      OrderDecisionFulfilmentActivities.class);

  private final OrderPaymentActivities paymentActivities = Workflow.newActivityStub(OrderPaymentActivities.class);

  CompletablePromise<String> signal1 = Workflow.newPromise();
  //Configurarble delay
  private long DELAY_IN_SECONDS = 30;

  /**
   * Basic order mechanics workflow that involves:
   * Start -> Create Order -> Remorse Hold -> End
   * Remorse hold is to make the workflow stop for preconfigured time
   * @param orderNo
   * @param orderJson
   * @return
   */
  @Override
  public String executeYantriksSimpleOrderCaptureWorkflow(String orderNo, String orderJson) {
    System.out.println("[OC WFW]Started createYantriksSimpleOrderCaptureWorkflow:: orderNo = " + orderNo + ", orderJson = " + orderJson+"\n");

    System.out.printf("[OC WFW]Invoke Create Order Activity \n");
    orderCaptureactivities.createOrder(orderNo, orderJson);

    System.out.printf("[OC WFW]Remorse hold for 30 seconds"+"\n");
    Workflow.sleep(Duration.ofSeconds(DELAY_IN_SECONDS));

    System.out.println("[OC WFW]Completed the createYantriksSimpleOrderCaptureWorkflow for : orderNo = " + orderNo + ", orderJson = " + orderJson+"\n");

    return orderNo;
  }

}
