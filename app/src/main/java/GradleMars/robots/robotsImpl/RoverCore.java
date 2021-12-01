package GradleMars.robots.robotsImpl;

import java.util.List;
import java.util.Scanner;

import GradleMars.mapStructures.ArealElements;
import GradleMars.mapStructures.AreaStructureImpl.Obstacle;
import GradleMars.tools.RoverUtils;

public class RoverCore {
    protected int sizex;
    protected int sizey;
    protected int roverx;
    protected int rovery;
    protected String roverz;
    protected List<ArealElements> listObstacles;
    protected Scanner roverReader;
    protected boolean exploreMars = true;

    protected final String ERRORORDER = "The order %s is unvalid";
    protected final String ERRORORDER2 = "Not valid order: ";
    protected final String ASKOBSTACLE = "Wanna insert an obstacle: [Yy/Nn]:";
    protected final String ASKCOMMAND = "Insert command (f = forward, b = backward, l = turn left, r = turn right, end = close program):";
    protected final String BASICREPORT = "Rover is at x:%d y:%d facing:%s";
    protected final String OBSTACLEREPORT = "Was find and obstacle on: %d, %d";
    protected final String INSERTOBSTACLEX = "Insert X position of obstacle:";
    protected final String INSERTOBSTACLEY = "Insert Y position of obstacle:";
    protected final String OBSTACLEEDGE = "Not valid size :%d. Must be greater than 0 and littler than horizont %d";
    protected final String OBSTACLEZERO = "Not valid size :%d. Must be greater than 0:";
    protected final String MAPHSIZE = "Insert horizontal map size:";
    protected final String MAPVSIZE = "Insert vertical map size:";
    protected final String ROVERX = "Insert horizontal initial rover position:";
    protected final String ROVERY = "Insert vertical initial rover position:";
    protected final String INSERTDIRECTION = "Insert initial rover direction:";
    protected final String ERRORDIRECTION = "Invalid direction for rover";
    protected final String ERROROBSTACLEPOS = "Error, coincidence with the rover starting point";

    enum Command {
        f, b, l, r, end
    }

    enum Orientation {
        n, e, s, w;
    }

    public RoverCore(Scanner reader) {
        this.updateScaner(reader);
    }

    public void updateScaner(Scanner reader) {
        this.roverReader = reader;
    }

    public void initerConditions() {
        this.insertHorizontal();
        this.insertVertical();
        this.insertHPosition();
        this.insertVPosition();
        this.insertOrientation();
    }

    public ArealElements generateObstacle() {
        System.out.println(this.INSERTOBSTACLEX);
        int posX = this.insertObstacle(this.roverReader, this.sizex);
        System.out.println(this.INSERTOBSTACLEY);
        int posY = this.insertObstacle(this.roverReader, this.sizey);
        return !this.checkPositionRover(posX, posY) ? new Obstacle(posX, posY) : this.doReCallObstacle();
    }

    public boolean keepExploring() {
        return this.exploreMars;
    }

    protected boolean insertNodeObstacles() {
        String inputOrder = this.roverReader.next();
        if ("Y".equalsIgnoreCase(inputOrder)) {
            this.listObstacles.add(this.generateObstacle());
            return true;
        } else if ("N".equalsIgnoreCase(inputOrder)) {
            return false;
        } else {
            RoverUtils.roverErrorReport(this.ERRORORDER2 + inputOrder);
            return true;
        }
    }

    protected boolean checkNoObstacle(int posX, int posY) {
        if (!RoverUtils.checkValidList(this.listObstacles)) {
            return true;
        }
        for (ArealElements nodeObs : this.listObstacles) {
            if (nodeObs.comparePosition(posX, posY)) {
                return false;
            }
        }
        return true;
    }

    private ArealElements doReCallObstacle() {
        System.out.println(this.ERROROBSTACLEPOS);
        return this.generateObstacle();
    }

    private boolean checkPositionRover(int posX, int posY) {
        return this.roverx == posX && this.rovery == posY;
    }

    private int insertObstacle(Scanner reader, int edge) {
        int valueInput = RoverUtils.readAsInt(reader.next());
        if (valueInput < 0 || valueInput > (edge - 1)) {
            RoverUtils.roverErrorReport(String.format(this.OBSTACLEEDGE, valueInput, edge));
            return this.insertObstacle(reader, edge);
        }
        return valueInput;
    }

    private void insertHorizontal() {
        System.out.println(this.MAPHSIZE);
        this.sizex = this.insertManager();
    }

    private void insertVertical() {
        System.out.println(this.MAPVSIZE);
        this.sizey = this.insertManager();
    }

    private void insertHPosition() {
        System.out.println(this.ROVERX);
        this.roverx = this.insertManager(this.sizex);
    }

    private void insertVPosition() {
        System.out.println(this.ROVERY);
        this.rovery = this.insertManager(this.sizey);
    }

    private int insertManager() {
        int valueInput = RoverUtils.readAsInt(this.roverReader.next());
        if (valueInput < 1) {
            RoverUtils.roverErrorReport(String.format(this.OBSTACLEZERO, valueInput));
            return this.insertManager();
        }
        return valueInput;
    }

    private int insertManager(int edge) {
        int valueInput = RoverUtils.readAsInt(this.roverReader.next());
        if (valueInput < 1 || valueInput > (edge - 1)) {
            RoverUtils.roverErrorReport(String.format(this.OBSTACLEEDGE, valueInput, edge));
            return this.insertManager(edge);
        }
        return valueInput;
    }

    private void insertOrientation() {
        System.out.println(this.INSERTDIRECTION);
        this.roverz = this.findOrientationInput(this.roverReader.next());
        if (this.roverz == null) {
            RoverUtils.roverErrorReport(String.format(this.ERRORDIRECTION));
            this.insertOrientation();
        }
    }

    private String findOrientationInput(String orientation) {
        if (!RoverUtils.checkValidString(orientation)) {
            return null;
        }
        for (Orientation nodeOrientation : Orientation.values()) {
            if (nodeOrientation.name().equalsIgnoreCase(orientation)) {
                return nodeOrientation.name();
            }
        }
        return null;
    }

    public int getSizex() {
        return this.sizex;
    }

    public void setSizex(int sizex) {
        this.sizex = sizex;
    }

    public int getSizey() {
        return this.sizey;
    }

    public void setSizey(int sizey) {
        this.sizey = sizey;
    }

    public int getRoverx() {
        return this.roverx;
    }

    public void setRoverx(int roverx) {
        this.roverx = roverx;
    }

    public int getRovery() {
        return this.rovery;
    }

    public void setRovery(int rovery) {
        this.rovery = rovery;
    }

    public String getRoverz() {
        return this.roverz;
    }

    public void setRoverz(String roverz) {
        this.roverz = roverz;
    }
}
