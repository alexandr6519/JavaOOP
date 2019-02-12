package ru.academits.ikonnikov.range;

import java.lang.IllegalArgumentException;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        if (from > to) {
            throw new IllegalArgumentException("Значение конечной точки не должно быть меньше значения начальной!!!");
        }

        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        if (from > this.to) {
            throw new IllegalArgumentException("Значение конечной точки не должно быть меньше значения начальной!!!");
        }

        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        if (this.from > to) {
            throw new IllegalArgumentException("Значение конечной точки не должно быть меньше значения начальной!!!");
        }

        this.to = to;
    }

    public double getLength() {
        return this.to - this.from;
    }

    public boolean isInside(double pointChecked) {
        return pointChecked >= this.from && pointChecked <= this.to;
    }

    public boolean checkPoints() {
        return (from > to);
    }

    public Range getIntersection(Range range) {
        if (Math.max(this.from, range.from) >= Math.min(this.to, range.to)) {
            return null;
        }

        return new Range(Math.max(this.from, range.from), Math.min(this.to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (Math.max(this.from, range.from) > Math.min(this.to, range.to)) {
            return new Range[]{new Range(this.from, this.to), new Range(range.from, range.to)};
        } else {
            return new Range[]{new Range(Math.min(this.from, range.from), Math.max(this.to, range.to))};
        }
    }

    public Range[] getDifference(Range range) {
        if (Math.max(this.from, range.from) >= Math.min(this.to, range.to)) {
            return new Range[]{new Range(this.from, this.to)};
        }

        if (range.from > this.from) {
            if (this.to > range.to) {
                return new Range[]{new Range(this.from, range.from), new Range(range.to, this.to)};
            } else {
                return new Range[]{new Range(this.from, range.from)};
            }
        } else if (this.to > range.to) {
            return new Range[]{new Range(range.to, this.to)};
        } else {
            return new Range[0];
        }
    }

    @Override
    public String toString() {
        return String.format("[%.2f ; %.2f]", this.from, this.to);
    }
}





