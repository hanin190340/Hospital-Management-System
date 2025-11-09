package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Doctor extends PersonBase implements Displayable {
    String doctorId;
    String specialization;
    String qualification;
    int experienceYears;
    String departmentId;
    double consultationFee;
    List<String> availableSlots;
    List<String> assignedPatients;
    boolean isAvailable;

    public Doctor(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, UUID doctorId, String specialization, String qualification, int experienceYears, UUID departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients, boolean isAvailable) {

    }

    public Doctor() {

    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public Doctor(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, String doctorId, String specialization, String qualification, int experienceYears, String departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients, boolean isAvailable) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.departmentId = departmentId;
        this.consultationFee = consultationFee;
        this.availableSlots = availableSlots;
        this.assignedPatients = assignedPatients;
        this.isAvailable = isAvailable;
    }

    public Doctor(String doctorId, String specialization, String qualification, int experienceYears, String departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients, boolean isAvailable) {
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.departmentId = departmentId;
        this.consultationFee = consultationFee;
        this.availableSlots = availableSlots;
        this.assignedPatients = assignedPatients;
        this.isAvailable = isAvailable;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if(!HelperUtils.isValidString(specialization)){
            System.out.println("Specialization cannot be empty.");
            return;
        }
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        if(!HelperUtils.isValidString(qualification)){
            System.out.println("Qualification cannot be empty.");
            return;
        }
        this.qualification = qualification;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        if(HelperUtils.isNegative(experienceYears) && experienceYears <= 0){
            System.out.println("Experience years cannot be negative.");
            return;
        }
        this.experienceYears = experienceYears;
    }


    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        if(HelperUtils.isNegative(consultationFee)){
            System.out.println("Consultation fee cannot be negative.");
            return;
        }
        this.consultationFee = consultationFee;
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<String> availableSlots) {
        this.availableSlots = availableSlots;
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
        System.out.println("===== .Doctor Information =====");
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Specialization: " + specialization);
        System.out.println("Qualification: " + qualification);
        System.out.println("Years of Experience: " + experienceYears);
        System.out.println("Department ID: " + departmentId);
        System.out.println("Consultation Fee: $" + consultationFee);

        System.out.println("Available Slots:");
        if (availableSlots == null || availableSlots.isEmpty()) {
            System.out.println(" - No slots available");
        } else {
            for (String slot : availableSlots) {
                System.out.println(" - " + slot);
            }
        }

        System.out.println("Assigned Patients:");
        if (assignedPatients == null || assignedPatients.isEmpty()) {
            System.out.println(" - No patients assigned");
        } else {
            for (String patientId : assignedPatients) {
                System.out.println(" - " + patientId);
            }
        }

        System.out.println("==============================");
    }

    @Override
    public void displaySummary() {
        System.out.println("Doctor ID: " + doctorId + ", Name: " + firstName + " " + lastName + ", Specialization: " + specialization);
    }

    public void assignPatient() {
    }

    public void removePatient() {
    }

    public void updateAvailability() {
    }

    // ===== Overloaded updateFee() methods =====
    public void updateFee(double fee) {
        this.consultationFee = fee;
        System.out.println("Consultation fee updated to: " + fee);
    }

    public void updateFee(double fee, String reason) {
        this.consultationFee = fee;
        System.out.println("Consultation fee updated to: " + fee + " | Reason: " + reason);
    }

    // ===== Overloaded addAvailability() methods =====
    public void addAvailability(String slot) {
        if (availableSlots == null) {
            availableSlots = new ArrayList<>();
        }
        availableSlots.add(slot);
        System.out.println("Added availability slot: " + slot);
    }

    public void addAvailability(List<String> slots) {
        if (availableSlots == null) {
            availableSlots = new ArrayList<>();
        }
        availableSlots.addAll(slots);
        System.out.println("Added multiple availability slots: " + slots);
    }

}
