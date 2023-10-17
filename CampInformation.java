import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

public abstract class CampInformation {
    
    private String campName;
    private double dates; //ddMMyyddMMyy format -> Error check format in MainApp class
    private double registrationClosing; // is this time or date or both??
    private int userGroup;
    private String location;
    private int totalSlots;
    private int campCommitteeSlots; // max 10
    private String description;

    /* if we tag staffInCharge to campName,
     * doesnt that imply that there can only be 1 staff per camp?
     * meaning camp committee cant use generateRecords(staffInCharge)
     * 
     * Should this be an String array instead then?
     * But then how will excel parse it?? Problems problems lol
     */

    private String staffInCharge;
    private boolean visibility;

    public CampInformation(String campName, double dates, double registrationClosing, int userGroup, String location, int totalSlots, int campCommitteeSlots, String description, String staffInCharge, boolean visibility){
        this.campName = campName;
        this.dates = dates;
        this.registrationClosing = registrationClosing;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;

        visibility = this.visibility;
    }

    /* Shall we use an index here?
     * int parameter to represent attribute to edit
     * (i.e.) 0 = campName, 1 = dates ...
     */

    // someone suggest if got better implementation pls
    
    public void editCampDetails(CampInformation camp, int attributeToEdit, String edit){ 
        
        switch(attributeToEdit){

            case 0:
                camp.campName = edit;
                break;

            case 1:
                try {
                    double campDate = Double.parseDouble(edit);
                    camp.dates = campDate;
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: invalid input string");
                }
                // Error checking for whether it follows ddMMyyddMMyy format done in MainApp class
                break;

            case 2:
                try {
                    double regClosing = Double.parseDouble(edit);
                    camp.registrationClosing = regClosing;
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: invalid input string");
                }
                break;

            case 3:
                try {
                    int userGrp = Integer.parseInt(edit);
                    camp.userGroup = userGrp;
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: invalid input string");
                }
                break;

            case 4:
                camp.location = edit;
                break;

            case 5:
                try {
                    int slots = Integer.parseInt(edit);
                    camp.totalSlots = slots;
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: invalid input string");
                }
                break;

            case 6:
                try {
                    int camslots = Integer.parseInt(edit);
                    if (camslots > 10){
                        System.out.println("Committee Slots exceeded! Max 10");
                    }
                    else {camp.campCommitteeSlots = camslots;}
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormat Exception: invalid input string");
                }
                break;
            
            case 7:
                camp.description = edit;
                break;
            
            case 8:
                camp.staffInCharge = edit;
                break;
            
            case 9:
                System.out.println("Quitting Program . . .");
                break;
            
            default:
                System.out.println("Attribute does not exist!");

        }
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
