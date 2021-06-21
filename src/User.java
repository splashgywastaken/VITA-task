import java.util.Scanner;

public class User extends Player {
    //Базовый конструктор
    //Для чего нужны методы с ключевым словом @Override описанно в классе Player
    @Override
    public void addMove(){
        //Выводим информацию о том, что нам сейчас надо делать атаковать или защищаться
        System.out.println("Вы " + (this.getState()? "атакуете" : "защищаетесь"));
        System.out.println("Ваши колода выглядит следующим образом:");
        System.out.print("( ");
        //Выводим информацию о том, какой набор карт у нас есть
        cards.forEach((var el)-> System.out.printf("[ %d ] ", el));
        System.out.print(")");
        //Делаем выбор из предложенных карт
        System.out.print("\nВы выбрали: ");
        Scanner in = new Scanner(System.in);
        int move;
        move = in.nextInt();
        //Если такой элемент действительно находится в нашей колоде то продолжаем работать с введенным числом,
        // иначе просто повторяем ввод, пока юзер не перестанет ошибаться
        while(!this.getDeck().contains(move)){

            System.out.println("Ваш ввод был некорректным, поробуйте ещё раз");
            System.out.print("\nВы выбрали карту с индексом: ");
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
