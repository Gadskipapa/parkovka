import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Car {

    private String mark; // марка машины
    private String color;
    private boolean korobkaAvtomat; // false - механика. true - автомат.
    private boolean pricep; // false - без прицепа. true - с прицепом.
    private int mass; // масса машины
    private static int count = 0; // счетчик машин на парковке в данный момент
    private static int idGlobal = 0; // счетчик ИДов
    private int id = 0; // уникальный идентификатор машины. по которому можно узнать конкретный автомобиль,
    // даже если на парковке присутствуют 100% одинаковые автомобили.

    String tmp = "";
    Scanner scan = new Scanner(System.in);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = ( Car ) o;
        return Objects.equals(tmp, car.tmp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tmp);
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isKorobkaAvtomat() {
        return korobkaAvtomat;
    }

    public void setKorobkaAvtomat(boolean korobkaAvtomat) {
        this.korobkaAvtomat = korobkaAvtomat;
    }

    public boolean isPricep() {
        return pricep;
    }

    public void setPricep(boolean pricep) {
        this.pricep = pricep;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Car.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public Car() {
        System.out.println("Укажите марку автомобиля");
        mark = scan.next();
        System.out.println("Укажите цвет автомобиля");
        color = scan.next();
        while (!(tmp.equals("1") || tmp.equals("2"))) { // сразу проверка на корректность ввода
            System.out.println("Укажите тип коробки передач автомобиля. 1 - механическая. 2 - автомат");
            tmp = scan.next();
            if (tmp.equals("2")) {
                korobkaAvtomat = true;
            }
        }
        tmp = "";
        while (!(tmp.equals("1") || tmp.equals("2"))) { // сразу проверка на корректность ввода
            System.out.println("Укажите наличие прицепа. 1 - нет. 2 - есть");
            tmp = scan.next();
            if (tmp.equals("2")) {
                pricep = true;
            }
        }
        while (mass == 0) {
            System.out.println("Укажите массу автомобиля");
            Scanner scan = new Scanner(System.in);
            try {
                mass = Math.abs(scan.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("Укажите массу цифрами");
            }
        }

        idGlobal++;
        setId(idGlobal);

        System.out.println("марка - " + mark);
        System.out.println("Цвет - " + color);
        System.out.println("Коробка - " + korobkaAvtomat);
        System.out.println("прицеп - " + pricep);
        System.out.println("масса - " + mass);
        System.out.println("id номер автомобиля - " + id);
    }


    // метод подсчета белых машин. нигде не вызывается.
    public int whiteNumbering(Car[] parkovka) {
        int whiteNumb = 0;
        for (int i = 0; i < parkovka.length; i++) {
            String col = parkovka[i].getColor().toLowerCase();
            if (col.equals("white") || col.contains("бел")) {
                whiteNumb++;
            }
        }
        return whiteNumb;
    }

    // метод подсчета количества машин с автоматом
    public int autoKppNumbering(Car[] parkovka) {
        int autoKpp = 0;
        for (int i = 0; i < parkovka.length; i++) {
            String col = parkovka[i].getColor().toLowerCase();
            if (parkovka[i].isKorobkaAvtomat() == true) {
                autoKpp++;
            }
        }
        return autoKpp;
    }

    // метод проверки одинаковых машин. марка+цвет
    public int similar(Car[] parkovka, Car car) {
        int similar = -1;
        for (int i = 0; i < parkovka.length; i++) {
            if (parkovka[i].getColor().equals(car.getColor()) && parkovka[i].getMark().equals(car.getMark())) {
                similar++;
            }
        }
        return similar;
    }

    // сортировка по имени марки
    public static void sortByName(Car[] parkovka) {
        Arrays.sort(parkovka);
    }

    // увеличение размера парковки
    public void expantion(Car[] park) {
        System.out.println("Введите новый размер парковки");
        Scanner scanner = new Scanner(System.in);
        int newSize = scanner.nextInt(); // обойдемся без проверки )
        Car[] tmp = Arrays.copyOf(park, newSize);
        park = tmp;
    }
}