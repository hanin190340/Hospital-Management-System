package Interface;

import Entity.Appointment;

import java.time.LocalDate;

public interface Appointable {
    void scheduleAppointment(Appointment appointment);

    static void cancelAppointment(String appointmentId) {

    }

    static void rescheduleAppointment(String appointmentId, LocalDate newDate) {

    }

}
