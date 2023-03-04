//класс игрушки
public class Toy {
    private int id; //идентификатор
    private String title; //описание
    private int size; //кол-во
    private double freq; //частота выпадения

    //статичный идентификатор
    public static int global_id = 1;

    //конструктор
    public Toy(String title, int size, double freq) {
        this.id = global_id++;
        this.title = title;
        this.size = size;
        this.freq = freq;
    }

    //получение идентификатора
    public int getId() {
        return id;
    }

    //получаем кол-во
    public int getSize() {
        return size;
    }

    //изменяем кол-во
    public void setSize(int value) {
        size = value;
    }

    //получаем название
    public String getTitle() {
        return title;
    }

    //изменяем название
    public void setTitle(String value) {
        title = value;
    }

    //изменение частоты выпадения игрушки
    public void setFreq(double value) {
        freq = value;
    }

    //получение частоты выпадения игрушки
    public double getFreq() {
        return freq;
    }
}