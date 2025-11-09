package Entity;

import Interface.Displayable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Nurse extends PersonBase implements Displayable {
  String nurseId ;
    String departmentId;
    String shift;   // (Morning/Evening/Night)
    String qualification;
    List<String> assignedPatients;

    public Nurse(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, String nurseId, String departmentId, String shift, String qualification, List<String> assignedPatients) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);
        this.nurseId = nurseId;
        this.departmentId = departmentId;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = assignedPatients;
    }

    public Nurse(String nurseId, String departmentId, String shift, String qualification, List<String> assignedPatients) {
        this.nurseId = nurseId;
        this.departmentId = departmentId;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = assignedPatients;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Nurse() {

    }


    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }



    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public List<String> getAssignedPatients() {
        return assignedPatients;
    }

    public void setAssignedPatients(List<String> assignedPatients) {
        this.assignedPatients = assignedPatients;
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("===== Entity.Nurse Information =====");
        System.out.println("Entity.Nurse ID: " + nurseId);
        System.out.println("Entity.Department ID: " + departmentId);
        System.out.println("Shift: " + shift);
        System.out.println("Qualification: " + qualification);

        System.out.println("Assigned Patients:");
        if (assignedPatients == null || assignedPatients.isEmpty()) {
            System.out.println(" - No patients assigned");
        } else {
            for (String patientId : assignedPatients) {
                System.out.println(" - " + patientId);
            }
        }

        System.out.println("=============================");
    }

    @Override
    public void displaySummary() {
        System.out.println("Entity.Nurse ID: " + nurseId + ", Name: " + getFirstName() + " " + getLastName() + ", Shift: " + shift);
    }
}
