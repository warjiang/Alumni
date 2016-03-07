package cn.edu.seu.alumni.util;

import cn.edu.seu.alumni.javabean.http.LoginAlumniRequest;
import cn.edu.seu.alumni.javabean.http.LoginWeiboRequest;
import cn.edu.seu.alumni.javabean.http.LoginWeixinRequest;
import cn.edu.seu.alumni.javabean.http.RegisterAlumniRequest;

public class InputChecker {

    public static class CheckResult{
        private boolean legal;
        private String errorReason;
        public boolean isLegal() {
            return legal;
        }
        public void setLegal(boolean legal) {
            this.legal = legal;
        }
        public String getErrorReason() {
            return errorReason;
        }
        public void setErrorReason(String errorReason) {
            this.errorReason = errorReason;
        }
    }

    public static CheckResult check(LoginAlumniRequest request){
        return null;
    }

    public static CheckResult check(LoginWeiboRequest request){
        return null;
    }

    public static CheckResult check(LoginWeixinRequest request){
        return null;
    }

    public static CheckResult check(RegisterAlumniRequest request){
        return null;
    }

}
