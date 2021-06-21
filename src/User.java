import java.util.LinkedList;
import java.util.Scanner;

public class User extends Player {
    //Метод для вывода колоды
    private static void displayList(LinkedList<Integer> deck, String message){
        if (deck.size()!=0) {
            System.out.println(message);
            System.out.print("( ");
            //Выводим информацию о том, какой набор карт у нас есть
            deck.forEach((var el)-> System.out.printf("[ %d ] ", el));
            System.out.println(")");
        }
    }
    //Для чего нужны методы с ключевым словом @Override описанно в классе Player
    @Override
    public void addMove(LinkedList<Integer> EnemyMoves){
        //Выводим информацию о том, что нам сейчас надо делать атаковать или защищаться
        System.out.println("Вы " + (this.getState()? "атакуете" : "защищаетесь"));
        //немного казуально, но решил сделать подобное, ибо любой игрок может и так спокойно запомнить ходы противника
        displayList(EnemyMoves, "Предыдущие ходы противника:");
        //Выводим колоду игрока:
        displayList(cards, "Ваша колода выглядит следующим образом:");
        //Делаем выбор из предложенных карт
        System.out.print("Вы выбираете карту: ");
        Scanner in = new Scanner(System.in);
        int move;
        move = in.nextInt();
        //Если такой элемент действительно находится в нашей колоде то продолжаем работать с введенным числом,
        // иначе просто повторяем ввод, пока юзер не перестанет ошибаться
        while(!this.getDeck().contains(move)){

            System.out.println("Ваш ввод был некорректным, поробуйте ещё раз");
            System.out.println("Вы выбрали карту с индексом: " + move);
            move = in.nextInt();

        }
        //Добавляем ход к уже сделанным, чтобы ИИ мог их просматривать и строить свои действия на основе этого
        //И также удаляем карту из нашей колоды
        moves.add(move);
        cards.remove((Integer)move);
    }
    @Override
    public String getName() {
        return System.getProperty("user.name");
    }
}
