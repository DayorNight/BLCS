package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.lwbtool.utils.dialog.dialogFragment.BaseDialogFragment;
import blcs.lwb.lwbtool.utils.dialog.dialogFragment.CustomDialogFragment;
import blcs.lwb.lwbtool.utils.dialog.dialogFragment.ListDialogFragment;
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
    private ListDialogFragment init;
    private View layout;
    private BaseDialogFragment dialogFragment;
    private CustomDialogFragment comDialog;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_dialog_bottom;
    }

    @Override
    protected void initView() {
        datas = MyUtils.getArray(activity, R.array.WeChat_Function);
        init = ListDialogFragment.init(datas).setOnClickListener(new ListDialogFragment.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RxToast.info(activity, datas.get(position));
                init.dismiss();
            }
        });
        comDialog = CustomDialogFragment.init();
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

    @OnClick({R.id.btn_dialog_default,R.id.btn_dialog_top, R.id.btn_dialog_bottom, R.id.btn_dialog_left, R.id.btn_dialog_right,
            R.id.btn_dialog_center, R.id.btn_dialog_custom_image, R.id.btn_dialog_custom_sure, R.id.btn_dialog_custom_cancle,
            R.id.btn_dialog_custom_editext,R.id.btn_dialog_custom_loading,R.id.btn_dialog_custom_big_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog_default:
                init.show(getFragmentManager());
                break;
            case R.id.btn_dialog_top:
                init.show(getFragmentManager(),Gravity.TOP);
                break;
            case R.id.btn_dialog_bottom:
                init.show(getFragmentManager(),Gravity.BOTTOM);
                break;
            case R.id.btn_dialog_left:
                init.show(getFragmentManager(),Gravity.LEFT);
                break;
            case R.id.btn_dialog_right:
                init.show(getFragmentManager(),Gravity.RIGHT);
                break;
            case R.id.btn_dialog_center:
                init.show(getFragmentManager(),Gravity.CENTER);
                break;
            case R.id.btn_dialog_custom_image:
                comDialog.setImage(R.mipmap.img_view).setType(CustomDialogFragment.TYPE_IMAGE).show(getFragmentManager());
                break;
            case R.id.btn_dialog_custom_sure:
                comDialog.setTitle("提示").
                        setContent("内容不能为空").
                        setType(CustomDialogFragment.TYPE_SURE)
                        .setOnClickListener(new CustomDialogFragment.OnSureListener() {
                            @Override
                            public void clickSure() {
                                RxToast.info(activity,"确定");
                            }
                        }).show(getFragmentManager());
                break;
            case R.id.btn_dialog_custom_cancle:
                comDialog.setTitle(getString(R.string.app_name)).
                        setContent("是否退出登录？").
                        setType(CustomDialogFragment.TYPE_CANCLE)
                        .setOnClickListener(new CustomDialogFragment.OnSureCancleListener() {
                            @Override
                            public void clickSure(String str) {
                                RxToast.info(activity,"确定");
                            }
                            @Override
                            public void clickCancle() {
                                RxToast.info(activity,"取消");
                            }
                        }).show(getFragmentManager());
                break;
            case R.id.btn_dialog_custom_editext:
                comDialog.setTitle(getString(R.string.app_name)).
                        setType(CustomDialogFragment.TYPE_EDITEXT)
                        .setOnClickListener(new CustomDialogFragment.OnSureCancleListener() {
                            @Override
                            public void clickSure(String str) {
                                RxToast.info(activity,str);
                            }
                            @Override
                            public void clickCancle() {
                                RxToast.info(activity,"取消");
                            }
                        }).show(getFragmentManager());
                break;

            case R.id.btn_dialog_custom_loading:
                comDialog.setType(CustomDialogFragment.TYPE_LOADING).show(getFragmentManager());
                break;
            case R.id.btn_dialog_custom_big_image:
                comDialog.setImage(R.mipmap.ic1).setType(CustomDialogFragment.TYPE_IMAGE_BIG).show(getFragmentManager());
                break;
        }
    }
}
