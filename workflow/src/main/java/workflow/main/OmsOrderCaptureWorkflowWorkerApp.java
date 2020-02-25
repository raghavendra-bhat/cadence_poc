package workflow.main;

import com.uber.cadence.worker.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utilties.Constants;
import workflow.contract.oms.SimpleOrderWithPaymentChildWorkflow;
import workflow.implementation.oms.CompleteOrderCaptureWorkflowImpl;
import workflow.implementation.oms.SimpleOrderCaptureWithPaymentChildWorkflowImpl;
import workflow.implementation.oms.SimpleOrderCaptureWorkflowImpl;

@SpringBootApplication
public class OmsOrderCaptureWorkflowWorkerApp {

  public static void main(String[] args) {
    SpringApplication.run(OmsOrderCaptureWorkflowWorkerApp.class, args);
    startOrderCaptureWorkflowWorker();
  }

  private static void startOrderCaptureWorkflowWorker() {
    // Start a worker that hosts only workflow
    Worker.Factory factory = new Worker.Factory(Constants.DOMAIN_OMS);
    Worker orderCaptureWorker = factory.newWorker(Constants.ORDER_CAPTURE_TASK_LIST);

    // Workflows are stateful. So you need a type to create instances.
    orderCaptureWorker.registerWorkflowImplementationTypes(SimpleOrderCaptureWorkflowImpl.class,
        SimpleOrderCaptureWithPaymentChildWorkflowImpl.class,
        CompleteOrderCaptureWorkflowImpl.class);
    // Start listening to the activity task lists.
    factory.start();
  }
}
