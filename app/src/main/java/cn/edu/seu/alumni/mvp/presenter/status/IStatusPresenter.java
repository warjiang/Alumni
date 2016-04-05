package cn.edu.seu.alumni.mvp.presenter.status;

import java.util.List;

/**
 * Created by my on 2016/3/3.
 */
public interface IStatusPresenter {

    void postStatus(List<String> images, String statusText);
}
