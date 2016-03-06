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

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEnroll_year() {
        return enroll_year;
    }

    public void setEnroll_year(String enroll_year) {
        this.enroll_year = enroll_year;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
