package cn.edu.seu.alumni.javabean.http;

public class LoginWeiboRequest {
    private String uid;
    private String access_token;

    public LoginWeiboRequest() {
    }

    public LoginWeiboRequest(String uid, String access_token) {
        this.uid = uid;
        this.access_token = access_token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
