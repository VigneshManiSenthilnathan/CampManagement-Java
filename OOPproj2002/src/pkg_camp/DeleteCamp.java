package pkg_camp;

import java.util.List;

public class DeleteCamp extends CampController {

    public static void deleteCamp(Staff staff, List<Camp> createdCampsList) {
        CampController.deleteCamp(staff, createdCampsList);
    }
}