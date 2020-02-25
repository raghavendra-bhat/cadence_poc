package workflow.main;

import com.uber.cadence.worker.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utilties.Constants;
import workflow.implementation.oms.OrderPaymentsWorkflowImpl;

@SpringBootApplication
public class PaymentsWorkflowWorkerApp {

  public static void main(String[] args) {
    SpringApplication.run(PaymentsWorkflowWorkerApp.class, args);
    startOrderPaymentsWorkflowWorker();
  }

  private static void startOrderPaymentsWorkflowWorker() {
    // Start a worker that hosts only workflow
    Worker.Factory factory = new Worker.Factory(Constants.DOMAIN_OMS);
    Worker paymentsWorker = factory.newWorker(Constants.PAYMENT_TASK_LIST);

    // Workflows are stateful. So you need a type to create instances.
    paymentsWorker.registerWorkflowImplementationTypes(OrderPaymentsWorkflowImpl.class);
    // Start listening to the activity task lists.
    factory.start();
  }
}
