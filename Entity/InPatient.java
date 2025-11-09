package Entity;

import Entity.Patient;
import Interface.Billable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class InPatient extends Patient implements Billable {
    LocalDate admissionDate ;
    LocalDate dischargeDate ;
    String roomNumber ;
    String bedNumber ;
    String  admittingDoctorId ;
    double dailyCharges ;

    public void InPatient(LocalDate admissionDate, LocalDate dischargeDate, String roomNumber, String bedNumber, String admittingDoctorId, double dailyCharges) {
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.admittingDoctorId = admittingDoctorId;
        this.dailyCharges = dailyCharges;
    }




    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        if(HelperUtils.isValidDate(admissionDate.toString())){
            this.admissionDate = admissionDate;
        }
        this.admissionDate = admissionDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        if(!HelperUtils.isValidDate(dischargeDate.toString())){
            System.out.println("Invalid Discharge Date");}
        this.dischargeDate = dischargeDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        if (HelperUtils.isValidString(roomNumber)){
            System.out.println("Invalid Room Number");
        }
        this.roomNumber = roomNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        if (HelperUtils.isValidString(bedNumber)){
            System.out.println("Invalid Bed Number");
        }
        this.bedNumber = bedNumber;
    }

    public String getAdmittingDoctorId() {
        return admittingDoctorId;
    }

    public void setAdmittingDoctorId(String admittingDoctorId) {
        if (HelperUtils.isValidString(admittingDoctorId)){
            System.out.println("Invalid Admitting Doctor ID");
        }
        this.admittingDoctorId = admittingDoctorId;
    }

    public double getDailyCharges() {
        return dailyCharges;
    }

    public void setDailyCharges(double dailyCharges) {
        if(HelperUtils.isNegative(dailyCharges)){
            System.out.println("Daily Charges cannot be negative");
        }
        this.dailyCharges = dailyCharges;
    }
    @Override
    public void displayInfo() {
        super.displayInfo(); // display Entity.Patient info first
        System.out.println("===== In Patient Details =====");
        System.out.println("Admission Date: " + admissionDate);
        System.out.println("Discharge Date: " + dischargeDate);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Bed Number: " + bedNumber);
        System.out.println("Admitting Doctor ID: " + admittingDoctorId);
        System.out.println("Daily Charges: $" + dailyCharges);
        System.out.println("==============================");
    }

    // Custom methods
    public void calculateStayDuration() {
        if (HelperUtils.isValidString(admissionDate.toString()) || HelperUtils.isValidString(dischargeDate.toString())) {
            System.out.println("Admission or discharge date not set.");
        } else {
            long days = ChronoUnit.DAYS.between(admissionDate, dischargeDate);
            System.out.println("Stay Duration: " + days + " days");

        }
    }

    public void calculateTotalCharges() {
        if (HelperUtils.isValidString(admissionDate.toString()) || HelperUtils.isValidString(dischargeDate.toString())) {
            System.out.println("Cannot calculate total charges without both dates.");
        } else {
            long days = ChronoUnit.DAYS.between(admissionDate, dischargeDate);
            double total = days * dailyCharges;
            System.out.println("Total Charges: " + total + " OMR");
        }
    }

    @Override
    public void calculateCharges() {
        double totalCharges = dailyCharges * ChronoUnit.DAYS.between(admissionDate, dischargeDate);
        System.out.println("Total Charges: $" + totalCharges);

    }

    @Override
    public void generateBill() {
        System.out.println("===== Patient Bill =====");
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        boolean isPaid = false; // This would typically be determined by payment processing logic
        System.out.println("Status: " + (isPaid ? "Paid" : "Unpaid"));
        System.out.println("========================");

    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of amount: " + amount + " OMR");
        // Payment processing logic would go here



    }
}
