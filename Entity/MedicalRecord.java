package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.Scanner;


public class MedicalRecord implements Displayable {
    Scanner scanner = new Scanner(System.in);
    String recordId;
    String patientId;
    String doctorId;
    LocalDate visitDate;
    String diagnosis;
    String prescription;
    String testResults;
    String notes;

    public MedicalRecord(String recordId, String patientId, String doctorId, LocalDate visitDate, String diagnosis, String prescription, String testResults, String notes) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.testResults = testResults;
        this.notes = notes;
    }

    public MedicalRecord() {

    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }


    public void setVisitDate(LocalDate visitDate) {
        while (visitDate == null || !HelperUtils.isValidDate(visitDate.toString())) {
            System.out.println("Invalid visit date. Please enter a valid date (YYYY-MM-DD): ");
            String input = scanner.nextLine();

            if (HelperUtils.isValidDate(input)) {
                visitDate = LocalDate.parse(input);
            } else {
                System.out.println("Invalid format or value. Try again.");
            }
        }

        this.visitDate = visitDate;
    }


    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        while (!HelperUtils.isValidString(diagnosis)) {
            System.out.println("Invalid diagnosis provided. Please enter a valid diagnosis: ");
            diagnosis = scanner.nextLine();
        }
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }


    public void setPrescription(String prescription) {
        while (!HelperUtils.isValidString(prescription)) {
            System.out.println("Invalid prescription provided. Please enter a valid prescription: ");
            prescription = scanner.nextLine();
        }

        this.prescription = prescription;
    }


    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        while (!HelperUtils.isValidString(testResults)) {
            System.out.println("Invalid test results provided. Please enter valid test results: ");
            testResults = scanner.nextLine();
        }

        this.testResults = testResults;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        while (!HelperUtils.isValidString(notes)) {
            System.out.println("Invalid notes provided. Please enter valid notes: ");
            notes = scanner.nextLine();
        }
        this.notes = notes;
    }

    public void displayInfo() {
        System.out.println("===== Medical Record =====");
        System.out.println("Record ID: " + recordId);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Visit Date: " + visitDate);
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Prescription: " + prescription);
        System.out.println("Test Results: " + testResults);
        System.out.println("Notes: " + notes);
        System.out.println("==========================");
    }

    @Override
    public void displaySummary() {
        System.out.println("Medical Record Summary - Record ID: " + recordId + ", Patient ID: " + patientId + ", Doctor ID: " + doctorId + ", Visit Date: " + visitDate);
    }

}
