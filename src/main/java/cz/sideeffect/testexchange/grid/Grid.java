package cz.sideeffect.testexchange.grid;

import cz.sideeffect.testexchange.Validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a grid of cells where some are colored and thus form single-colored structures.
 * Since the coordinates of the grid can be arbitrary `int`s (possitive according to the specification,
 * but the implementation is more general) the cells are held in a HashMap.
 */
public class Grid {

    private final Map<Coord,Structure> coord2structure;
    private final Set<Structure> structures;

    public Grid() {
        coord2structure = new HashMap<>();
        structures = new HashSet<>();
    }

    /**
     * adds one cell to the grid
     * @param coord coordinates of the cell
     * @param color color of the cell
     */
    public void addCell(Coord coord, Color color){
        Structure tmpStructure = Structure.singleton(coord, color);
        coord2structure.put(coord, tmpStructure);
        structures.add(tmpStructure);
        
        merge(tmpStructure);
    }

    /**
     * merges the given structure into its neighbors of the same color
     * @param structure structure to be merged into its surroundings
     * @return the resulting new merged structure or the original structure if merge didn't happen
     */
    private Structure merge(Structure structure) {
        Set<Structure> neighborsSameColor = neighborsSameColor(structure);
        
        if(neighborsSameColor.isEmpty()){
            return structure;
        }
        else {
            Color color = structure.color;
            Set<Coord> coords = new HashSet<>(structure.coords);
            structures.remove(structure);
            for(Structure neighbor : neighborsSameColor){
                coords.addAll(neighbor.coords);
                structures.remove(neighbor);
            }
            Structure newStructure = new Structure(coords, color);
            structures.add(newStructure);
            for(Coord coord : newStructure.coords){
                coord2structure.put(coord, newStructure);
            }
            return newStructure;
        }
    }

    /**
     * 
     * @param structure
     * @return adjacent structures of `structure`
     */
    private Set<Structure> neighbors(Structure structure){
        Set<Structure> neighbors = new HashSet<>();
        for(Coord coord : structure.coords){
            for(Coord neighbor : coord.neighbours()){
                if(coord2structure.containsKey(neighbor)){
                    Structure neighborStruct = coord2structure.get(neighbor);
                    if(neighborStruct != structure){
                        neighbors.add(neighborStruct);
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * 
     * @param structure
     * @return adjacent structures of `structure` that have the same color
     */
    private Set<Structure> neighborsSameColor(Structure structure){
        return filterByColor(neighbors(structure), structure.color);
    }

    /**
     * 
     * @param structures
     * @param color
     * @return structures that have the specified color
     */
    private Set<Structure> filterByColor(Set<Structure> structures, Color color){
        Set<Structure> filteredStructures = new HashSet<>();
        for(Structure structure : structures){
            if(structure.color == color){
                filteredStructures.add(structure);
            }
        }
        return filteredStructures;
    }

    /**
     * validates a structure the cell belongs to
     * @param coord
     * @return result of the validation
     */
    public String validate(Coord coord){
        return validate(coord2structure.get(coord));
    }

    /**
     * validates the structure
     * @param structure
     * @return result of the validation
     */
    public String validate(Structure structure) {
        // here follows match on the structure's color
        //
        // I considered creating subclasses of Structure for each color
        // with overridden method validate
        //
        // but since validation is not inherent ot the instance of Structure,
        // and is more like a relation between structures inside the grid
        // and the if is small, I kept the if here
        //
        // should the need arise (the if grows branches) we can refactor
        if(structure.color == Color.RED){
            if(neighbors(structure).size() > 1){
                return Validator.fail(Validator.R_ADJ_S);
            }
            if(structure.coords.size() > 5){
                return Validator.fail(Validator.R_CELLS);
            }
        }
        else if(structure.color == Color.YELLOW){
            for(Structure neighbor : neighbors(structure)){
                if(neighbor.color == Color.GREEN){
                    return Validator.fail(Validator.Y_ADJ_S);
                }
            }
            if(!structure.isLinear()){
                return Validator.fail(Validator.Y_LIN);
            }
        }
        else if(structure.color == Color.GREEN){
            boolean hasBlueNeighbor = false;
            for(Structure neighbor : neighbors(structure)){
                if(neighbor.color == Color.BLUE){
                    hasBlueNeighbor = true;
                    break;
                }
            }
            if(!hasBlueNeighbor){
                return Validator.fail(Validator.G_ADJ_S);
            }
        }
        else if(structure.color == Color.BLUE){
            if(filterByColor(structures, Color.BLUE).size() > 2){
                return Validator.fail(Validator.B_STRUCTS);
            }
        }
        
        
        // redundancy when `validate` called multiple times --> opportunity for optimization, if needed
        Set<Structure> sameColorStructs = filterByColor(structures, structure.color);
        double sum = 0;
        for(Structure sameColorStruct : sameColorStructs){
            sum += sameColorStruct.coords.size();
        }
        double avg = sum / sameColorStructs.size();
        if(avg >= 5){
            return Validator.fail(Validator.CELL_AVG);
        }
        
        return Validator.OK;
    }

    /**
     * 
     * @return number of structures in the grid
     */
    public int structuresCount(){
        return structures.size();
    }
}
