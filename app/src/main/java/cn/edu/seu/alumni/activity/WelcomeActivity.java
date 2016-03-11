package cn.edu.seu.alumni.activity;

import android.os.Handler;
import android.os.Looper;
import android.widget.Button;

import butterknife.Bind;
import butterknife.OnClick;
import cn.edu.seu.alumni.R;

public class WelcomeActivity extends BaseActivity{

    private Jump runnable;

    @Bind(R.id.skip_button)
    Button skipButton;

    @OnClick(R.id.skip_button)
    public void skipButtonOnClick(){
        runnable.skip();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.welcome;
    }

    @Override
    protected boolean hasToolBar() {
        return false;
    }

    @Override
    protected boolean hasToolBarBackButton() {
        return false;
    }

    @Override
    protected void initial() {
        runnable = new Jump(2000);
        new Thread(runnable).start();
    }



    private class Jump implements Runnable{

        private long waitTime;
        private long startTime;
        private boolean running;
        private Handler handler;

        public Jump(long waitTime) {
            this.waitTime = waitTime;
            this.running = true;
            this.startTime = System.currentTimeMillis();
            this.handler = new Handler(Looper.getMainLooper());
        }

        @Override
        public void run() {
            while(running){
                if(System.currentTimeMillis() - startTime >= waitTime){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            jumpThenFinish(LoginActivity.class);
                        }
                    });
                    running = false;
                }
            }
        }

        public void skip(){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    jumpThenFinish(LoginActivity.class);
                }
            });
            running = false;
        }
    }

    @Override
    public void onBackPressed() {
    }
}
