package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.util.List;
import java.util.Scanner;

public class Department implements Displayable {
    Scanner scanner = new Scanner(System.in);
    String departmentId;
    String departmentName;
    String headDoctorId;
    List<Doctor> doctors;
    List<Nurse> nurses;
    int bedCapacity;
    int availableBeds;

    public Department() {

    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getHeadDoctorId() {
        return headDoctorId;
    }

    public Department(String departmentId, String departmentName, String headDoctorId, List<Doctor> doctors, List<Nurse> nurses, int bedCapacity, int availableBeds) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.headDoctorId = headDoctorId;
        this.doctors = doctors;
        this.nurses = nurses;
        this.bedCapacity = bedCapacity;
        this.availableBeds = availableBeds;
    }

    public void setHeadDoctorId(String headDoctorId) {
        this.headDoctorId = headDoctorId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        while (!HelperUtils.isValidString(departmentName)) {
            System.out.println("Invalid department name. It cannot be null or empty.");
            departmentName = scanner.nextLine();

        }
        this.departmentName = departmentName;
    }


    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public int getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(int bedCapacity) {
        while (HelperUtils.isNegative(bedCapacity)) {
            System.out.println("Bed capacity cannot be negative.");
            bedCapacity = scanner.nextInt();

        }
        this.bedCapacity = bedCapacity;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        while (HelperUtils.isNegative(availableBeds) || availableBeds > bedCapacity) {
            System.out.println("Available beds cannot be negative or exceed bed capacity.");
            availableBeds = scanner.nextInt();

        }
        this.availableBeds = availableBeds;
    }

    public void displayInfo() {

        System.out.println("===== Department Details =====");
        System.out.println("Department ID: " + departmentId);
        System.out.println("Department Name: " + departmentName);
        System.out.println("Head Doctor ID: " + headDoctorId);

        System.out.println("Doctors in Department:");
        if (doctors == null || doctors.isEmpty()) {
            System.out.println(" - No doctors assigned");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(" -Doctor ID: " + doctor.getDoctorId() +
                        ", Specialization: " + doctor.getSpecialization());
            }
        }

        System.out.println("Nurses in Department:");
        if (nurses == null || nurses.isEmpty()) {
            System.out.println(" - No nurses assigned");
        } else {
            for (Nurse nurse : nurses) {
                System.out.println(" - Nurse ID: " + nurse.getNurseId() +
                        ", Shift: " + nurse.getShift());
            }
        }

        System.out.println("Bed Capacity: " + bedCapacity);
        System.out.println("Available Beds: " + availableBeds);
        System.out.println("================================");
    }

    @Override
    public void displaySummary() {
        System.out.println("Department ID: " + departmentId + ", Name: " + departmentName +
                ", Head Doctor ID: " + headDoctorId);
    }


}

