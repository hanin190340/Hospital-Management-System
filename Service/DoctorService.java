package Service;

import Entity.*;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;
import Utils.InputHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DoctorService implements Manageable, Searchable {
    public static List<Doctor> ListOfDoctors = new ArrayList<>();


    public static Doctor addDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(HelperUtils.generateId(7));
        doctor.setDoctorId(HelperUtils.generateId("Dr", 5));
        doctor.setFirstName(InputHelper.getStringInput("Enter the FirstName"));
        doctor.setLastName(InputHelper.getStringInput("Enter the lastName"));
        doctor.setDateOfBirth(InputHelper.getDateInput("Enter date of birth "));
        doctor.setGender(InputHelper.getStringInput("Enter gender (M/F) "));
        doctor.setPhoneNumber(InputHelper.getStringInput("Enter phoneNumber "));
        doctor.setEmail(InputHelper.getStringInput("Enter email "));
        doctor.setAddress(InputHelper.getStringInput("Enter address"));
        doctor.setSpecialization(InputHelper.getStringInput(" Enter specialization "));
        doctor.setQualification(InputHelper.getStringInput(" Enter qualification"));
        doctor.setDepartmentId(HelperUtils.generateId("Dep", 5));
        doctor.setExperienceYears(InputHelper.getIntInput("Enter experience Years"));
        doctor.setConsultationFee(InputHelper.getDoubleInput("Enter consultation Fee: "));
        //---------- availableSlots ----------
        boolean continueInput = true;
        int SlotsNumber = 1;
        List<String> SlotsInput = new LinkedList<>();
        while (continueInput) {
            String slots = InputHelper.getStringInput("Enter available Slots #" + SlotsNumber);
            SlotsInput.add(slots);
            SlotsNumber++;

            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press any letter to add another");
            if (exitFlag.equalsIgnoreCase("q")) {
                continueInput = false;
            }
        }
        doctor.setAvailableSlots(SlotsInput);
        //  ---------- assignedPatients ----------
        boolean Input = true;
        int assignedNumber = 1;
        List<String> assignedInput = new LinkedList<>();

        while (Input) {

            String record = InputHelper.getStringInput("Enter assigned Patients #" + assignedNumber);
            assignedInput.add(record);
            assignedNumber++;

            System.out.println();
            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press any letter to add another");
            if (exitFlag.equalsIgnoreCase("q")) {
                Input = false;
            }
        }
        doctor.setAssignedPatients(assignedInput);


        return doctor;
    }

    public static void save(Doctor doctor) {
        if (HelperUtils.isNull(doctor)) {
            System.out.println("Please provide a valid Doctor before saving");
        }
        ListOfDoctors.add(doctor);
        System.out.println("Doctor added successfully!\n");
    }

    public static void save(Surgeon surgeon) {
        if (HelperUtils.isNull(surgeon)) {
            System.out.println("Please provide a valid Doctor before saving");
        }
        ListOfDoctors.add(surgeon);
        System.out.println("surgeon added successfully!\n");
    }
    public static void save(Consultant consultant) {
        if (HelperUtils.isNull(consultant)) {
            System.out.println("Please provide a valid Doctor before saving");
        }
        ListOfDoctors.add(consultant);
        System.out.println("Consultant added successfully!\n");
    }

    public static void editDoctorById(String doctorId) {
        for (Doctor doctor : ListOfDoctors) {
            if (HelperUtils.isNotNull(doctor.getDoctorId()) && doctor.getDoctorId().equals(doctorId)) {

                System.out.println("=== Editing Doctor: " + doctor.getFirstName() + " " + doctor.getLastName() + " ===");

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
                    System.out.println("8. Specialization");
                    System.out.println("9. Qualification");
                    System.out.println("10. Department ID");
                    System.out.println("11. Experience Years");
                    System.out.println("12. Consultation Fee");
                    System.out.println("13. Available Slots");
                    System.out.println("14. Assigned Patients");
                    System.out.println("15. Exit Editing");

                    int choice = InputHelper.getIntInput("Enter your choice: ");

                    switch (choice) {
                        case 1 -> doctor.setFirstName(InputHelper.getStringInput("Enter new first name: "));
                        case 2 -> doctor.setLastName(InputHelper.getStringInput("Enter new last name: "));
                        case 3 -> doctor.setDateOfBirth(InputHelper.getDateInput("Enter new date of birth (yyyy-MM-dd): "));
                        case 4 -> doctor.setGender(InputHelper.getStringInput("Enter new gender (M/F): "));
                        case 5 -> doctor.setPhoneNumber(InputHelper.getStringInput("Enter new phone number: "));
                        case 6 -> doctor.setEmail(InputHelper.getStringInput("Enter new email: "));
                        case 7 -> doctor.setAddress(InputHelper.getStringInput("Enter new address: "));
                        case 8 -> doctor.setSpecialization(InputHelper.getStringInput("Enter new specialization: "));
                        case 9 -> doctor.setQualification(InputHelper.getStringInput("Enter new qualification: "));
                        case 10 -> doctor.setDepartmentId(InputHelper.getStringInput("Enter new Department ID: "));
                        case 11 -> doctor.setExperienceYears(InputHelper.getIntInput("Enter new experience years: "));
                        case 12 -> doctor.setConsultationFee(InputHelper.getDoubleInput("Enter new consultation fee: "));
                        case 13 -> {
                            List<String> newSlots = new ArrayList<>();
                            boolean addMore = true;
                            int slotNumber = 1;
                            while (addMore) {
                                String slot = InputHelper.getStringInput("Enter available slot #" + slotNumber + ": ");
                                newSlots.add(slot);
                                slotNumber++;
                                String exitFlag = InputHelper.getStringInput("Press 'q' to stop or Enter to add more: ");
                                if (exitFlag.equalsIgnoreCase("q")) addMore = false;
                            }
                            doctor.setAvailableSlots(newSlots);
                        }
                        case 14 -> {
                            List<String> newAssignedPatients = new ArrayList<>();
                            boolean addMore = true;
                            int patientNumber = 1;
                            while (addMore) {
                                String patient = InputHelper.getStringInput("Enter assigned patient #" + patientNumber + ": ");
                                newAssignedPatients.add(patient);
                                patientNumber++;
                                String exitFlag = InputHelper.getStringInput("Press 'q' to stop or Enter to add more: ");
                                if (exitFlag.equalsIgnoreCase("q")) addMore = false;
                            }
                            doctor.setAssignedPatients(newAssignedPatients);
                        }
                        case 15 -> {
                            continueEditing = false;
                            System.out.println("Exiting edit mode...");
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }

                System.out.println(" Doctor details updated successfully!");
                return;
            }
        }

        System.out.println(" Doctor with ID " + doctorId + " not found.");
    }


    public static boolean removeDoctor(String doctorId) {
        for (int i = 0; i < ListOfDoctors.size(); i++) {
            Doctor D = ListOfDoctors.get(i);
            if (HelperUtils.isNotNull(D.getDoctorId()) && D.getDoctorId().equals(doctorId)) {
                ListOfDoctors.remove(i); // remove by index
                System.out.println("Doctor with ID " + doctorId + " removed successfully!");
                return true;
            }
        }
        System.out.println("Doctor  with ID " + doctorId + " not found.");
        return false;
    }

    public static Doctor getDoctorById(String doctorId) {
        for (Doctor D : ListOfDoctors) {
            if (HelperUtils.isNotNull(D.getDoctorId()) && D.getDoctorId().equals(doctorId)) {
                return D;
            }
        }
        System.out.println("Doctor with ID " + doctorId + " not found.");
        return null;
    }

    public static void getDoctorsBySpecialization(String specialization) {
        boolean found = false;

        for (Doctor d : ListOfDoctors) {
            if (HelperUtils.isNotNull(d.getSpecialization()) && d.getSpecialization().toLowerCase().contains(specialization.toLowerCase())) {
                d.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No doctors found with specialization: " + specialization);
        }
    }


    public static void displayAllDoctor() {
        if (ListOfDoctors.isEmpty()) {
            System.out.println("No doctors to display.");
            return;
        }

        for (Doctor p : ListOfDoctors) {
            p.displayInfo();
        }
    }

    public static void getAvailableDoctors() {
        boolean found = false;

        for (Doctor d : ListOfDoctors) {
            if (d.isAvailable()) {
                d.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available doctors at the moment.");
        }
    }

    public Doctor addDoctor(String name, String specialization, String phone) {
        Doctor doctor = new Doctor();
        doctor.setId(HelperUtils.generateId(7));
        doctor.setDoctorId(HelperUtils.generateId("Dr", 5));
        doctor.setFirstName(name);
        doctor.setSpecialization(specialization);
        doctor.setPhoneNumber(phone);
        doctor.setAvailable(true);
        ListOfDoctors.add(doctor);
        System.out.println("Doctor added successfully .");
        return doctor;
    }


    public Doctor addDoctor(String name, String specialization, String phone, double consultationFee) {
        Doctor doctor = new Doctor();
        doctor.setId(HelperUtils.generateId(7));
        doctor.setDoctorId(HelperUtils.generateId("Dr", 5));
        doctor.setFirstName(name);
        doctor.setSpecialization(specialization);
        doctor.setPhoneNumber(phone);
        doctor.setConsultationFee(consultationFee);
        doctor.setAvailable(true);
        ListOfDoctors.add(doctor);
        System.out.println("Doctor added successfully (with consultation fee).");
        return doctor;
    }

    public static Consultant addConsultant() {
        Consultant consultant = new Consultant();
        Doctor baseDr = DoctorService.addDoctor();
        consultant.setId(baseDr.getId());
        consultant.setDoctorId(baseDr.getDoctorId());
        consultant.setFirstName(baseDr.getFirstName());
        consultant.setLastName(baseDr.getLastName());
        consultant.setDateOfBirth(baseDr.getDateOfBirth());
        consultant.setGender(baseDr.getGender());
        consultant.setPhoneNumber(baseDr.getPhoneNumber());
        consultant.setEmail(baseDr.getEmail());
        consultant.setAddress(baseDr.getAddress());
        consultant.setSpecialization(baseDr.getSpecialization());
        consultant.setQualification(baseDr.getQualification());
        consultant.setExperienceYears(baseDr.getExperienceYears());
        consultant.setDepartmentId(baseDr.getDepartmentId());
        consultant.setConsultationFee(baseDr.getConsultationFee());
        consultant.setAvailableSlots(baseDr.getAvailableSlots());
        consultant.setAssignedPatients(baseDr.getAssignedPatients());
        // Add doctor information first
        DoctorService.addDoctor();
        consultant.setConsultationDuration(InputHelper.getIntInput("Enter consultation duration (in minutes)"));

        boolean onlineInput = InputHelper.getConfirmation("Is online consultation available?");
        consultant.setOnlineConsultationAvailable(onlineInput);
        boolean Input = true;
        int assignedNumber = 1;
        List<String> consultationtype = new LinkedList<>();

        while (Input) {

            String record = InputHelper.getStringInput("Enter consultation type #" + assignedNumber);
            consultationtype.add(record);
            assignedNumber++;

            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press Enter to add another");
            if (exitFlag.equalsIgnoreCase("q")) {
                Input = false;
            }
        }
        consultant.setConsultationTypes(consultationtype);
        consultant.setAvailable(true);
        ListOfDoctors.add(consultant);

        System.out.println("Consultant added successfully!");
        return consultant;
    }


    public static GeneralPractitioner addGeneralPractitioner() {
        GeneralPractitioner gp = new GeneralPractitioner();
        DoctorService.addDoctor();
        Doctor baseDr = DoctorService.addDoctor();
        gp.setId(baseDr.getId());
        gp.setDoctorId(baseDr.getDoctorId());
        gp.setFirstName(baseDr.getFirstName());
        gp.setLastName(baseDr.getLastName());
        gp.setDateOfBirth(baseDr.getDateOfBirth());
        gp.setGender(baseDr.getGender());
        gp.setPhoneNumber(baseDr.getPhoneNumber());
        gp.setEmail(baseDr.getEmail());
        gp.setAddress(baseDr.getAddress());
        gp.setSpecialization(baseDr.getSpecialization());
        gp.setQualification(baseDr.getQualification());
        gp.setExperienceYears(baseDr.getExperienceYears());
        gp.setDepartmentId(baseDr.getDepartmentId());
        gp.setConsultationFee(baseDr.getConsultationFee());
        gp.setAvailableSlots(baseDr.getAvailableSlots());
        gp.setAssignedPatients(baseDr.getAssignedPatients());
        boolean walkinInput = InputHelper.getConfirmation("Is walk-in consultation available?");
        gp.setWalkinAvailable(walkinInput);
        boolean homeVisitInput = InputHelper.getConfirmation("Is home visit available?:");
        gp.setHomeVisitAvailable(homeVisitInput);
        boolean vaccinationInput = InputHelper.getConfirmation("Is vaccination certified?");
        gp.setVaccinationCertified(vaccinationInput);
        gp.setAvailable(true);
        ListOfDoctors.add(gp);
        System.out.println("General Practitioner added successfully.");
        return gp;
    }

    public static Surgeon addSurgeon() {
        Surgeon surgeon = new Surgeon();

        Doctor baseDr = DoctorService.addDoctor();
        surgeon.setId(baseDr.getId());
        surgeon.setDoctorId(baseDr.getDoctorId());
        surgeon.setFirstName(baseDr.getFirstName());
        surgeon.setLastName(baseDr.getLastName());
        surgeon.setDateOfBirth(baseDr.getDateOfBirth());
        surgeon.setGender(baseDr.getGender());
        surgeon.setPhoneNumber(baseDr.getPhoneNumber());
        surgeon.setEmail(baseDr.getEmail());
        surgeon.setAddress(baseDr.getAddress());
        surgeon.setSpecialization(baseDr.getSpecialization());
        surgeon.setQualification(baseDr.getQualification());
        surgeon.setExperienceYears(baseDr.getExperienceYears());
        surgeon.setDepartmentId(baseDr.getDepartmentId());
        surgeon.setConsultationFee(baseDr.getConsultationFee());
        surgeon.setAvailableSlots(baseDr.getAvailableSlots());
        surgeon.setAssignedPatients(baseDr.getAssignedPatients());
        surgeon.setSurgeriesPerformed(InputHelper.getIntInput("Enter number of surgeries performed"));
        boolean continueInput = true;
        int surgeryTypes = 1;
        List<String> inputList = new LinkedList<>();
        while (continueInput) {
            String s = InputHelper.getStringInput("Enter available surgeryTypes #" + surgeryTypes);
            inputList.add(s);
            surgeryTypes++;

            System.out.println();
            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press any letter to add another");
            if (exitFlag.equalsIgnoreCase("q")) {
                continueInput = false;
            }
        }
        surgeon.setSurgeryTypes(inputList);


        boolean accessInput = InputHelper.getConfirmation("Operation theatre access?");
        surgeon.setOperationTheatreAccess(accessInput);

        surgeon.setAvailable(true);
        //ListOfDoctors.add(surgeon);
        return surgeon;
    }


    public Doctor assignPatient(String doctorId, String patientId) {
        for (Doctor doctor : ListOfDoctors) {
            if (HelperUtils.isNotNull(doctor.getDoctorId()) && doctor.getDoctorId().toString().equals(doctorId)) {
                List<String> assigned = doctor.getAssignedPatients();
                if (assigned == null) {
                    assigned = new ArrayList<>();
                }
                assigned.add(patientId);
                doctor.setAssignedPatients(assigned);
                System.out.println("Assigned patient " + patientId + " to doctor " + doctor.getFirstName());
                return doctor;
            }
        }
        System.out.println("Doctor not found with ID " + doctorId);
        return null;
    }


    public Doctor assignPatient(Doctor doctor, Patient patient) {
        if (HelperUtils.isNull(doctor) || HelperUtils.isNull(patient)) {
            System.out.println("Doctor or patient is null.");
            return null;
        }
        List<String> assigned = doctor.getAssignedPatients();
        if (assigned == null) {
            assigned = new ArrayList<>();
        }
        assigned.add(patient.getPatientId().toString());
        doctor.setAssignedPatients(assigned);
        System.out.println("Assigned patient " + patient.getFirstName() + " to doctor " + doctor.getFirstName());
        return doctor;
    }

    public Doctor assignPatient(String doctorId, List<String> patientIds) {
        for (Doctor doctor : ListOfDoctors) {
            if (HelperUtils.isNotNull(doctor.getDoctorId()) && doctor.getDoctorId().toString().equals(doctorId)) {
                List<String> assigned = doctor.getAssignedPatients();
                if (assigned == null) {
                    assigned = new ArrayList<>();
                }
                assigned.addAll(patientIds);
                doctor.setAssignedPatients(assigned);
                System.out.println("Assigned multiple patients to doctor " + doctor.getFirstName());
                return doctor;
            }
        }
        System.out.println("Doctor not found with ID " + doctorId);
        return null;
    }

    public Doctor displayDoctors(String specialization) {
        boolean found = false;
        for (Doctor doctor : ListOfDoctors) {
            if (HelperUtils.isNotNull(doctor.getSpecialization()) && doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                doctor.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No doctors found with specialization: " + specialization);
        }
        return null;
    }

    public Doctor displayDoctors(String departmentId, boolean showAvailableOnly) {
        boolean found = false;
        for (Doctor doctor : ListOfDoctors) {
            if (HelperUtils.isNotNull(doctor.getDepartmentId()) && doctor.getDepartmentId().toString().equals(departmentId)) {
                if (!showAvailableOnly || doctor.isAvailable()) {
                    doctor.displayInfo();
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No doctors found in department " + departmentId + (showAvailableOnly ? " (available only)" : ""));
        }
        return null;
    }

    @Override
    public void add(Object entity) {
        if (entity instanceof Doctor) {
            save((Doctor) entity);
        } else {
            System.out.println("Invalid entity Expected Doctor.");
        }

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void getAll() {
        displayAllDoctor();

    }

    @Override
    public void search(String keyword) {
        boolean found = false;
        String k = keyword == null ? "" : keyword.toLowerCase();
        for (Doctor d : ListOfDoctors) {
            if ((d.getFirstName() != null && d.getFirstName().toLowerCase().contains(k)) ||
                    (d.getLastName() != null && d.getLastName().toLowerCase().contains(k)) ||
                    (d.getSpecialization() != null && d.getSpecialization().toLowerCase().contains(k))) {
                d.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No doctors matched the keyword: " + keyword);
        }

    }

    @Override
    public void searchById(String id) {
        if (HelperUtils.isNull(id)) {
            System.out.println("Invalid ID. Please enter a valid Doctor ID");
            return;
        }
        boolean found = false;
        for (Doctor b : ListOfDoctors) {
            if (b.getDoctorId().equals(id)) {
                b.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Doctor found for Doctor ID: " + id);
        }
    }


    public static void generateDoctorPerformanceReport() {
        if (ListOfDoctors.isEmpty()) {
            System.out.println("No doctors available to generate report.");
            return;
        }

        System.out.println("===== Doctor Performance Report =====");

        for (Doctor doctor : ListOfDoctors) {
            System.out.println("Doctor: " + doctor.getFirstName() + " " + doctor.getLastName());
            System.out.println("Specialization: " + doctor.getSpecialization());

            List<String> patients = doctor.getAssignedPatients();
            int totalPatients = (patients != null) ? patients.size() : 0;
            System.out.println("Assigned Patients: " + totalPatients);

            double consultationFee = doctor.getConsultationFee();
            double totalEarnings = consultationFee * totalPatients;
            System.out.println("Consultation Fee: " + consultationFee);
            System.out.println("Total Earnings: " + totalEarnings);

            // If the doctor is a surgeon, include surgeries performed
            if (doctor instanceof Surgeon surgeon) {
                System.out.println("Surgeries Performed: " + surgeon.getSurgeriesPerformed());
            }

            System.out.println("-----------------------------------");
        }

        System.out.println("===== End of Report =====\n");
    }

    public static void addSampleDoctors() {
        for (int i = 0; i < 3; i++) {
            Doctor doctor = new Doctor();
            doctor.setId(HelperUtils.generateId(7));
            doctor.setDoctorId(HelperUtils.generateId("Dr", 5));
            doctor.setFirstName("muna" + (i + 1));
            doctor.setLastName("hashmia" + (i + 1));
            doctor.setEmail("muna" + (i + 1) + "@gmail.com");
            doctor.setDateOfBirth(LocalDate.of(1980, 5, 12).plusYears(i));
            doctor.setAddress("123 muscat St, City" + (i + 1));
            doctor.setGender("F");
            doctor.setQualification("MBBS");
            doctor.setExperienceYears(5 + i);
            doctor.setDepartmentId(HelperUtils.generateId("Dep", 5));
            doctor.setSpecialization("Cardiology" + (i + 1));
            doctor.setPhoneNumber("968-9566690" + (i + 1));
            doctor.setConsultationFee(100.0 + (i * 50));
            doctor.setAvailable(i % 2 == 0); // Alternate availability
            ListOfDoctors.add(doctor);
        }
        // Adding sample Surgeons
        for (int i = 0; i < 3; i++) {
            Surgeon surgeon = new Surgeon();
            surgeon.setId(HelperUtils.generateId(7));
            surgeon.setDoctorId(HelperUtils.generateId("Dr", 5));
            surgeon.setFirstName("sara" + (i + 1));
            surgeon.setLastName("al salmai" + (i + 1));
            surgeon.setEmail("sara" + (i + 1) + "@gmail.com");
            surgeon.setDateOfBirth(LocalDate.of(1980, 5, 12).plusYears(i));
            surgeon.setAddress(" muscat St, City" + (i + 1));
            surgeon.setGender("F");
            List<String> surgeryTypes = new ArrayList<>();
            surgeryTypes.add("Cardiac Surgery");
            surgeryTypes.add("Neurosurgery");
            surgeryTypes.add("Orthopedic Surgery");
            surgeon.setSurgeryTypes(surgeryTypes);
            surgeon.setOperationTheatreAccess(false);
            surgeon.setQualification("MBBS, MS");
            surgeon.setExperienceYears(5 + i);
            surgeon.setDepartmentId(HelperUtils.generateId("Dep", 5));
            surgeon.setSpecialization("Orthopedic Surgery" + (i + 1));
            surgeon.setPhoneNumber("968-9566660" + (i + 1));
            surgeon.setConsultationFee(100.0 + (i * 50));
            surgeon.setAvailable(i % 2 == 0); // Alternate availability
            surgeon.setConsultationFee(200.0);
            surgeon.setSurgeriesPerformed(50);
            surgeon.setAvailable(true);
            ListOfDoctors.add(surgeon);

        }
        // Adding sample consultants
        for (int i = 0; i < 3; i++) {
            Consultant consultant = new Consultant();
            consultant.setId(HelperUtils.generateId(7));
            consultant.setDoctorId(HelperUtils.generateId("Dr", 5));
            consultant.setFirstName("lina" + (i + 1));
            consultant.setLastName("al farsi" + (i + 1));
            consultant.setEmail("lina" + (i + 1) + "@gmail.com");
            consultant.setDateOfBirth(LocalDate.of(1980, 5, 12).plusYears(i));
            consultant.setAddress(" muscat St, City" + (i + 1));
            consultant.setGender("F");
            List<String> consultationTypes = new ArrayList<>();
            consultationTypes.add("In-person");
            consultationTypes.add("Online");
            consultant.setConsultationTypes(consultationTypes);
            consultant.setOnlineConsultationAvailable(true);
            consultant.setQualification("MBBS, MD");
            consultant.setExperienceYears(5 + i);
            consultant.setDepartmentId(HelperUtils.generateId("Dep", 5));
            consultant.setSpecialization("Neurology" + (i + 1));
            consultant.setPhoneNumber("968-9566670" + (i + 1));
            consultant.setConsultationFee(150.0 + (i * 50));
            consultant.setAvailable(i % 2 == 0);
            consultant.setConsultationDuration(30);
            consultant.setAvailable(true);
            ListOfDoctors.add(consultant);


        }
    }
}





