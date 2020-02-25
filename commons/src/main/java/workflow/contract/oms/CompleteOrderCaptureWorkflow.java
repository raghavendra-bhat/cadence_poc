package workflow.contract.oms;

import com.uber.cadence.workflow.QueryMethod;
import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import utilties.Constants;

public interface CompleteOrderCaptureWorkflow {

  /** @return Order workflow spanning across mechanics and decisioning */
  @WorkflowMethod(executionStartToCloseTimeoutSeconds = 300, taskList = Constants.ORDER_CAPTURE_TASK_LIST)
  String createYantriksCompleteOrderWorkflow(String orderNo, String orderJson);



  @SignalMethod
  void waitForSignal(String signal);

  @QueryMethod
  String queryResults();

  default String getDate(){
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    String stringDate = sdf.format(new Date());
    return stringDate + " ------- : ";
  }
}
