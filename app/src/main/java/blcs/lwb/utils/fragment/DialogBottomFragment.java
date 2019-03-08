package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.lwbtool.utils.dialog.dialogFragment.BottomDialogFragment;
import blcs.lwb.lwbtool.utils.dialog.dialogFragment.CustomDialogFragment;
import blcs.lwb.utils.R;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class DialogBottomFragment extends BaseFragment {
    @BindView(R.id.btn_dialog_top)
    Button btnDialogTop;
    @BindView(R.id.btn_dialog_bottom)
    Button btnDialogBottom;
    @BindView(R.id.btn_dialog_left)
    Button btnDialogLeft;
    @BindView(R.id.btn_dialog_right)
    Button btnDialogRight;
    private ArrayList<String> datas;
    private BottomDialogFragment init;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_dialog_bottom;
    }

    @Override
    protected void initView() {
        datas = MyUtils.getArray(activity, R.array.WeChat_Function);
        init = BottomDialogFragment.init(datas).setOnClickListener(new BottomDialogFragment.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RxToast.info(activity, datas.get(position));
                init.dismiss();
            }
        });

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

    @OnClick({R.id.btn_dialog_top, R.id.btn_dialog_bottom, R.id.btn_dialog_left, R.id.btn_dialog_right, R.id.btn_dialog_center, R.id.btn_dialog_custom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_top:
                init.setGravity(Gravity.TOP).show(getFragmentManager());
                break;
            case R.id.btn_dialog_bottom:
                init.setGravity(Gravity.BOTTOM).show(getFragmentManager());
                break;
            case R.id.btn_dialog_left:
                init.setGravity(Gravity.LEFT).show(getFragmentManager());
                break;
            case R.id.btn_dialog_right:
                init.setGravity(Gravity.RIGHT).show(getFragmentManager());
                break;
            case R.id.btn_dialog_center:
                init.setGravity(Gravity.CENTER).show(getFragmentManager());
                break;
            case R.id.btn_dialog_custom:
                CustomDialogFragment.init().show(getFragmentManager());
                break;
        }
    }

}
