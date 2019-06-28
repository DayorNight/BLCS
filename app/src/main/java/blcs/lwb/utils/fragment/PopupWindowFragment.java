package blcs.lwb.utils.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinPopup;
import blcs.lwb.utils.R;
import butterknife.BindView;
import butterknife.OnClick;

public class PopupWindowFragment extends BaseFragment {
    @BindView(R.id.btn_pop)
    Button btnPop;
    @BindView(R.id.btn_pop1)
    Button btnPop1;
    @BindView(R.id.btn_pop2)
    Button btnPop2;
    @BindView(R.id.btn_pop3)
    Button btnPop3;

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

    @OnClick({R.id.btn_pop, R.id.btn_pop1, R.id.btn_pop2, R.id.btn_pop3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pop:
                if(LinPopup.get().isShowing()){
                    LinPopup.get().dismiss();
                }
                LinPopup.get().showAsDropDown(btnPop);
                break;
            case R.id.btn_pop1:
                if(LinPopup.get().isShowing()){
                    LinPopup.get().dismiss();
                }
                LinPopup.get().showAsDropDown(btnPop1, 10, 30);
                break;
            case R.id.btn_pop2:
                if(LinPopup.get().isShowing()){
                    LinPopup.get().dismiss();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    LinPopup.get().showAsDropDown(btnPop2, 10,30 , Gravity.RIGHT);
                }
                break;
            case R.id.btn_pop3:
                if(LinPopup.get().isShowing()){
                    LinPopup.get().dismiss();
                }
                LinPopup.get().showAtLocation(btnPop3, Gravity.CENTER, 0, 0);
                break;
        }
    }

}
