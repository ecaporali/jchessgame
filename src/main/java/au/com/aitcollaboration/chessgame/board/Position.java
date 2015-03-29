package au.com.aitcollaboration.chessgame.board;

public class Position {

    private int myX;
    private int myY;

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

        if (myX != position.myX) return false;
        if (myY != position.myY) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = myX;
        result = 31 * result + myY;

        return result;
    }

    @Override
    public String toString() {
        return "[myX=" + myX + ", myY=" + myY + "]";
    }
}
