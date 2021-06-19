import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class User extends Player {
    //Базовый конструктор
    User(){

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
        //Выводим информацию о том, что нам сейчас надо делать атаковать или защищаться
        System.out.println("You are " + (this.getState()? "attacking" : "defending"));
        System.out.println("Your cards is:");
        System.out.print("( ");
        //Выводим информацию о том, какой набор карт у нас есть
        for (var el : this.getDeck()){

            System.out.printf("[ %d ] ", el);

        }
        System.out.print(")");
        //Делаем выбор из предложенных карт
        System.out.print("\nYour pick is: ");
        Scanner in = new Scanner(System.in);
        int move;
        move = Integer.parseInt(in.next());
        //Если такой элемент действительно находится в нашей колоде то продолжаем работать с введенным числом,
        // иначе просто повторяем ввод, пока юзер не перестанет ошибаться
        while(!this.getDeck().contains(move)){

            System.out.println("Your input was incorrect, try again.");
            System.out.print("\nYour pick is: ");
            move = in.nextInt();

        }
        //Добавляем ход к уже сделанным, чтобы ИИ мог их просматривать и строить свои действия на основе этого
        //И также удаляем карту из нашей колоды
        this.moves.add(move);
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
