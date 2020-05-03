package workflow.main;

import com.uber.cadence.client.WorkflowClient;
import java.util.List;
import utilties.Constants;
import workflow.contract.oms.CompleteOrderCaptureWorkflow;

public class TriggerSignal {
  public static void main(String[] args) {
    System.out.println("**********************************test");
    WorkflowClient workflowClient = WorkflowClient.newInstance(Constants.DOMAIN_OMS);

    CompleteOrderCaptureWorkflow workflowById =
        workflowClient.newWorkflowStub(CompleteOrderCaptureWorkflow.class, "0b590e23-500e-4e37-90da-207ed04e8c50");
    workflowById.waitForSignal("Resume");
    System.out.printf("Signal Sent");
  }
}
