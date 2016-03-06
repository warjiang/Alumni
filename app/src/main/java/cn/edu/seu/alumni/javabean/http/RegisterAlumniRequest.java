package cn.edu.seu.alumni.javabean.http;

public class RegisterAlumniRequest {
    private String phone_num;
    private String enroll_year;
    private String school;
    private String major;
    private String password;

    public RegisterAlumniRequest() {
    }

    public RegisterAlumniRequest(String phone_num, String enroll_year, String school, String major, String password) {
        this.phone_num = phone_num;
        this.enroll_year = enroll_year;
        this.school = school;
        this.major = major;
        this.password = password;
    }
}
