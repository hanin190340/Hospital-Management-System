package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class OutPatient  extends Patient implements Displayable {
    Scanner scanner = new Scanner(System.in);
    int visitCount ;
    LocalDate lastVisitDate;
    String  preferredDoctorId ;

    public String getPreferredDoctorId() {
        return preferredDoctorId;
    }

    public void setPreferredDoctorId(String preferredDoctorId) {
        this.preferredDoctorId = preferredDoctorId;
    }

    public OutPatient(int visitCount, LocalDate lastVisitDate, String preferredDoctorId) {
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
        this.preferredDoctorId = preferredDoctorId;
    }

    public OutPatient(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, String insuranceId, List<String> medicalRecords, List<String> appointments, int visitCount, LocalDate lastVisitDate, String preferredDoctorId) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, patientId, bloodGroup, allergies, emergencyContact, registrationDate, insuranceId, medicalRecords, appointments);
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
        this.preferredDoctorId = preferredDoctorId;
    }

    public OutPatient(String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, String insuranceId, List<String> medicalRecords, List<String> appointments, int visitCount, LocalDate lastVisitDate, String preferredDoctorId) {
        super(patientId, bloodGroup, allergies, emergencyContact, registrationDate, insuranceId, medicalRecords, appointments);
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
        this.preferredDoctorId = preferredDoctorId;
    }

    public OutPatient() {

    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        while (HelperUtils.isFutureDate(lastVisitDate)) {
            System.out.println("Last visit date cannot be in the future.");
            String dateInput = scanner.nextLine();
            lastVisitDate = LocalDate.parse(dateInput);
        }
        this.lastVisitDate = lastVisitDate;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        while (HelperUtils.isNegative(visitCount)) {
            System.out.println("Visit count cannot be negative.");
            String countInput = scanner.nextLine();
            visitCount = Integer.parseInt(countInput);
        }
        this.visitCount = visitCount;
    }



    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("===== OutPatient Details =====");
        System.out.println("Visit Count: " + visitCount);
        System.out.println("Last Visit Date: " + lastVisitDate);
        System.out.println("Preferred Doctor ID: " + preferredDoctorId);
        System.out.println("==============================");
    }
    // Custom methods
    public void scheduleFollowUp() {
        System.out.print("Enter follow-up date (YYYY-MM-DD): ");
        LocalDate followUpDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Follow-up appointment scheduled for: " + followUpDate);
    }

    public void updateVisitCount() {
        visitCount++;
        lastVisitDate = LocalDate.now();
        System.out.println("Visit count updated to: " + visitCount);
        System.out.println("Last visit date updated to: " + lastVisitDate);
    }
}
