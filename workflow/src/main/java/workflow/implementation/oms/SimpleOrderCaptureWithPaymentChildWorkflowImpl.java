package workflow.implementation.oms;

import activity.contract.oms.OrderCaptureActivities;
import activity.contract.oms.OrderDecisionFulfilmentActivities;
import activity.contract.oms.OrderPaymentActivities;
import com.uber.cadence.workflow.Async;
import com.uber.cadence.workflow.CompletablePromise;
import com.uber.cadence.workflow.Promise;
import com.uber.cadence.workflow.Workflow;
import java.time.Duration;
import workflow.contract.oms.OrderPaymentsWorkflow;
import workflow.contract.oms.SimpleOrderWithPaymentChildWorkflow;

public class SimpleOrderCaptureWithPaymentChildWorkflowImpl implements
    SimpleOrderWithPaymentChildWorkflow {

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
   * Simple order capture workflow that triggers payment process as child workflow
   * Start -> Create Order ->........... Remorse Hold .........................-> End
   *  |-> Child (Payment) Workflow                               ^
   *    |-> Start -> Authorize Payment -> Collect Charge -> End _|
   * @param orderNo
   * @param orderJson
   * @return
   */
  @Override
  public String createYantriksSimpleOrderCaptureWithChildPaymentWorkflow(String orderNo,
      String orderJson) {
    System.out.println("[OC WFW]Started createYantriksSimpleOrderCaptureWithChildPaymentWorkflow:: orderNo = " + orderNo + ", orderJson = " + orderJson+"\n");

    System.out.printf("[OC WFW]Invoke Create Order Activity"+"\n");
    orderCaptureactivities.createOrder(orderNo, orderJson);
    
    System.out.printf("[OC WFW]Start Payment Authorization Workflow"+"\n");
    OrderPaymentsWorkflow child = Workflow.newChildWorkflowStub(OrderPaymentsWorkflow.class);
    Promise<String> paymentResponse = Async.function(child::startPaymentsProcessing, orderNo);
    
    System.out.printf("[OC WFW]Remorse hold for 30 seconds"+"\n");
    Workflow.sleep(Duration.ofMinutes(DELAY_IN_SECONDS));

    System.out.printf("[OC WFW]Wait for payment response"+"\n");
    String paymentStatus = paymentResponse.get();
    System.out.printf("[OC WFW]Payment response: Status: "+ paymentStatus+"\n");
    
    System.out.println("[OC WFW]Completed the createYantriksSimpleOrderCaptureWithChildPaymentWorkflow for : orderNo = " + orderNo + ", orderJson = " + orderJson+"\n");

    return paymentStatus;
  }


}
