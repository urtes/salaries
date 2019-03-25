package lt.interviewtasks.salaries;

import lt.interviewtasks.salaries.model.Payment;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SalariesService {

    private List<Payment> payments = new ArrayList();
    private Map<String, Long> paymentsSumAndTaxes = new HashMap();
    private Map<String, Long> paymentsSumByType = new HashMap();

    private final Float TAX_FACTOR = 0.4F;
    private final String ERROR_MESSAGE = "Invalid report type";
    public final static String OUTPUT_DELIMITER = ";";
    private final String INPUT_DELIMITER = ",";
    private final String OUTPUT_FILE_SUM_TAXES = "suma_mokesciai.csv";
    private final String OUTPUT_FILE_SUM_TYPE = "suma_tipas.csv";
    private final File INPUT_FILE;
    private final String PATH;

    private final String SUM_TAXES_HEADER = "Darbuotojas" + OUTPUT_DELIMITER
            + "Suma" + OUTPUT_DELIMITER
            + "Mokesciai";

    private final String SUM_TYPE_HEADER = "Darbuotojas" + OUTPUT_DELIMITER
            + "Tipas" + OUTPUT_DELIMITER
            + "Suma";

    private enum ReportType {
        SUM_TAXES, SUM_TYPE
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public Map<String, Long> getPaymentsSumAndTaxes() {
        return paymentsSumAndTaxes;
    }

    public Map<String, Long> getPaymentsSumByType() {
        return paymentsSumByType;
    }

    public SalariesService (File inputFile, String path) {
        this.INPUT_FILE = inputFile;
        this.PATH = path;
    }

    public void generateReports() {
        loadDataFromCsv();
        processData();

        writeDataToCsv(paymentsSumAndTaxes,
                SUM_TAXES_HEADER,
                OUTPUT_FILE_SUM_TAXES,
                ReportType.SUM_TAXES);

        writeDataToCsv(paymentsSumByType,
                SUM_TYPE_HEADER,
                OUTPUT_FILE_SUM_TYPE,
                ReportType.SUM_TYPE);
    }

    private void loadDataFromCsv() {

        BufferedReader bufferedReader;
        String line;

        try {
            bufferedReader = new BufferedReader(new FileReader(INPUT_FILE));
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] employeeData = line.split(INPUT_DELIMITER);
                Payment payment = new Payment(employeeData[0].trim(),
                        employeeData[1].trim(),
                        Long.valueOf(employeeData[2].trim()));
                payments.add(payment);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processData() {

        paymentsSumAndTaxes = payments.stream()
                .collect(Collectors.groupingBy(Payment::getEmployee,
                        Collectors.summingLong(Payment::getPaymentAmount)));

        paymentsSumByType = payments.stream()
                .collect(Collectors.groupingBy(Payment::getGroupingKey,
                        Collectors.summingLong(Payment::getPaymentAmount)));
    }

        private void writeDataToCsv(Map<String, Long> data,
                                String header,
                                String fileName,
                                ReportType reportType) {

        Map<String, Long> sortedData = new TreeMap(data);
        File file = new File(PATH + fileName);
        file.getParentFile().mkdirs();
        PrintWriter printWriter = null;
        String outputLine = "";

        try {
            printWriter = new PrintWriter(file);
            printWriter.println(header);
            for(Map.Entry<String, Long> entry : sortedData.entrySet()) {

                if (reportType == ReportType.SUM_TAXES) {
                    outputLine = entry.getKey() + OUTPUT_DELIMITER
                            + entry.getValue() + OUTPUT_DELIMITER
                            + entry.getValue() * TAX_FACTOR;
                } else if (reportType == ReportType.SUM_TYPE) {
                    outputLine = entry.getKey() + OUTPUT_DELIMITER + entry.getValue();
                } else {
                    System.out.println(ERROR_MESSAGE);
                }

                printWriter.println(outputLine);
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