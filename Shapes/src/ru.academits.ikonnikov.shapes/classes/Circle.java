package ru.academits.ikonnikov.shapes.classes;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * radius * 2;
    }

    @Override
    public String toString() {
        return String.format("class: Circle; radius: %.2f, Width: %.2f, Height: %.2f, Area: %.2f, Perimeter: %.2f", this.radius, getWidth(), getHeight(), getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object.getClass() != this.getClass()) {
            return false;
        }

        Circle circle = (Circle) object;

        return this.radius == circle.radius;
    }
}
