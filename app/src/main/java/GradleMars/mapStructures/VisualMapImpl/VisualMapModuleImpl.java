package GradleMars.mapStructures.VisualMapImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import GradleMars.mapStructures.ArealElements;
import GradleMars.mapStructures.VisualMapModule;
import GradleMars.mapStructures.AreaStructureImpl.NeutralArea;
import GradleMars.mapStructures.AreaStructureImpl.Obstacle;
import GradleMars.mapStructures.AreaStructureImpl.RovertView;
import GradleMars.tools.RoverUtils;

public class VisualMapModuleImpl implements VisualMapModule {

    List<List<ArealElements>> map2D;
    RovertView currentRover;
    List<ArealElements> listObstacles;

    private final String ERRORNOTMAP = "Error, must build teh map before to draw it";

    public void buildMapMars(int sizeX, int sizeY, RovertView currentRover, List<ArealElements> listObstacles) {
        this.map2D = new ArrayList();
        this.currentRover = currentRover;
        this.listObstacles = listObstacles;
        for (int coordY = 0; coordY < sizeY; coordY++) {
            List<ArealElements> innerMap2D = new ArrayList<ArealElements>();
            for (int coordX = 0; coordX < sizeX; coordX++) {
                innerMap2D.add(this.logicAreaCreation(coordY, coordX));
            }
            this.map2D.add(innerMap2D);
        }
    }

    public void drawMapMars() {
        if (!RoverUtils.checkValidList(this.map2D)) {
            System.out.println(this.ERRORNOTMAP);
        } else {
            this.logicDrawMap();
        }
    }

    public void updateMapElements(int posX, int posy, String orientation) {
        if (!this.currentRover.comparePosition(posX, posy)) {
            this.updateOldPosRover();
            this.updateNewPosRover(posX, posy, orientation);
        } else {
            this.currentRover.setOrientationRovert(orientation);
        }
    }

    private void updateOldPosRover() {
        this.updateElemnetonMap2D(this.currentRover.getIndexX(), this.currentRover.getIndexY(),
                new NeutralArea(this.currentRover.getIndexX(), this.currentRover.getIndexY(), true));
    }

    private void updateNewPosRover(int posX, int posy, String orientation) {
        this.currentRover.setIndexX(posX);
        this.currentRover.setIndexY(posy);
        this.currentRover.setOrientationRovert(orientation);
        this.updateElemnetonMap2D(posX, posy, this.currentRover);
    }

    private void updateElemnetonMap2D(int x, int y, ArealElements newElem) {
        this.map2D.get(y).set(x, newElem);
    }

    private void logicDrawMap() {
        String lineEdgeH = String.join("", Collections.nCopies((this.map2D.get(0).size() + 2), "-"));
        System.out.println(lineEdgeH);
        List<String> linesPile = new ArrayList();
        for (List<ArealElements> rowList : this.map2D) {
            linesPile.add(this.rowManager(rowList));
        }
        System.out.println(loginSeparatorsDraw(linesPile));
        System.out.println(lineEdgeH);
    }

    private String loginSeparatorsDraw(List<String> linesPile) {
        return "|" + String.join("|\n|", linesPile) + "|";
    }

    private String rowManager(List<ArealElements> rowList) {
        String lineDraw = "";
        for (ArealElements areaNode : rowList) {
            lineDraw += areaNode.writeContent();
        }
        return lineDraw;
    }

    private ArealElements logicAreaCreation(int coordY, int coordX) {
        if (this.currentRover.comparePosition(coordX, coordY)) {
            return this.currentRover;
        } else if (this.checkObstacle(coordX, coordY)) {
            return new Obstacle(coordX, coordY);
        } else {
            return new NeutralArea(coordX, coordY);
        }
    }

    private boolean checkObstacle(int posX, int posY) {
        if (!RoverUtils.checkValidList(this.listObstacles)) {
            return false;
        }
        for (ArealElements nodeObs : this.listObstacles) {
            if (nodeObs.comparePosition(posX, posY)) {
                return true;
            }
        }
        return false;
    }
}
