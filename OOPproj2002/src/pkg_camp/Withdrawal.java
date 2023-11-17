package pkg_camp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Boolean;

public class Withdrawal extends Student {
    // since student can join multiple camp, need to let them choose which to
    // withdraw
    // I forgot to account for that in the CAM
    public Withdrawal(Staff staff, List<Camp> createdCampsList) {
        CampController.createNewCamp(staff, createdCampsList);
    }
}
