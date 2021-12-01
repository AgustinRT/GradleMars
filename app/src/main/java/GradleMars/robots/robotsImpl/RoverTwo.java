package GradleMars.robots.robotsImpl;

import java.util.ArrayList;
import java.util.Scanner;

import GradleMars.mapStructures.VisualMapModule;
import GradleMars.mapStructures.AreaStructureImpl.RovertView;
import GradleMars.mapStructures.VisualMapImpl.VisualMapModuleImpl;
import GradleMars.robots.Rover;
import GradleMars.tools.RoverUtils;

public class RoverTwo extends RoverCore implements Rover {

    VisualMapModule mapModule;
    protected final String ENDMESSAGE = "Good bye explorer";

    public RoverTwo(Scanner reader) {
        super(reader);
        this.mapModule = new VisualMapModuleImpl();
    }

    public void initerConditions() {
        super.initerConditions();
        this.mapModule.buildMapMars(this.sizex, this.sizey, this.roverViewBuilder(), null);
    }

    public void roverMovement(String order) {
        if (order.equals(Command.f.name())) {
            this.roverRelocation(1);
        } else if (order.equals(Command.b.name())) {
            this.roverRelocation(-1);
        } else if (order.equals(Command.l.name())) {
            this.turnRover(-1);
        } else if (order.equals(Command.r.name())) {
            this.turnRover(1);
        } else if (order.equalsIgnoreCase(Command.end.name())) {
            this.setEndProgram();
        } else {
            RoverUtils.roverErrorReport(String.format(this.ERRORORDER, order));
        }
    }

    public void insertListObstacles() {
        this.listObstacles = new ArrayList();
        do {
            System.out.println(this.ASKOBSTACLE);
        } while (this.insertNodeObstacles());
        this.mapModule.buildMapMars(this.sizex, this.sizey, this.roverViewBuilder(), this.listObstacles);
        this.roverMapReport();
    }

    private void setEndProgram() {
        this.exploreMars = false;
        System.out.println(this.ENDMESSAGE);
    }

    private RovertView roverViewBuilder() {
        return new RovertView(this.roverx, this.rovery, this.roverz);
    }

    public void roverInputOrder() {
        System.out.println(this.ASKCOMMAND);
        this.roverMovement(this.roverReader.next());
        this.roverMapReport();
    }

    public void roverMapReport() {
        System.out.println(String.format(this.BASICREPORT, this.roverx, this.rovery, this.roverz));
        this.mapModule.drawMapMars();
    }

    private void updateRoverOnMap() {
        this.mapModule.updateMapElements(this.roverx, this.rovery, this.roverz);
    }

    private void roverRelocation(int movement) {
        if (this.roverz.equals(Orientation.n.name())) {
            this.updateRovery((movement * -1));
        } else if (this.roverz.equals(Orientation.e.name())) {
            this.updateRoverx(movement);
        } else if (this.roverz.equals(Orientation.s.name())) {
            this.updateRovery(movement);
        } else if (this.roverz.equals(Orientation.w.name())) {
            this.updateRoverx((movement * -1));
        }
    }

    private void updateRoverx(int movement) {
        int newPosX = this.checkEdges(this.roverx, this.sizex, movement);
        if (this.checkNoObstacle(newPosX, this.rovery)) {
            this.roverx = newPosX;
            this.updateRoverOnMap();
        } else {
            RoverUtils.roverErrorReport(String.format(this.OBSTACLEREPORT, newPosX, this.rovery));
        }
    }

    private void updateRovery(int movement) {
        int newPosY = this.checkEdges(this.rovery, this.sizey, movement);
        if (this.checkNoObstacle(this.roverx, newPosY)) {
            this.rovery = newPosY;
            this.updateRoverOnMap();
        } else {
            RoverUtils.roverErrorReport(String.format(this.OBSTACLEREPORT, this.roverx, newPosY));
        }
    }

    private int checkEdges(int position, int edge, int change) {
        int newpos = position + change;
        if (newpos == edge) {
            return 0;
        } else if (newpos < 0) {
            return edge - 1;
        }
        return newpos;
    }

    private void turnRover(int gradeTurn) {
        Orientation[] valuesOrientation = Orientation.values();
        int indexOrientation = Orientation.valueOf(this.roverz).ordinal();
        int newIndex = indexOrientation + gradeTurn;
        if (newIndex == (valuesOrientation.length)) {
            this.roverz = valuesOrientation[0].name();
        } else if (newIndex == -1) {
            this.roverz = valuesOrientation[(valuesOrientation.length - 1)].name();
        } else {
            this.roverz = valuesOrientation[newIndex].name();
        }
        this.updateRoverOnMap();
    }
}
