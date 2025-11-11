package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Consultant extends Doctor implements Displayable {
    Scanner scanner = new Scanner(System.in);
    List<String> consultationTypes;
    boolean onlineConsultationAvailable;
    int consultationDuration;

    public Consultant(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, UUID doctorId, String specialization, String qualification, int experienceYears, UUID departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients, boolean isAvailable, List<String> consultationTypes, boolean onlineConsultationAvailable, int consultationDuration) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients, isAvailable);
        this.consultationTypes = consultationTypes;
        this.onlineConsultationAvailable = onlineConsultationAvailable;
        this.consultationDuration = consultationDuration;
    }

    public Consultant(List<String> consultationTypes, boolean onlineConsultationAvailable, int consultationDuration) {
        this.consultationTypes = consultationTypes;
        this.onlineConsultationAvailable = onlineConsultationAvailable;
        this.consultationDuration = consultationDuration;
    }


    public Consultant(String doctorId, String specialization, String qualification, int experienceYears, String departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients, boolean isAvailable, List<String> consultationTypes, boolean onlineConsultationAvailable, int consultationDuration) {
        super(doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients, isAvailable);
        this.consultationTypes = consultationTypes;
        this.onlineConsultationAvailable = onlineConsultationAvailable;
        this.consultationDuration = consultationDuration;
    }

    public Consultant() {
        super();
    }

    public boolean isOnlineConsultationAvailable() {
        return onlineConsultationAvailable;
    }

    public void setOnlineConsultationAvailable(boolean onlineConsultationAvailable) {
        this.onlineConsultationAvailable = onlineConsultationAvailable;
    }


    public void setConsultationTypes(List<String> consultationTypes) {
        Scanner scanner = new Scanner(System.in);

        // Keep looping until a valid list is provided
        while (consultationTypes == null || consultationTypes.isEmpty() ||
                !HelperUtils.isValidString(String.join(",", consultationTypes))) {
            System.out.println("Invalid consultation types provided.");
            System.out.print("Please enter consultation types separated by commas: ");
            String input = scanner.nextLine();
            consultationTypes = Arrays.asList(input.split("\\s*,\\s*")); // Split by commas and trim spaces
        }

        this.consultationTypes = consultationTypes;
    }


    public int getConsultationDuration() {
        return consultationDuration;
    }

    public void setConsultationDuration(int consultationDuration) {
        while (HelperUtils.isNegative(consultationDuration)) {
            System.out.println("Consultation duration cannot be negative.");
            consultationDuration = scanner.nextInt();
            return;
        }
        this.consultationDuration = consultationDuration;
    }


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("===== Consultant Details =====");
        System.out.println("Online Consultation Available: " + onlineConsultationAvailable);
        System.out.println("Consultation Duration: " + consultationDuration + " minutes");

        System.out.println("Consultation Types:");
        if (consultationTypes == null || consultationTypes.isEmpty()) {
            System.out.println(" - None");
        } else {
            for (String type : consultationTypes) {
                System.out.println(" - " + type);
            }
        }
        System.out.println("==============================");
    }

    public void scheduleConsultation() {
    }

    public void provideSecondOpinion() {
    }

}
