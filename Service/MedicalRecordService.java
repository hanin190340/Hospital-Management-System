package Service;


import Entity.MedicalRecord;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;
import Utils.InputHelper;

import java.time.LocalDate;
import java.util.*;

public class MedicalRecordService implements Manageable, Searchable {
    public static List<MedicalRecord> medicalRecordList = new ArrayList<>();


    public static MedicalRecord addMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord();
        System.out.println("Adding MedicalRecord");
        medicalRecord.setRecordId(HelperUtils.generateId("MR", 6));
        medicalRecord.setPatientId(HelperUtils.generateId("PT", 5));
        medicalRecord.setDoctorId(HelperUtils.generateId("DR", 5));
        medicalRecord.setDiagnosis(InputHelper.getStringInput("Enter the diagnosis"));
        System.out.println();
        medicalRecord.setPrescription(InputHelper.getStringInput("Enter the prescription "));
        System.out.println();
        LocalDate date = InputHelper.getDateInput("Enter visit Date ");
        medicalRecord.setVisitDate(date);
        medicalRecord.setTestResults(InputHelper.getStringInput("Enter test Results"));
        medicalRecord.setNotes(InputHelper.getStringInput("Enter notes "));
        return medicalRecord;
    }

    public static void save(MedicalRecord medicalRecord) {
        if (HelperUtils.isNull(medicalRecord)) {
            System.out.println("Cannot save a null medicalRecord.");
            return;
        }
        medicalRecordList.add(medicalRecord);
        System.out.println("medicalRecord added successfully!\n");
    }

    public static void generatePatientHistoryReport(String patientId) {
        System.out.println("===== Patient History Report =====");

        if (medicalRecordList.isEmpty()) {
            System.out.println("No medical records found.");
            return;
        }

        boolean found = false;
        for (MedicalRecord record : medicalRecordList) {
            if (record.getPatientId() != null && record.getPatientId().toString().equals(patientId)) {
                System.out.println("Record ID: " + record.getRecordId());
                System.out.println("Doctor ID: " + record.getDoctorId());
                System.out.println("Visit Date: " + record.getVisitDate());
                System.out.println("Diagnosis: " + record.getDiagnosis());
                System.out.println("Prescription: " + record.getPrescription());
                System.out.println("Test Results: " + record.getTestResults());
                System.out.println("Notes: " + record.getNotes());
                System.out.println("----------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No medical records found for patient ID: " + patientId);
        } else {
            System.out.println("===== End of Patient History Report =====");
        }
    }


    public static void editMedicalRecord(String medicalRecordId) {
        MedicalRecord foundRecord = null;
        for (MedicalRecord record : medicalRecordList) {
            if (record.getRecordId().equalsIgnoreCase(medicalRecordId)) {
                foundRecord = record;
                break;
            }
        }
        if (foundRecord == null) {
            System.out.println(" Medical Record with ID " + medicalRecordId + " not found.");
            return;
        }

        boolean editing = true;

        while (editing) {
            System.out.println("\n=== Edit Medical Record: " + foundRecord.getRecordId() + " ===");
            System.out.println("""
                Choose what to edit:
                1 - Diagnosis
                2 - Prescription
                3 - Visit Date
                4 - Test Results
                5 - Notes
                6 - Exit Editing
                """);

            int choice = InputHelper.getIntInput("Enter your choice ");

            switch (choice) {
                case 1 -> {
                    String newDiagnosis = InputHelper.getStringInput("Enter new diagnosis ");
                    foundRecord.setDiagnosis(newDiagnosis);
                    System.out.println("Diagnosis updated successfully.");
                }
                case 2 -> {
                    String newPrescription = InputHelper.getStringInput("Enter new prescription ");
                    foundRecord.setPrescription(newPrescription);
                    System.out.println("Prescription updated successfully.");
                }
                case 3 -> {
                    LocalDate newDate = InputHelper.getDateInput("Enter new visit date ");
                    foundRecord.setVisitDate(newDate);
                    System.out.println(" Visit date updated successfully.");
                }
                case 4 -> {
                    String newTestResults = InputHelper.getStringInput("Enter new test results ");
                    foundRecord.setTestResults(newTestResults);
                    System.out.println("Test results updated successfully.");
                }
                case 5 -> {
                    String newNotes = InputHelper.getStringInput("Enter new notes: ");
                    foundRecord.setNotes(newNotes);
                    System.out.println("Notes updated successfully.");
                }
                case 6 -> {
                    editing = false;
                    System.out.println("Exiting medical record editing...");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }


    public static boolean removeMedicalRecord(String recordId) {
        for (int i = 0; i < medicalRecordList.size(); i++) {
            MedicalRecord p = medicalRecordList.get(i);
            if (HelperUtils.isNotNull(p.getRecordId()) && p.getRecordId().equals(recordId)) {
                medicalRecordList.remove(i);
                System.out.println("MedicalRecord with ID " + recordId + " removed successfully!");
                return true;
            }
        }
        System.out.println("MedicalRecord with ID " + recordId + " not found.");
        return false;
    }

    public static void getRecordsByPatientId(String patientId) {
        boolean found = false;

        for (MedicalRecord p : medicalRecordList) {
            if (HelperUtils.isNotNull(p.getPatientId()) && p.getPatientId().equals(patientId)) {
                p.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Patient with ID " + patientId + " not found.");
        }
    }

    public static void getRecordsByDoctorId(String doctorId) {
        boolean found = false;
        for (MedicalRecord p : medicalRecordList) {
            if (HelperUtils.isNotNull(p.getDoctorId()) && p.getDoctorId().equals(doctorId)) {
                p.displayInfo();
            }
        }
        if (!found) {
            System.out.println("Doctor with ID " + doctorId + " not found.");
        }
    }
    public static void displayPatientHistory(String patientId) {
        if (medicalRecordList.isEmpty()) {
            System.out.println("No patients to display.");
            return;
        }

        for (MedicalRecord p : medicalRecordList) {
            p.displayInfo();
        }
    }


    @Override
    public void add(Object entity) {
        if (entity instanceof MedicalRecord) {
            MedicalRecord medicalRecord = (MedicalRecord) entity;
            save(medicalRecord);
        } else {
            System.out.println("Invalid entity type Expected MedicalRecord.");
        }

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void getAll() {
        displayAllMedicalRecord();

    }

    @Override
    public void search(String keyword) {
        if (HelperUtils.isNull(keyword)) {
            System.out.println("Invalid keyword. Please enter a valid search keyword.");
            return;
        }
        boolean found = false;
        String k = keyword.toLowerCase();
        for (MedicalRecord b : medicalRecordList) {
            if ((HelperUtils.isNotNull(b.getDiagnosis())  && b.getDiagnosis().toLowerCase().contains(k)) ||
                (HelperUtils.isNotNull(b.getPrescription())  && b.getPrescription().toLowerCase().contains(k)) ||
                (HelperUtils.isNotNull(b.getTestResults())  && b.getTestResults().toLowerCase().contains(k)) ||
                (HelperUtils.isNotNull(b.getNotes())  && b.getNotes().toLowerCase().contains(k))) {
                b.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Records matched the keyword: " + keyword);
        }

    }

    @Override
    public void searchById(String id) {
        if (HelperUtils.isNull(id)) {
            System.out.println("Invalid ID. Please enter a valid medical Record ID.");
            return;
        }
        boolean found = false;
        for (MedicalRecord b : medicalRecordList) {
            if (b.getRecordId().equals(id)) {
                b.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Record found for medical Record ID: " + id);
        }

    }

    public static void displayAllMedicalRecord() {
        if (medicalRecordList.isEmpty()) {
            System.out.println("No medical Record to display.");
            return;
        }

        for (MedicalRecord p : medicalRecordList) {
            p.displayInfo();
        }
    }
    public static void addSampleMedicalRecord() {
        for (int i = 0; i < 12; i++) {
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setRecordId(HelperUtils.generateId("MR", 5));
            medicalRecord.setPatientId(HelperUtils.generateId("PT" , 5));
            medicalRecord.setDoctorId(HelperUtils.generateId("DR" ,  5));
            medicalRecord.setVisitDate(LocalDate.now().minusDays(i * 7)); // weekly visits
            medicalRecord.setDiagnosis("Follow-up check for blood pressure – visit #" + (i + 1));
            medicalRecord.setTestResults("Blood pressure reading: " + (110 + i) + "/" + (70 + i / 2) + " mmHg. Heart rate normal.");
            medicalRecord.setPrescription("Continue prescribed medication and maintain low-salt diet.");
            medicalRecord.setNotes("Patient condition improving steadily. Next visit scheduled after one week.");
            medicalRecordList.add(medicalRecord);

        }

        }



    }



