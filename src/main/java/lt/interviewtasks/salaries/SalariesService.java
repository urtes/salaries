package lt.interviewtasks.salaries;

import lt.interviewtasks.salaries.model.Payment;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SalariesService {

    private List<Payment> payments = new ArrayList();
    private Map<String, Integer> paymentsSumAndTaxes = new HashMap();
    private Map<String, Integer> paymentsSumByType = new HashMap();

    public void generateReports() {
        loadPaymentDataFromCsv();
        processPaymentData();
        writePaymentSumAndTaxesDataToCsv(paymentsSumAndTaxes);
        writePaymentSumByTypeDataToCsv(paymentsSumByType);
    }

    private void loadPaymentDataFromCsv() {

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
                    payment.setPaymentAmount(Integer.valueOf(employeeData[2]));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                payments.add(payment);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processPaymentData() {

        paymentsSumAndTaxes = payments.stream()
                .collect(Collectors.groupingBy(Payment::getEmployee,
                        Collectors.summingInt(Payment::getPaymentAmount)));

        paymentsSumByType = payments.stream()
                .map(payment -> {payment.setSortingKey(); return payment;})
                .collect(Collectors.groupingBy(Payment::getSortingKey,
                        Collectors.summingInt(Payment::getPaymentAmount)));
    }

    private void writePaymentSumAndTaxesDataToCsv(Map<String, Integer> data) {

        Map<String, Integer> sortedData = new TreeMap(data);
        String csvPath = "src/main/resources/static/suma_mokesciai.csv";
        File file = new File(csvPath);
        file.getParentFile().mkdirs();
        PrintWriter printWriter = null;
        Float taxFactor = 0.4F;
        try {
            printWriter = new PrintWriter(file);
            printWriter.println("Darbuotojas,Suma,Mokesciai");
            for(Map.Entry<String, Integer> entry : sortedData.entrySet()) {
                printWriter.println(entry.getKey() + ","
                        + entry.getValue() + ","
                        + entry.getValue()*taxFactor);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    private void writePaymentSumByTypeDataToCsv(Map<String, Integer> data) {
        Map<String, Integer> sortedData = new TreeMap(data);
        String csvPath = "src/main/resources/static/suma_tipas.csv";
        File file = new File(csvPath);
        file.getParentFile().mkdirs();
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
            printWriter.println("Darbuotojas,Tipas,Suma");
            for(Map.Entry<String, Integer> entry : sortedData.entrySet()) {
                printWriter.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
