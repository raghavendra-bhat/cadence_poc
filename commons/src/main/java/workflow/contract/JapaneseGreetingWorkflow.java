package workflow.contract;

import com.uber.cadence.workflow.WorkflowMethod;
import utilties.Constants;

public interface JapaneseGreetingWorkflow {

  /** @return greeting string */
  @WorkflowMethod(executionStartToCloseTimeoutSeconds = 25, taskList = Constants.TASK_LIST)
  String getGreeting(String name);

}
