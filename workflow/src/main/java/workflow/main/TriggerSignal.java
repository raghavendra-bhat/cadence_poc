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
        workflowClient.newWorkflowStub(CompleteOrderCaptureWorkflow.class, "0507ed8d-da5f-47cc-97e6-a9796c15f7de");
    workflowById.waitForSignal("Resume");
    System.out.printf("Signal Sent");
  }
}
