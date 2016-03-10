package cn.edu.seu.alumni.javabean.auth;

public class AuthResponse {

    private String user_id;
    private String access_token;

    public AuthResponse() {
    }

    public AuthResponse(String user_id, String access_token) {
        this.user_id = user_id;
        this.access_token = access_token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
