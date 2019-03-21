package lt.interviewtasks.salaries;

public class SalariesApplication {
    public static void main (String[] args) {
        SalariesService salariesService = new SalariesService();
        salariesService.loadDataFromCsv();
        salariesService.sortPaymentData();
    }
}
