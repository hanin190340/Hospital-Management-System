package Entity;

import Interface.Displayable;
import Utils.HelperUtils;
import Utils.InputHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Appointment implements Displayable {
    Scanner scanner = new Scanner(System.in);
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private String status; // (Scheduled/Completed/Cancelled/Rescheduled)
    private String reason;
    private String notes;


    public Appointment(String appointmentId, String patientId, String doctorId,
                       LocalDate appointmentDate, String appointmentTime,
                       String status, String reason, String notes) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.reason = reason;
        this.notes = notes;
    }

    public Appointment() {
    }


    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        if (!HelperUtils.isValidDate(String.valueOf(appointmentDate))) {
            System.out.println(" is not valid date ");

        }
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {

        return appointmentTime;
    }


    public void setAppointmentTime() {
        boolean valid = false;
        String inputTime;
        while (!valid) {
            inputTime = InputHelper.getStringInput("Enter Appointment Time (e.g., 10:30 AM): ");

            if (!HelperUtils.isValidString(inputTime)) {
                System.out.println("Appointment time cannot be null or empty. Please try again.");
                continue;
            }

            if (!inputTime.matches("^(0?[1-9]|1[0-2]):[0-5][0-9]\\s?(AM|PM|am|pm)$")) {
                System.out.println("Invalid time format. Use format like '10:30 AM'.");
                continue;
            }

            this.appointmentTime = inputTime;
            valid = true;
            System.out.println(" Appointment time set successfully.");
        }
    }
    public void setAppointmentTimeForSamples(String appTime) {
        // Check if the input is null or empty
        if (appTime == null || appTime.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment time cannot be null or empty.");
        }

        // Validate the format HH:mm (24-hour)
        if (!appTime.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
            throw new IllegalArgumentException("Invalid time format! Use 24-hour format like '09:30' or '15:45'.");
        }

        // If valid, set the appointment time
        this.appointmentTime = appTime;
    }




    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        Scanner scanner = new Scanner(System.in);

        // Keep asking until a valid status is entered
        while (!status.equalsIgnoreCase("Scheduled") && !status.equalsIgnoreCase("Completed") &&
                !status.equalsIgnoreCase("Cancelled") && !status.equalsIgnoreCase("Rescheduled")) {

            System.out.println("Invalid status. Allowed values are: Scheduled, Completed, Cancelled, Rescheduled.");
            System.out.print("Please enter a valid status: ");
            status = scanner.nextLine();
        }

        this.status = status;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        Scanner scanner = new Scanner(System.in);

        while (HelperUtils.isNull(reason) || !HelperUtils.isValidString(reason)) {
            System.out.println("Reason cannot be null or empty.");
            reason = scanner.nextLine().trim();
        }

        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        Scanner scanner = new Scanner(System.in);

        while (HelperUtils.isNull(notes) || !HelperUtils.isValidString(notes)) {
            System.out.println("Notes cannot be null or empty.");
            notes = scanner.nextLine().trim();
        }

        this.notes = notes;
    }

    // Display Info
    public void displayInfo() {
        System.out.println("===== Appointment Details =====");
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Date: " + appointmentDate);
        System.out.println("Time: " + appointmentTime);
        System.out.println("Status: " + status);
        System.out.println("Reason: " + reason);
        System.out.println("Notes: " + notes);
        System.out.println("===============================");
    }

    @Override
    public void displaySummary() {
        System.out.println("Appointment ID: " + appointmentId + ", Date: " + appointmentDate + ", Time: " + appointmentTime + ", Status: " + status);
    }

    //  Overloaded
    public void addNotes(String notes) {
        this.notes = notes;
        System.out.println("Notes added: " + notes);
    }

    public void addNotes(String notes, String addedBy) {
        this.notes = notes + " (Added by: " + addedBy + ")";
        System.out.println("Notes added by " + addedBy + ": " + notes);
    }

    public void addNotes(String notes, String addedBy, LocalDateTime timestamp) {
        this.notes = notes + " (Added by: " + addedBy + " at " + timestamp + ")";
        System.out.println("Notes added by " + addedBy + " on " + timestamp + ": " + notes);
    }
}



