package edu.javacourse.studentorder.domain;

//CREATE TABLE jc_registre_office(
//        r_office_id INTEGER NOT NULL,
//        r_office_area_id CHAR(12) NOT NULL,
//        r_office_name VARCHAR(200),
//        PRIMARY KEY (r_office_id),
//        FOREIGN KEY (r_office_area_id) REFERENCES jc_country_struct(area_id) ON DELETE RESTRICT
//        );

public class RegisterOffice
{
    private Long officeId;
    private String officeAreaId;
    private String officeName;

    public RegisterOffice() {
    }

    public RegisterOffice(Long officeId, String officeAreaId, String officeName) {
        this.officeId = officeId;
        this.officeAreaId = officeAreaId;
        this.officeName = officeName;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeAreaId() {
        return officeAreaId;
    }

    public void setOfficeAreaId(String officeAreaId) {
        this.officeAreaId = officeAreaId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    @Override
    public String toString() {
        return "RegisterOffice{" +
                "officeId=" + officeId +
                ", officeAreaId='" + officeAreaId + '\'' +
                ", officeName='" + officeName + '\'' +
                '}';
    }
}
