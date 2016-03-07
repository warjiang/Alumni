package cn.edu.seu.alumni.util;

import cn.edu.seu.alumni.javabean.http.LoginAlumniRequest;
import cn.edu.seu.alumni.javabean.http.LoginWeiboRequest;
import cn.edu.seu.alumni.javabean.http.LoginWeixinRequest;
import cn.edu.seu.alumni.javabean.http.RegisterAlumniRequest;

public class InputChecker {

    public static class CheckResult{
        private boolean legal;
        private String errorReason;

        public CheckResult() {
            this(false, "未知错误");
        }

        private CheckResult(boolean legal, String errorReason) {
            this.legal = legal;
            this.errorReason = errorReason;
        }

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

    /**
     * Alumni注册校验
     */
    public static CheckResult check(RegisterAlumniRequest request){
        if(CommonUtil.String.isEmpty(request.getPhone_num())){
            return new CheckResult(false, "手机号不能为空");
        }
        if(CommonUtil.Regex.match("^1\\d{10}$", request.getPhone_num())){
            return new CheckResult(false, "手机号格式错误");
        }
        if(CommonUtil.String.isEmpty(request.getPassword())){
            return new CheckResult(false, "密码不能为空");
        }
        if(request.getPassword().length() < 6 || request.getPassword().length() > 12){
            return new CheckResult(false, "密码应为6-12位");
        }
        if(CommonUtil.String.isEmpty(request.getEnroll_year())){
            return new CheckResult(false, "入学年份不能为空");
        }
        if(CommonUtil.Regex.match("^\\d{4}$", request.getEnroll_year())){
            return new CheckResult(false, "入学年份格式错误");
        }
        if(CommonUtil.String.isEmpty(request.getSchool())){
            return new CheckResult(false, "学院不能为空");
        }
        if(CommonUtil.String.isEmpty(request.getMajor())){
            return new CheckResult(false, "专业不能为空");
        }
        return new CheckResult(true, null);
    }

    /**
     * Alumni登录校验
     */
    public static CheckResult check(LoginAlumniRequest request){
        if(CommonUtil.String.isEmpty(request.getPhone_num())){
            return new CheckResult(false, "手机号不能为空");
        }
        if(CommonUtil.Regex.match("^1\\d{10}$", request.getPhone_num())){
            return new CheckResult(false, "手机号格式错误");
        }
        if(CommonUtil.String.isEmpty(request.getPassword())){
            return new CheckResult(false, "密码不能为空");
        }
        if(request.getPassword().length() < 6 || request.getPassword().length() > 12){
            return new CheckResult(false, "密码应为6-12位");
        }
        return new CheckResult(true, null);
    }

    /**
     * Weibo登录校验
     */
    public static CheckResult check(LoginWeiboRequest request){
        return new CheckResult(false, "未启用");
    }

    /**
     * Weixin登录校验
     */
    public static CheckResult check(LoginWeixinRequest request){
        return new CheckResult(false, "未启用");
    }



}
