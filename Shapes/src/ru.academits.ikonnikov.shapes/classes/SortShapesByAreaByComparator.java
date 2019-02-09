package ru.academits.ikonnikov.shapes.classes;

import java.util.Comparator;
import java.lang.Double;

public class SortShapesByAreaByComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return (int) Double.compare(shape2.getArea(), shape1.getArea());
    }
}
