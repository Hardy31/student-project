package edu.javacourse.studentorder.domain.wedding;

public class Street {

    private  String streetName;
    private long streetCode ;

    public Street() {
    }

    public Street(long streetCode, String streetName) {
        this.streetCode = streetCode;
        this.streetName = streetName;
    }

    public long getStreetCode() {
        return streetCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetCode(long streetCode) {
        this.streetCode = streetCode;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
