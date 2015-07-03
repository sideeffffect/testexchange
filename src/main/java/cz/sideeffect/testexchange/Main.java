package cz.sideeffect.testexchange;

import java.io.*;

/**
 * The main class for the project.
 * Reads grid description from `stdin` and prints validation for each cell to `stdout`.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Validator validator = new Validator();
        validator.validate(reader, writer);
        
        reader.close();
        writer.close();
    }
}
