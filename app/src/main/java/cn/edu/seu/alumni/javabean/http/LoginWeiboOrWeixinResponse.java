package cn.edu.seu.alumni.javabean.http;

public class LoginWeiboOrWeixinResponse {
    private String access_token;

    public LoginWeiboOrWeixinResponse() {
    }

    public LoginWeiboOrWeixinResponse(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
