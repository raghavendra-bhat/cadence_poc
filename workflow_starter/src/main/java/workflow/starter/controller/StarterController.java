package workflow.starter.controller;

import com.uber.cadence.WorkflowService;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.serviceclient.IWorkflowService;
import com.uber.cadence.worker.Worker;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utilties.Constants;
import workflow.contract.GreetingWorkflow;

@RestController
@RequestMapping(value = "/start/workflow")
public class StarterController {

  @GetMapping(value = "/greeting/{greet}")
  public String startWorkflow(@PathVariable String greet) {
    System.out.println("greeting workflow started : " + greet );
    // Start a workflow execution. Usually this is done from another program.
    WorkflowClient workflowClient = WorkflowClient.newInstance(Constants.DOMAIN_SAMPLE);
    // Get a workflow stub using the same task list the worker uses.
    GreetingWorkflow workflow = workflowClient.newWorkflowStub(GreetingWorkflow.class);

    // Execute a workflow waiting for it to complete.
    String greeting = workflow.getGreeting(greet);
    System.out.println("Returned output from parent workflow: "+greeting);
    return greeting;
  }


//  @GetMapping(value = "/greeting/{id}")
//  public String getWorkflow(@PathVariable String id) {
//    System.out.println("workflow  id: " + id );
//    // Start a workflow execution. Usually this is done from another program.
//    WorkflowClient workflowClient = WorkflowClient.newInstance(Constants.DOMAIN_SAMPLE);
//    Adapter
//    // Get a workflow stub using the same task list the worker uses.
//    GreetingWorkflow workflow = workflowClient.newWorkflowStub(GreetingWorkflow.class);
//    // Execute a workflow waiting for it to complete.
//    String greeting = workflow.getGreeting(greet);
//    System.out.println(greeting);
//    return greeting;
//  }

}
