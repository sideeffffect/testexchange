package cz.sideeffect.testexchange;

import cz.sideeffect.testexchange.grid.Color;
import cz.sideeffect.testexchange.grid.Coord;
import cz.sideeffect.testexchange.grid.Grid;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the grid.
 */
public class TestGrid {

    /**
     * tests the method merge from Grid
     */
    @Test
    public void merge1() {
        Grid grid = new Grid();
        grid.addCell(new Coord(1,0), Color.RED);
        grid.addCell(new Coord(1,2), Color.RED);
        grid.addCell(new Coord(0,1), Color.RED);
        grid.addCell(new Coord(2,1), Color.RED);

        Assert.assertEquals(4, grid.structuresCount());

        grid.addCell(new Coord(1, 1), Color.RED);

        Assert.assertEquals("Structures are merged correctly", 1, grid.structuresCount());
    }

    /**
     * tests the method merge from Grid
     */
    @Test
    public void merge2() {
        Grid grid = new Grid();
        grid.addCell(new Coord(1,0), Color.RED);
        grid.addCell(new Coord(1,2), Color.RED);
        grid.addCell(new Coord(0,1), Color.RED);
        grid.addCell(new Coord(2,1), Color.RED);

        Assert.assertEquals(4, grid.structuresCount());

        grid.addCell(new Coord(1, 1), Color.YELLOW);

        Assert.assertEquals("Structures of different colors cannot be merged", 5, grid.structuresCount());
    }
}
