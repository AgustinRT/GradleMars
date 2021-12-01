package GradleMars.mapStructures.AreaStructureImpl;

import java.util.HashMap;
import java.util.Map;

import GradleMars.mapStructures.ArealElements;

public class RovertView extends AreaElementImpl implements ArealElements {
    String roverOrientation;
    Map<String, String> orientationGuide = new HashMap<String, String>() {
        {
            put("n", "^");
            put("e", ">");
            put("s", "v");
            put("w", "<");
        }
    };

    public RovertView(int x, int y) {
        this.indexX = x;
        this.indexY = y;
        this.setOrientationRovert("n");
    }

    public RovertView(int x, int y, String orientation) {
        this.indexX = x;
        this.indexY = y;
        this.setOrientationRovert(orientation);
    }

    public void setOrientationRovert(String orientation) {
        if (this.orientationGuide.containsKey(orientation)) {
            this.roverOrientation = orientation;
            this.tipeElement = orientationGuide.get(this.roverOrientation);
        }
    }

    public boolean comparePosition(int outX, int outY) {
        return outX == this.indexX && outY == this.indexY;
    }

    public String writeContent() {
        return this.tipeElement;
    }

}
