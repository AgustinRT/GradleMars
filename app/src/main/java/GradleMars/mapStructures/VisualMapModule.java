package GradleMars.mapStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import GradleMars.mapStructures.AreaStructureImpl.RovertView;

public interface VisualMapModule {
    void buildMapMars(int sizeX, int sizeY, RovertView currentRover, List<ArealElements> listObstacles);

    void drawMapMars();

    void updateMapElements(int posX, int posy, String orientation);
}
