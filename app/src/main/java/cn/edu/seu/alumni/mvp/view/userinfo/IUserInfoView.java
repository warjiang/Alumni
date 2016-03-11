package cn.edu.seu.alumni.mvp.view.userinfo;

public interface IUserInfoView {
    public void doGetUserBasicInfoSucceed();
    public void doGetUserBasicInfoFailure(String reason);

    public void doUpdateUserNameSucceed();
    public void doUpdateUserImageUrlSucceed();
    public void doUpdateUserEnrollYearSucceed();
    public void doUpdateUserSchoolSucceed();
    public void doUpdateUserMajorSucceed();
    public void doUpdateUserStudentNumSucceed();
    public void doUpdateUserLocationSucceed();
    public void doUpdateUserBasicInfoFailure(String reason);

    public void doGetUserDetailedInfoSucceed();
    public void doGetUserDetailedInfoFailure(String reason);
}
