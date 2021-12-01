package GradleMars.mapStructures;

import java.util.Scanner;

public interface ArealElements {
    int getIndexY();

    void setIndexY(int indexY);

    int getIndexX();

    void setIndexX(int indexX);

    boolean comparePosition(int outX, int outY);

    String writeContent();
}
