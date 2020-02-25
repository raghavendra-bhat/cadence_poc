package activity.implementations;

import activity.contract.GreetingActivities;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.workflow.ChildWorkflowStub;
import com.uber.cadence.workflow.Workflow;
import utilties.Constants;
import workflow.contract.GreetingWorkflow;
import workflow.contract.JapaneseGreetingWorkflow;

public class GreetingActivitiesImpl implements GreetingActivities {

  @Override
  public String composeGreeting(String greeting, String name, String language) {

    System.out.println("language = [" + language + "], greeting = [" + greeting + "], name = [" + name + "]");
    //System.out.println(getDate() + "Activity: Picked up message: " + name);
    try {
      if ("English".equalsIgnoreCase(language)) {
        Thread.sleep(3000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("language = [" + language + "], "+getDate() + "Activity: ended up message: " + name);
    return greeting + " " + name + "!";
  }

  @Override
  public String triggerChildWorkflow(String name, String language) {
    /*JapaneseGreetingWorkflow child = Workflow.newChildWorkflowStub(JapaneseGreetingWorkflow.class);
    child.getGreeting(name);*/
    WorkflowClient workflowClient = WorkflowClient.newInstance(Constants.DOMAIN_SAMPLE);
    // Get a workflow stub using the same task list the worker uses.
    JapaneseGreetingWorkflow child = workflowClient.newWorkflowStub(JapaneseGreetingWorkflow.class);
    child.getGreeting(name);
    //ChildWorkflowStub workflow = Workflow.newUntypedChildWorkflowStub("JapaneseGreetingWorkflowType");
    //workflow.execute(String.class, name);

    return "Started";
  }

}
