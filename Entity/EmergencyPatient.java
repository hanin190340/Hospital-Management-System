package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.util.Scanner;

public class EmergencyPatient  extends InPatient  implements Displayable {
    Scanner scanner = new Scanner(System.in);
    String emergencyType ;
    String  arrivalMode; //(- Ambulance/Walk-in)
    int triageLevel ;
    boolean admittedViaER ;


    public EmergencyPatient() {

    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        while (!HelperUtils.isValidString(emergencyType)){
            System.out.println("Invalid emergency type.");
            emergencyType= scanner.nextLine();
            return;
        }
        this.emergencyType = emergencyType;
    }

    public String getArrivalMode() {
        return arrivalMode;
    }

    public void setArrivalMode(String arrivalMode) {
        while (!HelperUtils.isValidString(arrivalMode)){
            System.out.println("Invalid arrival mode.");
            arrivalMode= scanner.nextLine();
            return;
        }
        this.arrivalMode = arrivalMode;
    }

    public int getTriageLevel() {
        return triageLevel;
    }

    public void setTriageLevel(int triageLevel) {
        while (triageLevel <= 1 || triageLevel >= 5){
            System.out.println("Triage level must be between 1 and 5.");
            triageLevel= scanner.nextInt();
            return;
        }
        this.triageLevel = triageLevel;
    }

    public boolean isAdmittedViaER() {
        return admittedViaER;
    }

    public void setAdmittedViaER(boolean admittedViaER) {
        this.admittedViaER = admittedViaER;
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("===== Emergency Patient Details =====");
        System.out.println("Emergency Type: " + emergencyType);
        System.out.println("Arrival Mode: " + arrivalMode);
        System.out.println("Triage Level: " + triageLevel);
        System.out.println("Admitted via ER: " + (admittedViaER ? "Yes" : "No"));
        System.out.println("=====================================");
    }

    @Override
    public void displaySummary() {

    }

}
