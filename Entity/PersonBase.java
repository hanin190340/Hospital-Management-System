package Entity;

import Interface.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class PersonBase implements Displayable {
    Scanner scanner = new Scanner(System.in);
    String id;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String gender;
    String phoneNumber;
    String email;
    String address ;

    public PersonBase(String id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNumber, String email, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        while (!HelperUtils.isValidString(firstName)){
            System.out.println("First name cannot be empty or null.");
            firstName= scanner.nextLine();
            return;
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        while (!HelperUtils.isValidString(lastName)){
            System.out.println("Last name cannot be empty or null.");
            lastName= scanner.nextLine();
            return;
        }
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        while (HelperUtils.isFutureDate(dateOfBirth)) {
            System.out.println("Date of birth cannot be in the future.");
            String dateInput = scanner.nextLine();
            dateOfBirth = LocalDate.parse(dateInput);
            return;
        }
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        while (!HelperUtils.isValidString(gender) && gender.equalsIgnoreCase("F") && gender.equalsIgnoreCase("M")){
            System.out.println("Gender cannot be empty or null.");
            gender= scanner.nextLine();
            return;}
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        while (!HelperUtils.isValidString(phoneNumber)){
            System.out.println("Phone number cannot be empty or null.");
            phoneNumber= scanner.nextLine();
            return;
        }
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        while (!HelperUtils.isValidString(email) || !HelperUtils.isValidEmail(email)){
            System.out.println("Email cannot be empty or null.");
            email= scanner.nextLine();
            return;
        }
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        while (!HelperUtils.isValidString(address)){
            System.out.println("Address cannot be empty or null.");
            address= scanner.nextLine();
            return;
        }
        this.address = address;
    }
    public PersonBase() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonBase that = (PersonBase) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(gender, that.gender) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);
    }



    public void displayInfo() {
        System.out.println("===== Person Information =====");
        System.out.println("ID: " + id);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
    }

    @Override
    public void displaySummary() {
        System.out.println("ID: " + id + ", Name: " + firstName + " " + lastName);
    }


}
