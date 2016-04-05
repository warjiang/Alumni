package cn.edu.seu.alumni.mvp.presenter.userinfo;



public interface IUserInfoPresenter {

    public void getUserBasicInfo(String user_id);

    public void updateUserName(String name);

    public void updateUserImageUrl(String image);

    public void updateUserEnrollYear(String enroll_year);

    public void updateUserSchool(String school);

    public void updateUserMajor(String major);

    public void updateUserStudentNum(String student_num);

    public void updateUserLocation(String location);

    public void getUserDetailedInfo(String user_id);

}
