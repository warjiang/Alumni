package cn.edu.seu.alumni.mvp.presenter.auth;

import cn.edu.seu.alumni.javabean.http.RegisterAlumniRequest;

public interface IRegisterPresenter {

    /**
     * 本应用注册
     */
    public void registerAlumni(RegisterAlumniRequest registerAlumniRequest, boolean agreeWithProtocol);

//    /**
//     * 使用微博信息注册
//     */
//    public void registerWeibo();
//
//    /**
//     * 使用微信信息注册
//     */
//    public void registerWeixin();
}
