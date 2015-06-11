package au.com.aitcollaboration.chessgame.model.game.structure;

import au.com.aitcollaboration.chessgame.support.Utils;

public class Position {

    private final int myX;
    private final int myY;

    public Position(int myX, int myY) {
        this.myX = myX;
        this.myY = myY;
    }

    public Position nextPosition(int myX, int myY) {
        int incrementedX = this.myX + myX;
        int incrementedY = this.myY + myY;

        return new Position(incrementedX, incrementedY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        return (myX == position.myX) && (myY == position.myY);
    }

    @Override
    public int hashCode() {
        int result = myX;
        result = 31 * result + myY;

        return result;
    }

    @Override
    public String toString() {
        return "[" + Utils.toGamePosition(new int[]{myX, myY}) + "]";
    }
}
