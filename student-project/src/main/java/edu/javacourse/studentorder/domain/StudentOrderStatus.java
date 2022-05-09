package edu.javacourse.studentorder.domain;

public enum StudentOrderStatus {
    START, CHECKED;

    public static StudentOrderStatus fromValues (int code){
        for (StudentOrderStatus sos: StudentOrderStatus.values() ){
            if (sos.ordinal() == code  ) {
                return sos;
            }
        }
        throw new RuntimeException(" Unknow values: "+ code);
    };
}
