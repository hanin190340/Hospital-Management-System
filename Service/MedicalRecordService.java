package Service;

import Entity.Appointment;
import Entity.Doctor;
import Entity.MedicalRecord;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;
import Utils.InputHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        System.out.println("Enter notes ");
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


    public static boolean editMedicalRecord(String medicalRecordId, MedicalRecord updatedMedicalRecord) {
        for (int i = 0; i < medicalRecordList.size(); i++) {
            MedicalRecord existingRecord = medicalRecordList.get(i);

            if (HelperUtils.isNotNull( existingRecord.getRecordId()) && existingRecord.getRecordId().equals(medicalRecordId)) {

                updatedMedicalRecord.setPatientId(existingRecord.getRecordId());

                // Replace old medicalRecord with updated one
                medicalRecordList.set(i, updatedMedicalRecord);
                System.out.println("Medical Record updated successfully!");
                return true;
            }
        }

        System.out.println("MedicalRecord with ID " + medicalRecordId + " not found.");
        return false;
    }

    public static boolean removeMedicalRecord(String recordId) {
        for (int i = 0; i < medicalRecordList.size(); i++) {
            MedicalRecord p = medicalRecordList.get(i);
            if (HelperUtils.isNotNull(p.getRecordId()) && p.getRecordId().equals(recordId)) {
                medicalRecordList.remove(i); // remove by index
                System.out.println("MedicalRecord with ID " + recordId + " removed successfully!");
                return true;
            }
        }
        System.out.println("MedicalRecord with ID " + recordId + " not found.");
        return false;
    }

    public static MedicalRecord getRecordsByPatientId(String patientId) {
        for (MedicalRecord p : medicalRecordList) {
            if (p.getPatientId() != null && p.getPatientId().equals(patientId)) {
                return p;
            }
        }
        System.out.println("Patient with ID " + patientId + " not found.");
        return null;
    }

    public static MedicalRecord getRecordsByDoctorId(String doctorId) {
        for (MedicalRecord p : medicalRecordList) {
            if ( HelperUtils.isNotNull(p.getDoctorId())  && p.getDoctorId().equals(doctorId)) {
                return p;
            }
        }
        System.out.println("Doctor with ID " + doctorId + " not found.");
        return null;
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
            if ((b.getDiagnosis() != null && b.getDiagnosis().toLowerCase().contains(k)) ||
                (b.getPrescription() != null && b.getPrescription().toLowerCase().contains(k)) ||
                (b.getTestResults() != null && b.getTestResults().toLowerCase().contains(k)) ||
                (b.getNotes() != null && b.getNotes().toLowerCase().contains(k))) {
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
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setRecordId(HelperUtils.generateId("MR", 5));
            medicalRecord.setPatientId(HelperUtils.generateId("PT" , 5));
            medicalRecord.setDoctorId(HelperUtils.generateId("DR" ,  5));
            medicalRecord.setVisitDate(LocalDate.now().minusDays(i * 7)); // weekly visits
            medicalRecord.setDiagnosis("Diagnosis example " + (i + 1));
            medicalRecord.setPrescription("Prescription example " + (i + 1));
            medicalRecord.setTestResults("Test results example " + (i + 1));
            medicalRecord.setNotes("Notes for visit " + (i + 1));
            medicalRecords.add(medicalRecord);

        }

    }

}


