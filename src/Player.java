import java.util.LinkedList;
//Абстрактный класс для более приятного построения методов и двух производных классов User и AI
public abstract class Player {

    //Колода игроков
    protected LinkedList<Integer> cards;
    //Ходы игроков
    protected LinkedList<Integer> moves;
    //Штрафные очки
    protected int points;
    //Позиция на поле
    protected boolean attacks;

    //Базовый конструктор
    Player() {
        cards = new LinkedList<>();
        moves = new LinkedList<>();
        points = 0;
        fillDeck();
    }

    //Метод для получения имени класса
    public abstract String getName();

    //Метод для добавления нового хода
    public abstract void addMove(LinkedList<Integer> EnemyMoves);

    //Метод для собирания колоды карт
    protected void fillDeck() {
        for (int i = 0; i < 12; i++){
            cards.add(i);
        }
    }

    //Метод для получения колоды карт
    public LinkedList<Integer> getDeck() {
        return cards;
    }
    //Метод для получения всех сделанных ходов
    public LinkedList<Integer> getMoves() {
        return moves;
    }

    //Метод для получения последнего хода, сделанного игроком
    public int getLastMove() {
        return moves.getLast();
    }

    //Метод для установки текущей позиции игрока на поле
    public void setState(boolean state) {
        this.attacks = state;
    }
    //Метод для получения текущей позиции игрока на поле
    public boolean getState() {
        return attacks;
    }

    //Метод для добавления штрафных очков
    public void addPoints(int _points) {
        points += _points;
    }

    //Метод для получения текущего количества штрафных очков
    public int getPoints() {
        return points;
    }

}
