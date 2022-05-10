package edu.javacourse.studentorder.domain;

public class University {
    private  long universityId;
    private  String universityNmae;

    public University(long universityId, String universityNmae) {
        this.universityId = universityId;
        this.universityNmae = universityNmae;
    }

    public University() {
    }

    public long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(long universityId) {
        this.universityId = universityId;
    }

    public String getUniversityNmae() {
        return universityNmae;
    }

    public void setUniversityNmae(String universityNmae) {
        this.universityNmae = universityNmae;
    }
}
