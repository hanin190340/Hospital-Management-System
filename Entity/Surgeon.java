package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Surgeon extends Doctor implements Displayable {
    int surgeriesPerformed;
    List<String> surgeryTypes;
    boolean operationTheatreAccess;

    public Surgeon() {
    }

    public Surgeon(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, UUID doctorId, String specialization, String qualification, int experienceYears, UUID departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients, boolean isAvailable, int surgeriesPerformed, List<String> surgeryTypes, boolean operationTheatreAccess) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients, isAvailable);
        this.surgeriesPerformed = surgeriesPerformed;
        this.surgeryTypes = surgeryTypes;
        this.operationTheatreAccess = operationTheatreAccess;
    }

    public Surgeon(int surgeriesPerformed, List<String> surgeryTypes, boolean operationTheatreAccess) {
        this.surgeriesPerformed = surgeriesPerformed;
        this.surgeryTypes = surgeryTypes;
        this.operationTheatreAccess = operationTheatreAccess;
    }

    public Surgeon(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, String doctorId, String specialization, String qualification, int experienceYears, String departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients, boolean isAvailable, int surgeriesPerformed, List<String> surgeryTypes, boolean operationTheatreAccess) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients, isAvailable);
        this.surgeriesPerformed = surgeriesPerformed;
        this.surgeryTypes = surgeryTypes;
        this.operationTheatreAccess = operationTheatreAccess;
    }

    public Surgeon(String doctorId, String specialization, String qualification, int experienceYears, String departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients, boolean isAvailable, int surgeriesPerformed, List<String> surgeryTypes, boolean operationTheatreAccess) {
        super(doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients, isAvailable);
        this.surgeriesPerformed = surgeriesPerformed;
        this.surgeryTypes = surgeryTypes;
        this.operationTheatreAccess = operationTheatreAccess;
    }

    public List<String> getSurgeryTypes() {
        return surgeryTypes;
    }

    public void setSurgeryTypes(List<String> surgeryTypes) {
        this.surgeryTypes = surgeryTypes;
    }

    public int getSurgeriesPerformed() {
        return surgeriesPerformed;
    }

    public void setSurgeriesPerformed(int surgeriesPerformed) {
        if (HelperUtils.isNegative(surgeriesPerformed)) {
            System.out.println("Surgeries performed cannot be negative.");
            return;
        }
        this.surgeriesPerformed = surgeriesPerformed;
    }

    public boolean isOperationTheatreAccess() {
        return operationTheatreAccess;
    }

    public void setOperationTheatreAccess(boolean operationTheatreAccess) {
        this.operationTheatreAccess = operationTheatreAccess;
    }




    public void performSurgery() {

    }
    public void updateSurgeryCount (){}


    // ===== Overridden method =====
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("===== Surgeon Details =====");
        System.out.println("Surgeries Performed: " + surgeriesPerformed);
        System.out.println("Operation Theatre Access: " + operationTheatreAccess);
        System.out.println("Surgery Types:");
        if (surgeryTypes == null || surgeryTypes.isEmpty()) {
            System.out.println(" - None");
        } else {
            for (String type : surgeryTypes) {
                System.out.println(" - " + type);
            }
        }
        System.out.println("==============================");
    }
}


