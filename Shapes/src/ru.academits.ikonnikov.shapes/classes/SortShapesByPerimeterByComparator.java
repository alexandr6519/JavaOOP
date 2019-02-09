package ru.academits.ikonnikov.shapes.classes;

import java.util.Comparator;
import java.lang.Double;

public class SortShapesByPerimeterByComparator implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        return (int) Double.compare(shape2.getPerimeter(), shape1.getPerimeter());
    }
}
