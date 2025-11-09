package Service;

import Entity.Nurse;
import Interface.Manageable;
import Interface.Searchable;
import Utils.HelperUtils;
import Utils.InputHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NurseService implements Manageable, Searchable {
   public static List <Nurse> ListOfNurse =new ArrayList<>() ;


public static Nurse assignPatientToNurse(String nurseId, String patientName) {
    for (Nurse n : ListOfNurse) {
        if (HelperUtils.isNotNull(n.getNurseId()) && n.getNurseId().toString().equals(nurseId)) {
            List<String> assigned = n.getAssignedPatients();
            if (assigned == null) {
                assigned = new LinkedList<>();
                n.setAssignedPatients(assigned);
            }
            assigned.add(patientName);
            System.out.println("Patient \"" + patientName + "\" assigned to Nurse " + nurseId + " successfully!");

        }
    }
    System.out.println("Nurse with ID " + nurseId + " not found.");
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

    public static Nurse addNurse () {
        Nurse nurse = new Nurse();
        System.out.println("Adding Entity.Nurse");
        nurse.setId(HelperUtils.generateId("NUR",5));

        nurse.setFirstName(InputHelper.getStringInput("Enter the FirstName:"));
        nurse.setLastName(InputHelper.getStringInput("Enter the lastName:"));
        nurse.setDateOfBirth(InputHelper.getDateInput("Enter date of birth (YYYY-MM-DD):"));
        nurse.setGender(InputHelper.getStringInput("Enter gender (M/F): "));
        nurse.setPhoneNumber(InputHelper.getStringInput("Enter phoneNumber: "));
        nurse.setEmail(InputHelper.getStringInput("Enter email: "));
        nurse.setAddress(InputHelper.getStringInput("Enter address : "));
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
        nurse.setNurseId(HelperUtils.generateId("NUR",5));
        nurse.setDepartmentId(HelperUtils.generateId("DEP",6));

        //  ---------- assignedPatients ----------
        boolean Input = true;
        int assignedNumber = 1;
        List<String> assignedInput = new LinkedList<>();

        while (Input) {

            String record = InputHelper.getStringInput("Enter assigned Patients #" + assignedNumber + ":");
            assignedInput.add(record);
            assignedNumber++;

            String exitFlag = InputHelper.getStringInput("Press 'q' and Enter to stop, or just press Enter to add another:");
            if (exitFlag.equalsIgnoreCase("q")) {
                Input = false;
            }
        }
        nurse.setAssignedPatients(assignedInput);


        return nurse;
    }

    public static void save(Nurse nurse) {
        ListOfNurse.add(nurse);
        System.out.println("Entity.Nurse added successfully!\n");
    }
    public static boolean editNurse(String nurseId, Nurse updatedNurse) {
        for (int i = 0; i < ListOfNurse.size(); i++) {
            Nurse existingNurse = ListOfNurse.get(i);

            if (existingNurse.getNurseId() != null && existingNurse.getNurseId().equals(nurseId)) {

                updatedNurse.setNurseId(existingNurse.getNurseId());

                // Replace old Entity.Nurse with updated one
                ListOfNurse.set(i, updatedNurse);
                System.out.println("Nurse updated successfully!");
                return true;
            }
        }

        System.out.println("Nurse with ID " + nurseId + " not found.");
        return false;
    }
    public static boolean removeNurse(String NurseId) {
        for (int i = 0; i < ListOfNurse.size(); i++) {
            Nurse D = ListOfNurse.get(i);

            if (D.getNurseId() != null && D.getNurseId().equals(NurseId)) {
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
            if (D.getNurseId() != null && D.getNurseId().equals(nurseId)) {
                return D;
            }
        }
        System.out.println("Nurse with ID " + nurseId + " not found.");
        return null;
    }

    public static void getNursesByDepartment(String Department) {
        boolean found = false;

        for (Nurse d : ListOfNurse) {
            if (d.getDepartmentId() != null &&
                    d.getDepartmentId().equals(Department.toLowerCase())) {
                d.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Nurse found with Entity.Department: " + Department);
        }
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



}
