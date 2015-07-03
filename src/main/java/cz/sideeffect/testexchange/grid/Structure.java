package cz.sideeffect.testexchange.grid;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a structure in the grid, a group of adjacent cells of the same color
 */
public class Structure {
    public final Set<Coord> coords;
    public final Color color;

    public Structure(Set<Coord> coords, Color color) {
        this.coords = coords;
        this.color = color;
    }

    /**
     * creates new structure from a single cell of a given color
     * @param coord
     * @param color
     * @return the newly created structure
     */
    public static Structure singleton(Coord coord, Color color){
        return new Structure(new HashSet<>(Collections.singleton(coord)), color);
    }

    /**
     * 
     * @return whether the scructure is linear -- a single horizontal or vertical line
     */
    public boolean isLinear(){
        boolean horizontal = true;
        boolean vertical = true;
        Coord origin = coords.iterator().next();
        
        for(Coord coord : coords){
            if(coord.x != origin.x){
                horizontal = false;
                break;
            }
        }

        for(Coord coord : coords){
            if(coord.y != origin.y){
                vertical = false;
                break;
            }
        }
        
        return horizontal || vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Structure structure = (Structure) o;

        if (!coords.equals(structure.coords)) return false;
        return color == structure.color;
    }

    @Override
    public int hashCode() {
        int result = coords.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return color + ", " + coords;
    }
}
