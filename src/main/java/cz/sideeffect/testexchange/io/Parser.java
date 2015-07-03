package cz.sideeffect.testexchange.io;

import cz.sideeffect.testexchange.grid.Color;
import cz.sideeffect.testexchange.grid.Coord;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used for parsing the input.
 */
public class Parser {

    /**
     * 
     * @param line one line of the input
     * @return parsed input line
     */
    public static InputLine parseLine(String line){
        Matcher m = Pattern.compile("\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(R|Y|G|B)\\s*(.*)").matcher(line);
        if(m.find()){
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            Coord coord = new Coord(x, y);
            
            Color color;
            if(m.group(3).equals("R")){
                color = Color.RED;
            }
            else if(m.group(3).equals("Y")){
                color = Color.YELLOW;
            }
            else if(m.group(3).equals("G")){
                color = Color.GREEN;
            }
            else if(m.group(3).equals("B")){
                color = Color.BLUE;
            }
            else {
                throw new RuntimeException("Wrong color: " + m.group(3));
            }
            
            String comment = m.group(4);
            
            return new InputLine(coord, color, comment);
        }
        else {
            throw new RuntimeException("Invalid input: " + "'" + line + "'");
        }
    }

    /**
     * this method parses lines from the input (provided by `reader`)
     * 
     * @param reader provides the whole input
     * @return all parsed lines
     * @throws IOException
     */
    public static List<InputLine> parse(BufferedReader reader) throws IOException {
        List<InputLine> lines = new ArrayList<>();

        String line;
        while((line = reader.readLine()) != null){
            //if(!line.equals("")) {
            lines.add(parseLine(line));
            //}
        }

        return lines;
    }
}
