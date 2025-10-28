package com.clinic;

import java.io.*;
import java.util.*;

public class ClinicLogger {
    static Scanner sc = new Scanner(System.in);
    static String patientFile = "patients.csv";
    static String doctorFile = "doctors.csv";
    static String visitFile = "visits.csv";

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("\n--- Clinic Logger ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Log Visit");
            System.out.println("4. View Visits");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> addPatient();
                case "2" -> addDoctor();
                case "3" -> logVisit();
                case "4" -> viewVisits();
                case "0" -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addPatient() throws IOException {
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine());
        System.out.print("Phone: "); String phone = sc.nextLine();
        int id = getNextId(patientFile);
        Patient p = new Patient(id, name, age, phone);
        appendLine(patientFile, p.toCSV());
        System.out.println("Patient added with ID: " + id);
    }

    static void addDoctor() throws IOException {
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Specialty: "); String sp = sc.nextLine();
        int id = getNextId(doctorFile);
        Doctor d = new Doctor(id, name, sp);
        appendLine(doctorFile, d.toCSV());
        System.out.println("Doctor added with ID: " + id);
    }

    static void logVisit() throws IOException {
        System.out.print("Patient ID: "); int pid = Integer.parseInt(sc.nextLine());
        System.out.print("Doctor ID: "); int did = Integer.parseInt(sc.nextLine());
        System.out.print("Date (YYYY-MM-DD): "); String date = sc.nextLine();
        System.out.print("Reason: "); String reason = sc.nextLine();
        int id = getNextId(visitFile);
        Visit v = new Visit(id, pid, did, date, reason);
        appendLine(visitFile, v.toCSV());
        System.out.println("Visit logged with ID: " + id);
    }

    static void viewVisits() throws IOException {
        List<String> lines = readLines(visitFile);
        if (lines.isEmpty()) {
            System.out.println("No visits found.");
            return;
        }
        for (String line : lines) {
            Visit v = Visit.fromCSV(line);
            System.out.println("Visit ID: " + v.id + ", Patient: " + v.patientId +
                               ", Doctor: " + v.doctorId + ", Date: " + v.date +
                               ", Reason: " + v.reason);
        }
    }

    static int getNextId(String file) throws IOException {
        List<String> lines = readLines(file);
        if (lines.isEmpty()) return 1;
        String last = lines.get(lines.size() - 1);
        return Integer.parseInt(last.split(",")[0]) + 1;
    }

    static void appendLine(String file, String line) throws IOException {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(line + "\n");
        }
    }

    static List<String> readLines(String file) throws IOException {
        File f = new File(file);
        if (!f.exists()) return new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) lines.add(line);
            return lines;
        }
    }
}
