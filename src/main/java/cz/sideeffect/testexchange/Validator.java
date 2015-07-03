package cz.sideeffect.testexchange;

import cz.sideeffect.testexchange.grid.Grid;
import cz.sideeffect.testexchange.io.InputLine;
import cz.sideeffect.testexchange.io.OutputLine;
import cz.sideeffect.testexchange.io.Parser;

import java.io.*;
import java.util.List;

/**
 * This class performs the validation on grid specified by the input.
 */
public class Validator {
    
    public static String OK = "OK";
    public static String R_ADJ_S = "Red structure has no more than one another adjacent structure.";
    public static String R_CELLS = "Red structure cannot consist from more than 5 cells.";
    public static String Y_ADJ_S = "Yellow structure cannot have an adjacent green structure.";
    public static String Y_LIN = "Yellow structure is always linear, i.e. all cells that form the structure are on a single horizontal or vertical line.";
    public static String G_ADJ_S = "Green structure always has an adjacent blue structure.";
    public static String B_STRUCTS = "There are no more than two blue structures in the grid.";
    public static String CELL_AVG = "For each color, average number of cells per structure is less than 5.";

    /**
     * 
     * @param rule the string describing a rule
     * @return description that a particular rule was broken
     */
    public static String fail(String rule){
        return "Failed '" + rule + "'";
    }

    /**
     * reads the description of a grid, creates the grid, performs validation of all cells
     * and prints the result to the specified writer
     * 
     * @param reader provides the description of a grid
     * @param writer here is written the result of the validation
     * @throws IOException
     */
    public int validate(BufferedReader reader, BufferedWriter writer) throws IOException {
        List<InputLine> lines = Parser.parse(reader);

        Grid grid = new Grid();
        
        for(InputLine line : lines){
            grid.addCell(line.coord, line.color);
        }

        for(InputLine line : lines){
            String answer = grid.validate(line.coord);
            OutputLine outputLine = new OutputLine(line, answer);
            writer.append(outputLine.toString()).append('\n');
        }
        
        return grid.structuresCount();
    }
}
