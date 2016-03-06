package cn.edu.seu.alumni.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import cn.edu.seu.alumni.R;
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



    public void jump(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    public void jump(Class<?> clazz) {

        this.jump(clazz, null);
    }

    public void jumpForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        getActivity().startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    public void jumpForResult(Class<?> clazz, int requestCode) {
        this.jumpForResult(clazz, requestCode, null);
    }

    protected void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
