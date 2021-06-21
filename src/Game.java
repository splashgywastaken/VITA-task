import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Game {

    private boolean gameEnded;
    private final Player player1;
    private final Player player2;

    Game() {
        gameEnded = false;
        player1 = new User();
        player2 = new AI();
    }

    private void switchSides(){

        //Меняем позиции игроков на поле
        boolean boolSwitch = player1.getState();
        player1.setState(!boolSwitch);
        player2.setState(boolSwitch);

    }
    //Функция для задержки вывода в консоли
    private void delay(int time){
        try{
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void round() {

        //Ход первого игрока, вызываем метод класса по добавлению нового хода в "историю" ходов
        System.out.println("Выберите карту для разыгровки в раунде");
        //Вызываем функцию для добавления хода и передаем в нее ходы противника
        player1.addMove(player2.getMoves());
        //Ход второго игрока, проводим аналогичные действия
        System.out.println("Игрок выбрал карту");
        delay(100);
        //Вызываем функцию для добавления хода и передаем в нее ходы противника
        player2.addMove(player1.getMoves());
        //Ходы сделаны, карты на стол, теперь уже можно глянуть что там наворотил ИИ
        System.out.println("ИИ выбрал карту");
        System.out.println("выбор ИИ: " + player2.getLastMove());

        //Распределение штрафных очков между игроками по результатам раунда
        Player attacking = player1.getState() ? player1 : player2;
        Player defending = player1.getState() ? player2 : player1;
        delay(50);
        int roundResult = attacking.getLastMove() - defending.getLastMove();
        if (roundResult > 0) {
            //Выводим соответствующее сообщение
            System.out.println(attacking.getName() + " выиграл этот раунд, " + defending.getName() +
                    " получит " + roundResult + " штрафных очков");
            defending.addPoints(roundResult);
        }else {  //Иначе выводим сообщение о том, что защита  выстояла
            System.out.println("Защита " + defending.getName() + " была успешна");
        }
        //Делаем небольшую задержку перед сменой сторон
        delay(100);
        //Смена сторон т.е. позиции атакующего/защищающегося
        switchSides();

        gameEnded = player1.getDeck().isEmpty() || player2.getDeck().isEmpty();
        //конец раунда, больше ничего происходить не должно
    }

    private String results(){

        if (player1.getPoints() < player2.getPoints()){

            return "игрок " + player1.getName() + " победил ";

        } else if (player1.getPoints() > player2.getPoints()){

            return "игрок " + player2.getName() + " победил ";

        } else {

            return (" ничья");

        }

    }

    //Основной метод класса, который собственно является единственным доступным для Main
    public void game(){

        //Выбираем кто будет ходить первым и выводим соответствующие сообщения
        System.out.println("Кто ходит первым? Введите (на латиннице) \"P\" для игрока или \"A\" для ИИ");
        Scanner in = new Scanner(System.in);
        String whoStarts = in.nextLine();
        while (!(whoStarts.equals("P") || whoStarts.equals("A"))){
            //Проверка на дурака
            System.out.println("Ввод был некорректным, попробуйте снова.");
            System.out.println("Вы выбрали карту со значением: ");
            whoStarts = in.nextLine();

        }
        //Как раз таки соответствующие сообщения
        System.out.println("Игра начинается, первым ходит " + (whoStarts.equals("P") ? player1.getName() : player2.getName()));
        //Устанавливаем текующее состояние двух игроков
        player1.setState(whoStarts.equals("P"));
        player2.setState(whoStarts.equals("A"));
        //Пока игра не завершится, а точнее, пока не будет выполненно следующее условие, то играются раунды
        while(!gameEnded){
            //Вызываем статический метод round для отыгрыша раундов
            round();
        }
        //Если мы вышли из цикла while то игра завершилась и одному из игроков можно праздновать победу
        System.out.printf("Результаты игры таковы:  %s \n", results());
        System.out.println("У игрока " + player1.getName() + " было " + player1.getPoints() + " очков");
        System.out.println("У игрока " + player2.getName() + " было "  + player2.getPoints() + " очков");

    }

}
