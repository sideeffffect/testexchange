package cz.sideeffect.testexchange;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * Tests the whole validation of grids with I/O.
 */
public class TestValidationIO {

    private static final String resources = "src/test/resources";

    public void fileTest(String inputFileName, String outputFileName, int expectedStructuresCount) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(resources + "/" + inputFileName));
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);

        Validator validator = new Validator();
        int actualStructuresCount = validator.validate(reader, writer);

        reader.close();
        writer.close();
        
        String expected = FileUtils.readFileToString(new File(resources + "/" + outputFileName));
        String actual = stringWriter.toString();

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedStructuresCount, actualStructuresCount);
    }

    /**
     * performs a test according to the example in the specification
     * @throws IOException
     */
    @Test
    public void providedExampleTest() throws IOException {
        fileTest("providedExampleInput", "providedExampleOutput", 5);
    }

    /**
     * tests the rule 'Red structure has no more than one another adjacent structure.'
     * @throws IOException
     */
    @Test
    public void redAdjS() throws IOException {
        fileTest("redAdjSInput", "redAdjSOutput", 3);
    }

    /**
     * tests the rule 'Red structure cannot consist from more than 5 cells.'
     * @throws IOException
     */
    @Test
    public void redCells() throws IOException {
        fileTest("redCellsInput", "redCellsOutput", 1);
    }

    /**
     * tests the rule 'Yellow structure cannot have an adjacent green structure.'
     * @throws IOException
     */
    @Test
    public void yellowAdjS() throws IOException {
        fileTest("yellowAdjSInput", "yellowAdjSOutput", 3);
    }

    /**
     * tests the rule 'Yellow structure is always linear, i.e. all cells that form the structure are on a single horizontal or vertical line.'
     * @throws IOException
     */
    @Test
    public void yellowLinear() throws IOException {
        fileTest("yellowLinearInput", "yellowLinearOutput", 1);
    }

    /**
     * tests the rule 'Green structure always has an adjacent blue structure.'
     * @throws IOException
     */
    @Test
    public void greenAdjS() throws IOException {
        fileTest("greenAdjSInput", "greenAdjSOutput", 1);
    }

    /**
     * tests the rule 'There are no more than two blue structures in the grid.'
     * @throws IOException
     */
    @Test
    public void blueStructs() throws IOException {
        fileTest("blueStructsInput", "blueStructsOutput", 3);
    }

    /**
     * tests the rule 'For each color, average number of cells per structure is less than 5.'
     * @throws IOException
     */
    @Test
    public void cellAvg() throws IOException {
        fileTest("cellAvgInput", "cellAvgOutput", 4);
    }

    /**
     * tests if a valid grid is accepted as such
     * @throws IOException
     */
    @Test
    public void valid() throws IOException {
        fileTest("validInput", "validOutput", 6);
    }
}
