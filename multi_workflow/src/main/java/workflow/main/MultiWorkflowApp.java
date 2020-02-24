package workflow.main;

import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.Worker.FactoryOptions;
import com.uber.cadence.worker.Worker.FactoryOptions.Builder;
import com.uber.cadence.worker.WorkerOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utilties.Constants;
import workflow.implementation.GreetingWorkflowImpl;

@SpringBootApplication
public class MultiWorkflowApp {

  public static void main(String[] args) {
    SpringApplication.run(MultiWorkflowApp.class, args);
//    FactoryOptions factoryOptions = new Builder()
//        .setMaxWorkflowThreadCount(2)
//        .setCacheMaximumSize(2)
//        .setStickyDecisionScheduleToStartTimeoutInSeconds(5)
//        .build();

    // Start a worker that hosts only workflow
    Worker.Factory factory = new Worker.Factory(Constants.DOMAIN_SAMPLE);

//    WorkerOptions workerOptions = new WorkerOptions.Builder().setMaxConcurrentWorkflowExecutionSize(2)
//        .setDisableActivityWorker(true).build();
    Worker worker1 = factory.newWorker(Constants.TASK_LIST);
//    Worker worker2 = factory.newWorker(Constants.TASK_LIST_2);

    // Workflows are stateful. So you need a type to create instances.
    worker1.registerWorkflowImplementationTypes(GreetingWorkflowImpl.class);
//    worker2.registerWorkflowImplementationTypes(GreetingWorkflowImpl.class);

//    worker2.registerWorkflowImplementationTypes(GreetingWorkflowImpl.class, JapaneseGreetingWorkflowImpl.class);

    // Start listening to the activity task lists.
    factory.start();
  }
}
