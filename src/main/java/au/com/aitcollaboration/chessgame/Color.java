package au.com.aitcollaboration.chessgame;

public enum Color {

    WHITE(0), BLACK(1);

    private int position;

    private Color(int position){
        this.position = position;
    }

    public int position(){
        return this.position;
    }

    public Color flip(){
        return (this.position == 1)? WHITE : BLACK;
    }
}
