package Map.Occupant;

public class Player extends Occupant {
    public Player(int r, int c) {
        super(r, c);
    }

    @Override
    public char getRepresentation() {
        return '@';
    }
}
