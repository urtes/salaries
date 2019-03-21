package lt.interviewtasks.salaries.model;

public class Payment {

    private String Employee;
    private String PaymentType;
    private Float PaymentAmount;

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String name) {
        Employee = name;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public Float getPaymentAmount() {
        return PaymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        PaymentAmount = paymentAmount;
    }
}
