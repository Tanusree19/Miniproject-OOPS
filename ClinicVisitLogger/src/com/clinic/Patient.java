package com.clinic;

public class Patient {
    public int id;
    public String name;
    public int age;
    public String phone;

    public Patient(int id, String name, int age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public String toCSV() {
        return id + "," + name + "," + age + "," + phone;
    }

    public static Patient fromCSV(String line) {
        String[] parts = line.split(",");
        return new Patient(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]), parts[3]);
    }
}
