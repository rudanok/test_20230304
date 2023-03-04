import java.io.*;
import java.util.ArrayList;
import java.util.Random;

//класс включает функционал для розыгрыша игрушек
public class Lottery {
    ArrayList<Toy> toyList; //список игрушек
    ArrayList<Integer> playOut; //список игрушек для выдачи
    Random rd; //для розыгрыша игрушки
    String file; //файл куда записываются все игрушки

    //конструктор
    Lottery(String file) {
        this.file = file;
        rd = new Random(System.currentTimeMillis());
        toyList = new ArrayList<>();
        playOut = new ArrayList<>();
    }

    //метод добавления новой игрушки в список
    public void addToy(Toy toy) {
        toyList.add(toy);
        System.out.println("Игрушка успешно добавлена в список");
    }

    //кол-во игрушек
    public int size() {
        return toyList.size();
    }

    //метод изменения частоты выпадения для заданной игрушки
    public boolean setFreq(int id, double freq) {
        if (toyList.size() == 0) {
            System.out.println("Список игрушек пуст");
            return false;
        }
        for (int i = 0; i < toyList.size(); i++) {
            Toy toy = toyList.get(i);
            if (toy.getId() == id) {
                toy.setFreq(freq);
                System.out.println("Частота выпадения игрушки успешно изменена");
                return true;
            }
        }
        return false;
    }

    //розыгрыш игрушки методом рулетки
    public void roulette() {
        if (toyList.size() == 0) {
            System.out.println("Список игрушек пуст");
            return;
        }

        //считаем суммарную частоту
        double sum = 0;
        for (int i = 0; i < toyList.size(); i++) {
            sum += toyList.get(i).getFreq();
        }

        //находим подходящую игрушку
        double r = rd.nextDouble();
        double acc = 0;
        int j = -1;
        for (int i = 0; i < toyList.size() && j == -1; i++) {
            acc += toyList.get(i).getFreq() / sum;
            if (acc > r) {

                //выбрали игрушку под номером i
                j = i;
            }
        }

        //если не удалось разыграть игрушку
        if (j == -1) {
            j = rd.nextInt(toyList.size()); //берем случайно
        }
        int id = toyList.get(j).getId();
        playOut.add(j);
        System.out.println("Была разыграна игрушка номер " + id);
    }

    //получить игрушку
    public void getToy() throws Exception {
        if (playOut.size() > 0) {

            //пишем игрушку в файл
            int i = playOut.get(0);
            playOut.remove(0);
            Toy toy = toyList.get(i);

            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            out.println(toy.getId() + ";" + toy.getTitle());
            out.close();

            toy.setSize(toy.getSize() - 1); //уменьшаем кол-во игрушек
            if (toy.getSize() <= 0) {
                toyList.remove(i);
            }
            System.out.println("Игрушка с номером " + toy.getId() + " добавлена в файл " + file);
        } else {
            System.out.println("Нет игрушек для выдачи");
        }
    }

    //показать все игрушки
    public void show() {
        if (toyList.size() == 0) {
            System.out.println("Список игрушек пуст");
        } else {
            System.out.println("Список игрушек:");
            for (int i = 0; i < toyList.size(); i++) {
                Toy toy = toyList.get(i);
                System.out.println("\nИдентификатор: " + toy.getId());
                System.out.println("Название: " + toy.getTitle());
                System.out.println("Кол-во: " + toy.getSize());
                System.out.println("Частота выпадения: " + toy.getFreq());
            }
        }
        if (playOut.size() == 0) {
            System.out.println("\nСписок разыгранных игрушек пуст");
        } else {
            System.out.println("\nСписок разыгранных игрушек:");
            for (int i = 0; i < playOut.size(); i++) {
                int j = playOut.get(i);
                Toy toy = toyList.get(j);
                System.out.println("\nИдентификатор: " + toy.getId());
                System.out.println("Название: " + toy.getTitle());
            }
        }
    }
}