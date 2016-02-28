package cn.edu.seu.alumni.service;



import cn.edu.seu.alumni.model.LoginReq;
import cn.edu.seu.alumni.model.LoginRes;
import cn.edu.seu.alumni.model.QiniuRes;
import cn.edu.seu.alumni.model.RegisterReq;
import cn.edu.seu.alumni.model.UserInfoRes;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface Service {

    //创建七牛上传凭证
    @POST("/static/token/")
    void createQiNiuToken(Callback<QiniuRes> cb);

    //注册
    @POST("/user/")
    void register(@Body RegisterReq req, Callback<LoginRes> cb);

    //登录
    @POST("/auth/login/")
    void login(@Body LoginReq req, Callback<LoginRes> cb);

    //获取用户信息
    @GET("/user/{user_id}")
    void getUserInfo(@Path("user_id") String user_id, Callback<UserInfoRes> cb);


}
