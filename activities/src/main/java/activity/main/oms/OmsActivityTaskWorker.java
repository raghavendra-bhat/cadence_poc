package activity.main.oms;

import activity.implementations.oms.OrderCaptureActivitiesImpl;
import activity.implementations.oms.OrderDecisioningFulfilmentActivitiesImpl;
import activity.implementations.oms.OrderPaymentActivitiesImpl;
import com.uber.cadence.worker.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utilties.Constants;

@SpringBootApplication
public class OmsActivityTaskWorker {

  public static void main(String[] args) {
    SpringApplication.run(OmsActivityTaskWorker.class, args);

    // Start a worker that hosts only activity implementations.
    Worker.Factory factory = new Worker.Factory(Constants.DOMAIN_OMS);
    Worker orderCaptureWorker = factory.newWorker(Constants.ORDER_CAPTURE_TASK_LIST);
    Worker paymentWorker = factory.newWorker(Constants.PAYMENT_TASK_LIST);

    // Activities are stateless and thread safe. So a shared instance is used.
    orderCaptureWorker.registerActivitiesImplementations(new OrderCaptureActivitiesImpl(),
        new OrderDecisioningFulfilmentActivitiesImpl());
    paymentWorker.registerActivitiesImplementations(new OrderPaymentActivitiesImpl());
    // Start listening to the activity task lists.
    factory.start();
  }
}
