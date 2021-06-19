import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Game {

    public boolean gameEnded = false;
    private final AI playerAI = new AI();
    private final User playerUser = new User();

    static private void switchSides(Player player1, Player player2, Game obj){

        //switching positions of attacker and defending one
        Boolean boolSwitch = player1.getState();
        player1.setState(!boolSwitch);
        player2.setState(boolSwitch);

        //now we are switching decks of players
        obj.switchDecks();

    }

    static private void round(Player player1, Player player2, Game obj){

        //Ход первого игрока, вызываем метод класса по добавлению нового хода в "историю" ходов
        System.out.println("Pick card to play");
        player1.addMove();
        //Ход второго игрока, проводим аналогичные действия
        System.out.println("Player picked a card");
        player2.addMove();
        //Ходы сделаны, карты на стол, теперь уже можно глянуть что там наворотил ИИ
        System.out.println("AI picked a card");
        System.out.println("AI pick is " + player2.getLastMove());
        //Смотрим кто атаковал/защищался и делаем на основе этого вывод о том, сколько и кому полетит штрафных очков
        int roundResult = 0;
        if (player1.getState()){

            //round if player1 attacks
            roundResult = player2.getLastMove() - player1.getLastMove();

        } else
            if (player2.getState()){

            //round if player2 attacks
            roundResult = player1.getLastMove() - player2.getLastMove();

        }
        //Выводим информацию о том, что игрок номер 1 (его имя) молодец, а второй получает штрафные очки
        if (roundResult < 0){
            System.out.println(player1.getName() + " won that round, " + player2.getName() + " will get " + Math.abs(roundResult) + " penalty points");
            player2.setPoints(Math.abs(roundResult));
        }
        //Аналогичная ситуация, только вот теперь все наоборот
        else if (roundResult > 0) {

            System.out.println(player2.getName() + " won that round, " + player1.getName() + " will get " + roundResult + " penalty points");
            player1.setPoints(roundResult);

        } //получаем ничью в случае если защита была удачной, а атака плохо постаралась
        else {
            System.out.println("Draw, no one will get penalty points");
        }
        //Делаем небольшую задержку перед сменой сторон
        try{
        TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e){

            Thread.currentThread().interrupt();

        }
        //Смена сторон (я это интерпретировал как то, что двум игрокам надо менятся всем, кроме штрафных очков)
        // Т.е. колодой и позицией атакующего/защищающегося
        switchSides(player1, player2, obj);

        //Если у нас одна из колод пуста, то игра завершена
        if (player1.getDeck().isEmpty() || player2.getDeck().isEmpty()){
            //Ставим маркер о том, что игра завершилась на true
            obj.setGameEnded(true);

        }
        //конец раунда, больше ничего происходить не должно
    }

    static private String results(Player player1, Player player2){

        if (player1.getPoints() > player2.getPoints()){

            return player1.getName() + " won";

        } else if (player1.getPoints() < player2.getPoints()){

            return player2.getName() + " won";

        } else {

            return (" draw");

        }

    }
    private void switchDecks(){

        var temp = playerAI.getDeck();
        playerAI.cards = playerUser.cards;
        playerUser.cards = temp;

    }

    //Основной метод класса, который собственно является единственным доступным для Main
    public void game(){

        //Выбираем кто будет ходить первым и выводим соответствующие сообщения
        System.out.println("Who will start the game? Type \"P\" for player or \"A\" for AI");
        Scanner in = new Scanner(System.in);
        String whoStarts = in.nextLine();
        while (!(whoStarts.equals("P") || whoStarts.equals("A"))){
            //Проверка на дурака
            System.out.println("Your input was incorrect, try again.");
            System.out.print("\nYour pick is: ");
            whoStarts = in.nextLine();

        }
        //Как раз таки соответствующие сообщения
        System.out.println("The game has started the move begins the " + (whoStarts.equals("P") ? "player" : "AI"));
        //Устанавливаем текующее состояние двух игроков
        this.playerUser.setState(whoStarts.equals("P"));
        this.playerAI.setState(whoStarts.equals("A"));
        //Пока игра не завершится, а точнее, пока не будет выполненно следующее условие, то играются раунды
        while(!this.gameEnded){

            //Вызываем статический метод round для отыгрыша раундов
            round(this.playerUser, this.playerAI, this);

        }
        //Если мы вышли из цикла while то игра завершилась и одному из игроков можно праздновать победу
        System.out.printf("Results of game is %s", results(this.playerAI, this.playerAI));

    }
    //Сеттер на текущее состояние игры
    public void setGameEnded(Boolean state){

        this.gameEnded = state;

    }

}
