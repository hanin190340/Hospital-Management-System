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
    public static List <Doctor> ListOfDoctors =new ArrayList<>() ;


    public static Doctor addDoctor () {
        Doctor doctor = new Doctor();
        doctor.setId(HelperUtils.generateId(7));
        doctor.setDoctorId(HelperUtils.generateId("Dr", 5));
        doctor.setFirstName(InputHelper.getStringInput("Enter the FirstName:"));
        doctor.setLastName(InputHelper.getStringInput("Enter the lastName:"));
        doctor.setDateOfBirth(InputHelper.getDateInput("Enter date of birth (YYYY-MM-DD):"));
        doctor.setGender(InputHelper.getStringInput("Enter gender (M/F): "));
        doctor.setPhoneNumber(InputHelper.getStringInput("Enter phoneNumber: "));
        doctor.setEmail(InputHelper.getStringInput("Enter email: "));
        doctor.setAddress(InputHelper.getStringInput("Enter address : "));
        doctor.setSpecialization(InputHelper.getStringInput(" Enter specialization : "));
        doctor.setQualification(InputHelper.getStringInput(" Enter qualification"));
        doctor.setDepartmentId(HelperUtils.generateId("Dep", 5));
        doctor.setExperienceYears(InputHelper.getIntInput("Enter experience Years"));
        doctor.setConsultationFee(InputHelper.getDoubleInput("Enter consultation Fee: "));
        //---------- availableSlots ----------
        boolean continueInput = true;
        int SlotsNumber = 1;
        List<String> SlotsInput = new LinkedList<>();
        while (continueInput) {
            String slots = InputHelper.getStringInput("Enter available Slots #" + SlotsNumber + ":");
            SlotsInput.add(slots);
            SlotsNumber++;

            String exitFlag =InputHelper.getStringInput("Press 'q' and Enter to stop, or just press Enter to add another:");
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

            String record = InputHelper.getStringInput("Enter assigned Patients #" + assignedNumber + ":");
            assignedInput.add(record);
            assignedNumber++;

            System.out.println();
            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press Enter to add another:");
            if (exitFlag.equalsIgnoreCase("q")) {
                Input = false;
            }
        }
        doctor.setAssignedPatients(assignedInput);


        return doctor;
    }

    public static void save(Doctor doctor) {
        if (HelperUtils.isNull(doctor)){
            System.out.println("Please provide a valid Doctor before saving");
        }
        ListOfDoctors.add(doctor);
        System.out.println("Doctor added successfully!\n");
    }
    public static void save(Surgeon surgeon) {
        if (HelperUtils.isNull(surgeon)){
            System.out.println("Please provide a valid Doctor before saving");
        }
        ListOfDoctors.add(surgeon);
        System.out.println("surgeon added successfully!\n");
    }

    public static boolean editDoctor(String doctorId, Doctor updatedDoctor) {
        for (int i = 0; i < ListOfDoctors.size(); i++) {
            Doctor existingDoctor = ListOfDoctors.get(i);

            if (HelperUtils.isNotNull(existingDoctor.getDoctorId()) && existingDoctor.getDoctorId().equals(doctorId)) {

                updatedDoctor.setDoctorId(existingDoctor.getDoctorId());

                ListOfDoctors.set(i, updatedDoctor);
                System.out.println("Doctor updated successfully!");
                return true;
            }
        }

        System.out.println("Doctor  with ID " + doctorId + " not found.");
        return false;
    }
    public static boolean removeDoctor(String doctorId) {
        for (int i = 0; i < ListOfDoctors.size(); i++) {
            Doctor D = ListOfDoctors.get(i);
            if ( HelperUtils.isNotNull(D.getDoctorId()) && D.getDoctorId().equals(doctorId)) {
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
            if ( HelperUtils.isNotNull(D.getDoctorId())  && D.getDoctorId().equals(doctorId)) {
                return D;
            }
        }
        System.out.println("Doctor with ID " + doctorId + " not found.");
        return null;
    }

    public static void getDoctorsBySpecialization(String specialization) {
        boolean found = false;

        for (Doctor d : ListOfDoctors) {
            if ( HelperUtils.isNotNull(d.getSpecialization())  && d.getSpecialization().toLowerCase().contains(specialization.toLowerCase())) {
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
        consultant.setConsultationDuration(InputHelper.getIntInput("Enter consultation duration (in minutes): "));

        String onlineInput = InputHelper.getStringInput("Is online consultation available? (y/n): ").trim();
        consultant.setOnlineConsultationAvailable(
                onlineInput.equalsIgnoreCase("y") || onlineInput.equalsIgnoreCase("N")
        );
        boolean Input = true;
        int assignedNumber = 1;
        List<String> consultationtype = new LinkedList<>();

        while (Input) {

            String record = InputHelper.getStringInput("Enter consultation type #" + assignedNumber + ":");
            consultationtype.add(record);
            assignedNumber++;

            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press Enter to add another:");
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


    public  static GeneralPractitioner addGeneralPractitioner()  {
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
        String walkinInput =InputHelper.getStringInput("Is walk-in consultation available? (y/n):");
        boolean walkinAvailable = walkinInput.equalsIgnoreCase("y") || walkinInput.equalsIgnoreCase("N");
        gp.setWalkinAvailable(walkinAvailable);
        String homeVisitInput =InputHelper.getStringInput("Is home visit available? (y/n):");
        boolean homeVisitAvailable = homeVisitInput.equalsIgnoreCase("y") || homeVisitInput.equalsIgnoreCase("N");
        gp.setHomeVisitAvailable(homeVisitAvailable);
        String vaccinationInput =InputHelper.getStringInput("Is vaccination certified? (y/n):");
        boolean vaccinationCertified = vaccinationInput.equalsIgnoreCase("y") || vaccinationInput.equalsIgnoreCase("N");
        gp.setVaccinationCertified(vaccinationCertified);

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
        surgeon.setSurgeriesPerformed(InputHelper.getIntInput("Enter number of surgeries performed:"));
        boolean continueInput = true;
        int surgeryTypes = 1;
        List<String> inputList = new LinkedList<>();
        while (continueInput) {
            String s = InputHelper.getStringInput("Enter available surgeryTypes #" + surgeryTypes + ":");
            inputList.add(s);
            surgeryTypes++;

            System.out.println();
            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press Enter to add another:");
            if (exitFlag.equalsIgnoreCase("q")) {
                continueInput = false;
            }
        }
        surgeon.setSurgeryTypes(inputList);


        String accessInput = InputHelper.getStringInput("Operation theatre access? (y/n):");
        boolean operationTheatreAccess = accessInput.equalsIgnoreCase("y") || accessInput.equalsIgnoreCase("n");
        surgeon.setOperationTheatreAccess(operationTheatreAccess);

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
            if ( HelperUtils.isNull(doctor) || HelperUtils.isNull(patient)) {
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
            System.out.println("Invalid ID. Please enter a valid Doctor ID.");
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

}




