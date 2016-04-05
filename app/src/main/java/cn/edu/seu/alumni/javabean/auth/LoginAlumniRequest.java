package cn.edu.seu.alumni.javabean.auth;

public class LoginAlumniRequest {

    public LoginAlumniRequest() {
    }

    public LoginAlumniRequest(String phone_num, String password) {
        this.phone_num = phone_num;
        this.password = password;
    }

    private String phone_num;

    private String password;

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
