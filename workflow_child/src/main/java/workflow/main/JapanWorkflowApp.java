package workflow.main;

import com.uber.cadence.worker.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utilties.Constants;
import workflow.implementation.JapaneseGreetingWorkflowImpl;

@SpringBootApplication
public class JapanWorkflowApp {

  public static void main(String[] args) {
    SpringApplication.run(JapanWorkflowApp.class, args);

    // Start a worker that hosts only workflow
    Worker.Factory factory = new Worker.Factory(Constants.DOMAIN_SAMPLE);
    Worker worker = factory.newWorker(Constants.TASK_LIST);
    // Workflows are stateful. So you need a type to create instances.
    worker.registerWorkflowImplementationTypes(JapaneseGreetingWorkflowImpl.class);
    // Start listening to the activity task lists.
    factory.start();
  }
}
