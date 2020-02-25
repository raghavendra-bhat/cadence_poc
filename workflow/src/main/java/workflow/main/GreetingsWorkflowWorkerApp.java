package workflow.main;

import com.uber.cadence.worker.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utilties.Constants;
import workflow.implementation.GreetingWorkflowImpl;

@SpringBootApplication
public class GreetingsWorkflowWorkerApp {

  public static void main(String[] args) {
    SpringApplication.run(GreetingsWorkflowWorkerApp.class, args);
    greetingsWorkflowWorker();
  }

  private static void greetingsWorkflowWorker() {
    // Start a worker that hosts only workflow
    Worker.Factory factory = new Worker.Factory(Constants.DOMAIN_SAMPLE);
    Worker worker = factory.newWorker(Constants.TASK_LIST);
    // Workflows are stateful. So you need a type to create instances.
    worker.registerWorkflowImplementationTypes(GreetingWorkflowImpl.class);
    // Start listening to the activity task lists.
    factory.start();
  }
}
