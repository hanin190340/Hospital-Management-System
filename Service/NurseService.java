package Service;

import Entity.Nurse;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;
import Utils.InputHelper;

import java.time.LocalDate;
import java.util.*;

public class NurseService implements Manageable, Searchable {
    public static List<Nurse> ListOfNurse = new ArrayList<>();


    public static Nurse assignPatientToNurse(String nurseId, String patientName) {
        for (Nurse n : ListOfNurse) {
            if (HelperUtils.isNotNull(n.getNurseId()) && n.getNurseId().equals(nurseId)) {
                List<String> assigned = n.getAssignedPatients();
                if (assigned == null) {
                    assigned = new ArrayList<>();
                } else {
                    assigned = new ArrayList<>(assigned);
                }

                assigned.add(patientName);
                n.setAssignedPatients(assigned);

                System.out.println(" Patient \"" + patientName + "\" assigned to Nurse " + nurseId + " successfully!");
                return n;
            }
        }

        System.out.println(" Nurse with ID " + nurseId + " not found.");
        return null;
    }


    @Override
    public void add(Object entity) {
        if (entity instanceof Nurse) {
            save((Nurse) entity);
        } else {
            System.out.println("Invalid entity. Expected Nurse.");
        }
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void getAll() {
        displayAllNurse();
    }

    @Override
    public void search(String keyword) {
        boolean found = false;
        String k = keyword == null ? "" : keyword.toLowerCase();
        for (Nurse n : ListOfNurse) {
            if ((n.getFirstName() != null && n.getFirstName().toLowerCase().contains(k)) ||
                    (n.getLastName() != null && n.getLastName().toLowerCase().contains(k))) {
                n.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No nurses matched the keyword: " + keyword);
        }
    }

    @Override
    public void searchById(String id) {
        Nurse n = getNurseById(id);
        if (n != null) {
            n.displayInfo();
        }
    }

    public static Nurse addNurse() {
        Nurse nurse = new Nurse();
        System.out.println("Adding Nurse");
        nurse.setId(HelperUtils.generateId("NUR", 5));

        nurse.setFirstName(InputHelper.getStringInput("Enter the FirstName"));
        nurse.setLastName(InputHelper.getStringInput("Enter the lastName"));
        nurse.setDateOfBirth(InputHelper.getDateInput("Enter date of birth "));
        nurse.setGender(InputHelper.getStringInput("Enter gender (M/F) "));
        nurse.setPhoneNumber(InputHelper.getStringInput("Enter phoneNumber "));
        nurse.setEmail(InputHelper.getStringInput("Enter email "));
        nurse.setAddress(InputHelper.getStringInput("Enter address"));
        int shiftChoice = InputHelper.getIntInput("""
                Enter shift (number only):
                1. Morning
                2. Evening
                3. Night
                """);
        String shift = switch (shiftChoice) {
            case 1 -> "Morning";
            case 2 -> "Evening";
            case 3 -> "Night";
            default -> "Unknown";
        };
        nurse.setShift(shift);
        System.out.println();
        nurse.setQualification(InputHelper.getStringInput(" Enter qualification"));
        nurse.setNurseId(HelperUtils.generateId("NUR", 5));
        nurse.setDepartmentId(HelperUtils.generateId("DEP", 6));

        //  ---------- assignedPatients ----------
        boolean Input = true;
        int assignedNumber = 1;
        List<String> assignedInput = new LinkedList<>();

        while (Input) {

            String record = InputHelper.getStringInput("Enter assigned Patients #" + assignedNumber);
            assignedInput.add(record);
            assignedNumber++;

            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press any letter to add another");
            if (exitFlag.equalsIgnoreCase("q")) {
                Input = false;
            }
        }
        nurse.setAssignedPatients(assignedInput);
        ListOfNurse.add(nurse);
        return nurse;
    }

    public static void save(Nurse nurse) {
        ListOfNurse.add(nurse);
        System.out.println("Nurse added successfully!\n");
    }

    public static void editNurseByMenu(String nurseId) {
        Nurse nurseToEdit = null;

        for (Nurse n : ListOfNurse) {
            if (HelperUtils.isNotNull(n.getNurseId()) && n.getNurseId().equalsIgnoreCase(nurseId)) {
                nurseToEdit = n;
                break;
            }
        }

        if (nurseToEdit == null) {
            System.out.println(" Nurse with ID " + nurseId + " not found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("""
                ===== Edit Nurse Information =====
                1 - Change First Name
                2 - Change Last Name
                3 - Change Phone Number
                4 - Change Email
                5 - Change Address
                6 - Change Shift
                7 - Change Qualification
                8 - Exit
                ==================================
                """);

            System.out.print("Enter your choice: ");
            choice = InputHelper.getIntInput("");

            switch (choice) {
                case 1 -> {
                    String newFirstName = InputHelper.getStringInput("Enter new First Name ");
                    nurseToEdit.setFirstName(newFirstName);
                    System.out.println(" First name updated successfully.");
                }
                case 2 -> {
                    String newLastName = InputHelper.getStringInput("Enter new Last Name ");
                    nurseToEdit.setLastName(newLastName);
                    System.out.println(" Last name updated successfully.");
                }
                case 3 -> {
                    String newPhone = InputHelper.getStringInput("Enter new Phone Number ");
                    nurseToEdit.setPhoneNumber(newPhone);
                    System.out.println("Phone number updated successfully.");
                }
                case 4 -> {
                    String newEmail = InputHelper.getStringInput("Enter new Email ");
                    nurseToEdit.setEmail(newEmail);
                    System.out.println("Email updated successfully.");
                }
                case 5 -> {
                    String newAddress = InputHelper.getStringInput("Enter new Address ");
                    nurseToEdit.setAddress(newAddress);
                    System.out.println(" Address updated successfully.");
                }
                case 6 -> {
                    int shiftChoice = InputHelper.getIntInput("""
                        Choose new Shift:
                        1 - Morning
                        2 - Evening
                        3 - Night
                        """);
                    String newShift = switch (shiftChoice) {
                        case 1 -> "Morning";
                        case 2 -> "Evening";
                        case 3 -> "Night";
                        default -> "Unknown";
                    };
                    nurseToEdit.setShift(newShift);
                    System.out.println("Shift updated successfully.");
                }
                case 7 -> {
                    String newQualification = InputHelper.getStringInput("Enter new Qualification ");
                    nurseToEdit.setQualification(newQualification);
                    System.out.println(" Qualification updated successfully.");
                }
                case 8 -> System.out.println("Exiting edit menu...");
                default -> System.out.println(" Invalid choice, please try again.");
            }

        } while (choice != 8);
    }


    public static boolean removeNurse(String NurseId) {
        for (int i = 0; i < ListOfNurse.size(); i++) {
            Nurse D = ListOfNurse.get(i);

            if (HelperUtils.isNotNull(D.getNurseId())  && D.getNurseId().equals(NurseId)) {
                ListOfNurse.remove(i); // remove by index
                System.out.println("Nurse with ID " + NurseId + " removed successfully!");
                return true;
            }
        }
        System.out.println("Nurse  with ID " + NurseId + " not found.");
        return false;
    }

    public static Nurse getNurseById(String nurseId) {
        for (Nurse D : ListOfNurse) {
            if (HelperUtils.isNotNull(D.getNurseId())  && D.getNurseId().equals(nurseId)) {
                return D;
            }
        }
        System.out.println("Nurse with ID " + nurseId + " not found.");
        return null;
    }

    public static void getNursesByDepartment(String departmentId) {
        if (ListOfNurse == null || ListOfNurse.isEmpty()) {
            System.out.println(" No nurses available in the system.");
            return;
        }

        boolean found = false;
        System.out.println("\n===== Nurses in Department: " + departmentId + " =====");

        for (Nurse nurse : ListOfNurse) {
            if (HelperUtils.isNotNull(nurse.getDepartmentId()) &&
                    nurse.getDepartmentId().trim().equalsIgnoreCase(departmentId.trim())) {

                nurse.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println(" No nurses found in department: " + departmentId);
        }

        System.out.println("==========================================\n");
    }



    public static void displayAllNurse() {
        if (ListOfNurse.isEmpty()) {
            System.out.println("No Nurse to display.");
            return;
        }

        for (Nurse p : ListOfNurse) {
            p.displayInfo();
        }
    }

    public static void getNursesByShift(String shift) {
        boolean found = false;

        for (Nurse n : ListOfNurse) {
            if (n.getShift() != null && n.getShift().equalsIgnoreCase(shift)) {
                n.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No nurses found in the " + shift + " shift.");
        }
    }

    public static void addSampleNurses() {
        for (int i = 0; i < 6; i++) {
            Nurse nurse = new Nurse();
            nurse.setNurseId(HelperUtils.generateId("NUR", 5));
            nurse.setFirstName("Alice" + i);
            nurse.setLastName("Johnson" + i);
            nurse.setEmail("alice.johnson" + i + "@hospital.com");
            nurse.setAddress("Muscat, Oman");
            nurse.setPhoneNumber("+96890123456");
            nurse.setGender("F");
            nurse.setDateOfBirth(LocalDate.of(1990, 5, 15));
            nurse.setId(HelperUtils.generateId(8));
            nurse.setDepartmentId(HelperUtils.generateId("DEP", 6));
            nurse.setShift("Morning");
            nurse.setQualification("BSc Nursing");
            List<String> assignedPatients = Arrays.asList("Patient A", "Patient B");
            nurse.setAssignedPatients(assignedPatients);
            ListOfNurse.add(nurse);


        }
    }
}





