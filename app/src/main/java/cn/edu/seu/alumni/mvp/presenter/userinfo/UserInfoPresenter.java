package cn.edu.seu.alumni.mvp.presenter.userinfo;

import android.os.Handler;
import android.os.Looper;

import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.application.App;
import cn.edu.seu.alumni.javabean.user_info.UserBasicInfo;
import cn.edu.seu.alumni.javabean.user_info.UserDetailedInfo;
import cn.edu.seu.alumni.mvp.model.IService;
import cn.edu.seu.alumni.mvp.model.ServiceProvider;
import cn.edu.seu.alumni.mvp.view.userinfo.IUserInfoView;
import cn.edu.seu.alumni.util.CommonUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UserInfoPresenter implements IUserInfoPresenter {

    private IUserInfoView iUserInfoView;
    private Handler handler;

    private static final int USER_NAME = 0;
    private static final int USER_IMAGE_URL = 1;
    private static final int USER_ENROLL_YEAR= 2;
    private static final int USER_SCHOOL = 3;
    private static final int USER_MAJOR = 4;
    private static final int USER_STUDENT_NUM = 5;
    private static final int USER_LOCATION = 6;


    public UserInfoPresenter(IUserInfoView iUserInfoView) {
        this.iUserInfoView = iUserInfoView;
        this.handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 获取用户基本信息
     */
    @Override
    public void getUserBasicInfo(String user_id) {

        if(!CommonUtil.Network.isConnected(App.getContext())){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    iUserInfoView.doGetUserBasicInfoFailure(App.getContext().getString(R.string.error_reason_network));
                }
            });
            return;
        }
        IService service = ServiceProvider.getService();
        service.getUserBasicInfo(user_id, new Callback<UserBasicInfo>() {
            @Override
            public void success(UserBasicInfo userBasicInfo, Response response) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        //TODO 存储数据
                        iUserInfoView.doGetUserBasicInfoSucceed();
                    }
                });
            }

            @Override
            public void failure(final RetrofitError error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUserInfoView.doGetUserBasicInfoFailure(CommonUtil.Retrofit.getErrorReason(error));
                    }
                });
            }
        });

    }

    /**
     * 更新用户基本信息
     */
    private void updateUserBasicInfo(final int infoType, String data){
        if(!CommonUtil.Network.isConnected(App.getContext())){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    iUserInfoView.doUpdateUserBasicInfoFailure(App.getContext().getString(R.string.error_reason_network));
                }
            });
            return;
        }
        IService service = ServiceProvider.getService();
        Callback callback = new Callback() {
            @Override
            public void success(Object o, Response response) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        switch(infoType){
                            case USER_NAME:
                                iUserInfoView.doUpdateUserNameSucceed();
                                break;
                            case USER_IMAGE_URL:
                                iUserInfoView.doUpdateUserImageUrlSucceed();
                                break;
                            case USER_ENROLL_YEAR:
                                iUserInfoView.doUpdateUserEnrollYearSucceed();
                                break;
                            case USER_SCHOOL:
                                iUserInfoView.doUpdateUserSchoolSucceed();
                                break;
                            case USER_MAJOR:
                                iUserInfoView.doUpdateUserMajorSucceed();
                                break;
                            case USER_STUDENT_NUM:
                                iUserInfoView.doUpdateUserStudentNumSucceed();
                                break;
                            case USER_LOCATION:
                                iUserInfoView.doUpdateUserLocationSucceed();
                                break;
                        }
                    }
                });
            }

            @Override
            public void failure(final RetrofitError error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUserInfoView.doUpdateUserBasicInfoFailure(CommonUtil.Retrofit.getErrorReason(error));
                    }
                });
            }
        };
        switch(infoType){
            case USER_NAME:
                service.updateUserName(data, callback);
                break;
            case USER_IMAGE_URL:
                service.updateUserImageUrl(data, callback);
                break;
            case USER_ENROLL_YEAR:
                service.updateUserEnrollYear(data, callback);
                break;
            case USER_SCHOOL:
                service.updateUserSchool(data, callback);
                break;
            case USER_MAJOR:
                service.updateUserMajor(data, callback);
                break;
            case USER_STUDENT_NUM:
                service.updateUserStudentNum(data, callback);
                break;
            case USER_LOCATION:
                service.updateUserLocation(data, callback);
                break;
        }
    }

    @Override
    public void updateUserName(String name) {
        updateUserBasicInfo(USER_NAME, name);
    }

    @Override
    public void updateUserImageUrl(String image) {
        updateUserBasicInfo(USER_IMAGE_URL, image);
    }

    @Override
    public void updateUserEnrollYear(String enroll_year) {
        updateUserBasicInfo(USER_ENROLL_YEAR, enroll_year);
    }

    @Override
    public void updateUserSchool(String school) {
        updateUserBasicInfo(USER_SCHOOL, school);
    }

    @Override
    public void updateUserMajor(String major) {
        updateUserBasicInfo(USER_MAJOR, major);
    }

    @Override
    public void updateUserStudentNum(String student_num) {
        updateUserBasicInfo(USER_STUDENT_NUM, student_num);
    }

    @Override
    public void updateUserLocation(String location) {
        updateUserBasicInfo(USER_LOCATION, location);
    }

    /**
     * 获取用户详细信息
     */
    @Override
    public void getUserDetailedInfo(String user_id) {
        if(!CommonUtil.Network.isConnected(App.getContext())){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    iUserInfoView.doGetUserDetailedInfoFailure(App.getContext().getString(R.string.error_reason_network));
                }
            });
            return;
        }
        IService service = ServiceProvider.getService();
        service.getUserDetailedInfo(user_id, new Callback<UserDetailedInfo>() {
            @Override
            public void success(UserDetailedInfo userDetailedInfo, Response response) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        //TODO 存储数据
                        iUserInfoView.doGetUserDetailedInfoSucceed();
                    }
                });
            }

            @Override
            public void failure(final RetrofitError error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUserInfoView.doGetUserDetailedInfoFailure(CommonUtil.Retrofit.getErrorReason(error));
                    }
                });
            }
        });
    }

}
