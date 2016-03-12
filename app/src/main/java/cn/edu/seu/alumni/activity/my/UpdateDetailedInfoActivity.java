package cn.edu.seu.alumni.activity.my;

import butterknife.OnClick;
import cn.edu.seu.alumni.R;
import cn.edu.seu.alumni.activity.SwipeBackBaseActivity;

public class UpdateDetailedInfoActivity extends SwipeBackBaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_edit_detailed_info;
    }

    @OnClick(R.id.me_edit_info_photo_tr)
    public void click1(){
//          jump(PhotoPickerActivity.class);
//        PhotoP
//        PhotoPickerIntent intent = new PhotoPickerIntent(EditInformationActiviy.this);
//        intent.setPhotoCount(1);
//        intent.setShowCamera(true);
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
        setToolbarTitle("基本资料");


    }
}
