package lt.interviewtasks.salaries;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class SalariesServiceTests {

    @Test
    public void testSalariesService() {

        File inputFile = new File("IOFiles/duomenys.csv");
        SalariesService target = new SalariesService(inputFile, "IOFiles/");

        assertEquals(0, target.getPayments().size());
        assertEquals(0, target.getPaymentsSumAndTaxes().size());
        assertEquals(0, target.getPaymentsSumByType().size());

        target.generateReports();

        assertEquals(17, target.getPayments().size());
        assertEquals(5, target.getPaymentsSumAndTaxes().size());
        assertEquals(8, target.getPaymentsSumByType().size());
    }
}
