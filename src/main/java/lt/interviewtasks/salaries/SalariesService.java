package lt.interviewtasks.salaries;

import lt.interviewtasks.salaries.model.Payment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class SalariesService {

    private ArrayList<Payment> unsortedPayments = new ArrayList();

    private Comparator<Payment> comparatorByPayment = new Comparator<Payment>() {

        @Override
        public int compare(Payment payment1, Payment payment2) {
            int i = payment1.getEmployee().compareTo(payment2.getEmployee());
            if (i == 0) {
                return payment1.getPaymentType().compareTo(payment2.getPaymentType());
            } else {
            return i;
            }
        }
    };

    public void loadDataFromCsv() {

        String csvPath = "src/main/resources/static/duomenys.csv";
        BufferedReader bufferedReader;
        String line;
        String csvSplitBy = ",";

        try {
            bufferedReader = new BufferedReader(new FileReader(csvPath));
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] employeeData = line.split(csvSplitBy);
                for (int i = 0; i < employeeData.length; i++) {
                    employeeData[i] = employeeData[i].trim();
                }
                Payment payment = new Payment();
                payment.setEmployee(employeeData[0]);
                payment.setPaymentType(employeeData[1]);
                try {
                    payment.setPaymentAmount(Float.valueOf(employeeData[2]));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                unsortedPayments.add(payment);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (Payment payment : unsortedPayments) {
//            System.out.println(payment.getEmployee() + "," + payment.getPaymentType() + "," + payment.getPaymentAmount());
//        }
    }

    public void sortPaymentData() {
        ArrayList<Payment> sortedPayments = unsortedPayments;
        sortedPayments.sort(comparatorByPayment);
//        for (Payment payment : sortedPayments) {
//            System.out.println(payment.getEmployee() + "," + payment.getPaymentType() + "," + payment.getPaymentAmount());
//        }
    }
}
