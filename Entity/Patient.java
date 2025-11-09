package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Patient extends PersonBase implements Displayable {
    String patientId;
    String bloodGroup;
     List<String> allergies ;
     String emergencyContact;
    LocalDate registrationDate;
    String insuranceId;
    List <String> medicalRecords;
    List <String>appointments ;

    public Patient() {
        super();
    }


    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        if (!HelperUtils.isValidString(bloodGroup)){
            System.out.println("Invalid blood group.");
        }
        this.bloodGroup = bloodGroup;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        if (!HelperUtils.isValidString(emergencyContact)){
            System.out.println("Invalid emergency contact.");
        }
        this.emergencyContact = emergencyContact;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        if (HelperUtils.isFutureDate(registrationDate)){
            System.out.println("Registration date cannot be in the future.");
        }
        this.registrationDate = registrationDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public List<String> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<String> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public List<String> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<String> appointments) {
        this.appointments = appointments;
    }

    public Patient(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, String insuranceId, List<String> medicalRecords, List<String> appointments) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.allergies = allergies;
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.insuranceId = insuranceId;
        this.medicalRecords = medicalRecords;
        this.appointments = appointments;
    }

    public Patient(String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, String insuranceId, List<String> medicalRecords, List<String> appointments) {
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.allergies = allergies;
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.insuranceId = insuranceId;
        this.medicalRecords = medicalRecords;
        this.appointments = appointments;
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("===== Patient Details =====");
        System.out.println("Patient ID: " + patientId);
        System.out.println("Blood Group: " + bloodGroup);

        System.out.println("Allergies:");
        if (allergies == null || allergies.isEmpty()) {
            System.out.println(" - None");
        } else {
            for (String allergy : allergies) {
                System.out.println(" - " + allergy);
            }
        }
        System.out.println("Emergency Contact: " + emergencyContact);
        System.out.println("Registration Date: " + registrationDate);
        System.out.println("Insurance ID: " + insuranceId);

        System.out.println("Medical Records:");
        if (medicalRecords == null || medicalRecords.isEmpty()) {
            System.out.println(" - No records available");
        } else {
            for (String record : medicalRecords) {
                System.out.println(" - " + record);
            }
        }
        System.out.println("Appointments:");
        if (appointments == null || appointments.isEmpty()) {
            System.out.println(" - No appointments scheduled");
        } else {
            for (String appointment : appointments) {
                System.out.println(" - " + appointment);
            }
        }
        System.out.println("==============================");
    }

    @Override
    public void displaySummary() {
System.out.println("Entity.Patient: " + firstName + " " + lastName + " | ID: " + patientId + " | Contact: " + phoneNumber);
    }


    // ===== Overloaded updateContact() methods =====
    public void updateContact(String phone) {
        this.phoneNumber = phone;
        System.out.println("Phone number updated to: " + phone);
    }

    public void updateContact(String phone, String email) {
        this.phoneNumber = phone;
        this.email = email;
        System.out.println("Phone and email updated to: " + phone + ", " + email);
    }

    public void updateContact(String phone, String email, String address) {
        this.phoneNumber = phone;
        this.email = email;
        this.address = address;
        System.out.println("Contact details updated: ");
        System.out.println("Phone: " + phone + ", Email: " + email + ", Address: " + address);
    }

    }


