package Entity;

import Interface.Displayable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class GeneralPractitioner  extends Doctor implements Displayable {
    boolean walkinAvailable ;
    boolean homeVisitAvailable;
    boolean vaccinationCertified ;

    public GeneralPractitioner() {

    }


    public boolean isWalkinAvailable() {
        return walkinAvailable;
    }

    public void setWalkinAvailable(boolean walkinAvailable) {
        this.walkinAvailable = walkinAvailable;
    }

    public boolean isHomeVisitAvailable() {
        return homeVisitAvailable;
    }

    public void setHomeVisitAvailable(boolean homeVisitAvailable) {
        this.homeVisitAvailable = homeVisitAvailable;
    }

    public boolean isVaccinationCertified() {
        return vaccinationCertified;
    }

    public void setVaccinationCertified(boolean vaccinationCertified) {
        this.vaccinationCertified = vaccinationCertified;
    }

    public GeneralPractitioner(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, UUID doctorId, String specialization, String qualification, int experienceYears, UUID departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients, boolean isAvailable) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients, isAvailable);
    }

    public GeneralPractitioner(String doctorId, String specialization, String qualification, int experienceYears, String departmentId, double consultationFee, List<String> availableSlots, List<String> assignedPatients, boolean isAvailable) {
        super(doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients, isAvailable);
    }

    @Override
    public void displayInfo(){
        super.displayInfo();
            System.out.println("===== Entity.Consultant Details =====");
            System.out.println("Walk-in Available: " + walkinAvailable);
            System.out.println("Home Visit Available: " + homeVisitAvailable);
            System.out.println("Vaccination Certified: " + vaccinationCertified);

            System.out.println("==============================");
        }
public void scheduleHomeVisit() {}
    public void administerVaccine() {}

    }

