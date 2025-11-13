package Main;

import Entity.*;
import Service.*;
import Utils.InputHelper;

import java.time.LocalDate;


public class HospitalManagementApp {
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
            mainMenu = InputHelper.getIntInput("please enter a valid option", 1, 8);

            switch (mainMenu) {
                case 1 -> {
                    PatientMenu = 0;
                    while (PatientMenu != 10) {
                        showPatientMenu();
                        PatientMenu = InputHelper.getIntInput("please enter a valid option", 1, 10);

                        switch (PatientMenu) {
                            case 1 -> {
                                PatientService patientService = new PatientService();
                                patientService.add(PatientService.addPatient());
                            }
                            case 2 -> {
                                Patient patient = PatientService.addInPatient();
                                PatientService.save(patient);
                            }
                            case 3 -> {
                                Patient patient = PatientService.addOutPatient();
                                PatientService.save(patient);
                            }
                            case 4 -> {
                                Patient patient = PatientService.addEmergencyPatient();
                                PatientService.save(patient);
                            }
                            case 5 -> {
                                PatientService.addSamplePatients();
                                PatientService service = new PatientService();
                                service.getAll();

                            }

                            case 6 -> {
                                String patientId = InputHelper.getStringInput("Enter Patient ID to search ");
                                PatientService service = new PatientService();
                                service.searchById(patientId);
                            }

                            case 7 -> {
                                String patientId = InputHelper.getStringInput("Enter Patient ID to update");
                                PatientService.editPatient(patientId);

                            }

                            case 8 -> {
                                String patientIdToRemove = InputHelper.getStringInput("Enter Patient ID to remove ");
                                PatientService service = new PatientService();
                                service.remove(patientIdToRemove);
                            }

                            case 9 -> {
                                String patientIdForHistory = InputHelper.getStringInput("Enter Patient ID to view medical history ");
                                PatientService.getRecordsByPatientId(patientIdForHistory);
                            }

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
                        doctoMenu = InputHelper.getIntInput("please enter a valid option", 1, 11);

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
                            case 4 -> {
                                DoctorService service = new DoctorService();
                                service.add(DoctorService.addGeneralPractitioner());
                            }
                            case 5 -> {
                                DoctorService.addSampleDoctors();
                                DoctorService.displayAllDoctor();
                            }

                            case 6 -> {
                                String specialization = InputHelper.getStringInput("Enter specialization to search ");
                                DoctorService.getDoctorsBySpecialization(specialization);
                            }
                            case 7 -> {
                                DoctorService.getAvailableDoctors();
                            }

                            case 8 -> {
                                String doctorIdAssign = InputHelper.getStringInput("Enter Doctor ID to assign to ");
                                String patientIdAssign = InputHelper.getStringInput("Enter Patient ID to assign ");
                                DoctorService doctorService = new DoctorService();
                                Doctor assigned = doctorService.assignPatient(doctorIdAssign, patientIdAssign);
                                if (assigned.isAvailable()) {
                                    System.out.println("Patient assigned to doctor successfully.");
                                } else {
                                    System.out.println("Assignment failed. Check IDs or implement assignPatient in DoctorService");
                                }
                            }

                            case 9 -> {
                                String doctorId = InputHelper.getStringInput("Enter Doctor ID to update");
                                DoctorService.editDoctorById(doctorId);

                            }
                            case 10 -> {
                                String doctorIdToRemove = InputHelper.getStringInput("Enter Doctor ID to remove");
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
                        nurseMenu = InputHelper.getIntInput("please enter a valid option", 1, 8);

                        switch (nurseMenu) {
                            case 1 -> {
                                NurseService service = new NurseService();
                                service.add(service.addNurse());

                            }
                            case 2 -> {
                                NurseService.addSampleNurses();
                                NurseService.displayAllNurse();
                            }
                            case 3 -> {
                                String departmentId = InputHelper.getStringInput("Enter department Id");
                                NurseService.getNursesByDepartment(departmentId);


                            }
                            case 4 -> {

                                String shift = InputHelper.getStringInput("Enter shift (Morning/Evening/Night) ");
                                NurseService.getNursesByShift(shift);
                            }
                            case 5 -> {
                                String nurseIdAssign = InputHelper.getStringInput("Enter Nurse ID to assign to ");
                                String patientIdAssign = InputHelper.getStringInput("Enter Patient ID to assign");
                                NurseService nurseService = new NurseService();
                                Nurse assignedNurse = nurseService.assignPatientToNurse(nurseIdAssign, patientIdAssign);
                                if (assignedNurse != null) {
                                    System.out.println("Patient assigned to nurse successfully.");
                                } else {
                                    System.out.println("Assignment failed. Check IDs or implement assignPatient in NurseService.");
                                }
                            }

                            case 6 -> {
                                String nurseId = InputHelper.getStringInput("Enter Nurse ID to edit");
                                NurseService.editNurseByMenu(nurseId);
                            }

                            case 7 -> {
                                String nurseIdToRemove = InputHelper.getStringInput("Enter Nurse ID to remove ");
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
                        appointmentMenu = InputHelper.getIntInput("please enter a valid option", 1, 10);

                        switch (appointmentMenu) {
                            case 1 -> {
                                AppointmentService Service = new AppointmentService();
                                Service.add(Service.addAppointment());
                            }
                            case 2 -> {
                                AppointmentService.addSampleAppointments();
                                AppointmentService.displayAllAppointments();
                            }
                            case 3 -> {
                                String patientId = InputHelper.getStringInput("Enter Patient ID to view appointments ");
                                Service.AppointmentService.getAppointmentsByPatient(patientId);
                            }
                            case 4 -> {
                                String doctorId = InputHelper.getStringInput("Enter Doctor ID to view appointments");
                                Service.AppointmentService.getAppointmentByDoctorId(doctorId);
                            }
                            case 5 -> {
                                LocalDate date = InputHelper.getDateInput("Enter Date to view appointments ");
                                Service.AppointmentService.getAppointmentsByDate(date);
                            }
                            case 6 -> {
                                String appointmentId = InputHelper.getStringInput("Enter Appointment ID to reschedule ");
                                LocalDate newDate = InputHelper.getDateInput("Enter new Date");
                                Service.AppointmentService.rescheduleAppointment(appointmentId, newDate);
                            }
                            case 7 -> {
                                String appointmentIdToCancel = InputHelper.getStringInput("Enter Appointment ID to cancel");
                                Service.AppointmentService.cancelAppointment(appointmentIdToCancel);
                            }
                            case 8 -> {
                                String appointmentIdToComplete = InputHelper.getStringInput("Enter Appointment ID to complete");
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
                        medicalRecordsMenu = InputHelper.getIntInput("please enter a valid option", 1, 8);

                        switch (medicalRecordsMenu) {
                            case 1 -> {
                                MedicalRecordService service = new MedicalRecordService();
                                service.add(service.addMedicalRecord());
                            }
                            case 2 -> {
                                MedicalRecordService.addSampleMedicalRecord();
                                MedicalRecordService.displayAllMedicalRecord();
                            }
                            case 3 -> {

                                String patientId = InputHelper.getStringInput("Enter Patient ID to view records ");
                                MedicalRecordService.getRecordsByPatientId(patientId);
                            }
                            case 4 -> {

                                String doctorId = InputHelper.getStringInput("Enter Doctor ID to view records");
                                MedicalRecordService.getRecordsByDoctorId(doctorId);
                            }
                            case 5 -> {
                                String recordId = InputHelper.getStringInput("Enter Medical Record ID to edit or add ");
                                MedicalRecordService.editMedicalRecord(recordId);
                            }


                            case 6 -> {
                                String recordIdToDelete = InputHelper.getStringInput("Enter Medical Record ID to delete");
                                MedicalRecordService.removeMedicalRecord(recordIdToDelete);
                            }
                            case 7 -> {

                                String patientIdForReport = InputHelper.getStringInput("Enter Patient ID to generate history report ");
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
                        departmentMenu = InputHelper.getIntInput("please enter a valid option", 1, 8);

                        switch (departmentMenu) {
                            case 1 -> {
                                Department department = DepartmentService.addDepartment();
                                DepartmentService.save(department);
                            }
                            case 2 -> {
                                DepartmentService.addSampleDepartments();
                                DepartmentService.displayAllDepartments();
                            }
                            case 3 -> {
                                String departmentId = InputHelper.getStringInput("Enter Department ID to view details");
                                DepartmentService.viewDepartmentDetails(departmentId);
                            }
                            case 4 -> {
                                String departmentIdAssignDoctor = InputHelper.getStringInput("Enter Department ID to assign doctor to ");
                                String doctorIdAssign = InputHelper.getStringInput("Enter Doctor ID to assign: ");
                                DepartmentService.assignDoctorToDepartment(doctorIdAssign, departmentIdAssignDoctor);

                            }
                            case 5 -> {
                                String departmentIdAssignNurse = InputHelper.getStringInput("Enter Department ID ");
                                String nurseIdAssign = InputHelper.getStringInput("Enter Nurse ID to assign ");
                                DepartmentService.assignNurseToDepartment(nurseIdAssign, departmentIdAssignNurse);
                            }
                            case 6 -> {
                                String departmentId = InputHelper.getStringInput("Enter Department ID to update ");
                                DepartmentService.editDepartment(departmentId);

                            }

                            case 7 -> {
                                String departmentIdForStats = InputHelper.getStringInput("Enter Department ID to view statistics ");
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
                        reportsAndStatisticsMenu = InputHelper.getIntInput("please enter a valid option", 1, 6);

                        switch (reportsAndStatisticsMenu) {
                            case 1 -> {

                                LocalDate date = InputHelper.getDateInput("Enter the date for the report ");
                                Service.AppointmentService.viewDailyAppointmentsReport(date);

                            }
                            case 2 -> {
                                String Id = InputHelper.getStringInput("Enter Doctor ID to Performance Report ");
                                DoctorService.generateDoctorPerformanceReport(Id);
                            }
                            case 3 -> {

                                String Id = InputHelper.getStringInput("Enter Department ID to  Report");
                                DepartmentService.generateDepartmentOccupancyReport(Id);
                            }
                            case 4 -> {
                                String patientid = InputHelper.getStringInput("Enter Patient ID: ");
                                PatientService.generatePatientStatisticsReport(patientid);
                            }
                            case 5 -> {
                                String patientid = InputHelper.getStringInput("Enter Patient ID: ");
                                PatientService.generateEmergencyCasesReport(patientid);
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

    public static void showMainMenu() {
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

    public static void showDoctorMenu() {
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

    public static void showPatientMenu() {
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

    public static void showNurseMenu() {
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

    public static void showAppointmentMenu() {
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

    public static void showMedicalRecordsMenu() {
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

    public static void showDepartmentMenu() {
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

    public static void showReportsAndStatisticsMenu() {
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
        NurseService.addSampleNurses();
        DepartmentService.addSampleDepartments();
        MedicalRecordService.addSampleMedicalRecord();
        AppointmentService.addSampleAppointments();

    }

}
