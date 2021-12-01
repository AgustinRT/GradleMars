package GradleMars;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AplicationManagerTest {

    @Test
    public void testHappyPath() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("1\n10\n10\n5\n5\nn\nn\nend\n".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);
        AplicationManager aplicationTest = new AplicationManager(new Scanner(System.in));
        aplicationTest.launchExploration();

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString();
        String[] listOoutputs = outputText.split("\n");
        String closeMessage = listOoutputs[(listOoutputs.length - 2)].trim();
        String lastMessage = listOoutputs[(listOoutputs.length - 1)].trim();
        assertEquals(closeMessage, "Closing the program");
        assertEquals(lastMessage, "Rover is at x:5 y:5 facing:n");
    }

    @Test
    public void testFailsAtOrdersPath() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("w\n8\n2\n10\n10\n5\n5\nn\nn\nend\n".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);
        AplicationManager aplicationTest = new AplicationManager(new Scanner(System.in));
        aplicationTest.launchExploration();

        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString();
        String[] listOoutputs = outputText.split("\n");
        String closeMessage = listOoutputs[(listOoutputs.length - 14)].trim();
        String lastMessage = listOoutputs[(listOoutputs.length - 13)].trim();
        String roverPosition = listOoutputs[(listOoutputs.length - 6)].trim();
        String badSeleccionOfRover = listOoutputs[(listOoutputs.length - 42)].trim();
        assertEquals(closeMessage, "Good bye explorer");
        assertEquals(lastMessage, "Rover is at x:5 y:5 facing:n");
        assertEquals(roverPosition, "|     ^    |");
        assertEquals(badSeleccionOfRover, "Bad selection");
    }
}
