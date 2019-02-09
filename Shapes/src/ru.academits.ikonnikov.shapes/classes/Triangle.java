package JavaOOP.shapes.classes;

import java.lang.NullPointerException;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }
    
    public double getTriangleLength(double x1, double x2, double x3) {
        return (Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3));
    }
    
    @Override
    public double getWidth() {
        return getTriangleLength(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getTriangleLength(y1, y2, y3);;
    }
    
    @Override
    public double getArea() {
        return 0.5 * Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1));       
    }
    
    @Override
    public double getPerimeter() 
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) + Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2)) +
                    Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
    }

    @Override
    public String toString() {
        return String.format("class: Triangle; Width: %.2f, Height: %.2f, Area: %.2f, Perimeter: %.2f, %n", getWidth(), getHeight(), getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
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
            Triangle triangle = (Triangle) object;

            return (this.x1 == triangle.x1 && this.y1 == triangle.y1 && this.x2 == triangle.x2 && this.y2 == triangle.y2 && this.x3 == triangle.x3 && this.y3 == triangle.y3);
       
    }
}
