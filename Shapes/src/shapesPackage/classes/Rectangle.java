package shapesPackage.classes;

import java.lang.NullPointerException;

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
        try {
            return getWidth() * getHeight();
        } catch ( NullPointerException e ) {
            System.out.println("Множество фигур не должно быть пустым! ");
            return 0;
        }
    }
    
    @Override
    public double getPerimeter() {
        try {
            return (sideLength + sideWidth) * 2;
        } catch ( NullPointerException e ) {
            System.out.println("Множество фигур не должно быть пустым! ");
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("class: Rectangle; Width: %.2f, Height: %.2f, Area: %.2f, Perimeter: %.2f, %n", getWidth(), getHeight(), getArea(), getPerimeter());
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
                System.out.println(" true (This is the same object!)");
                return true;
            }

            if (object.getClass() != this.getClass()) {
                System.out.println("Classes of objects are different!");
                return false;
            }
            Rectangle rectangle = (Rectangle) object;

            if (this.sideLength == rectangle.sideLength && this.sideWidth == rectangle.sideWidth) {
                return true;
            } else {
                System.out.println("Parameters of objects are different!");
                return false;
            }      
    }
}

