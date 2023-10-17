public abstract class CampInformation {
    private String campName;
    private double dates; //ddmmyy format
    private double registrationClosing; // is this time or date or both??
    private int userGroup;
    private String location;
    private int totalSlots;
    private int campCommitteeSlots; // max 10
    private String description;

    /* if we tag staffInCharge to campName,
     * doesnt that imply that there can only be 1 staff per camp?
     * meaning camp committee cant use generateRecords(staffInCharge)
     */

    private String staffInCharge;
    private boolean visibility;

    public CampInformation(String campName, double dates, double registrationClosing, int userGroup, String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility){
        campName = this.campName;
        dates = this.dates;
        registrationClosing = this.registrationClosing;
        userGroup = this.userGroup;
        location = this.location;
        totalSlots = this.totalSlots;
        campCommitteeSlots = this.campCommitteeSlots;
        description = this.description;
        staffInCharge = this.staffInCharge;

        visibility = this.visibility;
    }

    /* Shall we use an index here?
     * int parameter to represent attribute to edit
     * (i.e.) 1 = campName, 2 = dates ...
     */
    public void editCampDetails(CampInformation camp, int attributeToEdit, String edit){ 
        // switch-case to find attribute to be editted
        // typecast String to appropriate type based on attribute chosen
        // handle errors and return to calling function

        // camp.attributeToEdit = edit;
    }

    public void toggleVisibility(CampInformation camp, int set){
        if (set == 1){camp.visibility = true;}
        else if (set == 0){camp.visibility = false;}
        else {
            System.out.println("Invalid integer input!");
            System.out.println("To Set Visible Enter: 1");
            System.out.println("To Set Invisible Enter: 0");
        } 
    }

    //void return cuz reports are generated in excel
    public void generateReports(){ 
        //generate the whole excel sheet at one go
    }

    //overloaded method
    public void generateReports(String staffInCharge){ 
        //generate camp details filtered by staff
    }

    //abstract method for staff
    public void generateReports(String staffInCharge, String campName){  }

    //abstract method for camp committee members
    public void generateReports(CampInformation camp){  }

}   
