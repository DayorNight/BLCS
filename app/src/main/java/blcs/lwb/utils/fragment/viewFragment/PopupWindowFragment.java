package blcs.lwb.utils.fragment.viewFragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinPopup;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class PopupWindowFragment extends BaseFragment {
    @BindView(R.id.tv_pos)
    TextView tv_pos;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_popupwindow;
    }

    @Override
    protected void initView() {
        View view = activity.getLayoutInflater().inflate(R.layout.layout_popup, null);
        LinPopup.getInstance(activity, view);
    }

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) { }

    @OnClick({R.id.btn_up_left, R.id.btn_up_right, R.id.btn_down_left, R.id.btn_down_right,
            R.id.btn_up_center,R.id.btn_down_center,R.id.btn_left_center,R.id.btn_right_center,
            R.id.btn_left_up,R.id.btn_left_down,R.id.btn_right_up,R.id.btn_right_down,
            R.id.btn_up,R.id.btn_down,R.id.btn_left,R.id.btn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_up_left:
                LinPopup.showUpLeft(tv_pos);
                break;
            case R.id.btn_up_right:
                LinPopup.showUpRight(tv_pos);
                break;
            case R.id.btn_down_left:
                LinPopup.showDownLeft(tv_pos);
                break;
            case R.id.btn_down_right:
                LinPopup.showDownRight(tv_pos);
                break;
            case R.id.btn_up_center:
                LinPopup.showUpCenter(tv_pos);
                break;
            case R.id.btn_down_center:
                LinPopup.showDownCenter(tv_pos);
                break;
            case R.id.btn_left_center:
                LinPopup.showLeftCenter(tv_pos);
                break;
            case R.id.btn_right_center:
                LinPopup.showRightCenter(tv_pos);
                break;
            case R.id.btn_left_up:
                LinPopup.showLeftUp(tv_pos);
                break;
            case R.id.btn_left_down:
                LinPopup.showLeftDown(tv_pos);
                break;
            case R.id.btn_right_up:
                LinPopup.showRightUp(tv_pos);
                break;
            case R.id.btn_right_down:
                LinPopup.showRightDown(tv_pos);
                break;
            case R.id.btn_up:
                LinPopup.showUp(activity);
                break;
            case R.id.btn_down:
                LinPopup.showDown(activity);
                break;
            case R.id.btn_left:
                LinPopup.showLeft(activity);
                break;
            case R.id.btn_right:
                LinPopup.showRight(activity);
                break;
        }
    }

}
