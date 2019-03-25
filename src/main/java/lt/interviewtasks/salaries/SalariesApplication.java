package lt.interviewtasks.salaries;

import java.io.File;
import java.io.IOException;

public class SalariesApplication {

    public static void main (String[] args) throws IOException {

        final String ERROR_MESSAGE = "Enter name of existing input file .csv";
        final String PATH = "IOFiles/";

        if (args.length < 1) {
            System.out.println(ERROR_MESSAGE);
            System.exit(1);
        }

        File inputFile = new File(PATH + args[0]);

        if (inputFile.exists() && inputFile.getName().endsWith(".csv")) {
            SalariesService salariesService = new SalariesService(inputFile, PATH);
            salariesService.generateReports();
        } else {
            System.out.println(ERROR_MESSAGE);
            System.exit(1);
        }
    }
}