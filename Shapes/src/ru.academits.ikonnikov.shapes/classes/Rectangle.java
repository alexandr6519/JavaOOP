package ru.academits.ikonnikov.shapes.classes;

public class Rectangle implements Shape {
    private double sideLength;
    private double sideWidth;

    public Rectangle(double sideLength, double sideWidth) {
        this.sideLength = sideLength;
        this.sideWidth = sideWidth;
    }

    @Override
    public double getWidth() {
        return sideWidth;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return getWidth() * getHeight();
    }

    @Override
    public double getPerimeter() {
        return (sideLength + sideWidth) * 2;
    }

    @Override
    public String toString() {
        return String.format("class: Rectangle; sideLength: %.2f, sideWidth %.2f, Width: %.2f, Height: %.2f, Area: %.2f, Perimeter: %.2f",
                this.sideLength, this.sideWidth, getWidth(), getHeight(), getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);
        hash = prime * hash + Double.hashCode(sideWidth);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object.getClass() != this.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) object;

        return (this.sideLength == rectangle.sideLength && this.sideWidth == rectangle.sideWidth);
    }
}
