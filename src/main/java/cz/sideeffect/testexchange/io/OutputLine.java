package cz.sideeffect.testexchange.io;

import cz.sideeffect.testexchange.grid.Color;
import cz.sideeffect.testexchange.grid.Coord;

/**
 * This class represents single line of the output
 */
public class OutputLine {
    public final Coord coord;
    public final Color color;
    public final String answer;
    public final String comment;

    public OutputLine(Coord coord, Color color, String answer, String comment) {
        this.coord = coord;
        this.color = color;
        this.answer = answer;
        this.comment = comment;
    }

    public OutputLine(InputLine line, String answer) {
        this(line.coord, line.color, answer, line.comment);
    }

    @Override
    public String toString() {
        return coord.x + "," + coord.y +
                   "," + color +
                   "," + answer +
                   comment;
    }
}
