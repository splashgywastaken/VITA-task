import java.util.ArrayList;
import java.util.Collections;

public class AI extends Player {

    //Базовый конструктор
    AI(){

       this.setDeck();

    }
    //Для чего нужны методы с ключевым словом @Override описанно в классе Player
    @Override
    protected void setDeck(){

        for (int i = 0; i < 12; i++){

            this.cards.add(i);

        }

    }
    @Override
    public ArrayList<Integer> getDeck(){

        return this.cards;

    }

    @Override
    public void addMove(){

        //Алгоритм по выбору того, как ИИ будет ходить
        //Пока что максимально простой алгоритм: ИИ берет самую сильную карту для защиты и самую слабую для атаки
        Integer move;
        if (getState())
            move = Collections.min(cards);
        else
            move = Collections.max(cards);
        this.cards.remove(cards.indexOf(move));
        this.moves.add(move);

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
