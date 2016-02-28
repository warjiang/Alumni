package cn.edu.seu.alumni.model;


/**
 * 错误信息
 */
public class Error {

    private String reason;
    private String state;
    private int code;


    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    public String getState() {
        return state;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setState(String state) {
        this.state = state;
    }

}
