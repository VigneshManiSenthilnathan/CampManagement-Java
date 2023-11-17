package pkg_camp;

import java.util.List;

public class CreateCamp extends CampController {

    public CreateCamp(Staff staff, List<Camp> createdCampsList) {
        super.createNewCamp(staff, createdCampsList);
    }
}