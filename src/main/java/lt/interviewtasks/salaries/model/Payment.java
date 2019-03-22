package lt.interviewtasks.salaries.model;

import lt.interviewtasks.salaries.SalariesService;

public class Payment {

    private final String employee;
    private final String paymentType;
    private Long paymentAmount;
    private String groupingKey;

    public Payment(String employee, String paymentType, Long paymentAmount) {
        this.employee = employee;
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
        this.groupingKey = this.employee + SalariesService.OUTPUT_DELIMITER + this.paymentType;
    }

    public String getEmployee() {
        return employee;
    }
    public Long getPaymentAmount() {
        return paymentAmount;
    }
    public String getGroupingKey() {
        return groupingKey;
    }
}
