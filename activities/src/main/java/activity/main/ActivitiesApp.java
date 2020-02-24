package activity.main;

import activity.implementations.GreetingActivitiesImpl;
import com.uber.cadence.worker.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utilties.Constants;

@SpringBootApplication
public class ActivitiesApp {

  public static void main(String[] args) {
    SpringApplication.run(ActivitiesApp.class, args);

    // Start a worker that hosts only activity implementations.
    Worker.Factory factory = new Worker.Factory(Constants.DOMAIN_SAMPLE);
    Worker worker = factory.newWorker(Constants.TASK_LIST);
    // Activities are stateless and thread safe. So a shared instance is used.
    worker.registerActivitiesImplementations(new GreetingActivitiesImpl());
    // Start listening to the activity task lists.
    factory.start();
  }
}
