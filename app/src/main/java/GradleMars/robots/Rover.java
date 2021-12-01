package GradleMars.robots;

import java.util.Scanner;

public interface Rover {
    void initerConditions();

    void insertListObstacles();

    void roverInputOrder();

    void roverMovement(String order);

    void roverMapReport();

    boolean keepExploring();
}
