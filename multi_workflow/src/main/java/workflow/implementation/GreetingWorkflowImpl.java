package workflow.implementation;

import activity.contract.GreetingActivities;
import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.workflow.Workflow;
import java.time.Duration;
import workflow.contract.GreetingWorkflow;

/**
 * GreetingWorkflow implementation that calls GreetingsActivities#composeGreeting.
 */
public class GreetingWorkflowImpl implements GreetingWorkflow {

  /**
   * Activity stub implements activity interface and proxies calls to it to Cadence activity invocations. Because activities are reentrant, only a single stub can be used for
   * multiple activity invocations.
   */
  private final GreetingActivities activities = Workflow.newActivityStub(GreetingActivities.class);

  @Override
  public String getGreeting(String name) {
    System.out.println(getDate() + "English work flow STARTED and name = [" + name + "]"
        + Thread.currentThread().getName()
    +"Group: " +Thread.currentThread().getThreadGroup() +" Id: "+ Thread.currentThread().getId()
    +" Workflow.getWorkflowInfo():"+Workflow.getWorkflowInfo());


    Workflow.sleep( Duration.ofSeconds(20,0));

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


    // Workflows are stateful. So a new stub must be created for each new child.
    //JapaneseGreetingWorkflow child = Workflow.newChildWorkflowStub(JapaneseGreetingWorkflow.class);
//    activities.triggerChildWorkflow(name, "Japanese");
    // This is a non blocking call that returns immediately.
    // Use child.composeGreeting("Hello", name) to call synchronously.
    //to detach workflow :https://app.slack.com/client/TB70E9VJQ/CL22WDF70/thread/CM29NND28-1571693950.024500
    //Promise<String> greeting = Async.function(child::getGreeting, name);

    // Do something else here.
    // This is a blocking call that returns only after the activity has completed.
    String englishGreeting = activities.composeGreeting("Hello", name, "English");

    //String japanGreeting = greeting.get(); // blocks waiting for the child to complete.

    //return englishGreeting + "\n" + japanGreeting;
    System.out.println(getDate() + "English work flow ENDED and name = [" + name + "]");
    return englishGreeting;
  }
}

