package cn.edu.seu.alumni.mvp.model;



import cn.edu.seu.alumni.javabean.PostStatusRequest;
import cn.edu.seu.alumni.javabean.PostStatusResponse;
import cn.edu.seu.alumni.javabean.StatusItem;
import cn.edu.seu.alumni.javabean.auth.AuthResponse;
import cn.edu.seu.alumni.javabean.auth.LoginAlumniRequest;
import cn.edu.seu.alumni.javabean.auth.LoginWeiboRequest;
import cn.edu.seu.alumni.javabean.auth.LoginWeixinRequest;
import cn.edu.seu.alumni.javabean.auth.RegisterAlumniRequest;
import cn.edu.seu.alumni.javabean.user_info.UserBasicInfo;
import cn.edu.seu.alumni.javabean.user_info.UserDetailedInfo;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * 定义http请求与后台的交互接口
 */
public interface IService {

    /**
     *  auth模块
     */

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


    /**
     * user_info模块
     */

    //获取基本资料
    @GET("/v1.0/user/{user_id}")
    void getUserBasicInfo(@Path("user_id") String user_id, Callback<UserBasicInfo> callback);

    //更新基本资料，下同
    @PUT("/v1.0/user/")
    void updateUserName(@Body String name, Callback callback);

    @PUT("/v1.0/user/")
    void updateUserImageUrl(@Body String image, Callback callback);

    @PUT("/v1.0/user/")
    void updateUserEnrollYear(@Body String enroll_year, Callback callback);

    @PUT("/v1.0/user/")
    void updateUserSchool(@Body String school, Callback callback);

    @PUT("/v1.0/user/")
    void updateUserMajor(@Body String major, Callback callback);

    @PUT("/v1.0/user/")
    void updateUserStudentNum(@Body String student_num, Callback callback);

    @PUT("/v1.0/user/")
    void updateUserLocation(@Body String location, Callback callback);

    //获取详细资料
    @GET("/v1.0/user/info/{user_id}")
    void getUserDetailedInfo(@Path("user_id") String user_id, Callback<UserDetailedInfo> callback);


//    //创建七牛上传凭证
//    @POST("/v1.0/static/token/")
//    void createQiNiuToken(Callback<QiniuRes> cb);
//
//    //注册
//    @POST("/v1.0/user/")
//    void register(@Body RegisterReq req, Callback<LoginRes> cb);
//
//
//    //获取用户信息
//    @GET("/v1.0/user/{user_id}")
//    void getUserInfo(@Path("user_id") String user_id, Callback<UserInfoRes> cb);


    //获取最新动态
    @GET("/v1.0/news/timeline/{page}")
    void getLatestStatus(@Path("page") int page, Callback<StatusItem[]> cb);

    //发布动态
    @POST("/v1.0/news/")
    void postStatus(@Body PostStatusRequest request, Callback<PostStatusResponse> cb);

}
