package com.nik.springcloud.entities;

public class Payment {
    private long id;
    private String serial;

    public Payment(long id, String serial) {
        this.id = id;
        this.serial = serial;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Parment{" +
                "id=" + id +
                ", serial='" + serial + '\'' +
                '}';
    }
}
