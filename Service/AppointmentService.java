package Service;

import Entity.Appointment;
import Entity.Doctor;
import Interface.Appointable;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;
import Utils.InputHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AppointmentService implements Manageable, Searchable, Appointable {
    public static List<Appointment> appointmentList = new ArrayList<>();
    private static LocalDate date;


    public static boolean completeAppointment(String appointmentId) {
        for (Appointment appt : appointmentList) {
            if (HelperUtils.isNotNull(appt.getAppointmentId()) && appt.getAppointmentId().toString().equals(appointmentId)) {
                appt.setStatus("Completed");
                String prevNotes = appt.getNotes() == null ? "" : appt.getNotes() + " ";
                appt.setNotes(prevNotes + "Completed on: " + LocalDate.now());
                System.out.println("Appointment with ID " + appointmentId + " marked as Completed.");
                return true;
            }
        }
        System.out.println("Appointment with ID " + appointmentId + " not found.");
        return false;
    }

    public static void viewUpcomingAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments available.");
            return;
        }

        LocalDate today = LocalDate.now();
        boolean found = false;

        System.out.println("===== Upcoming Appointments =====");
        for (Appointment appt : appointmentList) {
            if (HelperUtils.isNotNull(appt.getAppointmentDate()) && appt.getAppointmentDate().isAfter(today)) {
                appt.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No upcoming appointments found after " + today + ".");
        }

        System.out.println("================================");
    }

    // Add Entity.Appointment (Interactive)
    public static Appointment addPatient() {
        Appointment appointment = new Appointment();
        System.out.println("Adding Appointment");
        appointment.setAppointmentId(HelperUtils.generateId("APPT", 5));
        appointment.setPatientId(HelperUtils.generateId("PT", 5));
        appointment.setDoctorId(HelperUtils.generateId("Dr", 5));
        appointment.setAppointmentTime(InputHelper.getStringInput("Enter appointment Time (HH:MM):"));
        appointment.setReason(InputHelper.getStringInput("Enter the reason:"));
        appointment.setAppointmentDate(InputHelper.getDateInput("Enter appointment Date (YYYY-MM-DD):"));
        System.out.println();
        appointment.setNotes(InputHelper.getStringInput("Enter notes:"));

        int statusChoice = InputHelper.getIntInput("""
        Enter status (number only):
        1. Scheduled
        2. Cancelled
        3. Rescheduled
        4. Completed
        """);

        String status = switch (statusChoice) {
            case 1 -> "Scheduled";
            case 2 -> "Cancelled";
            case 3 -> "Rescheduled";
            case 4 -> "Completed";
            default -> "Unknown";
        };

        appointment.setStatus(status);
        return appointment; }

        public static void save(Appointment appointment) {
        if (HelperUtils.isNull(appointment)) {
            System.out.println("Error: Appointment cannot be null");
        }
        appointmentList.add(appointment);
        System.out.println("Appointment added successfully!\n");
    }

    // ========== Reschedule ==========
    public static boolean rescheduleAppointment(String appointmentId, LocalDate newDate, String newTime) {
        for (Appointment existingAppointment : appointmentList) {
            if (HelperUtils.isNotNull(existingAppointment.getAppointmentId()) &&
                    existingAppointment.getAppointmentId().toString().equals(appointmentId)) {

                existingAppointment.setAppointmentDate(newDate);
                existingAppointment.setAppointmentTime(newTime);
                System.out.println("Appointment updated successfully!");
                return true;
            }
        }
        System.out.println("Appointment with ID " + appointmentId + " not found.");
        return false;
    }

    @Override
    public void scheduleAppointment(Appointment appointment) {
        if (HelperUtils.isNull(appointment)) {
            System.out.println("Error: Appointment cannot be null");
        }
        save(appointment);
    }

    // ========== Cancel Appointment ==========
    public static boolean cancelAppointment(String appointmentId) {
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment p = appointmentList.get(i);
            if (HelperUtils.isNotNull(p.getAppointmentId()) && p.getAppointmentId().toString().equals(appointmentId)) {
                appointmentList.remove(i);
                System.out.println("Appointment with ID " + appointmentId + " removed successfully!");
                return true;
            }
        }
        System.out.println("Appointment with ID " + appointmentId + " not found.");
        return false;
    }

    // ========== Search Methods ==========
    public static Appointment getAppointmentsByDate(LocalDate date) {
        for (Appointment p : appointmentList) {
            if (p.getAppointmentDate() != null && p.getAppointmentDate().equals(date)) {
                return p;
            }
        }
        System.out.println("No appointments found on date: " + date);
        return null;
    }

    public static Appointment getAppointmentsByPatient(String patientId) {
        for (Appointment p : appointmentList) {
            if (HelperUtils.isNotNull(p.getPatientId()) && p.getPatientId().toString().equals(patientId)) {
                return p;
            }
        }
        System.out.println("Patient with ID " + patientId + " not found.");
        return null;
    }

    public static Appointment getRecordsByDoctorId(String doctorId) {
        for (Appointment p : appointmentList) {
            if (HelperUtils.isNotNull(p.getDoctorId()) && p.getDoctorId().toString().equals(doctorId)) {
                return p;
            }
        }
        System.out.println("Doctor with ID " + doctorId + " not found.");
        return null;
    }

    // ========== Display ==========
    public static void displayPatientHistory(String patientId) {
        if (appointmentList.isEmpty()) {
            System.out.println("No patients to display.");
            return;
        }

        for (Appointment p : appointmentList) {
            if (HelperUtils.isNotNull(p.getPatientId()) && p.getPatientId().toString().equals(patientId)) {
                p.displayInfo();
            }
        }
    }

    // ========== Overloaded createAppointment ==========
    public Appointment createAppointment(String patientId, String doctorId, LocalDate date) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(HelperUtils.generateId("APPT", 5));
        appointment.setPatientId(HelperUtils.generateId("PAT", 5));
        appointment.setDoctorId(HelperUtils.generateId("Dr", 5));
        appointment.setAppointmentDate(date);
        appointmentList.add(appointment);
        System.out.println("Appointment created successfully for " + date);
        return appointment;
    }

    public Appointment createAppointment(String patientId, String doctorId, LocalDate date, String time) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(HelperUtils.generateId("APPT", 5));
        appointment.setPatientId(HelperUtils.generateId("PAT", 5));
        appointment.setDoctorId(HelperUtils.generateId("Dr", 5));
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);
        appointmentList.add(appointment);
        System.out.println("Appointment created successfully for " + date + " at " + time);
        return appointment;
    }

    public Appointment createAppointment(Appointment appointment) {
        if (HelperUtils.isNull(appointment)) {
            System.out.println("Error: Appointment cannot be null.");
        }
        appointment.setAppointmentId(HelperUtils.generateId("APPT", 5));
        appointmentList.add(appointment);
        System.out.println("Appointment created successfully .");
        return appointment;
    }

    // ========== Overloaded Reschedule ==========
    public static Appointment rescheduleAppointment(String appointmentId, LocalDate newDate) {
        for (Appointment appt : appointmentList) {
            if (appt.getAppointmentId() != null && appt.getAppointmentId().toString().equals(appointmentId)) {
                appt.setAppointmentDate(newDate);
                System.out.println("Appointment rescheduled to " + newDate);
                return appt;
            }
        }
        System.out.println("Appointment with ID " + appointmentId + " not found.");
        return null;
    }

    public Appointment rescheduleAppointment(Appointment appointment, LocalDate newDate, String newTime, String reason) {
        if (HelperUtils.isNull(appointment)) {
            System.out.println("Appointment object is null.");
            return null;
        }
        appointment.setAppointmentDate(newDate);
        appointment.setAppointmentTime(newTime);
        appointment.setReason(reason);
        System.out.println("Appointment rescheduled to " + newDate + " at " + newTime + ". Reason: " + reason);
        return appointment;
    }

    // ========== Overloaded Display ==========
    public static Appointment displayAppointments(LocalDate date) {
        boolean found = false;
        for (Appointment appt : appointmentList) {
            if (HelperUtils.isNotNull(appt.getAppointmentDate().toString()) && appt.getAppointmentDate().equals(date)) {
                appt.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for date: " + date);
        }
        return null;
    }

    public Appointment displayAppointments(String doctorId, LocalDate startDate, LocalDate endDate) {
        boolean found = false;
        for (Appointment appt : appointmentList) {
            if (HelperUtils.isNotNull(appt.getDoctorId()) && appt.getDoctorId().toString().equals(doctorId)) {
                LocalDate d = appt.getAppointmentDate();
                if (HelperUtils.isNotNull(d) && (d.isEqual(startDate) || d.isAfter(startDate)) &&
                        (d.isEqual(endDate) || d.isBefore(endDate))) {
                    appt.displayInfo();
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No appointments found for doctor " + doctorId +
                    " between " + startDate + " and " + endDate);
        }
        return null;
    }

    @Override
    public void add(Object entity) {
        if (entity instanceof Appointment) {
            Appointment appointment = (Appointment) entity;
            save(appointment);
        } else {
            System.out.println("Invalid entity type Expected Appointment.");
        }

    }

    @Override
    public void getAll() {
        displayAllAppointments();

    }

    @Override
    public void search(String keyword) {
        if (HelperUtils.isNull(keyword)) {
            System.out.println("Invalid keyword. Please enter a valid search keyword.");
            return;
        }
        boolean found = false;
        String k = keyword.toLowerCase();
        for (Appointment b : appointmentList) {
            if ((HelperUtils.isNotNull(b.getReason()) && b.getReason().toLowerCase().contains(k)) ||
                    (HelperUtils.isNotNull(b.getStatus()) && b.getStatus().toLowerCase().contains(k))) {
                b.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Appointments matched the keyword: " + keyword);
        }

    }

    @Override
    public void searchById(String id) {
        if (HelperUtils.isNull(id)) {
            System.out.println("Invalid ID. Please enter a valid patient ID.");
            return;
        }
        boolean found = false;
        for (Appointment A : appointmentList) {
            if (A.getPatientId().equals(id)) {
                A.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointment found for patient ID: " + id);
        }
    }

    public static void displayAllAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("No Appointments to display.");
            return;
        }

        for (Appointment p : appointmentList) {
            p.displayInfo();
        }
    }

    public static void viewDailyAppointmentsReport(LocalDate date) {
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments scheduled.");
            return;
        }

        System.out.println("\n===== Daily Appointments Report for " + date + " =====");
        boolean found = false;

        for (Appointment appt : appointmentList) {
            if (appt.getAppointmentDate() != null && appt.getAppointmentDate().equals(date)) {
                appt.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments found for this date.");
        }

        System.out.println("=====================================================\n");
    }
}











