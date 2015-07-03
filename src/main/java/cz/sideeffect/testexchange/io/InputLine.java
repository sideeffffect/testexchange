package cz.sideeffect.testexchange.io;

import cz.sideeffect.testexchange.grid.Color;
import cz.sideeffect.testexchange.grid.Coord;

/**
 * This class represents single line of the input
 */
public class InputLine {
    public final Coord coord;
    public final Color color;
    public final String comment;

    public InputLine(Coord coord, Color color, String comment) {
        this.coord = coord;
        this.color = color;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return coord.x + "," + coord.y +
                   "," + color +
                   comment;
    }
}
