import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int razmer = 0; // размер парковки
        int del;

        Scanner scan = new Scanner(System.in);

        // просим указать размер парковки. с защитой от дурака
        while (razmer == 0) {
            System.out.println("Укажите размер парковки. Но только цифрами");
            Scanner scan1 = new Scanner(System.in);
            try {
                razmer = scan1.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("!!!!!Надо было цифрами!!!!!");
            }
        }

// создаем массив объектов типа car - это парковка. коллекции не используем, так как у парковки размер сам не меняется
        // а если и изменится, то это будет редкое явление
        Car[] parkovka = new Car[Math.abs(razmer)];

// ставим/(убираем) машину на парковку
        while (true) {
            System.out.println("Вы хотите поставить машину (1) или забрать (2) ? Машин сейчас на парковке " + Car.getCount());
            String tmp = scan.next();
            if (tmp.equals("1")) {
                if (Car.getCount() >= razmer) {
                    System.out.println("!!!!!НЕТ МЕСТ!!!!!");
                    continue;
                }
                Car car = new Car();
                if ((car.getMass() <= 3000) && !car.isPricep()) { // проверка наличия места. проверка наличия прицепа и массы больше 3000
                    parkovka[car.getCount()] = car;
                    Car.setCount(Car.getCount() + 1);
                } else {
                    System.out.println("!!!!!!Такую машину нельзя поставить здесь!!!!!!");
                }
            }
            if (tmp.equals("2")) { // вывозим машину. называем ID машины и удаляем ее
                try {
                    System.out.println("Введите id машины, которую хотите забрать");
                    del = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Укажите ID цифрами");
                    continue;
                }
                // удаляем машину
                for (int i = 0; i < parkovka.length; i++) {
                    if (parkovka[i] != null) {
                        if (parkovka[i].getId() == del) {
                            Car.setCount(Car.getCount() - 1);
                            System.out.println("машина удалена. осталось " + Car.getCount());
                            for (; i < (parkovka.length - 1); i++) {
                                parkovka[i] = parkovka[i + 1];
                            }
                            parkovka[razmer - 1] = null;
                        }
                    }
                }
            }
        }
    }
}