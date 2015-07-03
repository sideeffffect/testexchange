package cz.sideeffect.testexchange.grid;

/**
 * This class represents the color of a structure
 */
public enum Color {
    RED, YELLOW, GREEN, BLUE;

    @Override
    public String toString() {
        if(this == RED){
            return "R";
        }
        else if(this == YELLOW){
            return "Y";
        }
        else if(this == GREEN){
            return "G";
        }
        else if(this == BLUE){
            return "B";
        }
        else {
            throw new RuntimeException("Wrong color enum: ");
        }
    }
}
