package blcs.lwb.utils.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinPopup;
import blcs.lwb.utils.R;
import butterknife.BindView;
import butterknife.OnClick;

public class PopupWindowFragment extends BaseFragment {
    @BindView(R.id.btn_up)
    Button btn_up;
    @BindView(R.id.btn_down)
    Button btn_down;
    @BindView(R.id.btn_left)
    Button btn_left;
    @BindView(R.id.btn_right)
    Button btn_right;
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
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @OnClick({R.id.btn_up, R.id.btn_down, R.id.btn_left, R.id.btn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_up:
                if(!LinPopup.get().isShowing()){
                    LinPopup.get().showAsDropDown(tv_pos, 100,-100 );
                }
                break;
            case R.id.btn_down:
                if(!LinPopup.get().isShowing()){
                    LinPopup.get().showAsDropDown(tv_pos);
                }
                break;
            case R.id.btn_left:

                break;
            case R.id.btn_right:

                break;
        }
    }

}
