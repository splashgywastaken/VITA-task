import java.util.ArrayList;
//Абстрактный класс для более приятного построения методов и двух производных классов User и AI
public abstract class Player {

    //Колода игроков
    protected ArrayList<Integer> cards = new ArrayList<>();
    //Ходы игроков
    protected ArrayList<Integer> moves = new ArrayList<>();
    //Штрафные очки
    protected Integer points = 0;
    //Позиция на поле
    protected Boolean attacks;

    //Метод для собирания колоды карт
    protected abstract void setDeck();
    //Метод для получения колоды карт
    public abstract ArrayList<Integer> getDeck();

    //Метод для добавления нового хода
    public abstract void addMove();
    //Метод для получения всех сделанных ходов
    public abstract  ArrayList<Integer> getMoves();
    //Метод для получения последнего хода, сделанного игроком
    public abstract Integer getLastMove();

    //Метод для установки текущей позиции игрока на поле
    public abstract void setState(Boolean state);
    //Метод для получения текущей позиции игрока на поле
    public abstract Boolean getState();

    //Метод для добавления штрафных очков
    public abstract void setPoints(Integer _points);
    //Метод для получения текущего количества штрафных очков
    public abstract Integer getPoints();

    //Метод для получения имени класса
    public abstract String getName();

}
