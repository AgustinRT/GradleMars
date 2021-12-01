package GradleMars.tools;

import java.util.List;

public class RoverUtils {
    public static int readAsInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Bad format of integer: " + input);
            return -1;
        }
    }

    public static boolean checkValidString(String textCheck) {
        return textCheck != null && !textCheck.isEmpty();
    }

    public static boolean checkValidList(List listCheck) {
        return listCheck != null && !listCheck.isEmpty();
    }

    public static void roverErrorReport(String errorMessage) {
        if (checkValidString(errorMessage)) {
            System.out.println(errorMessage);
        }
    }
}
