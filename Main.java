import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        Lottery lot = new Lottery("result.txt");
        do {
            try {
                System.out.println("1 - Добавить игрушку");
                System.out.println("2 - Изменить частоту выпадения игрушки");
                System.out.println("3 - Разыграть игрушку");
                System.out.println("4 - Получить разыгранную игрушку");
                System.out.println("5 - Показать все игрушки");
                System.out.println("6 - Выход");
                System.out.print(">> ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice < 1 || choice > 6) {
                    throw new Exception("Ошибка ввода");
                }
                switch (choice) {
                    case 1: {
                        System.out.print("Укажите название игрушки: ");
                        String title = sc.nextLine();
                        System.out.print("Укажите количество: ");
                        int size = Integer.parseInt(sc.nextLine());
                        System.out.print("Укажите частоту выпадения игрушки: ");
                        double freq = Double.parseDouble(sc.nextLine());
                        lot.addToy(new Toy(title, size, freq));
                        break;
                    }
                    case 2: {
                        System.out.print("Укажите идентификатор игрушки: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Укажите частоту выпадения игрушки: ");
                        double freq = Double.parseDouble(sc.nextLine());
                        if (lot.setFreq(id, freq) == false) {
                            System.out.println("Не удалось изменить частоту выпадения игрушки");
                        }
                        break;
                    }
                    case 3: {
                        lot.roulette();
                        break;
                    }
                    case 4: {
                        lot.getToy();
                        break;
                    }
                    case 5: {
                        lot.show();
                        break;
                    }
                }
            } catch (Exception ex) {
                choice = -1;
                System.out.println(ex.getMessage());
            }
            System.out.println();
        } while (choice != 6);
    }
}