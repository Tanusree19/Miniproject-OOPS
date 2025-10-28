package com.clinic;

public class Visit {
    public int id;
    public int patientId;
    public int doctorId;
    public String date;
    public String reason;

    public Visit(int id, int patientId, int doctorId, String date, String reason) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.reason = reason;
    }

    public String toCSV() {
        return id + "," + patientId + "," + doctorId + "," + date + "," + reason;
    }

    public static Visit fromCSV(String line) {
        String[] parts = line.split(",");
        return new Visit(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),
                         Integer.parseInt(parts[2]), parts[3], parts[4]);
    }
}
