package test.zzk;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;

import butterknife.Bind;
import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.BaseActivity;
import test.dwj.DecodeActivity;
import test.dwj.GenerateActivity;
import cn.edu.seu.alumni.activity.other.MainActivity;
import test.dwj.ScanActivity;

public class DeveloperActivity extends BaseActivity{

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    protected boolean hasToolBarBackButton() {
        return true;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.test;
    }

    @Override
    protected void initial() {
        setToolbarTitle("开发者专属");
    }

    @Bind(R.id.btn_activity_qrcode_decode)
    Button btn_activity_qrcode_decode;
    @Bind(R.id.btn_activity_qrcode_generate)
    Button btn_activity_qrcode_generate;
    @Bind(R.id.btn_activity_qrcode_scan)
    Button btn_activity_qrcode_scan;

    @OnClick(R.id.btn_activity_qrcode_scan)
    void scanQRCode(){

        Log.d("aa", "scanQRCode");
        startActivity(new Intent(this, ScanActivity.class));
    }
    @OnClick(R.id.btn_activity_qrcode_generate)
    void generateQRCode(){

        Log.d("aa","generateQRCode");
        startActivity(new Intent(this,GenerateActivity.class));
    }
    @OnClick(R.id.btn_activity_qrcode_decode)
    void decodeQRCode(){

        Log.d("aa", "decodeQRCode");
        startActivity(new Intent(this, DecodeActivity.class));
    }

    @Bind(R.id.tmp)
    Button tmp;
    @OnClick((R.id.tmp))
    void tmpButtonOnClick(){
        jumpThenFinish(MainActivity.class);
    }
}
