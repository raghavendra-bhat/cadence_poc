package workflow.contract;

import com.uber.cadence.workflow.WorkflowMethod;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import utilties.Constants;

public interface GreetingWorkflow {

  /** @return greeting string */
  @WorkflowMethod(executionStartToCloseTimeoutSeconds = 50, taskList = Constants.TASK_LIST)
  String getGreeting(String name);

  default String getDate(){
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    String stringDate = sdf.format(new Date());
    return stringDate + " ------- : ";
  }

}
