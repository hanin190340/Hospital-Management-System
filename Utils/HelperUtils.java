package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

public class HelperUtils {


    // --- Existing Methods ---
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNotNull(String str) {
        return str != null && !str.trim().isEmpty();
    }


    // 1️ Checks if string is NOT null and NOT empty
    public static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    // 2️ Checks not null/empty and has at least minLength characters
    public static boolean isValidString(String str, int minLength) {
        return isValidString(str) && str.trim().length() >= minLength;
    }

    // 3️ Checks not null/empty and within length range
    public static boolean isValidString(String str, int minLength, int maxLength) {
        if (!isValidString(str)) return false;
        int length = str.trim().length();
        return length >= minLength && length <= maxLength;
    }

    // 4️ Checks not null/empty and matches a regex pattern
    public static boolean isValidString(String str, String regex) {
        return isValidString(str) && str.matches(regex);
    }

    // 1️ Checks not null
    public static String isValidDate(Date date) { //checks not null and valid
        if (date == null) {
            return "Date is null";
        }
        Date today = new Date();
        if (date.before(today)) {
            return "Date cannot be in the past";
        }
        return null; //valid
    }

    // 2️ Parses and validates String date (format: yyyy-MM-dd)
    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // 3 Range Check (min ≤ date ≤ max)
    public static boolean isValidDate(Date date, Date minDate, Date maxDate) {
        if (date == null || minDate == null || maxDate == null) return false;
        return !date.before(minDate) && !date.after(maxDate);
    }

    // 4️ Check if Future Date
    public static boolean isFutureDate(LocalDate date) {
        if (date == null) return false;
        return date.isAfter(LocalDate.now());
    }

    // 5️ Check if Past Date
    public static boolean isPastDate(Date date) {
        if (date == null) return false;
        return date.before(new Date());
    }

    // 6️ Check if Today
    public static boolean isToday(Date date) {
        if (date == null) return false;
        LocalDate givenDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return givenDate.isEqual(today);
    }

    // 1️ Integer range
    public static boolean isValidNumber(int num, int min, int max) {
        return num >= min && num <= max;
    }

    // 2 Double range
    public static boolean isValidNumber(double num, double min, double max) {
        return num >= min && num <= max;
    }

    // 3️ Positive numbers
    public static boolean isPositive(int num) {
        return num > 0;
    }

    public static boolean isPositive(double num) {
        return num > 0;
    }

    // 4️ Negative numbers
    public static boolean isNegative(int num) {
        return num < 0;
    }

    public static boolean isNegative(double num) {
        return num < 0;
    }


    // 1️ Age validation by number
    public static boolean isValidAge(int age) {
        return age >= 0 && age <= 120;
    }

    // 2️ Age validation by date of birth
    public static boolean isValidAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) return false;
        LocalDate today = LocalDate.now();
        int age = today.getYear() - dateOfBirth.getYear();
        if (dateOfBirth.plusYears(age).isAfter(today)) {
            age--;
        }
        return isValidAge(age);
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
    public static String generateId(int length) {
        String uuid = UUID.randomUUID().toString().replace("-", ""); // remove dashes

        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        // If length > UUID length (32), repeat the UUID
        while (uuid.length() < length) {
            uuid += UUID.randomUUID().toString().replace("-", "");
        }

        return uuid.substring(0, length);
    }


    public static String generateId(String prefix) {
        int randomNum = new Random().nextInt(90000) + 10000; // 5 digits
        return prefix + "-" + randomNum;
    }


    public static String generateId(String prefix, int length) {
        StringBuilder id = new StringBuilder(prefix + "-");
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            id.append(random.nextInt(10)); // adds random digits
        }

        return id.toString();
    }


    public static String generateId(String prefix, String suffix) {
        String middle = UUID.randomUUID().toString().substring(0, 8); // shorter UUID part
        return prefix + "-" + middle + "-" + suffix;
    }

        public static boolean isValidEmail(String email) {
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (email == null)
                return false;
            return pattern.matcher(email).matches();
        }


    }




