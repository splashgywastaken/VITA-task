import java.util.Collections;

public class AI extends Player {

    //Для чего нужны методы с ключевым словом @Override описанно в классе Player
    @Override
    public void addMove(){
        //Алгоритм по выбору того, как ИИ будет ходить
        //Пока что максимально простой алгоритм: ИИ берет самую сильную карту для защиты и самую слабую для атаки
        Integer move;
        if (getState())
            move = Collections.min(cards);
        else
            move = Collections.max(cards);
        cards.remove(move);
        moves.add(move);

    }
    @Override
    public String getName(){
        return "ИИ";
    }

}
