package cn.edu.seu.alumni.activity;

import android.os.Handler;
import android.os.Looper;

import cn.edu.seu.alumni.R;

public class WelcomeActivity extends BaseActivity{

    @Override
    protected int getContentViewId() {
        return R.layout.welcome;
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }

    @Override
    protected void initial() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpThenFinish(RegisterActivity.class);
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
    }
}
