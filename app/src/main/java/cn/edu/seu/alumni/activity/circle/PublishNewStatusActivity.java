package cn.edu.seu.alumni.activity.circle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.SwipeBackBaseActivity;
import cn.edu.seu.alumni.mvp.presenter.status.StatusPresenter;
import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.entity.Photo;
import me.iwf.photopicker.utils.PhotoPickerIntent;

public class PublishNewStatusActivity extends SwipeBackBaseActivity {

    private final String TAG = PublishNewStatusActivity.class.getSimpleName();

    private final int PICK_PHOTO_REQUEST_CODE = 1;

    //被选中图片的路径列表
    private List<String> photoPaths;

    @Bind(R.id.publish_new_status_edit_text)
    protected EditText editText;

    @Bind(R.id.publish_new_status_add_pic_image_view)
    protected ImageView pickPhotoImageView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_publish_new_status;
    }

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @Override
    protected boolean hasToolBarBackButton() {
        return true;
    }

    @Override
    protected void initial() {

        setToolbarTitle("发动态");

        photoPaths = new ArrayList<>();

        pickPhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerIntent intent = new PhotoPickerIntent(PublishNewStatusActivity.this);
                intent.setPhotoCount(9);
                intent.setShowCamera(true);
                intent.setShowGif(true);
                startActivityForResult(intent, PICK_PHOTO_REQUEST_CODE);
            }
        });

        Button sendButton = (Button)findViewById(R.id.publish_new_status_send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String statusText = editText.getText().toString();
                new StatusPresenter().postStatus(photoPaths, statusText);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_PHOTO_REQUEST_CODE) {

            photoPaths.clear();
            if (null != data) {
                ArrayList<Photo> photos = data.getParcelableArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                for (Photo photo : photos) {
                   photoPaths.add(photo.getPath());
                }
            }

            //TODO:利用gridview将phtotoPaths中的图片显示出来

        }
    }
}
