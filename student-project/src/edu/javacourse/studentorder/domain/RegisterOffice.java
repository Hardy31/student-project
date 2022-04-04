package edu.javacourse.studentorder.domain;

//CREATE TABLE jc_registre_office(
//        r_office_id INTEGER NOT NULL,
//        r_office_area_id CHAR(12) NOT NULL,
//        r_office_name VARCHAR(200),
//        PRIMARY KEY (r_office_id),
//        FOREIGN KEY (r_office_area_id) REFERENCES jc_country_struct(area_id) ON DELETE RESTRICT
//        );

public class RegisterOffice {
    private Long registerId;
    private String registerAreaId;
    private String registerName;

    public RegisterOffice() {
    }

    public RegisterOffice(Long registerId, String registerAreaId, String registerName) {
        this.registerId = registerId;
        this.registerAreaId = registerAreaId;
        this.registerName = registerName;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public String getRegisterAreaId() {
        return registerAreaId;
    }

    public void setRegisterAreaId(String registerAreaId) {
        this.registerAreaId = registerAreaId;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }
}
