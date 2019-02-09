package ru.academits.ikonnikov.range;

import java.util.Locale;
import java.util.Scanner;

public class MainRange {
    public static void main(String[] args) throws NullPointerException {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        Range range1 = new Range(0.0, 0.0);

        do {
            System.out.println("Введите значение начальной точки первого отрезка: ");
            range1.setPointStart(scanner.nextDouble());
            System.out.println("Введите значение конечной точки первого отрезка: ");
            range1.setPointEnd(scanner.nextDouble());

        } while (range1.checkPointsRange());

        System.out.printf("   Длина первого отрезка = %.2f %n", range1.getLength());

        System.out.println("   Проверка принадлежности точки первому отрезку.");
        System.out.println("Введите значение проверяемой точки: ");
        double pointChecked = scanner.nextDouble();

        if (range1.isInside(pointChecked)) {
            System.out.printf("Точка (%.2f) принадлежит отрезку [%.2f ; %.2f] %n ", pointChecked, range1.getPointStart(), range1.getPointEnd());
        } else {
            System.out.printf("Точка (%.2f) НЕ принадлежит отрезку [%.2f ; %.2f] %n ", pointChecked, range1.getPointStart(), range1.getPointEnd());
        }

        Range range2 = new Range(0.0, 0.0);
        do {
            System.out.println("Введите значение начальной точки второго отрезка: ");
            range2.setPointStart(scanner.nextDouble());
            System.out.println("Введите значение конечной точки второго отрезка: ");
            range2.setPointEnd(scanner.nextDouble());

        } while (range2.checkPointsRange());

        System.out.printf("   Длина второго отрезка = %.2f %n", range2.getLength());

        System.out.println("   Проверка принадлежности точки второму отрезку.");

        if (range2.isInside(pointChecked)) {
            System.out.printf("Точка (%.2f) принадлежит отрезку [%.2f ; %.2f] %n ", pointChecked, range2.getPointStart(), range2.getPointEnd());
        } else {
            System.out.printf("Точка (%.2f) НЕ принадлежит отрезку [%.2f ; %.2f] %n ", pointChecked, range2.getPointStart(), range2.getPointEnd());
        }

        System.out.println("   Получение пересечения двух отрезков:");

        if ( range1.getIntersectionRanges(range2) == null) {
            System.out.printf("Отрезки [%.2f ; %.2f] и [%.2f ; %.2f] НЕ пересекаются. %n ", range1.getPointStart(), range1.getPointEnd(),
                    range2.getPointStart(), range2.getPointEnd());
        } else {
            System.out.printf("Пересечением отрезка [%.2f ; %.2f] с отрезком [%.2f ; %.2f] является отрезок: %s %n", range1.getPointStart(), range1.getPointEnd(),
                    range2.getPointStart(), range2.getPointEnd(), range1.getIntersectionRanges(range2).toString());
        }

        System.out.println("   Получение объединения двух отрезков:");

        if (range1.getUnionRanges(range2).length == 1) {
            System.out.printf("Объединением отрезка [%.2f ; %.2f] с отрезком [%.2f ; %.2f] является отрезок: %s %n", range1.getPointStart(), range1.getPointEnd(),
                    range2.getPointStart(), range2.getPointEnd(), range1.getUnionRanges(range2)[0].toString());
        } else {
            System.out.printf("Объединением отрезка [%.2f ; %.2f] с отрезком [%.2f ; %.2f] являются 2 отрезка: %s и %s %n ", range1.getPointStart(), range1.getPointEnd(),
                    range2.getPointStart(), range2.getPointEnd(), range1.getUnionRanges(range2)[0].toString(), range1.getUnionRanges(range2)[1].toString());
        }

        System.out.println("   Получение разности двух отрезков:");

        if (range1.getDifferenceRanges(range2) == null) {
            System.out.printf("Результатом вычитания отрезка [%.2f ; %.2f] из отрезка  [%.2f ; %.2f] является ПУСТОЙ отрезок %n", range2.getPointStart(), range2.getPointEnd(),
                    range1.getPointStart(), range1.getPointEnd());
        } else if (range1.getDifferenceRanges(range2).length == 2) {
            System.out.printf("Результатом вычитания отрезка [%.2f ; %.2f] из отрезка [%.2f ; %.2f] являются 2 отрезка: %s и %s %n", range2.getPointStart(), range2.getPointEnd(),
                    range1.getPointStart(), range1.getPointEnd(), range1.getDifferenceRanges(range2)[0].toString(), range1.getDifferenceRanges(range2)[1].toString());
        } else {
            System.out.printf("Результатом вычитания отрезка [%.2f ; %.2f] из отрезка  [%.2f ; %.2f] является отрезок: %s %n", range2.getPointStart(), range2.getPointEnd(),
                    range1.getPointStart(), range1.getPointEnd(), range1.getDifferenceRanges(range2)[0].toString());
        }
    }
}


