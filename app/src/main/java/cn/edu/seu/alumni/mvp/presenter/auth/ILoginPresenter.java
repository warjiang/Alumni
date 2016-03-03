package cn.edu.seu.alumni.mvp.presenter.auth;

/**
 * 变量名和后台交互key一致
 */
public interface ILoginPresenter {

    /**
     * 本应用登录
     */
    public void loginAlumni(String phone_num, String password);

    /**
     * 微博登录
     */
    public void loginWeibo(String uid, String access_token);

    /**
     * 微信登录
     */
    public void loginWeixin(String open_id, String access_token);
}
