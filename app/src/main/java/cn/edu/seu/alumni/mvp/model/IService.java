package cn.edu.seu.alumni.mvp.model;



import android.telecom.Call;

import cn.edu.seu.alumni.javabean.http.AuthResponse;
import cn.edu.seu.alumni.javabean.http.LoginAlumniRequest;
import cn.edu.seu.alumni.javabean.http.LoginWeiboRequest;
import cn.edu.seu.alumni.javabean.http.LoginWeixinRequest;
import cn.edu.seu.alumni.javabean.http.RegisterAlumniRequest;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * 定义http请求与后台的交互接口
 */
public interface IService {

    //Alumni注册
    @POST("/v1.0/user/")
    void registerAlumni(@Body RegisterAlumniRequest request, Callback<AuthResponse> callback);

    //Alumni登录
    @POST("/v1.0/auth/login/")
    void loginAlumni(@Body LoginAlumniRequest request, Callback<AuthResponse> callback);

    //Weibo登录
    @POST("/v1.0/auth/weibo/login/")
    void loginWeibo(@Body LoginWeiboRequest request, Callback<AuthResponse> callback);

    //Weixin登录
    @POST("/v1.0/auth/weixin/login/")
    void loginWeixin(@Body LoginWeixinRequest request, Callback<AuthResponse> callback);




//    //创建七牛上传凭证
//    @POST("/v1.0/static/token/")
//    void createQiNiuToken(Callback<QiniuRes> cb);
//
//    //注册
//    @POST("/v1.0/user/")
//    void register(@Body RegisterReq req, Callback<LoginRes> cb);
//
//    //登录
//    @POST("/v1.0/auth/login/")
//    void login(@Body LoginReq req, Callback<LoginRes> cb);
//
//    //获取用户信息
//    @GET("/v1.0/user/{user_id}")
//    void getUserInfo(@Path("user_id") String user_id, Callback<UserInfoRes> cb);


}
