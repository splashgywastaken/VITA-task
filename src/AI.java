import java.util.Collections;
import java.util.LinkedList;

public class AI extends Player {

    //Для чего нужны методы с ключевым словом @Override описанно в классе Player
    @Override
    public void addMove(LinkedList<Integer> EnemyMoves){
        Integer move;
        if (cards.size() >= 6){
            move = Collections.min(cards);
        }
        else {
            //возможные для противника ходы:
            LinkedList<Integer> possibleEMoves = new LinkedList<>();
            for (int i = 0; i < 11; i++)
                if (!EnemyMoves.contains(i))
                    possibleEMoves.add(i);

            if (getState()){
                move = Collections.max(cards);
            } else {
                if (Collections.max(possibleEMoves) >= Collections.max(cards))
                    move = Collections.max(cards);
                else
                    move = Collections.min(cards);
            }
        }
        moves.add(move);
        cards.remove(move);
    }
    @Override
    public String getName(){
        return "ИИ";
    }

}
