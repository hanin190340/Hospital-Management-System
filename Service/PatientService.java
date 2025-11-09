package Service;

import Entity.*;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;
import Utils.InputHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PatientService implements Manageable, Searchable {
    public static List<Patient> listPatient = new ArrayList<>();


    public static Patient addPatient() {
Patient patient = new Patient();

        System.out.println("Adding Patient");
        patient.setPatientId(HelperUtils.generateId("PT", 5));
        patient.setId(HelperUtils.generateId(8));
       patient.setFirstName(InputHelper.getStringInput("Enter the FirstName"));
        patient.setLastName(InputHelper.getStringInput("Enter the lastName"));
        patient.setDateOfBirth(InputHelper.getDateInput("Enter date of birth "));
        patient.setGender(InputHelper.getStringInput("Enter gender (M/F): "));
        patient.setPhoneNumber(InputHelper.getStringInput("Enter phoneNumber: "));
        patient.setEmail(InputHelper.getStringInput("Enter email "));
        patient.setAddress(InputHelper.getStringInput("Enter address : "));
        patient.setBloodGroup(InputHelper.getStringInput(" Enter blood Group : "));
        patient.setEmergencyContact(InputHelper.getStringInput(" Enter emergency Contact"));
        patient.setInsuranceId(HelperUtils.generateId("INS", 6));
        patient.setRegistrationDate(LocalDate.now());

        // ---------- allergy ----------
        boolean continueInput = true;
        int allergyNumber = 1;
        List<String> allergiesInput = new LinkedList<>();
        while (continueInput) {
            String allergy = InputHelper.getStringInput("Enter allergy #" + allergyNumber + ":");
            allergiesInput.add(allergy);
            allergyNumber++;


            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press Enter to add another:");
            if (exitFlag.equalsIgnoreCase("q")) {
                continueInput = false;
            }
        }
        patient.setAllergies(allergiesInput);

        // ---------- medicalRecord ----------
        boolean continueMedicalInput = true;
        int medicalRecordNumber = 1;
        List<String> medicalRecordsInput = new LinkedList<>();

        while (continueMedicalInput) {

            String record = InputHelper.getStringInput("Enter medical record #" + medicalRecordNumber + ":");
            medicalRecordsInput.add(record);
            medicalRecordNumber++;


            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press Enter to add another:");
            if (exitFlag.equalsIgnoreCase("q")) {
                continueMedicalInput = false;
            }
        }
        patient.setMedicalRecords(medicalRecordsInput);

        // ---------- Appointments ----------
        boolean continueAppointmentInput = true;
        int appointmentNumber = 1;
        List<String> appointmentsInput = new LinkedList<>();

        while (continueAppointmentInput) {

            String appointment = InputHelper.getStringInput("Enter appointment #" + appointmentNumber + ":");
            appointmentsInput.add(appointment);
            appointmentNumber++;

            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press Enter to add another:");
            if (exitFlag.equalsIgnoreCase("q")) {
                continueAppointmentInput = false;
            }
        }
        patient.setAppointments(appointmentsInput);

        return patient;
    }

    public static void save(Patient patient) {
        listPatient.add(patient);
        System.out.println("patient added successfully!\n");
    }

    public static boolean editPatient(String patientId, Patient updatedPatient) {
        for (int i = 0; i < listPatient.size(); i++) {
            Patient existingPatient = listPatient.get(i);

            if (existingPatient.getPatientId() != null && existingPatient.getPatientId().toString().equals(patientId)) {

                updatedPatient.setPatientId(existingPatient.getPatientId());

                // Replace old patient with updated one
                listPatient.set(i, updatedPatient);
                System.out.println("Entity.Patient updated successfully!");
                return true;
            }
        }

        System.out.println("Entity.Patient with ID " + patientId + " not found.");
        return false;
    }


    public static Patient getPatientById(String patientId) {
        for (Patient p : listPatient) {
            if (p.getPatientId() != null && p.getPatientId().toString().equals(patientId)) {
                return p;
            }
        }
        System.out.println("Entity.Patient with ID " + patientId + " not found.");
        return null;
    }

    public static void searchPatientsByName(String name) {
        boolean found = false;
        for (Patient p : listPatient) {
            if ((p.getFirstName() != null && p.getFirstName().toLowerCase().contains(name.toLowerCase())) ||
                    (p.getLastName() != null && p.getLastName().toLowerCase().contains(name.toLowerCase()))) {
                p.displayInfo();  // display matching patient
                found = true;
            }
        }
        if (!found) {
            System.out.println("No patients found with name containing: " + name);
        }
    }

    public static void displayAllPatients() {
        if (listPatient.isEmpty()) {
            System.out.println("No patients to display.");
            return;
        }

        for (Patient p : listPatient) {
            p.displayInfo();
        }
    }

    public static Patient addPatient(String firstName, String lastName, String phone) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhoneNumber(phone);
        return patient;
    }

    public static Patient addPatient(String firstName, String lastName, String phone, String bloodGroup, String email) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhoneNumber(phone);
        patient.setBloodGroup(bloodGroup);
        patient.setEmail(email);
        return patient;
    }



    public static Patient searchPatients(String keyword) {
        for (Patient p : listPatient) {
            if ((HelperUtils.isNotNull(p.getPatientId()) && p.getPatientId().toString().equals(keyword)) ||
                    (
                    p.getFirstName() != null && p.getFirstName().equalsIgnoreCase(keyword)) ||
                    (p.getLastName() != null && p.getLastName().equalsIgnoreCase(keyword))) {
                return p;
            }
        }
        System.out.println("Patient with keyword " + keyword + " not found.");
        return null;
    }

    public Patient searchPatients(String firstName, String lastName) {
        for (Patient p : PatientService.listPatient) {
            if (p.getFirstName() != null && p.getFirstName().equalsIgnoreCase(firstName) &&
                    p.getLastName() != null && p.getLastName().equalsIgnoreCase(lastName)) {
                return p;
            }
        }
        System.out.println("Patient with name " + firstName + " " + lastName + " not found.");
        return null;
    }

    public Patient displayPatients() {
        for (Patient p : PatientService.listPatient) {
            p.displayInfo();
        }
        return null;
    }

    public Patient displayPatients(String filter) {
        for (Patient p : PatientService.listPatient) {
            if (p.getBloodGroup() != null && p.getBloodGroup().equalsIgnoreCase(filter)) {
                p.displayInfo();
            }
        }
        return null;
    }

    public Patient displayPatients(int limit) {
        int count = 0;
        for (Patient p : PatientService.listPatient) {
            if (count >= limit) {
                break;
            }
            p.displayInfo();
            count++;
        }
        return null;
    }

    @Override
    public void add(Object entity) {
        if (entity instanceof Patient) {
            save((Patient) entity);
        } else {
            System.out.println("Invalid entity. Expected Patient.");
        }

    }

    public  static void remove(String id) {
        for (int i = 0; i < listPatient.size(); i++) {
            Patient p = listPatient.get(i);
            if (p.getPatientId() != null && p.getPatientId().toString().equals(id)) {
                listPatient.remove(i); // remove by index
                System.out.println("Patient with ID " +id + " removed successfully!");

            }
        }
        System.out.println("Patient with ID " + id + " not found.");

    }

    public static InPatient addInPatient () {
        Patient P = PatientService.addPatient();
        InPatient inPatient = new InPatient();
        inPatient.setId(P.getId());
        inPatient.setPatientId(P.getPatientId());
        inPatient.setFirstName(P.getFirstName());
        inPatient.setLastName(P.getLastName());
        inPatient.setDateOfBirth(P.getDateOfBirth());
        inPatient.setGender(P.getGender());
        inPatient.setPhoneNumber(P.getPhoneNumber());
        inPatient.setEmail(P.getEmail());
        inPatient.setAddress(P.getAddress());
        inPatient.setBloodGroup(P.getBloodGroup());
        inPatient.setAllergies(P.getAllergies());
        inPatient.setEmergencyContact(P.getEmergencyContact());
        inPatient.setRegistrationDate(P.getRegistrationDate());
        inPatient.setMedicalRecords(P.getMedicalRecords());
        inPatient.setAppointments(P.getAppointments());
            // collect base patient info using existing interactive method
            Patient patient = PatientService.addPatient();

            // collect inpatient-specific info
            System.out.println("===== In-Patient Details =====");
inPatient.setAdmissionDate(InputHelper.getDateInput("Enter Admission Date (YYYY-MM-DD):"));
            System.out.println();
            String dischargeInput = InputHelper.getStringInput("Enter Discharge Date (YYYY-MM-DD) or leave blank if unknown:");
            inPatient.setDischargeDate(dischargeInput.isBlank() ? null
                    : LocalDate.parse(dischargeInput, DateTimeFormatter.ISO_LOCAL_DATE));
            LocalDate dischargeDate = dischargeInput.isBlank() ? null
                    : LocalDate.parse(dischargeInput, DateTimeFormatter.ISO_LOCAL_DATE);
            String roomNumber = InputHelper.getStringInput("Enter Room Number:");
inPatient.setRoomNumber(roomNumber);

inPatient.setBedNumber(InputHelper.getStringInput("Enter Bed Number:"));
            String admittingDoctorId = InputHelper.getStringInput("Enter Admitting Doctor ID :");
inPatient.setAdmittingDoctorId(admittingDoctorId);
            double dailyCharges = 0.0;
            String chargesInput = InputHelper.getStringInput("Enter Daily Charges:");
            if (!chargesInput.isBlank()) {
                try {
                    dailyCharges = Double.parseDouble(chargesInput);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number for daily charges. Defaulting to 0.0");
                }
            }
            inPatient.setDailyCharges(dailyCharges);

        return inPatient;
    }


    public static OutPatient addOutPatient() {
OutPatient outPatient = new OutPatient();
        PatientService.addPatient();
        Patient P = PatientService.addPatient();
        outPatient.setId(P.getId());
        outPatient.setPatientId(P.getPatientId());
        outPatient.setFirstName(P.getFirstName());
        outPatient.setLastName(P.getLastName());
        outPatient.setDateOfBirth(P.getDateOfBirth());
        outPatient.setGender(P.getGender());
        outPatient.setPhoneNumber(P.getPhoneNumber());
        outPatient.setEmail(P.getEmail());
        outPatient.setAddress(P.getAddress());
        outPatient.setBloodGroup(P.getBloodGroup());
        outPatient.setAllergies(P.getAllergies());
        outPatient.setEmergencyContact(P.getEmergencyContact());
        outPatient.setRegistrationDate(P.getRegistrationDate());
        outPatient.setMedicalRecords(P.getMedicalRecords());
        outPatient.setAppointments(P.getAppointments());
        System.out.println("===== Out-Patient Details =====");
        outPatient.setPreferredDoctorId(HelperUtils.generateId());

        outPatient.setVisitCount(InputHelper.getIntInput(" Enter Visit Count:"));

        outPatient.setLastVisitDate(InputHelper.getDateInput("Enter last Visit Date (YYYY-MM-DD):"));
        save(outPatient);
        return outPatient;
    }
    public static EmergencyPatient addEmergencyPatient() {
        EmergencyPatient emergencyPatient = new EmergencyPatient();
        PatientService.addInPatient();
        Patient P = PatientService.addPatient();
        InPatient I = PatientService.addInPatient();
        emergencyPatient.setId(P.getId());
        emergencyPatient.setPatientId(P.getPatientId());
        emergencyPatient.setFirstName(P.getFirstName());
        emergencyPatient.setLastName(P.getLastName());
        emergencyPatient.setDateOfBirth(P.getDateOfBirth());
        emergencyPatient.setGender(P.getGender());
        emergencyPatient.setPhoneNumber(P.getPhoneNumber());
        emergencyPatient.setEmail(P.getEmail());
        emergencyPatient.setAddress(P.getAddress());
        emergencyPatient.setBloodGroup(P.getBloodGroup());
        emergencyPatient.setAllergies(P.getAllergies());
        emergencyPatient.setEmergencyContact(P.getEmergencyContact());
        emergencyPatient.setRegistrationDate(P.getRegistrationDate());
        emergencyPatient.setMedicalRecords(P.getMedicalRecords());
        emergencyPatient.setAppointments(P.getAppointments());
        emergencyPatient.setAdmissionDate(I.getAdmissionDate());
        emergencyPatient.setDischargeDate(I.getDischargeDate());
        emergencyPatient.setRoomNumber(I.getRoomNumber());
        emergencyPatient.setBedNumber(I.getBedNumber());
        emergencyPatient.setAdmittingDoctorId(I.getAdmittingDoctorId());
        emergencyPatient.setDailyCharges(I.getDailyCharges());

        System.out.println("===== Register Emergency Patient =====");

        PatientService.addPatient();
        emergencyPatient.setEmergencyType(InputHelper.getStringInput("Enter Emergency Type (e.g., Cardiac, Trauma):"));

        emergencyPatient.setArrivalMode(InputHelper.getStringInput("Enter Arrival Mode (Ambulance/Walk-in):"));

        int triageLevel = 1;
        String triageInput = InputHelper.getStringInput("Enter Triage Level (1-5):");
        if (!triageInput.isBlank()) {
            try {
                triageLevel = Integer.parseInt(triageInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid triage level. Defaulting to 1.");
            }
        }
        emergencyPatient.setTriageLevel(triageLevel);

        
        String admitted = InputHelper.getStringInput("Admitted via ER? (y/n):");
        emergencyPatient.setAdmittedViaER(admitted.equalsIgnoreCase("y") || admitted.equalsIgnoreCase("yes"));

        save(emergencyPatient);
        System.out.println("Emergency patient registered successfully!");

        return emergencyPatient;
    }




    @Override
    public void getAll() {
displayAllPatients();
    }

    @Override
    public void search(String keyword) {

    }

    @Override
    public void searchById(String id) {

    }
    // ✅ Generate number of patients registered for a specific day
    public static void generateDailyPatientStatistics(LocalDate date) {
        if (listPatient.isEmpty()) {
            System.out.println(" No patients available.");
            return;
        }

        int count = 0;
        for (Patient p : listPatient) {
            if (p.getRegistrationDate() != null && p.getRegistrationDate().equals(date)) {
                count++;
            }
        }

        System.out.println("\n===== Daily Patient Statistics =====");
        System.out.println("Date              : " + date);
        System.out.println("Number of Patients: " + count);
        System.out.println("==================================\n");
    }

    public static void generateEmergencyCasesReport() {
        if (listPatient.isEmpty()) {
            System.out.println(" No patients available.");
            return;
        }

        int totalEmergencyPatients = 0;
        List<EmergencyPatient> emergencyPatients = new ArrayList<>();

        for (Patient p : listPatient) {
            if (p instanceof EmergencyPatient) {
                emergencyPatients.add((EmergencyPatient) p);
                totalEmergencyPatients++;
            }
        }

        System.out.println("\n===== Emergency Cases Report =====");
        System.out.println("Total Emergency Patients: " + totalEmergencyPatients);
        System.out.println("---------------------------------");
        if (!emergencyPatients.isEmpty()) {
            for (EmergencyPatient ep : emergencyPatients) {
                System.out.println("Patient Name  : " + ep.getFirstName() + " " + ep.getLastName());
                System.out.println("Emergency Type: " + ep.getEmergencyType());
                System.out.println("Arrival Mode  : " + ep.getArrivalMode());
                System.out.println("Triage Level  : " + ep.getTriageLevel());
                System.out.println("Admitted via ER: " + (ep.isAdmittedViaER() ? "Yes" : "No"));
                System.out.println("---------------------------------");
            }
        } else {
            System.out.println("No emergency patients recorded.");
        }
        System.out.println("=================================\n");
    }



}




