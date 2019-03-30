package ru.academits.ikonnikov.rangeMain;

import ru.academits.ikonnikov.range.Range;

import java.util.Locale;
import java.util.Scanner;

public class MainRange {
    public static void main(String[] args) throws NullPointerException {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        Range range1 = new Range(-100000000.0, 100000000.0);

        do {
            System.out.println("Введите значение начальной точки первого отрезка: ");
            range1.setFrom(scanner.nextDouble());
            System.out.println("Введите значение конечной точки первого отрезка: ");
            range1.setTo(scanner.nextDouble());

            if (range1.checkPoints()) {
                System.out.println("Значение конечной точки не должно быть меньше значения начальной!!! Введите значения точек снова.");
            }

        } while (range1.checkPoints());

        System.out.printf("   Длина первого отрезка = %.2f %n", range1.getLength());

        System.out.println("   Проверка принадлежности точки первому отрезку.");
        System.out.println("Введите значение проверяемой точки: ");
        double pointChecked = scanner.nextDouble();

        if (range1.isInside(pointChecked)) {
            System.out.printf("Точка (%.2f) принадлежит отрезку [%.2f ; %.2f] %n ", pointChecked, range1.getFrom(), range1.getTo());
        } else {
            System.out.printf("Точка (%.2f) НЕ принадлежит отрезку [%.2f ; %.2f] %n ", pointChecked, range1.getFrom(), range1.getTo());
        }

        Range range2 = new Range(-100000000.0, 100000000.0);
        do {
            System.out.println("Введите значение начальной точки второго отрезка: ");
            range2.setFrom(scanner.nextDouble());
            System.out.println("Введите значение конечной точки второго отрезка: ");
            range2.setTo(scanner.nextDouble());

            if (range2.checkPoints()) {
                System.out.println("Значение конечной точки не должно быть меньше значения начальной!!! Введите значения точек снова.");
            }
        } while (range2.checkPoints());

        System.out.printf("   Длина второго отрезка = %.2f %n", range2.getLength());

        System.out.println("   Проверка принадлежности точки второму отрезку.");

        if (range2.isInside(pointChecked)) {
            System.out.printf("Точка (%.2f) принадлежит отрезку [%.2f ; %.2f] %n ", pointChecked, range2.getFrom(), range2.getTo());
        } else {
            System.out.printf("Точка (%.2f) НЕ принадлежит отрезку [%.2f ; %.2f] %n ", pointChecked, range2.getFrom(), range2.getTo());
        }

        System.out.println("   Получение пересечения двух отрезков:");

        if ( range1.getIntersection(range2) == null) {
            System.out.printf("Отрезки [%.2f ; %.2f] и [%.2f ; %.2f] НЕ пересекаются. %n ", range1.getFrom(), range1.getTo(),
                    range2.getFrom(), range2.getTo());
        } else {
            System.out.printf("Пересечением отрезка [%.2f ; %.2f] с отрезком [%.2f ; %.2f] является отрезок: %s %n", range1.getFrom(), range1.getTo(),
                    range2.getFrom(), range2.getTo(), range1.getIntersection(range2).toString());
        }

        System.out.println("   Получение объединения двух отрезков:");

        if (range1.getUnion(range2).length == 1) {
            System.out.printf("Объединением отрезка [%.2f ; %.2f] с отрезком [%.2f ; %.2f] является отрезок: %s %n", range1.getFrom(), range1.getTo(),
                    range2.getFrom(), range2.getTo(), range1.getUnion(range2)[0].toString());
        } else {
            System.out.printf("Объединением отрезка [%.2f ; %.2f] с отрезком [%.2f ; %.2f] являются 2 отрезка: %s и %s %n ", range1.getFrom(), range1.getTo(),
                    range2.getFrom(), range2.getTo(), range1.getUnion(range2)[0].toString(), range1.getUnion(range2)[1].toString());
        }

        System.out.println("   Получение разности двух отрезков:");

        if (range1.getDifference(range2).length == 0) {
            System.out.printf("Результатом вычитания отрезка [%.2f ; %.2f] из отрезка  [%.2f ; %.2f] является ПУСТОЙ отрезок %n", range2.getFrom(), range2.getTo(),
                    range1.getFrom(), range1.getTo());
        } else if (range1.getDifference(range2).length == 2) {
            System.out.printf("Результатом вычитания отрезка [%.2f ; %.2f] из отрезка [%.2f ; %.2f] являются 2 отрезка: %s и %s %n", range2.getFrom(), range2.getTo(),
                    range1.getFrom(), range1.getTo(), range1.getDifference(range2)[0].toString(), range1.getDifference(range2)[1].toString());
        } else {
            System.out.printf("Результатом вычитания отрезка [%.2f ; %.2f] из отрезка  [%.2f ; %.2f] является отрезок: %s %n", range2.getFrom(), range2.getTo(),
                    range1.getFrom(), range1.getTo(), range1.getDifference(range2)[0].toString());
        }
    }
}


