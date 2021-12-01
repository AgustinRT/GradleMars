package GradleMars.robots.robotsImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class RoverOneTest {

    @Test
    public void testHappyPath() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("10\n10\n5\n5\nn\n".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);
        RoverOne robotTest = new RoverOne(new Scanner(System.in));
        robotTest.initerConditions();

        robotTest.roverMovement("f");
        assertEquals(robotTest.getRovery(), 4);

        robotTest.roverMovement("b");
        assertEquals(robotTest.getRovery(), 5);

        robotTest.roverMovement("r");
        assertEquals(robotTest.getRovery(), 5);
        assertEquals(robotTest.getRoverx(), 5);
        assertEquals(robotTest.getRoverz(), "e");

        robotTest.roverMovement("f");
        assertEquals(robotTest.getRovery(), 5);
        assertEquals(robotTest.getRoverx(), 6);

        robotTest.roverMovement("r");
        assertEquals(robotTest.getRoverz(), "s");
        robotTest.roverMovement("r");
        assertEquals(robotTest.getRoverz(), "w");
        robotTest.roverMovement("r");
        assertEquals(robotTest.getRoverz(), "n");
        assertEquals(robotTest.keepExploring(), true);
        robotTest.roverMovement("end");
        assertEquals(robotTest.keepExploring(), false);
    }

    @Test
    public void testFailsAtInput() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("10\n10\n5\n5\nn\n".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);
        RoverOne robotTest = new RoverOne(new Scanner(System.in));
        robotTest.initerConditions();
        robotTest.roverMovement("u");

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString();
        String[] listOoutputs = outputText.split("\n");
        String lastMessage = listOoutputs[(listOoutputs.length - 1)].trim();
        assertEquals(lastMessage, "The order u is unvalid");
    }

    @Test
    public void testMapEdge() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("5\n5\n2\n2\nn\n".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);
        RoverOne robotTest = new RoverOne(new Scanner(System.in));
        robotTest.initerConditions();

        robotTest.roverMovement("f");
        assertEquals(robotTest.getRovery(), 1);

        robotTest.roverMovement("f");
        assertEquals(robotTest.getRovery(), 0);

        robotTest.roverMovement("f");
        assertEquals(robotTest.getRovery(), 4);

        robotTest.roverMovement("b");
        assertEquals(robotTest.getRovery(), 0);

        robotTest.roverMovement("r");
        assertEquals(robotTest.getRovery(), 0);
        assertEquals(robotTest.getRoverx(), 2);
        assertEquals(robotTest.getRoverz(), "e");

        robotTest.roverMovement("f");
        assertEquals(robotTest.getRoverx(), 3);
        robotTest.roverMovement("f");
        assertEquals(robotTest.getRoverx(), 4);
        robotTest.roverMovement("f");
        assertEquals(robotTest.getRoverx(), 0);
        robotTest.roverMovement("b");
        assertEquals(robotTest.getRoverx(), 4);
        assertEquals(robotTest.getRovery(), 0);
        assertEquals(robotTest.getRoverz(), "e");
    }

    @Test
    public void testRoverRepot() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("10\n10\n5\n5\nn\n".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        RoverOne robotTest = new RoverOne(new Scanner(System.in));
        robotTest.initerConditions();
        robotTest.roverMovement("u");
        System.setIn(stdin);
        System.setOut(stdout);
        String outputText = byteArrayOutputStream.toString();
        String[] listOoutputs = outputText.split("\n");
        String lastMessage = listOoutputs[(listOoutputs.length - 1)].trim();
        assertEquals(lastMessage, "The order u is unvalid");

        ps = new PrintStream(byteArrayOutputStream);
        stdout = System.out;
        System.setOut(ps);
        robotTest.roverMovement("f");
        assertEquals(robotTest.getRovery(), 4);
        robotTest.roverMapReport();
        System.setOut(stdout);
        outputText = byteArrayOutputStream.toString();
        listOoutputs = outputText.split("\n");
        lastMessage = listOoutputs[(listOoutputs.length - 1)].trim();
        assertEquals(lastMessage, "Rover is at x:5 y:4 facing:n");

        ps = new PrintStream(byteArrayOutputStream);
        stdout = System.out;
        System.setOut(ps);
        robotTest.roverMovement("r");
        assertEquals(robotTest.getRoverz(), "e");
        robotTest.roverMapReport();
        System.setOut(stdout);
        outputText = byteArrayOutputStream.toString();
        listOoutputs = outputText.split("\n");
        lastMessage = listOoutputs[(listOoutputs.length - 1)].trim();
        assertEquals(lastMessage, "Rover is at x:5 y:4 facing:e");

        ps = new PrintStream(byteArrayOutputStream);
        stdout = System.out;
        System.setOut(ps);
        robotTest.roverMovement("f");
        assertEquals(robotTest.getRovery(), 4);
        assertEquals(robotTest.getRoverx(), 6);
        robotTest.roverMapReport();
        System.setOut(stdout);
        outputText = byteArrayOutputStream.toString();
        listOoutputs = outputText.split("\n");
        lastMessage = listOoutputs[(listOoutputs.length - 1)].trim();
        assertEquals(lastMessage, "Rover is at x:6 y:4 facing:e");
    }

    @Test
    public void testRoverObstaclesInput() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("10\n10\n5\n5\nn\n".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);
        RoverOne robotTest = new RoverOne(new Scanner(System.in));
        robotTest.initerConditions();

        System.setIn(new ByteArrayInputStream("Y\n3\n2\nY\n5\n4\nn\n".getBytes()));
        robotTest.updateScaner(new Scanner(System.in));
        robotTest.insertListObstacles();
        assertEquals(robotTest.listObstacles.size(), 2);
        assertEquals(robotTest.listObstacles.get(0).getIndexX(), 3);
        assertEquals(robotTest.listObstacles.get(1).getIndexY(), 4);

        System.setIn(new ByteArrayInputStream("Y\n30\n-1\n7\n2\nY\n5\nw\n2\nn\n".getBytes()));
        robotTest.updateScaner(new Scanner(System.in));
        robotTest.insertListObstacles();
        assertEquals(robotTest.listObstacles.size(), 2);
        assertEquals(robotTest.listObstacles.get(0).getIndexX(), 7);
        assertEquals(robotTest.listObstacles.get(1).getIndexY(), 2);

        System.setIn(new ByteArrayInputStream("Y\n30\n-1\n7\n2\nY\n5\nw\n2\nn\n".getBytes()));
        robotTest.updateScaner(new Scanner(System.in));
        robotTest.insertListObstacles();
        assertEquals(robotTest.listObstacles.size(), 2);
        assertEquals(robotTest.listObstacles.get(0).getIndexX(), 7);
        assertEquals(robotTest.listObstacles.get(1).getIndexY(), 2);
    }

    @Test
    public void testRoverObstaclesBloquing() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("10\n10\n5\n5\nn\n".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);
        RoverOne robotTest = new RoverOne(new Scanner(System.in));
        robotTest.initerConditions();

        System.setIn(new ByteArrayInputStream("Y\n5\n3\nY\n7\n7\nn\n".getBytes()));
        robotTest.updateScaner(new Scanner(System.in));
        robotTest.insertListObstacles();
        assertEquals(robotTest.listObstacles.size(), 2);
        assertEquals(robotTest.listObstacles.get(0).getIndexX(), 5);
        assertEquals(robotTest.listObstacles.get(1).getIndexY(), 7);

        ps = new PrintStream(byteArrayOutputStream);
        stdout = System.out;
        System.setOut(ps);
        robotTest.roverMovement("f");
        assertEquals(robotTest.getRovery(), 4);
        assertEquals(robotTest.getRoverx(), 5);
        robotTest.roverMovement("f");
        assertEquals(robotTest.getRovery(), 4);
        assertEquals(robotTest.getRoverx(), 5);
        System.setOut(stdout);
        String outputText = byteArrayOutputStream.toString();
        String[] listOoutputs = outputText.split("\n");
        String lastMessage = listOoutputs[(listOoutputs.length - 1)].trim();
        assertEquals(lastMessage, "Was find and obstacle on: 5, 3");

        ps = new PrintStream(byteArrayOutputStream);
        stdout = System.out;
        System.setOut(ps);
        robotTest.roverMapReport();

        System.setOut(stdout);
        outputText = byteArrayOutputStream.toString();
        listOoutputs = outputText.split("\n");
        lastMessage = listOoutputs[(listOoutputs.length - 1)].trim();
        assertEquals(lastMessage, "Rover is at x:5 y:4 facing:n");
    }
}
