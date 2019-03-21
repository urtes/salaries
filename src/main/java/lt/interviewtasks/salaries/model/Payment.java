package lt.interviewtasks.salaries.model;

public class Payment {

    private String employee;
    private String paymentType;
    private Integer paymentAmount;
    private String sortingKey;

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getSortingKey() {
        return sortingKey;
    }

    public void setSortingKey() {
        sortingKey = employee + "," + paymentType;
    }
}
