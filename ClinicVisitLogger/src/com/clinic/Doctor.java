
package com.clinic;

public class Doctor {
    public int id;
    public String name;
    public String specialty;

    public Doctor(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public String toCSV() {
        return id + "," + name + "," + specialty;
    }

    public static Doctor fromCSV(String line) {
        String[] parts = line.split(",");
        return new Doctor(Integer.parseInt(parts[0]), parts[1], parts[2]);
    }
}
