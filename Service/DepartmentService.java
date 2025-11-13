package Service;

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

    public static void editDepartment(String departmentId) {
        Department foundDepartment = null;

        for (Department dept : departmentList) {
            if (dept.getDepartmentId().equalsIgnoreCase(departmentId)) {
                foundDepartment = dept;
                break;
            }
        }

        if (foundDepartment == null) {
            System.out.println(" Department with ID " + departmentId + " not found.");
            return;
        }

        System.out.println("\n=== Edit Department: " + foundDepartment.getDepartmentName() + " ===");
        boolean editing = true;

        while (editing) {
            System.out.println("""
                    Choose what to edit:
                    1 - Department Name
                    2 - Head Doctor ID
                    3 - Bed Capacity
                    4 - Available Beds
                    5 - Exit Editing
                    """);

            int choice = InputHelper.getIntInput("Enter your choice ");

            switch (choice) {
                case 1 -> {
                    String newName = InputHelper.getStringInput("Enter new Department Name ");
                    foundDepartment.setDepartmentName(newName);
                    System.out.println(" Department name updated successfully.");
                }
                case 2 -> {
                    String newHeadDoctorId = InputHelper.getStringInput("Enter new Head Doctor ID");
                    foundDepartment.setHeadDoctorId(newHeadDoctorId);
                    System.out.println(" Head Doctor ID updated successfully.");
                }
                case 3 -> {
                    int newCapacity = InputHelper.getIntInput("Enter new Bed Capacity");
                    foundDepartment.setBedCapacity(newCapacity);
                    System.out.println(" Bed capacity updated successfully.");
                }
                case 4 -> {
                    int newAvailable = InputHelper.getIntInput("Enter new Available Beds ");
                    if (newAvailable > foundDepartment.getBedCapacity()) {
                        System.out.println(" Available beds cannot exceed total bed capacity!");
                    } else {
                        foundDepartment.setAvailableBeds(newAvailable);
                        System.out.println("Available beds updated successfully.");
                    }
                }
                case 5 -> {
                    editing = false;
                    System.out.println("Exiting department editing...");
                }
                default -> System.out.println(" Invalid option. Please try again.");
            }
        }
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
        Entity.Nurse foundNurse = null;
        for (Entity.Nurse nurse : NurseService.ListOfNurse) {
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
            if (HelperUtils.isNotNull(dept.getDepartmentId() ) && dept.getDepartmentId().toString().equals(departmentId)) {
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
            if (HelperUtils.isNotNull(dept.getDepartmentId())  && dept.getDepartmentId().equals(departmentId)) {
                foundDepartment = dept;
                break;
            }
        }

        if (HelperUtils.isNull(foundDepartment)) {
            System.out.println("Department with ID " + departmentId + " not found.");
            return;
        }

        List<Nurse> nurses = NurseService.ListOfNurse;
        if (HelperUtils.isNull(nurses)  || nurses.isEmpty()) {
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
    public static void generateDepartmentOccupancyReport(String departmentId) {
        if (departmentList.isEmpty()) {
            System.out.println("No departments available to generate report.");
            return;
        }

        Department foundDept = null;
        for (Department dept : departmentList) {
            if (HelperUtils.isNotNull(dept.getDepartmentId()) && dept.getDepartmentId().equals(departmentId)) {
                foundDept = dept;
                break;
            }
        }

        if (foundDept == null) {
            System.out.println("Department with ID " + departmentId + " not found.");
            return;
        }

        System.out.println("\n===== Department Occupancy Report =====");
        String deptId = foundDept.getDepartmentId();
        String deptName = foundDept.getDepartmentName();
        int totalBeds = foundDept.getBedCapacity();
        int availableBeds = foundDept.getAvailableBeds();
        int occupiedBeds = totalBeds - availableBeds;
        double occupancyRate = (totalBeds > 0) ? ((double) occupiedBeds / totalBeds) * 100 : 0;

        System.out.println("Department ID       : " + deptId);
        System.out.println("Department Name     : " + deptName);
        System.out.println("Total Beds          : " + totalBeds);
        System.out.println("Available Beds      : " + availableBeds);
        System.out.println("Occupied Beds       : " + occupiedBeds);
        System.out.printf("Occupancy Rate      : %.2f%%\n", occupancyRate);
        System.out.println("-----------------------------------");
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
            if ((HelperUtils.isNotNull(d.getDepartmentName()) && d.getDepartmentName().toLowerCase().contains(k))) {
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
        for (int i = 0; i < 4; i++) {
            Department dept = new Department();
            String deptId = HelperUtils.generateId("DEPT", 5);
            dept.setDepartmentId(deptId);
            dept.setDepartmentName("Cardiology" + i);
            dept.setHeadDoctorId(HelperUtils.generateId("DR", 5));
            dept.setBedCapacity(50);
            dept.setAvailableBeds(10);
            dept.setDoctors(new ArrayList<>());
            dept.setNurses(new ArrayList<>());

            departmentList.add(dept);
        }

    }

}








