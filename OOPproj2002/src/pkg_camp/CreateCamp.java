package pkg_camp;

import java.util.List;

public class CreateCamp {

    public static void createCamp(Staff staff, List<Camp> createdCampsList) {
        CampController.createNewCamp(staff, createdCampsList);
    }
}