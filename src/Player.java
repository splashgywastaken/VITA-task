import java.util.ArrayList;

public abstract class Player {

    protected ArrayList<Integer> cards = new ArrayList<>();
    protected ArrayList<Integer> moves = new ArrayList<>();
    protected Integer points = 0;
    protected Boolean attacks;

    protected abstract void setDeck();
    public abstract ArrayList<Integer> getDeck();

    public abstract void addMove();
    public abstract  ArrayList<Integer> getMoves();
    public abstract Integer getLastMove();

    public abstract void setState(Boolean state);
    public abstract Boolean getState();

    public abstract void setPoints(Integer _points);
    public abstract Integer getPoints();

    public abstract String getName();

}
