package pkg_camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Boolean;

public class Withdrawal extends CampController {

    public static List<Camp> withdrawCamp(Student student, List<Camp> createdCampsList) {
        List<Camp> newList;
        newList = CampController.withdrawCamp(student, createdCampsList);
        return newList;
    }
}

// withdraw from ntu