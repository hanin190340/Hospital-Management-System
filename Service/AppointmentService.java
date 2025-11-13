package Service;

import Entity.Appointment;

import Interface.Appointable;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;
import Utils.InputHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService implements Manageable, Searchable, Appointable {
    public static List<Appointment> appointmentList = new ArrayList<>();


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

    // Add Appointment (Interactive)
    public static Appointment addAppointment() {
        Appointment appointment = new Appointment();
        System.out.println("Adding Appointment");
        appointment.setAppointmentId(HelperUtils.generateId("APPT", 5));
        appointment.setPatientId(HelperUtils.generateId("PT", 5));
        appointment.setDoctorId(HelperUtils.generateId("Dr", 5));
        appointment.setAppointmentTime();
        appointment.setReason(InputHelper.getStringInput("Enter the reason"));
        appointment.setAppointmentDate(InputHelper.getDateInput("Enter appointment Date (YYYY-MM-DD)"));
        System.out.println();
        appointment.setNotes(InputHelper.getStringInput("Enter notes"));

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
        return appointment;
    }

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
                existingAppointment.setAppointmentTime();
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
    public static void getAppointmentsByDate(LocalDate date) {
        boolean found = false;
        for (Appointment p : appointmentList) {
            if (HelperUtils.isNotNull(p.getAppointmentDate()) && p.getAppointmentDate().equals(date)) {
                p.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found on date: " + date);
        }
    }

    public static List<Appointment> getAppointmentsByPatient(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointmentList) {
            if (HelperUtils.isNotNull(a.getPatientId()) && a.getPatientId().equals(patientId)) result.add(a);
        }
        if (result.isEmpty()) {
            System.out.println("No appointments found for patient ID: " + patientId);
        }
        return result;
    }


    public static void getAppointmentByDoctorId(String doctorId) {
        boolean found = false;
        for (Appointment p : appointmentList) {
            if (HelperUtils.isNotNull(p.getDoctorId()) && p.getDoctorId().toString().equals(doctorId)) {
                p.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Doctor with ID " + doctorId + " not found.");
        }
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
        appointment.setAppointmentTime();
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
            if (HelperUtils.isNotNull(appt.getAppointmentId()) && appt.getAppointmentId().toString().equals(appointmentId)) {
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
        appointment.setAppointmentTime();
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
    public void remove(String id) {

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
            if (HelperUtils.isNotNull(appt.getAppointmentDate()) && appt.getAppointmentDate().equals(date)) {
                appt.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments found for this date.");
        }

        System.out.println("=====================================================\n");
    }

    public static void generateDailyAppointmentsReport(LocalDate date) {
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments available.");
            return;
        }

        System.out.println("\n===== Daily Appointments Report for " + date + " =====");
        boolean found = false;

        for (Appointment app : appointmentList) {
            if (app.getAppointmentDate().equals(date)) {
                found = true;
                System.out.println("Appointment ID: " + app.getAppointmentId());
                System.out.println("Patient ID    : " + app.getPatientId());
                System.out.println("Doctor ID     : " + app.getDoctorId());
                System.out.println("Date & Time   : " + app.getAppointmentDate() + " " + app.getAppointmentTime());
                System.out.println("Status        : " + app.getStatus());
                System.out.println("Reason        : " + app.getReason());
                System.out.println("Notes         : " + app.getNotes());
                System.out.println("-------------------------------");
            }
        }

        if (!found) System.out.println("No appointments found for this date.");
        System.out.println("===== End of Report =====\n");
    }

    public static void addSampleAppointments() {

        for (int i = 0; i < 15; i++) {
            Appointment appointment = new Appointment();

            appointment.setAppointmentId(HelperUtils.generateId("APP", 5));
            appointment.setPatientId(HelperUtils.generateId("PT", 5));
            appointment.setDoctorId(HelperUtils.generateId("Dr ", 5));
            appointment.setAppointmentDate(LocalDate.now().plusDays(i));
            String sampleTime = String.format("%02d:00", 9 + (i % 8));
            appointment.setAppointmentTimeForSamples(sampleTime);
            String status;
            switch (i % 4) {
                case 0:
                    status = "Scheduled";
                    break;
                case 1:
                    status = "Completed";
                    break;
                case 2:
                    status = "Cancelled";
                    break;
                default:
                    status = "Rescheduled";
                    break;
            }
            appointment.setStatus(status);

            appointment.setReason("Routine check-up " + (i + 1));
            appointment.setNotes("Appointment created for testing. Doctor assigned: " + appointment.getDoctorId());

            appointmentList.add(appointment);

        }

    }


}











