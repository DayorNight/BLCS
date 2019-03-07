package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.dialog.fragment.BottomDialogFragment;
import blcs.lwb.utils.R;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.OnClick;

public class DialogBottomFragment extends BaseFragment {

    private BottomDialogFragment fragment;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_dialog_bottom;
    }

    @Override
    protected void initView() {
        fragment = new BottomDialogFragment(MyUtils.getArray(activity, R.array.WeChat_Function));

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


    @OnClick({R.id.btn_dialog_top, R.id.btn_dialog_bottom, R.id.btn_dialog_left, R.id.btn_dialog_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_top:
                break;
            case R.id.btn_dialog_bottom:
                fragment.show(getFragmentManager(),"2222222");
                break;
            case R.id.btn_dialog_left:
                break;
            case R.id.btn_dialog_right:
                break;
        }
    }
}
