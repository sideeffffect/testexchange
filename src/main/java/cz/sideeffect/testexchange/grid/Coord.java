package cz.sideeffect.testexchange.grid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a 2D cartesian coordinates (a cell in the grid).
 */
public class Coord {
    public final int x;
    public final int y;
    
    public Set<Coord> neighbours(){
        return new HashSet<>(Arrays.asList( new Coord(x, y+1)
                                          , new Coord(x, y-1)
                                          , new Coord(x+1, y)
                                          , new Coord(x-1, y)
                                          ));
    }

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        if (x != coord.x) return false;
        return y == coord.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
