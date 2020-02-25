package workflow.contract.oms;

import com.uber.cadence.workflow.WorkflowMethod;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import utilties.Constants;

public interface OrderPaymentsWorkflow {


  /** @return Workflow interface that defines Yantriks OMS payments */
  @WorkflowMethod(executionStartToCloseTimeoutSeconds = 200, taskList = Constants.PAYMENT_TASK_LIST)
  String startPaymentsProcessing(String orderNo);

  default String getDate(){
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    String stringDate = sdf.format(new Date());
    return stringDate + " ------- : ";
  }
}
