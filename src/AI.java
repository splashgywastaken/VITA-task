import java.util.ArrayList;
import java.util.Collections;

public class AI extends Player {

    AI(){

       this.setDeck();

    }

    @Override
    protected void setDeck(){

        for (int i = 0; i < 11; i++){

            this.cards.add(i);

        }

    }
    @Override
    public ArrayList<Integer> getDeck(){

        return this.cards;

    }

    @Override
    public void addMove(){

        Integer move = Collections.max(cards);
        this.cards.remove(cards.indexOf(move));

    }
    @Override
    public ArrayList<Integer> getMoves(){

        return this.moves;

    }
    @Override
    public Integer getLastMove(){

        return moves.get(moves.size() - 1);

    }

    @Override
    public void setState(Boolean state){

        this.attacks = state;

    }
    @Override
    public Boolean getState(){

        return this.attacks;

    }

    @Override
    public void setPoints(Integer _points){

        this.points += _points;

    }
    @Override
    public Integer getPoints(){

        return this.points;

    }

    @Override
    public String getName(){

        return this.getClass().getCanonicalName();

    }

}
