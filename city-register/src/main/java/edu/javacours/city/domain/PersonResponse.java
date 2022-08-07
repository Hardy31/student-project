package edu.javacours.city.domain;

public class PersonResponse {
    private  boolean registered;
    private  boolean temporal;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isTemporal() {
        return temporal;
    }

    public void setTemporal(boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public String toString() {
        return "PersonResponse{" +
                "registered=" + registered +
                ", temporal=" + temporal +
                ", status='" + status + '\'' +
                '}';
    }
}
