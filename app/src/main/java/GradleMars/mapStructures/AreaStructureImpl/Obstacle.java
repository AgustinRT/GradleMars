package GradleMars.mapStructures.AreaStructureImpl;

import java.util.Scanner;

import GradleMars.mapStructures.ArealElements;

public class Obstacle extends AreaElementImpl implements ArealElements {

    public Obstacle(int x, int y) {
        this.indexX = x;
        this.indexY = y;
        this.tipeElement = "*";
    }

    public boolean comparePosition(int outX, int outY) {
        return outX == this.indexX && outY == this.indexY;
    }

    public String writeContent() {
        return this.tipeElement;
    }
}