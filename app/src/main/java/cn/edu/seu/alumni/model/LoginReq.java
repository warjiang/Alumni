package cn.edu.seu.alumni.model;

public class LoginReq {

    private String phone_num;

    private String password;

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public String getPassword() {
        return password;
    }
}
