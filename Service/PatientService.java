package Service;

import Entity.*;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;
import Utils.InputHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static Service.AppointmentService.appointmentList;

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
        patient.setGender(InputHelper.getStringInput("Enter gender (M/F)"));
        patient.setPhoneNumber(InputHelper.getStringInput("Enter phoneNumber"));
        patient.setEmail(InputHelper.getStringInput("Enter email"));
        patient.setAddress(InputHelper.getStringInput("Enter address "));
        InputHelper.getStringInput("Enter blood group");
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


            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press any letter to add another");
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

            String record = InputHelper.getStringInput("Enter medical record #" + medicalRecordNumber );
            medicalRecordsInput.add(record);
            medicalRecordNumber++;


            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press any letter to add another");
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

            String appointment = InputHelper.getStringInput("Enter appointment #" + appointmentNumber);
            appointmentsInput.add(appointment);
            appointmentNumber++;

            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press any letter to add another");
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

            if (HelperUtils.isNotNull(existingPatient.getPatientId() ) && existingPatient.getPatientId().toString().equals(patientId)) {

                updatedPatient.setPatientId(existingPatient.getPatientId());

                // Replace old patient with updated one
                listPatient.set(i, updatedPatient);
                System.out.println("Patient updated successfully!");
                return true;
            }
        }

        System.out.println("Patient with ID " + patientId + " not found.");
        return false;
    }


    public static Patient getPatientById(String patientId) {
        for (Patient p : listPatient) {
            if (p.getPatientId() != null && p.getPatientId().toString().equals(patientId)) {
                return p;
            }
        }
        System.out.println("Patient with ID " + patientId + " not found.");
        return null;
    }

    public static void searchPatientsByName(String name) {
        boolean found = false;
        for (Patient p : listPatient) {
            if ((HelperUtils.isNotNull(p.getFirstName()) && p.getFirstName().toLowerCase().contains(name.toLowerCase())) ||
                    (HelperUtils.isNotNull(p.getLastName())  && p.getLastName().toLowerCase().contains(name.toLowerCase()))) {
                p.displayInfo();
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
                    (HelperUtils.isNotNull(p.getFirstName() )  && p.getFirstName().equalsIgnoreCase(keyword)) ||
                    (HelperUtils.isNotNull(p.getLastName())  && p.getLastName().equalsIgnoreCase(keyword))) {
                return p;
            }
        }
        System.out.println("Patient with keyword " + keyword + " not found.");
        return null;
    }

    public Patient searchPatients(String firstName, String lastName) {
        for (Patient p : PatientService.listPatient) {
            if (HelperUtils.isNotNull(p.getFirstName())  && p.getFirstName().equalsIgnoreCase(firstName) &&
                    HelperUtils.isNotNull( p.getLastName() ) && p.getLastName().equalsIgnoreCase(lastName)) {
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
            System.out.println("Invalid entity Expected Patient.");
        }

    }

    @Override
    public void remove(String id) {
        boolean isFound = false;
        for (int i = 0; i < listPatient.size(); i++) {
            Patient p = listPatient.get(i);
            if (p.getPatientId() != null && p.getPatientId().toString().equals(id)) {
                listPatient.remove(i); // remove by index
                System.out.println("Patient with ID " + id + " removed successfully!");
                isFound = true;
                break;

            }
        }
        if (!isFound) {
            System.out.println("Patient with ID " + id + " not found.");
        }

    }

    public static InPatient addInPatient() {
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
        // collect inpatient-specific info
        System.out.println("===== In-Patient Details =====");
        inPatient.setAdmissionDate(InputHelper.getDateInput("Enter Admission Date "));
        System.out.println();
        String dischargeInput = InputHelper.getStringInput("Enter Discharge Date  or  if unknown");
        inPatient.setDischargeDate(dischargeInput.isBlank() ? null
                : LocalDate.parse(dischargeInput, DateTimeFormatter.ISO_LOCAL_DATE));
        LocalDate dischargeDate = dischargeInput.isBlank() ? null
                : LocalDate.parse(dischargeInput, DateTimeFormatter.ISO_LOCAL_DATE);
        String roomNumber = InputHelper.getStringInput("Enter Room Number");
        inPatient.setRoomNumber(roomNumber);

        inPatient.setBedNumber(InputHelper.getStringInput("Enter Bed Number"));
        String admittingDoctorId = InputHelper.getStringInput("Enter Admitting Doctor ID ");
        inPatient.setAdmittingDoctorId(admittingDoctorId);
        double dailyCharges = 0.0;
        String chargesInput = InputHelper.getStringInput("Enter Daily Charges");
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

        outPatient.setVisitCount(InputHelper.getIntInput(" Enter Visit Count"));

        outPatient.setLastVisitDate(InputHelper.getDateInput("Enter last Visit Date "));
        save(outPatient);
        return outPatient;
    }

    public static EmergencyPatient addEmergencyPatient() {
        EmergencyPatient emergencyPatient = new EmergencyPatient();
        InPatient I = PatientService.addInPatient();
        emergencyPatient.setId(I.getId());
        emergencyPatient.setPatientId(I.getPatientId());
        emergencyPatient.setFirstName(I.getFirstName());
        emergencyPatient.setLastName(I.getLastName());
        emergencyPatient.setDateOfBirth(I.getDateOfBirth());
        emergencyPatient.setGender(I.getGender());
        emergencyPatient.setPhoneNumber(I.getPhoneNumber());
        emergencyPatient.setEmail(I.getEmail());
        emergencyPatient.setAddress(I.getAddress());
        emergencyPatient.setBloodGroup(I.getBloodGroup());
        emergencyPatient.setAllergies(I.getAllergies());
        emergencyPatient.setEmergencyContact(I.getEmergencyContact());
        emergencyPatient.setRegistrationDate(I.getRegistrationDate());
        emergencyPatient.setMedicalRecords(I.getMedicalRecords());
        emergencyPatient.setAppointments(I.getAppointments());
        emergencyPatient.setAdmissionDate(I.getAdmissionDate());
        emergencyPatient.setDischargeDate(I.getDischargeDate());
        emergencyPatient.setRoomNumber(I.getRoomNumber());
        emergencyPatient.setBedNumber(I.getBedNumber());
        emergencyPatient.setAdmittingDoctorId(I.getAdmittingDoctorId());
        emergencyPatient.setDailyCharges(I.getDailyCharges());

        System.out.println("===== Register Emergency Patient =====");

        PatientService.addPatient();
        emergencyPatient.setEmergencyType(InputHelper.getStringInput("Enter Emergency Type (e.g., Cardiac, Trauma):"));

        emergencyPatient.setArrivalMode(InputHelper.getStringInput("Enter Arrival Mode (Ambulance/Walk-in)"));

        int triageLevel = 1;
        String triageInput = InputHelper.getStringInput("Enter Triage Level (1-5)");
        if (!triageInput.isBlank()) {
            try {
                triageLevel = Integer.parseInt(triageInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid triage level. Defaulting to 1.");
            }
        }
        emergencyPatient.setTriageLevel(triageLevel);


        boolean admitted = InputHelper.getConfirmation("Admitted via ER?");
        emergencyPatient.setAdmittedViaER(admitted);

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
        boolean isFound = false;
        for (Patient p : listPatient) {
            if (p.getPatientId() != null && p.getPatientId().toString().equals(id)) {
                p.displayInfo();
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.println("Patient with ID " + id + " not found.");

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

    public static List<Patient> getAllPatients() {
        return new ArrayList<>(listPatient);
    }

    public static void editPatient(String patientId) {
        for (Patient patient : listPatient) {
            if (patient.getPatientId() != null && patient.getPatientId().equals(patientId)) {
                System.out.println("=== Editing Patient: " + patient.getFirstName() + " " + patient.getLastName() + " ===");

                boolean continueEditing = true;

                while (continueEditing) {
                    System.out.println("\nSelect what you want to edit:");
                    System.out.println("1. First Name");
                    System.out.println("2. Last Name");
                    System.out.println("3. Date of Birth");
                    System.out.println("4. Gender");
                    System.out.println("5. Phone Number");
                    System.out.println("6. Email");
                    System.out.println("7. Address");
                    System.out.println("8. Blood Group");
                    System.out.println("9. Emergency Contact");
                    System.out.println("10. Allergies");
                    System.out.println("11. Medical Records");
                    System.out.println("12. Appointments");
                    System.out.println("13. Exit Editing");

                    int choice = InputHelper.getIntInput("Enter your choice: ");

                    switch (choice) {
                        case 1 -> patient.setFirstName(InputHelper.getStringInput("Enter new first name "));
                        case 2 -> patient.setLastName(InputHelper.getStringInput("Enter new last name "));
                        case 3 ->
                                patient.setDateOfBirth(InputHelper.getDateInput("Enter new date of birth  "));
                        case 4 -> patient.setGender(InputHelper.getStringInput("Enter new gender (M/F)"));
                        case 5 -> patient.setPhoneNumber(InputHelper.getStringInput("Enter new phone number "));
                        case 6 -> patient.setEmail(InputHelper.getStringInput("Enter new email "));
                        case 7 -> patient.setAddress(InputHelper.getStringInput("Enter new address "));
                        case 8 -> patient.setBloodGroup(InputHelper.getStringInput("Enter new blood group"));
                        case 9 ->
                                patient.setEmergencyContact(InputHelper.getStringInput("Enter new emergency contact"));
                        case 10 -> {
                            List<String> allergies = new ArrayList<>();
                            boolean addMore = true;
                            while (addMore) {
                                String allergy = InputHelper.getStringInput("Enter allergy ");
                                allergies.add(allergy);
                                String exitFlag = InputHelper.getStringInput("Press 'q' to stop or Enter to add more ");
                                if (exitFlag.equalsIgnoreCase("q")) addMore = false;
                            }
                            patient.setAllergies(allergies);
                        }
                        case 11 -> {
                            List<String> records = new ArrayList<>();
                            boolean addMore = true;
                            while (addMore) {
                                String record = InputHelper.getStringInput("Enter medical record ");
                                records.add(record);
                                String exitFlag = InputHelper.getStringInput("Press 'q' to stop or Enter to add more ");
                                if (exitFlag.equalsIgnoreCase("q")) addMore = false;
                            }
                            patient.setMedicalRecords(records);
                        }
                        case 12 -> {
                            List<String> appointments = new ArrayList<>();
                            boolean addMore = true;
                            while (addMore) {
                                String appt = InputHelper.getStringInput("Enter appointment");
                                appointments.add(appt);
                                String exitFlag = InputHelper.getStringInput("Press 'q' to stop or Enter to add more ");
                                if (exitFlag.equalsIgnoreCase("q")) addMore = false;
                            }
                            patient.setAppointments(appointments);
                        }
                        case 13 -> {
                            continueEditing = false;
                            System.out.println("Exiting edit mode...");
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }

                System.out.println(" Patient details updated successfully!");
                return;
            }
        }

        System.out.println(" Patient with ID " + patientId + " not found.");
    }


    public static void generatePatientStatisticsReport(String patientId) {
        Patient patient = PatientService.getPatientById(patientId);
        if (HelperUtils.isNull(patient)) {
            System.out.println("Patient with ID " + patientId + " not found.");
            return;
        }

        List<Appointment> patientAppointments = AppointmentService.getAppointmentsByPatient(patientId);
        long totalAppointments = patientAppointments.size();

        long completedAppointments = 0;
        long cancelledAppointments = 0;

        for (Appointment appointment : patientAppointments) {
            String status = appointment.getStatus();
            if (status.equalsIgnoreCase("Completed")) {
                completedAppointments++;
            } else if (status.equalsIgnoreCase("Cancelled")) {
                cancelledAppointments++;
            }
        }

        System.out.println("===== Patient Statistics Report =====");
        System.out.println("Patient Name: " + patient.getFirstName() + " " + patient.getLastName());
        System.out.println("Total Appointments: " + totalAppointments);
        System.out.println("Completed Appointments: " + completedAppointments);
        System.out.println("Cancelled Appointments: " + cancelledAppointments);
        System.out.println("-------------------------------------------");
    }

    public static void getRecordsByPatientId(String patientId) {
        Patient foundPatient = null;
        for (Patient p : listPatient) {
            if (HelperUtils.isNotNull(p.getPatientId()) && p.getPatientId().equals(patientId)) {
                foundPatient = p;
                break;
            }
        }

        if (foundPatient == null) {
            System.out.println("Patient with ID " + patientId + " not found.");
            return;
        }

        ;
        System.out.println("Medical Record  " + foundPatient.getMedicalRecords());

    }

    public static void generateEmergencyCasesReport(String patientId) {
        System.out.println("===== Emergency Cases Report =====");

        if (HelperUtils.isNull(patientId) || patientId.isEmpty()) {
            System.out.println("No emergency patients found in the system.");
            return;
        }

        List<Patient> ep = PatientService.getAllPatients();
        for (Patient p : ep) {
            if (p.getPatientId().equalsIgnoreCase(patientId)) {
                System.out.println("Patient ID: " + p.getPatientId());
                System.out.println("Name: " + p.getFirstName() + " " + p.getLastName());
                System.out.println("Contact: " + p.getPhoneNumber());
                System.out.println("Address: " + p.getAddress());
                System.out.println("-------------------------------------------");
            }
        }
    }


    public static void addSamplePatients() {
        String[] bloodGroups = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        for (int i = 0; i < 4; i++) {
            Patient patient = new Patient();
            patient.setEmail("codeline@gmail.com" + i);
            patient.setFirstName("Ahmed" + i);
            patient.setLastName("Al sad" + i);
            patient.setBloodGroup(bloodGroups[i % bloodGroups.length]);
            patient.setPhoneNumber("90188890" + i);
            patient.setGender("M");
            List<String> allergyList = new ArrayList<>();
            allergyList.add("pollen");
            allergyList.add("dust");
            allergyList.add("nuts");
            patient.setAllergies(allergyList);
            patient.setDateOfBirth(LocalDate.of(1990, 1, 1).plusDays(i));
            patient.setAddress("Muscat Oman" + i);
            patient.setEmergencyContact("+9689012345" + i);
            patient.setRegistrationDate(LocalDate.now());
            List<String> medicalRecords = new ArrayList<>();
            medicalRecords.add("Blood test - normal");
            medicalRecords.add("X-ray - clear");
            medicalRecords.add("Vaccination - completed");
            patient.setMedicalRecords(medicalRecords);
            List<String> appointments = new ArrayList<>();
            appointments.add("2023-10-01 10:00 AM with Dr. Smith");
            appointments.add("2023-10-15 02:00 PM with Dr. Johnson");
            patient.setAppointments(appointments);
            patient.setId(HelperUtils.generateId(8));
            patient.setPatientId(HelperUtils.generateId("PT", 5));
            patient.setInsuranceId(HelperUtils.generateId("INS", 6));
            listPatient.add(patient);

        }
        // ---- IN  Patients ----
        for (int i = 0; i < 3; i++) {
            InPatient inPatient = new InPatient();
            inPatient.setEmail("codeline@gmail.com" + i);
            inPatient.setFirstName("Ali" + i);
            inPatient.setLastName("Al Said" + i);
            inPatient.setBloodGroup(bloodGroups[i % bloodGroups.length]);
            inPatient.setPhoneNumber("90188890" + i);
            inPatient.setGender("M");
            List<String> allergyList = List.of("pollen", "dust", "nuts");
            inPatient.setAllergies(allergyList);
            inPatient.setDateOfBirth(LocalDate.of(1990, 1, 1).plusDays(i));
            inPatient.setAddress("Muscat, Oman " + i);
            inPatient.setEmergencyContact("+9689012345" + i);
            inPatient.setRegistrationDate(LocalDate.now());
            List<String> medicalRecords = List.of("Blood test - normal", "Vaccination - completed");
            inPatient.setMedicalRecords(medicalRecords);
            List<String> appointments = List.of("2023-10-15 02:00 PM with Dr. Johnson");
            inPatient.setAppointments(appointments);
            inPatient.setId(HelperUtils.generateId(8));
            inPatient.setPatientId(HelperUtils.generateId("PT", 5));
            inPatient.setInsuranceId(HelperUtils.generateId("INS", 6));
            inPatient.setAdmissionDate(LocalDate.now().minusDays(10 - i));
            inPatient.setDischargeDate(LocalDate.now().minusDays(5 - i));
            inPatient.setRoomNumber("A10" + i);
            inPatient.setBedNumber("B" + (i + 1));
            inPatient.setAdmittingDoctorId("DOC" + (100 + i));
            inPatient.setDailyCharges(75.0 + (i * 10));
            inPatient.setPatientId(HelperUtils.generateId("INP", 5));
            listPatient.add(inPatient);

        }
        // ---- OutPatients ----
        for (int i = 0; i < 3; i++) {
            OutPatient outPatient = new OutPatient();
            outPatient.setBloodGroup(bloodGroups[i % bloodGroups.length]);
            outPatient.setEmergencyContact("+9689012345" + i);
            outPatient.setRegistrationDate(LocalDate.now());
            outPatient.setId(HelperUtils.generateId(8));
            outPatient.setEmail("outpatient@clinic.com" + i);
            outPatient.setFirstName("Fatma" + i);
            outPatient.setLastName("Al Rashdi" + i);
            outPatient.setGender("F");
            outPatient.setVisitCount(2 + i);
            outPatient.setLastVisitDate(LocalDate.now().minusDays(7 * i));
            outPatient.setPreferredDoctorId("DOC" + (200 + i));
            outPatient.setAddress("Sohar, Oman " + i);
            outPatient.setPhoneNumber("90234567" + i);
            outPatient.setRegistrationDate(LocalDate.now().minusDays(i));
            outPatient.setDateOfBirth(LocalDate.of(1992, 5, 12).plusYears(i));
            outPatient.setAllergies(List.of("penicillin", "dust"));
            outPatient.setMedicalRecords(List.of("Consultation - mild allergy", "Routine check-up"));
            outPatient.setAppointments(List.of("2023-09-05 09:30 AM with Dr. Ali",
                    "2023-10-10 11:00 AM with Dr. Noor"));
            outPatient.setPatientId(HelperUtils.generateId("OUT", 5));
            outPatient.setInsuranceId(HelperUtils.generateId("INS", 6));
            listPatient.add(outPatient);

        }
        // ---- Emergency Patients ----
        for (int i = 0; i < 3; i++) {
            EmergencyPatient emergencyPatient = new EmergencyPatient();
            emergencyPatient.setEmail("codeline@gmail.com" + i);
            emergencyPatient.setFirstName("Hanin" + i);
            emergencyPatient.setLastName("Al Said" + i);
            emergencyPatient.setBloodGroup(bloodGroups[i % bloodGroups.length]);
            emergencyPatient.setPhoneNumber("90188890" + i);
            emergencyPatient.setGender("F");
            List<String> allergyList = List.of("pollen", "dust", "nuts");
            emergencyPatient.setAllergies(allergyList);
            emergencyPatient.setDateOfBirth(LocalDate.of(1990, 1, 1).plusDays(i));
            emergencyPatient.setAddress("Muscat, Oman " + i);
            emergencyPatient.setEmergencyContact("+9689012345" + i);
            emergencyPatient.setRegistrationDate(LocalDate.now());
            List<String> medicalRecords = List.of("Blood test - normal", "Vaccination - completed");
            emergencyPatient.setMedicalRecords(medicalRecords);
            List<String> appointments = List.of("2023-10-15 02:00 PM with Dr. Johnson");
            emergencyPatient.setAppointments(appointments);
            emergencyPatient.setId(HelperUtils.generateId(8));
            emergencyPatient.setPatientId(HelperUtils.generateId("PT", 5));
            emergencyPatient.setInsuranceId(HelperUtils.generateId("INS", 6));
            emergencyPatient.setAdmissionDate(LocalDate.now().minusDays(10 - i));
            emergencyPatient.setDischargeDate(LocalDate.now().minusDays(5 - i));
            emergencyPatient.setRoomNumber("A10" + i);
            emergencyPatient.setBedNumber("B" + (i + 1));
            emergencyPatient.setAdmittingDoctorId("DOC" + (100 + i));
            emergencyPatient.setDailyCharges(75.0 + (i * 10));
            emergencyPatient.setPatientId(HelperUtils.generateId("INP", 5));
            emergencyPatient.setFirstName("Emergency" + i);
            emergencyPatient.setLastName("Case" + i);
            emergencyPatient.setEmergencyType(i % 2 == 0 ? "Accident" : "Heart Attack");
            emergencyPatient.setArrivalMode(i % 2 == 0 ? "Ambulance" : "Walk-in");
            emergencyPatient.setTriageLevel((i + 2));
            emergencyPatient.setAdmittedViaER(true);
            emergencyPatient.setAdmissionDate(LocalDate.now().minusDays(i));
            emergencyPatient.setRoomNumber("ER-" + (i + 1));
            emergencyPatient.setBedNumber("E" + (i + 1));
            emergencyPatient.setAdmittingDoctorId("DR" + (i + 1));
            emergencyPatient.setDailyCharges(100.0 + (i * 20));
            emergencyPatient.setPatientId(HelperUtils.generateId("EMP", 5));
            listPatient.add(emergencyPatient);
        }
    }


}







