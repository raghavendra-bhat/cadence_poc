package workflow.starter.controller.oms;

import com.uber.cadence.client.WorkflowClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utilties.Constants;
import workflow.contract.oms.CompleteOrderCaptureWorkflow;
import workflow.contract.oms.SimpleOrderCaptureWorkflow;
import workflow.contract.oms.SimpleOrderWithPaymentChildWorkflow;

@RestController
@RequestMapping(value = "/start/workflow")
public class OmsWorkflowStarterController {

  @GetMapping(value = "/order/simple/{orderNo}/{orderJson}")
  public String triggerSimpleOrderCaptureWorkflow(@PathVariable String orderNo, @PathVariable String orderJson) {
    System.out.println("Trigger Simple Order Capture Workflow with [order #: " + orderNo +"], [order Json: "+orderJson+" ]" );
    // Start a workflow execution. Usually this is done from another program.

    WorkflowClient workflowClient = WorkflowClient.newInstance(Constants.DOMAIN_OMS);
    // Get a workflow stub using the same task list the worker uses.
    SimpleOrderCaptureWorkflow workflow = workflowClient.newWorkflowStub(SimpleOrderCaptureWorkflow.class);

    String retOrderNo = workflow.executeYantriksSimpleOrderCaptureWorkflow(orderNo, orderJson);
    System.out.println("Returned output from simple order capture workflow: "+retOrderNo);
    return retOrderNo;
  }


  @GetMapping(value = "/order/childworkflow/{orderNo}/{orderJson}")
  public String triggerYantriksSimpleOrderCaptureWithChildPaymentWorkflow(@PathVariable String orderNo, @PathVariable String orderJson) {
    System.out.println("Trigger Order Capture Workflow with Payment Child Workflow [order #: " + orderNo +"], [order Json: "+orderJson+" ]" );
    // Start a workflow execution. Usually this is done from another program.

    WorkflowClient workflowClient = WorkflowClient.newInstance(Constants.DOMAIN_OMS);
    // Get a workflow stub using the same task list the worker uses.
    SimpleOrderWithPaymentChildWorkflow workflow = workflowClient.newWorkflowStub(
        SimpleOrderWithPaymentChildWorkflow.class);

    // Execute a workflow waiting for it to complete.
    //String greeting = workflow.getGreeting(greet);
    String retOrderNo = workflow.createYantriksSimpleOrderCaptureWithChildPaymentWorkflow(orderNo, orderJson);
    System.out.println("Returned output from simple with payment child order capture workflow: "+retOrderNo);
    return retOrderNo;
  }

  @GetMapping(value = "/order/complete/{orderNo}/{orderJson}")
  public String triggerYantriksCompleteOrderWorkflow(@PathVariable String orderNo, @PathVariable String orderJson) {
    System.out.println("Trigger Complete Order Capture Workflow [order #: " + orderNo +"], [order Json: "+orderJson+" ]" );
    // Start a workflow execution. Usually this is done from another program.

    WorkflowClient workflowClient = WorkflowClient.newInstance(Constants.DOMAIN_OMS);
    // Get a workflow stub using the same task list the worker uses.
    CompleteOrderCaptureWorkflow workflow = workflowClient.newWorkflowStub(
        CompleteOrderCaptureWorkflow.class);

    // Execute a workflow waiting for it to complete.
    //String greeting = workflow.getGreeting(greet);
    String retOrderNo = workflow.createYantriksCompleteOrderWorkflow(orderNo, orderJson);
    System.out.println("Returned output from Complete Order Capture workflow: "+retOrderNo);
    return retOrderNo;
  }

}
