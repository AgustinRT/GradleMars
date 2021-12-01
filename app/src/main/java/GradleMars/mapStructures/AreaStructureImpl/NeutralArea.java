package GradleMars.mapStructures.AreaStructureImpl;

import GradleMars.mapStructures.ArealElements;

public class NeutralArea extends AreaElementImpl implements ArealElements {

    public NeutralArea(int x, int y) {
        this.indexX = x;
        this.indexY = y;
        this.tipeElement = " ";
    }

    public NeutralArea(int x, int y, boolean viewed) {
        this.indexX = x;
        this.indexY = y;
        this.tipeElement = viewed ? "." : " ";
    }

    public boolean comparePosition(int outX, int outY) {
        return outX == this.indexX && outY == this.indexY;
    }

    public String writeContent() {
        return this.tipeElement;
    }

}
