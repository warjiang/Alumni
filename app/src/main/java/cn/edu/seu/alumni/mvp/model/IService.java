package cn.edu.seu.alumni.mvp.model;



import cn.edu.seu.alumni.javabean.http.LoginAlumniRequest;
import cn.edu.seu.alumni.javabean.http.LoginAlumniResponse;
import cn.edu.seu.alumni.javabean.http.LoginWeiboOrWeixinResponse;
import cn.edu.seu.alumni.javabean.http.LoginWeiboRequest;
import cn.edu.seu.alumni.javabean.http.LoginWeixinRequest;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * 定义http请求与后台的交互接口
 */
public interface IService {

    //Alumni登录
    @POST("/v1.0/auth/login/")
    void loginAlumni(@Body LoginAlumniRequest request, Callback<LoginAlumniResponse> callback);

    //Weibo登录
    @POST("/v1.0/auth/weibo/login/")
    void loginWeibo(@Body LoginWeiboRequest request, Callback<LoginWeiboOrWeixinResponse> callback);

    //Weixin登录
    @POST("/v1.0/auth/weixin/login/")
    void loginWeixin(@Body LoginWeixinRequest request, Callback<LoginWeiboOrWeixinResponse> callback);




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
