package cn.edu.seu.alumni.javabean.http;

public class LoginWeixinRequest {

    private String open_id;
    private String access_token;

    public LoginWeixinRequest() {
    }

    public LoginWeixinRequest(String open_id, String access_token) {
        this.open_id = open_id;
        this.access_token = access_token;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
