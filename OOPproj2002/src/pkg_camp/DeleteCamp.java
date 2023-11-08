package pkg_camp;

public class DeleteCamp extends Staff {

    public static void deleteCamp(String campName) {
        // Sanity check for camp name

        // no need to check running memory first
        // staff has a method to check running memory
        // that method will be called before this in CAM.java

        // check excel and delete
        Upload.deleteCamp(campName);

    }
}

// A WHOLE CLASS FOR ONE LINE??
// JUST INTEGRATE IN CAM.JAVA AND CALL DELETECAMP AFTER STAFFDELETECAMP THERE AS
// IVE ALR DONE