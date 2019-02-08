package shapesPackage;

import shapesPackage.classes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    static class SortShapesByArea implements Comparator<Shape> {
        public int compare(Shape shape1, Shape shape2) {
            return (int) (shape2.getArea() - shape1.getArea());
        }
    }

    private static Shape getShapeMaxArea(Shape shape[]) {
        try {
            Arrays.sort(shape, new SortShapesByArea());
            return shape[0];
        } catch ( NullPointerException e ) {
            System.out.println("Массив фигур не должен быть пустым! " + e.getMessage());
            return null;
        }
    }

    static class SortShapesByPerimeter implements Comparator<Shape> {
        public int compare(Shape shape1, Shape shape2) {
            return (int) (shape2.getPerimeter() - shape1.getPerimeter());
        }
    }

    private static Shape getShapeSecondMaxPerimeter(Shape shape[]) {
        try {
            Arrays.sort(shape, new SortShapesByPerimeter());
            return shape[1];
        } catch ( NullPointerException e ) {
            System.out.println("Массив фигур не должен быть пустым! " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Shape[] shape = new Shape[17];

        shape[0] = new Triangle(-4.0, 0.0, -6.0, 6.0, 0.0, 2.0);
        shape[1] = new Triangle(-7.0, -5.0, -2.0, 0.0, 0.0, -5.0);
        shape[2] = new Triangle(1.0, 2.0, 2.0, 6.0, 4.0, 1.0);

        shape[3] = new Square(3.3);
        shape[4] = new Square(3.5);
        shape[5] = new Square(6.9);

        shape[6] = new Rectangle(4.6, 2.5);
        shape[7] = new Rectangle(2.1, 5.8);
        shape[8] = new Rectangle(5.3, 4.8);

        shape[9] = new Circle(1.9);
        shape[10] = new Circle(2.1);
        shape[11] = new Circle(3.7);

        shape[12] = new Rectangle(5.3, 4.8);
        shape[13] = new Triangle(1.0, 2.0, 2.0, 6.0, 4.0, 1.0);
        shape[14] = new Square(3.5);
        shape[15] = new Circle(2.1);
        shape[16] = shape[0];

        System.out.printf("Результат сравнения фигуры - %s  с фигурой %s  =   ", shape[2].toString(), shape[13].toString());
        System.out.println(shape[2].equals(shape[13]));
        System.out.println();

        System.out.printf("Результат сравнения фигуры - - %s с фигурой %s  =   ", shape[8].toString(), shape[12].toString());
        System.out.println(shape[12].equals(shape[8]));
        System.out.println();

        System.out.printf("Результат сравнения фигуры - %s с фигурой %s  =   ", shape[4].toString(), shape[14].toString());
        System.out.println(shape[14].equals(shape[4]));
        System.out.println();

        System.out.printf("Результат сравнения фигуры %s с фигурой %s  =   ", shape[10].toString(), shape[15].toString());
        System.out.println(shape[10].equals(shape[15]));
        System.out.println();

        System.out.printf("Результат сравнения фигуры %s с фигурой %s  =   ", shape[0].toString(), shape[0].toString());
        System.out.println(shape[0].equals(shape[16]));
        System.out.println();

        Shape shapeMaxArea = getShapeMaxArea(shape);
        System.out.println("Фигура с максимальным значением площади имеет следующие параметры: ");
        System.out.println(Arrays.toString(new Shape[]{shapeMaxArea}));

        Shape shapeSecondMaxPerimeter = getShapeSecondMaxPerimeter(shape);
        System.out.println("Фигура со вторым по величине периметром имеет следующие параметры: ");
        System.out.println(Arrays.toString(new Shape[]{shapeSecondMaxPerimeter}));

        System.out.println(Arrays.toString(shape));

        System.out.printf("Результат сравнения фигуры - %s с фигурой %s  =  ", shape[10].toString(), shape[11].toString());
        System.out.println(shape[10].equals(shape[11]));
        System.out.println();
        System.out.printf("Результат сравнения фигуры - %s с фигурой %s  =  ", shape[3].toString(), shape[4].toString());
        System.out.println(shape[3].equals(shape[4]));
        System.out.println();

        System.out.printf("Hashcode of shape:  %s =  %d  %n", shape[0].toString(), shape[0].hashCode());
        System.out.printf("Hashcode of shape: %s =  %d  %n", shape[2].toString(), shape[2].hashCode());
        System.out.printf("Hashcode of shape: %s =  %d  %n", shape[3].toString(), shape[3].hashCode());
        System.out.printf("Hashcode of shape: %s =  %d  %n", shape[4].toString(), shape[4].hashCode());
        System.out.printf("Hashcode of shape: %s =  %d  %n", shape[9].toString(), shape[9].hashCode());
        System.out.printf("Hashcode of shape: %s =  %d  %n", shape[10].toString(), shape[10].hashCode());

    }
}
