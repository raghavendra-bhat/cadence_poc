package workflow.implementation.oms;

import activity.contract.oms.OrderCaptureActivities;
import activity.contract.oms.OrderDecisionFulfilmentActivities;
import activity.contract.oms.OrderPaymentActivities;
import com.uber.cadence.workflow.Async;
import com.uber.cadence.workflow.CompletablePromise;
import com.uber.cadence.workflow.Promise;
import com.uber.cadence.workflow.Workflow;
import java.time.Duration;
import workflow.contract.oms.CompleteOrderCaptureWorkflow;
import workflow.contract.oms.OrderPaymentsWorkflow;
import workflow.contract.oms.SimpleOrderCaptureWorkflow;

public class CompleteOrderCaptureWorkflowImpl implements CompleteOrderCaptureWorkflow {

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
  private long DELAY_IN_MINUTES = 1;

  /**
   * Captures order workflow starting from order capture to ship order. Spans mechanics, decisioning and fulfilment
   * Start -> Create Order -> Schedule Order -> Wait for external signal -> Ship Order -> End
   * @param orderNo
   * @param orderJson
   * @return
   */
  @Override
  public String createYantriksCompleteOrderWorkflow(String orderNo, String orderJson) {
    System.out.println("[OC WFW]Started createYantriksSimpleOrderCaptureWithChildPaymentWorkflow:: orderNo = " + orderNo + ", orderJson = " + orderJson+"\n");

    System.out.printf("[OC WFW]Invoke Create Order Activity"+"\n");
    orderCaptureactivities.createOrder(orderNo, orderJson);

    System.out.printf("[OC WFW] Schedule Order"+"\n");
    decisionFulfilmentActivities.scheduleOrder(orderNo);

    System.out.printf("[OC WFW] Listening to external signal to resume the workflow after the schedule order..."+"\n");
    String shipOrderResponse = "EMPTY";
    if (!signal1.get().isEmpty()) {
      shipOrderResponse = decisionFulfilmentActivities.shipOrder(orderNo);
    }
    System.out.println("[OC WFW]Completed the createYantriksSimpleOrderCaptureWithChildPaymentWorkflow for : orderNo = " + orderNo + ", orderJson = " + orderJson+"\n");

    return shipOrderResponse;
  }


  @Override
  public void waitForSignal(String signal) {
    System.out.printf("[OC WFW] Received signal to resume the workflow execution"+"\n");
    signal1.complete(signal);
  }

  @Override
  public String queryResults() {
    System.out.printf("[OC WFW] Received query on workflow execution status"+"\n");
    return "data";
  }
}
