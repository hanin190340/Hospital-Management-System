package Main;

import Entity.*;
import Service.*;
import Utils.InputHelper;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import static Service.DoctorService.addSurgeon;
import static Service.DoctorService.save;

public class HospitalManagementApp {
    public static Scanner scanner = new Scanner(System.in);
    public static Integer mainMenu = 0;
    public static Integer doctoMenu = 0;
    public static Integer nurseMenu = 0;
    public static Integer appointmentMenu = 0;
    public static Integer medicalRecordsMenu = 0;
    public static Integer departmentMenu = 0;
    public static Integer reportsAndStatisticsMenu = 0;
    public static Integer PatientMenu = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to Hospital Management System");
        MainMenu();

            addSampleDataForAll();
        }



        public static void MainMenu() {
        while (mainMenu != 8) {
            showMainMenu();
            mainMenu = InputHelper.getIntInput("please enter a valid option",1,8);

            switch (mainMenu) {
                case 1 -> {
                    PatientMenu = 0;
                    while (PatientMenu != 10) {
                        showPatientMenu();
                        PatientMenu = InputHelper.getIntInput("please enter a valid option",1,10);

                        switch (PatientMenu) {
                            case 1 -> {
                                PatientService patientService = new PatientService();
                                patientService.add( PatientService.addPatient());
                            }
                            case 2 -> {
                                Patient patient = PatientService.addInPatient();
                                PatientService.save(patient);
                            }
                            case 3 -> {
                                Patient patient = PatientService.addOutPatient();
                                PatientService.save( patient);
                            }
                            case 4 -> {
                                Patient patient = PatientService.addEmergencyPatient();
                                PatientService.save(patient);
                            }
                            case 5 -> {
                                PatientService.addSamplePatients();
                                PatientService service=new PatientService();
                                service.getAll();

                            }

                            case 6 -> {
                                scanner.nextLine();
                                System.out.print("Enter Patient ID to search : ");
                                String patientId = scanner.nextLine();
                                PatientService service=new PatientService();
                                service.searchById(patientId);
                            }

                                case 7 -> {
                                    System.out.print("Enter Patient ID to update: ");
                                    String patientId = scanner.nextLine();
                                    PatientService.editPatient(patientId);

                                }

                            case 8 -> {
                                System.out.print("Enter Patient ID to remove: ");
                                String patientIdToRemove = scanner.nextLine();
                                PatientService service = new PatientService();
                                service.remove(patientIdToRemove);
                            }

                            //case 9 -> {
                               // System.out.print("Enter Patient ID to view medical history: ");
                                //String patientIdForHistory = scanner.nextLine();
                               // MedicalRecordService.getRecordsByPatientId(patientIdForHistory);
                           // }

                            case 10 -> {
                                System.out.println("Exit from menu");
                                PatientMenu = 10;
                            }
                            default -> System.out.println("Please enter a number from the menu");
                        }
                    }
                }

                case 2 -> {
                    doctoMenu = 0;
                    while (doctoMenu != 11) {
                        showDoctorMenu();
                        doctoMenu = InputHelper.getIntInput("please enter a valid option",1,11);

                        switch (doctoMenu) {
                            case 1 -> {

                                DoctorService service = new DoctorService();
                                service.add(DoctorService.addDoctor());
                            }
                            case 2 -> {

                                DoctorService service = new DoctorService();
                                service.add(DoctorService.addSurgeon());

                            }
                            case 3 -> {
                                DoctorService service = new DoctorService();
                                service.add(DoctorService.addConsultant());

                            }
                            case 4-> {
                                DoctorService service = new DoctorService();
                                service.add(DoctorService.addGeneralPractitioner());
                            }
                              case 5 -> {
                                DoctorService.addSampleDoctors();
                                DoctorService.displayAllDoctor();
                            }

                        case 6 -> {
                                System.out.print("Enter specialization to search: ");
                                String specialization = scanner.nextLine();
                                DoctorService.getDoctorsBySpecialization(specialization);
                        }
                        case 7 -> {
                                DoctorService.getAvailableDoctors();
                        }

                            case 8 -> {
                                System.out.print("Enter Doctor ID to assign to: ");
                                String doctorIdAssign = scanner.nextLine();
                                System.out.print("Enter Patient ID to assign: ");
                                String patientIdAssign = scanner.nextLine();
                                DoctorService doctorService = new DoctorService();
                                Doctor assigned = doctorService.assignPatient(doctorIdAssign, patientIdAssign);
                                if (assigned.isAvailable()) {
                                    System.out.println("Patient assigned to doctor successfully.");
                                } else {
                                    System.out.println("Assignment failed. Check IDs or implement assignPatient in DoctorService.");
                                }
                            }

                            case 9 -> {
                                System.out.print("Enter Doctor ID to update: ");
                                String doctorId = scanner.nextLine();
                                DoctorService.editDoctorById(doctorId);

                        }
                            case 10 -> {
                                System.out.print("Enter Doctor ID to remove: ");
                                String doctorIdToRemove = scanner.nextLine();
                                DoctorService.removeDoctor(doctorIdToRemove);
                            }

                            case 11 -> {
                                System.out.println("Exit from menu");
                                doctoMenu = 11;
                            }
                            default -> System.out.println("Please enter a number from the menu");
                        }
                    }
                }

                case 3 -> {
                    nurseMenu = 0;
                    while (nurseMenu != 8) {
                        showNurseMenu();
                        nurseMenu = InputHelper.getIntInput("please enter a valid option",1,8);

                        switch (nurseMenu) {
                            case 1 -> {
                                NurseService service = new NurseService ();
                                Nurse nurse=new Nurse();
                                service.addNurse();

                            }
                            case 2 -> {
                                NurseService.addSampleNurses();
                                NurseService.displayAllNurse();
                            }
                            case 3 -> {
                                System.out.print("Enter department Id: ");
                                String departmentId = scanner.nextLine();
                                NurseService.getNursesByDepartment(departmentId);


                            }
                            case 4 -> {
                                System.out.print("Enter shift (Morning/Evening/Night): ");
                                String shift = scanner.nextLine();
                                NurseService.getNursesByShift(shift);
                            }
                            case 5 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Nurse ID to assign to: ");
                                String nurseIdAssign = scanner.nextLine();
                                System.out.print("Enter Patient ID to assign: ");
                                String patientIdAssign = scanner.nextLine();
                                NurseService nurseService = new NurseService();
                                Nurse assignedNurse = nurseService.assignPatientToNurse(nurseIdAssign, patientIdAssign);
                                if (assignedNurse != null) {
                                    System.out.println("Patient assigned to nurse successfully.");
                                } else {
                                    System.out.println("Assignment failed. Check IDs or implement assignPatient in NurseService.");
                                }
                            }
                            case 6 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Nurse ID to update: ");
                                String nurseId = scanner.nextLine();
                                System.out.println("Enter updated nurse details:");
                                Nurse updatedNurse = NurseService.addNurse();
                                boolean updated = NurseService.editNurse(nurseId, updatedNurse);
                                if (updated) {
                                    System.out.println("Nurse updated successfully.");
                                } else {
                                    System.out.println("Nurse with ID " + nurseId + " not found.");
                                }
                            }
                            case 7 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Nurse ID to remove: ");
                                String nurseIdToRemove = scanner.nextLine();
                                NurseService.removeNurse(nurseIdToRemove);
                            }
                            case 8 -> {
                                System.out.println("Exit from menu");
                                nurseMenu = 8;
                            }
                            default -> System.out.println("Please enter a number from the menu");
                        }
                    }
                }

                case 4 -> {
                    appointmentMenu = 0;
                    while (appointmentMenu != 10) {
                        showAppointmentMenu();
                        appointmentMenu = InputHelper.getIntInput("please enter a valid option",1,10);

                        switch (appointmentMenu) {
                            case 1 -> {
                                Appointment appointment = Service.AppointmentService.addAppointment();
                                Service.AppointmentService.save(appointment);
                            }
                            case 2 -> {
                                    AppointmentService.addSampleAppointments();
                                    AppointmentService.displayAllAppointments();
                            }
                            case 3 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Patient ID to view appointments: ");
                                String patientId = scanner.nextLine();
                                Service.AppointmentService.getAppointmentsByPatient(patientId);
                            }
                            case 4 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Doctor ID to view appointments: ");
                                String doctorId = scanner.nextLine();
                                Service.AppointmentService. getRecordsByDoctorId(doctorId);
                            }
                            case 5 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Date (YYYY-MM-DD) to view appointments: ");
                                String date = scanner.nextLine();
                                Service.AppointmentService.getAppointmentsByDate(LocalDate.parse(date));
                            }
                            case 6 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Appointment ID to reschedule: ");
                                String appointmentId = scanner.nextLine();
                                System.out.print("Enter new Date (YYYY-MM-DD): ");
                                String newDate = scanner.nextLine();
                                Service.AppointmentService.rescheduleAppointment(appointmentId, LocalDate.parse(newDate));
                            }
                            case 7 -> {
                                scanner.nextLine();
                                System.out.print("Enter Appointment ID to cancel: ");
                                String appointmentIdToCancel = scanner.nextLine();
                                Service.AppointmentService.cancelAppointment(appointmentIdToCancel);
                            }
                            case 8 -> {
                                scanner.nextLine();
                                System.out.print("Enter Appointment ID to complete: ");
                                String appointmentIdToComplete = scanner.nextLine();
                                Service.AppointmentService.completeAppointment(appointmentIdToComplete);
                            }
                            case 9 -> {
                                Service.AppointmentService.viewUpcomingAppointments();
                            }
                            case 10 -> {
                                System.out.println("Exit from menu");
                                appointmentMenu = 10;
                            }
                            default -> System.out.println("Please enter a number from the menu");
                        }
                    }
                }

                case 5 -> {
                    medicalRecordsMenu = 0;
                    while (medicalRecordsMenu != 8) {
                        showMedicalRecordsMenu();
                        medicalRecordsMenu = InputHelper.getIntInput("please enter a valid option",1,8);

                        switch (medicalRecordsMenu) {
                            case 1 -> {
                                MedicalRecord medicalRecord = MedicalRecordService.addMedicalRecord();
                                MedicalRecordService.save(medicalRecord);
                            }
                            case 2 ->  {
                                MedicalRecordService.addSampleMedicalRecord();
                                MedicalRecordService.displayAllMedicalRecord();
                            }
                            case 3 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Patient ID to view records: ");
                                String patientId = scanner.nextLine();
                                MedicalRecordService.getRecordsByPatientId(patientId);
                            }
                            case 4 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Doctor ID to view records: ");
                                String doctorId = scanner.nextLine();
                                MedicalRecordService.getRecordsByDoctorId(doctorId);
                            }
                            case 5 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Medical Record ID to update: ");
                                String recordId = scanner.nextLine();
                                System.out.println("Enter updated medical record details:");
                                MedicalRecord updatedRecord = MedicalRecordService.addMedicalRecord();
                                boolean updated = MedicalRecordService.editMedicalRecord(recordId, updatedRecord);
                                if (updated) {
                                    System.out.println("Medical Record updated successfully.");
                                } else {
                                    System.out.println("Medical Record with ID " + recordId + " not found.");
                                }
                            }
                            case 6 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Medical Record ID to delete: ");
                                String recordIdToDelete = scanner.nextLine();
                                MedicalRecordService.removeMedicalRecord(recordIdToDelete);
                            }
                            case 7 -> {
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Patient ID to generate history report: ");
                                String patientIdForReport = scanner.nextLine();
                                MedicalRecordService.generatePatientHistoryReport(patientIdForReport);
                            }

                            case 8 -> {
                                System.out.println("Exit from menu");
                                medicalRecordsMenu = 8;
                            }
                            default -> System.out.println("Please enter a number from the menu");
                        }
                    }
                }

                case 6 -> {
                    departmentMenu = 0;
                    while (departmentMenu != 8) {
                        showDepartmentMenu();
                        departmentMenu = InputHelper.getIntInput("please enter a valid option",1,8);

                        switch (departmentMenu) {
                            case 1 ->{
                                Department department = DepartmentService.addDepartment();
                                DepartmentService.save(department);
                            }
                            case 2 ->{
                                DepartmentService.addSampleDepartments();
                                DepartmentService.displayAllDepartments();
                            }
                            case 3 ->{
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Department ID to view details: ");
                                String departmentId = scanner.nextLine();
                                DepartmentService.viewDepartmentDetails(departmentId);
                            }
                            case 4 ->{
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Department ID to assign doctor to: ");
                                String departmentIdAssignDoctor = scanner.nextLine();
                                System.out.print("Enter Doctor ID to assign: ");
                                String doctorIdAssign = scanner.nextLine();
                                DepartmentService.assignDoctorToDepartment(departmentIdAssignDoctor, doctorIdAssign);
                            }
                            case 5 ->{
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Department ID to assign nurse to: ");
                                String departmentIdAssignNurse = scanner.nextLine();
                                System.out.print("Enter Nurse ID to assign: ");
                                String nurseIdAssign = scanner.nextLine();
                                DepartmentService.assignNurseToDepartment(departmentIdAssignNurse, nurseIdAssign);
                            }
                            case 6 ->{
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Department ID to update: ");
                                String departmentId = scanner.nextLine();
                                System.out.println("Enter updated department details:");
                                Department updatedDepartment = DepartmentService.addDepartment();
                                boolean updated = DepartmentService.editDepartment(departmentId, updatedDepartment);
                                if (updated) {
                                    System.out.println("Department updated successfully.");
                                } else {
                                    System.out.println("Department with ID " + departmentId + " not found.");
                                }
                            }
                            case 7 ->{
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Department ID to view statistics: ");
                                String departmentIdForStats = scanner.nextLine();
                                DepartmentService.viewDepartmentStatistics(departmentIdForStats);
                            }
                            case 8 -> {
                                System.out.println("Exit from menu");
                                departmentMenu = 8;
                            }
                            default -> System.out.println("Please enter a number from the menu");
                        }
                    }
                }

                case 7 -> {
                    reportsAndStatisticsMenu = 0;
                    while (reportsAndStatisticsMenu != 6) {
                        showReportsAndStatisticsMenu();
                        reportsAndStatisticsMenu = InputHelper.getIntInput("please enter a valid option",1,6);

                        switch (reportsAndStatisticsMenu) {
                            case 1 -> {

                                System.out.println("Enter the date for the report (YYYY-MM-DD): ");
                                String date = scanner.nextLine();
                                Service.AppointmentService.viewDailyAppointmentsReport(LocalDate.parse(date));

                            }
                            case 2 -> {
                                DoctorService.generateDoctorPerformanceReport();
                            }
                            case 3 -> {
                                DepartmentService.generateDepartmentOccupancyReport();
                            }
                            case 4 -> {

                                System.out.println("Enter the date for the report (YYYY-MM-DD): ");
                                String date = scanner.nextLine();
                                PatientService.generateDailyPatientStatistics(LocalDate.parse(date));
                            }
                            case 5 -> {
                                PatientService.generateEmergencyCasesReport();
                            }
                            case 6 -> {
                                System.out.println("Exit from menu");
                                reportsAndStatisticsMenu = 6;
                            }
                            default -> System.out.println("Please enter a number from the menu");
                        }
                    }
                }

                case 8 -> System.out.println("Exiting program...");

                default -> System.out.println("Please enter a valid main menu option");
            }
        }

    }

        public static void showMainMenu () {
            System.out.println("===== Hospital Management App =====\n");
            System.out.println("""
                1-Patient Management
                2-Doctor Management
                3-Nurse Management
                4-Appointment Management
                5-Medical Records Management
                6-Department Management
                7-Reports and Statistics
                8- Exit
                """);
            System.out.println("==============================");
        }

    public static void showDoctorMenu () {
        System.out.println("===== Doctor Management=====\n");
        System.out.println("""
                1-Add Doctor
                2-Add Surgeon
                3-Add Consultant
                4-Add General Practitioner
                5-View All Doctors
                6-Search Doctor by Specialization
                7-View Available Doctors
                8-Assign Patient to Doctor
                9-Update Doctor Information
                10-Remove Doctor
                11 - Exit
                """);
        System.out.println("==============================");

    }
    public static void showPatientMenu () {
        System.out.println("===== Patient Management=====\n");
        System.out.println("""
                1-Register New Patient
                2-Register InPatient
                3-Register OutPatient
                4-Register Emergency Patient
                5-View All Patients
                6-Search Patient
                7-Update Patient Information
                8-Remove Patient
                9-View Patient Medical History
                10 - Exit
                """);
        System.out.println("==============================");

    }
    public static void showNurseMenu () {
        System.out.println("===== Nurse Management=====\n");

        System.out.println("""
                1-Add Nurse
                2-View All Nurses
                3-View Nurses by Department
                4-View Nurses by Shift
                5-Assign  to Patient
                6-Update Nurse Information
                7-Remove Nurse
                8-Exit
             
                """);
        System.out.println("==============================");
    }
    public static void showAppointmentMenu () {
        System.out.println("===== Appointment Management=====\n");
        System.out.println("""
                1-Schedule New Appointment
                2-View All Appointments
                3-View Appointments by Patient
                4-View Appointments by Doctor
                5-View Appointments by Date
                6-Reschedule Appointment
                7-Cancel Appointment
                8-Complete Appointment
                9-View Upcoming Appointments
                10 - Exit
                """);
        System.out.println("==============================");
    }
    public static void showMedicalRecordsMenu () {
        System.out.println("=====Medical Records  Management=====\n");
        System.out.println("""
                1-Create Medical Record
                2-View All Records
                3-View Records by Patient
                4-View Records by Doctor
                5-Update Medical Record
                6-Delete Medical Record
                7-Generate Patient History Report
                8- Exit
                
                """);
        System.out.println("==============================");
    }
    public static void showDepartmentMenu () {
        System.out.println("=====Department Management=====\n");

        System.out.println("""
                1-Add Department
                2-View All Departments
                3-View Department Details
                4-Assign Doctor to Entity.Department
                5-Assign Nurse to Entity.Department
                6-Update Department Information
                7-View Department Statistics
                8- Exit
                
                """);
        System.out.println("==============================");
    }
    public static void showReportsAndStatisticsMenu () {
        System.out.println("=====Reports And Statistics Management=====\n");

        System.out.println("""
                1-Daily Appointments Report
                2-Doctor Performance Report
                3-Department Occupancy Report
                4-Patient Statistics
                5-Emergency Cases Report
                6-Exit
                
                """);
        System.out.println("==============================");
    }
    public static void addSampleDataForAll() {
        PatientService.addSamplePatients();
        DoctorService.addSampleDoctors();
        //NurseService.addSampleNurses();
        DepartmentService.addSampleDepartments();
        MedicalRecordService.addSampleMedicalRecord();
        AppointmentService.addSampleAppointments();

    }

    }
