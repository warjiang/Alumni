package cn.edu.seu.alumni.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cn.edu.seu.alumni.activity.BaseActivity;

/**
 *  Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    private BaseActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(getContentViewId(), container, false);

        ButterKnife.bind(this, view);

        initial();

        return view;
    }

    /**
     * 设置布局文件
     */
    protected abstract int getContentViewId();

    /**
     * 初始化
     */
    protected abstract void initial();



    public void jumpTo(Class<?> clazz, Bundle bundle) {
        activity.jumpTo(clazz, bundle);
    }

    public void jumpTo(Class<?> clazz) {
        activity.jumpTo(clazz, null);
    }

    public void jumpToForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        activity.jumpToForResult(clazz, requestCode, bundle);
    }

    public void jumpToForResult(Class<?> clazz, int requestCode) {
        activity.jumpToForResult(clazz, requestCode, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
