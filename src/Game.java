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

        //Write here something for great UX

        //
        player1.addMove();
        player2.addMove();
        int roundResult = 0;

        if (player1.getState()){

            //round if player1 attacks
            roundResult = player2.getLastMove() - player1.getLastMove();

        } else
            if (player2.getState()){

            //round if player2 attacks
            roundResult = player1.getLastMove() - player2.getLastMove();

        }

        if (roundResult < 0)
            player2.setPoints(roundResult);

        switchSides(player1, player2, obj);

        if (player1.getDeck().isEmpty() || player2.getDeck().isEmpty()){

            obj.setGameEnded(true);

        }

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

    public void game(){

        while(!this.gameEnded){

            round(this.playerAI, this.playerUser, this);

        }

        System.out.printf("Results of game is %s", results(this.playerAI, this.playerAI));

    }

    public void setGameEnded(Boolean state){

        this.gameEnded = state;

    }

}
