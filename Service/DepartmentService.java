package Service;

import Entity.Appointment;
import Entity.Department;
import Entity.Doctor;
import Entity.Nurse;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;
import Utils.InputHelper;

import java.util.*;

public class DepartmentService implements Manageable, Searchable {

    public static List<Department> departmentList = new ArrayList<>();


    public static Department addDepartment() {
        Department department = new Department();
        System.out.println("=== Adding Department ===");
        department.setDepartmentId(HelperUtils.generateId("DEPT", 5));
        department.setHeadDoctorId(HelperUtils.generateId("Dr", 5));

        department.setDepartmentName(InputHelper.getStringInput("Enter Department Name"));
        department.setBedCapacity(InputHelper.getIntInput("Enter Bed Capacity "));
        department.setAvailableBeds(InputHelper.getIntInput("Enter Available Beds"));
        return department;
    }


    public static void save(Department department) {
        if (HelperUtils.isNull(department)) {
            System.out.println("Error: Department cannot be null !!  Save operation cancelled.");
        }
        departmentList.add(department);
        System.out.println(" Department added successfully!\n");
    }


    public static void viewDepartmentDetails(String departmentId) {
        if (departmentList.isEmpty()) {
            System.out.println("No departments available.");
            return;
        }

        for (Department dept : departmentList) {
            if (dept.getDepartmentId() != null && dept.getDepartmentId().toString().equals(departmentId)) {
                System.out.println("\n=== Department Details ===");
                System.out.println("Department ID: " + dept.getDepartmentId());
                System.out.println("Name: " + dept.getDepartmentName());
                System.out.println("Bed Capacity: " + dept.getBedCapacity());
                System.out.println("Available Beds: " + dept.getAvailableBeds());
                System.out.println("Head Doctor ID: " + dept.getHeadDoctorId());
                System.out.println("Doctors Assigned: " +
                        (dept.getDoctors() != null ? dept.getDoctors().size() : 0));
                System.out.println("Nurses Assigned: " +
                        (dept.getNurses() != null ? dept.getNurses().size() : 0));
                return;
            }
        }

        System.out.println(" Department with ID " + departmentId + " not found.");
    }

    public static boolean editDepartment(String departmentId, Department updatedDepartment) {
        for (int i = 0; i < departmentList.size(); i++) {
            Department existing = departmentList.get(i);

            if (existing.getDepartmentId() != null && existing.getDepartmentId().equals(departmentId)) {
                updatedDepartment.setDepartmentId(existing.getDepartmentId());
                departmentList.set(i, updatedDepartment);
                System.out.println(" Department updated successfully!");
                return true;
            }
        }

        System.out.println(" Department with ID " + departmentId + " not found.");
        return false;
    }


    public static boolean removeDepartment(String departmentId) {
        for (Department dept : departmentList) {
            if (dept.getDepartmentId().equals(departmentId)) {
                departmentList.remove(dept);
                System.out.println("Department with ID " + departmentId + " removed successfully!");
                return true;
            }
        }
        System.out.println("Department with ID " + departmentId + " not found.");
        return false;
    }


    public static void assignDoctorToDepartment(String doctorId, String departmentId) {
        Doctor foundDoctor = null;
        for (Doctor doctor : DoctorService.ListOfDoctors) {
            if (doctor.getDoctorId().toString().equals(doctorId)) {
                foundDoctor = doctor;
                break;
            }
        }

        if (foundDoctor == null) {
            System.out.println(" Doctor with ID " + doctorId + " not found.");
            return;
        }

        Department foundDepartment = null;
        for (Department dept : departmentList) {
            if (dept.getDepartmentId().toString().equals(departmentId)) {
                foundDepartment = dept;
                break;
            }
        }

        if (foundDepartment == null) {
            System.out.println(" Department with ID " + departmentId + " not found.");
            return;
        }

        if (foundDepartment.getDoctors() == null) {
            foundDepartment.setDoctors(new ArrayList<>());
        }

        foundDepartment.getDoctors().add(foundDoctor);
        System.out.println(" Doctor " + foundDoctor.getFirstName() +
                " assigned to Department " + foundDepartment.getDepartmentName() + ".");
    }

    public static void displayAllDepartments() {
        if (departmentList.isEmpty()) {
            System.out.println(" No departments to display.");
            return;
        }

        System.out.println("\n=== All Departments ===");
        for (Department dept : departmentList) {
            dept.displayInfo();
        }
    }

    public static void assignNurseToDepartment(String nurseId, String departmentId) {

        // Find the nurse from NurseService
        Entity.Nurse foundNurse = null;
        for (Entity.Nurse nurse : NurseService.ListOfNurse) {   // assuming NurseService has a static List<Nurse> nurseList
            if (nurse.getNurseId().toString().equals(nurseId)) {
                foundNurse = nurse;
                break;
            }
        }

        if (foundNurse == null) {
            System.out.println(" Nurse with ID " + nurseId + " not found.");
            return;
        }
        Department foundDepartment = null;
        for (Department dept : departmentList) {
            if (dept.getDepartmentId().toString().equals(departmentId)) {
                foundDepartment = dept;
                break;
            }
        }

        if (foundDepartment == null) {
            System.out.println(" Department with ID " + departmentId + " not found.");
            return;
        }

        // Add nurse to department’s nurse list
        if (foundDepartment.getNurses() == null) {
            foundDepartment.setNurses(new ArrayList<>());
        }

        foundDepartment.getNurses().add(foundNurse);
        System.out.println(" Nurse " + foundNurse.getFirstName() +
                " assigned to Department " + foundDepartment.getDepartmentName() + ".");
    }

    public static void viewDepartmentStatistics(String departmentId) {
        if (departmentList.isEmpty()) {
            System.out.println("No departments available.");
            return;
        }

        Department found = null;
        for (Department dept : departmentList) {
            if (dept.getDepartmentId() != null && dept.getDepartmentId().toString().equals(departmentId)) {
                found = dept;
                break;
            }
        }

        if (found == null) {
            System.out.println("Department with ID " + departmentId + " not found.");
            return;
        }

        int totalBeds = found.getBedCapacity();
        int availableBeds = found.getAvailableBeds();
        int doctorCount = (found.getDoctors() != null) ? found.getDoctors().size() : 0;
        int nurseCount = (found.getNurses() != null) ? found.getNurses().size() : 0;

        double occupancyRate = 0.0;
        if (totalBeds > 0) {
            occupancyRate = ((double) (totalBeds - availableBeds) / totalBeds) * 100;
        }

        System.out.println("\n===== Department Statistics =====");
        System.out.println("Department Name     : " + found.getDepartmentName());
        System.out.println("Total Beds          : " + totalBeds);
        System.out.println("Available Beds      : " + availableBeds);
        System.out.println("Occupancy Rate      : " + String.format("%.2f", occupancyRate) + "%");
        System.out.println("Number of Doctors   : " + doctorCount);
        System.out.println("Number of Nurses    : " + nurseCount);
        System.out.println("=================================\n");
    }

    public static void viewNursesByDepartment(String departmentId) {
        if (departmentList.isEmpty()) {
            System.out.println("No departments available.");
            return;
        }

        Department foundDepartment = null;
        for (Department dept : departmentList) {
            if (dept.getDepartmentId() != null && dept.getDepartmentId().equals(departmentId)) {
                foundDepartment = dept;
                break;
            }
        }

        if (foundDepartment == null) {
            System.out.println("Department with ID " + departmentId + " not found.");
            return;
        }

        List<Nurse> nurses = foundDepartment.getNurses();
        if (nurses == null || nurses.isEmpty()) {
            System.out.println("No nurses assigned to Department " + foundDepartment.getDepartmentName() + ".");
            return;
        }

        System.out.println("\n===== Nurses in Department: " + foundDepartment.getDepartmentName() + " =====");
        for (Nurse nurse : nurses) {
            System.out.println("- Nurse: " + nurse.getFirstName() + " " + nurse.getLastName() +
                    " (ID: " + nurse.getNurseId() + ")");
        }
        System.out.println("=========================================\n");
    }

    //  Generate Department Occupancy Report
    public static void generateDepartmentOccupancyReport() {
        if (departmentList.isEmpty()) {
            System.out.println(" No departments available to generate report.");
            return;
        }

        System.out.println("\n===== Department Occupancy Report =====");

        for (Department dept : departmentList) {
            String deptName = dept.getDepartmentName();
            int totalBeds = dept.getBedCapacity();
            int availableBeds = dept.getAvailableBeds();
            int occupiedBeds = totalBeds - availableBeds;
            double occupancyRate = (totalBeds > 0) ? ((double) occupiedBeds / totalBeds) * 100 : 0;

            System.out.println("Department Name     : " + deptName);
            System.out.println("Total Beds          : " + totalBeds);
            System.out.println("Available Beds      : " + availableBeds);
            System.out.println("Occupied Beds       : " + occupiedBeds);
            System.out.printf("Occupancy Rate      : %.2f%%\n", occupancyRate);
            System.out.println("-----------------------------------");
        }

        System.out.println("===== End of Report =====\n");
    }


    // Interface implementations (not used yet)
    @Override
    public void add(Object entity) {
        if (entity instanceof Department) {
            save((Department) entity);
        } else {
            System.out.println("Invalid entity Expected Department.");
        }
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void getAll() {
        displayAllDepartments();
    }

    @Override
    public void search(String keyword) {
        boolean found = false;
        String k = keyword == null ? "" : keyword.toLowerCase();
        for (Department d : departmentList) {
            if ((d.getDepartmentName() != null && d.getDepartmentName().toLowerCase().contains(k))) {
                d.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No departments matched the keyword: " + keyword);
        }
    }

    @Override
    public void searchById(String id) {
        if (HelperUtils.isNull(id)) {
            System.out.println("Invalid ID. Please enter a valid patient ID.");
            return;
        }
        boolean found = false;
        for (Department b : departmentList) {
            if (b.getDepartmentId().equals(id)) {
                b.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Department found for Department ID: " + id);
        }
    }

    public static void addSampleDepartments() {
        for (int i = 0; i <4 ; i++) {
            Department dept = new Department();
            dept.setDepartmentId("DEPT001");
            dept.setDepartmentName("Cardiology");
            dept.setHeadDoctorId("DR001");
            dept.setBedCapacity(50);
            dept.setAvailableBeds(10);
            List<Doctor> cardioDoctors = Arrays.asList(
                    new Doctor(true, "DR001", "DEPT001"),
                    new Doctor(true, "DR002", "DEPT001"));
            List<Nurse> cardioNurses = Arrays.asList(
                    new Nurse("DEPT001", "NUR001", "Morning"),
                    new Nurse("DEPT001", "NUR002", "Evening"));
            dept.setDoctors(cardioDoctors);
            dept.setNurses(cardioNurses);
            departmentList.add(dept);
        }



        // --- Department 2: Neurology ---
        Department dept2 = new Department();
        dept2.setDepartmentId("DEPT002");
        dept2.setDepartmentName("Neurology");
        dept2.setHeadDoctorId("DR002");
        dept2.setBedCapacity(40);
        dept2.setAvailableBeds(5);

        List<Doctor> neuroDoctors = Arrays.asList(
                new Doctor(false, "DR003", "DEPT002"),
                new Doctor(true, "DR004", "DEPT002"));

        List<Nurse> neuroNurses = Arrays.asList(
                new Nurse("DEPT002", "NUR003", "Night"),
                new Nurse("DEPT002", "NUR004", "Morning"));
        dept2.setDoctors(neuroDoctors);
        dept2.setNurses(neuroNurses);
        departmentList.add(dept2);
        // --- Department 3: Pediatrics ---
        Department dept3 = new Department();
        dept3.setDepartmentId("DEPT003");
        dept3.setDepartmentName("Pediatrics");
        dept3.setHeadDoctorId("DR003");
        dept3.setBedCapacity(60);
        dept3.setAvailableBeds(15);

        List<Doctor> pediDoctors = Arrays.asList(
                new Doctor(true, "DR005", "DEPT003"),
                new Doctor(false, "DR006", "DEPT003"));
        List<Nurse> pediNurses = Arrays.asList(
                new Nurse("DEPT003", "NUR005", "Evening"),
                new Nurse("DEPT003", "NUR006", "Morning"));
        dept3.setDoctors(pediDoctors);
        dept3.setNurses(pediNurses);
        departmentList.add(dept3);

    }

}






