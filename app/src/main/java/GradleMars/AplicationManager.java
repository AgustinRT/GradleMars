package GradleMars;

import java.util.Scanner;

import GradleMars.robots.Rover;
import GradleMars.robots.robotsImpl.RoverOne;
import GradleMars.robots.robotsImpl.RoverTwo;

public class AplicationManager {
    Scanner appReader;

    public AplicationManager(Scanner reader) {
        this.appReader = reader;
    }

    public void launchExploration() {
        Rover roverBot = this.selectorRover();
        roverBot.initerConditions();
        roverBot.insertListObstacles();
        do {
            roverBot.roverInputOrder();
        } while (roverBot.keepExploring());
    }

    private Rover selectorRover() {
        try {
            System.out.println("Choose the rover model:");
            System.out.println("1 RoverOne: Write situation");
            System.out.println("2 RoverTwo: Draw map");
            String inpuSelect = this.appReader.next();
            if (inpuSelect.equals("1")) {
                return new RoverOne(this.appReader);
            } else if (inpuSelect.equals("2")) {
                return new RoverTwo(this.appReader);
            } else {
                System.out.println("Bad selection");
                return this.selectorRover();
            }
        } catch (Exception e) {
            System.out.println("Fail teh procces by: " + e.getMessage());
            return new RoverOne(this.appReader);
        }
    }
}
