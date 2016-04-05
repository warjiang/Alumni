package thirdpart.qrcode.decode;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.CountDownLatch;

import test.dwj.ScanActivity;

//import jiang.seu.edu.qrcode.ScanActivity;

//import com.jumtop.qrscan.activity.ScanActivity;

public class DecodeThread extends Thread {

    public static final String BARCODE_BITMAP = "BARCODE_BITMAP";
    public static final String DECODE_MODE = "DECODE_MODE";
    public static final String DECODE_TIME = "DECODE_TIME";

    private final ScanActivity activity;
    private Handler handler;
    private final CountDownLatch handlerInitLatch;

    public DecodeThread(ScanActivity activity) {

        this.activity = activity;
        handlerInitLatch = new CountDownLatch(1);
    }



    public Handler getHandler() {
        try {
            handlerInitLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return handler;
    }

    @Override
    public void run() {
        Looper.prepare();
        handler = new DecodeHandler(activity);
        handlerInitLatch.countDown();
        Looper.loop();
    }

}
