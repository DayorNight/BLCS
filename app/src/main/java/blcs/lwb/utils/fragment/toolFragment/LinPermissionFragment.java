package blcs.lwb.utils.fragment.toolFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import java.util.ArrayList;
import java.util.List;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinCommon;
import blcs.lwb.lwbtool.utils.LinPermission;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class LinPermissionFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.cb_permission_0)
    CheckBox cbPermission0;
    @BindView(R.id.cb_permission_1)
    CheckBox cbPermission1;
    @BindView(R.id.cb_permission_2)
    CheckBox cbPermission2;
    @BindView(R.id.cb_permission_3)
    CheckBox cbPermission3;
    @BindView(R.id.cb_permission_4)
    CheckBox cbPermission4;
    @BindView(R.id.cb_permission_5)
    CheckBox cbPermission5;
    @BindView(R.id.cb_permission_6)
    CheckBox cbPermission6;
    @BindView(R.id.cb_permission_7)
    CheckBox cbPermission7;
    @BindView(R.id.cb_permission_8)
    CheckBox cbPermission8;
    @BindView(R.id.btn_permission_check)
    Button btn_permission_check;
    @BindView(R.id.btn_permission_check_much)
    Button btn_permission_check_much;
    private List<Integer> codes = new ArrayList<>();
    private CheckBox[] cbs;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_permission;
    }

    @Override
    protected void initView() {
        cbs = new CheckBox[]{cbPermission0,cbPermission1,cbPermission2,cbPermission3,cbPermission4,cbPermission5,cbPermission6,cbPermission7,cbPermission8};
        for (CheckBox cb : cbs){
            cb.setOnCheckedChangeListener(this);
        }
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

    @OnClick({R.id.btn_permission_check, R.id.btn_permission_check_much, R.id.btn_permission_applay, R.id.btn_permission_applay_much})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_permission_check:
                if(codes.size()==0) return;
                RxToast.info(activity, "检查："+cbs[codes.get(0)].getText().toString());
                boolean b = LinPermission.checkPermission(activity, codes.get(0));
                btn_permission_check.setText("检查单个权限: "+b);
                break;
            case R.id.btn_permission_check_much:
                if(codes.size()==0) return;
                Integer[] integer = new Integer[codes.size()];
                Integer[] ints = codes.toArray(integer);
                boolean b1 = LinPermission.checkPermission(activity, ints);
                btn_permission_check_much.setText("检查多个权限: "+b1);
                break;
            case R.id.btn_permission_applay:
                if(codes.size()==0) return;
                String requestPermission = LinPermission.requestPermissions[codes.get(0)];
                requestPermissions(new String[]{requestPermission},LinPermission.RequestCode_Permission );
                LinCommon.showShortToast(activity,"申请"+ cbs[codes.get(0)].getText().toString());
                break;
            case R.id.btn_permission_applay_much:
                if(codes.size()==0) return;
                String[] strings = new String[codes.size()];
                for (int i = 0 ; i<codes.size();i++){
                    String permiss = LinPermission.requestPermissions[codes.get(i)];
                    strings[i] = permiss;
                }
                requestPermissions(strings,LinPermission.RequestCode_Permission);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.cb_permission_0:
                selectCode(isChecked,0);
                break;
            case R.id.cb_permission_1:
                selectCode(isChecked,1);
                break;
            case R.id.cb_permission_2:
                selectCode(isChecked,2);
                break;
            case R.id.cb_permission_3:
                selectCode(isChecked,3);
                break;
            case R.id.cb_permission_4:
                selectCode(isChecked,4);
                break;
            case R.id.cb_permission_5:
                selectCode(isChecked,5);
                break;
            case R.id.cb_permission_6:
                selectCode(isChecked,6);
                break;
            case R.id.cb_permission_7:
                selectCode(isChecked,7);
                break;
            case R.id.cb_permission_8:
                selectCode(isChecked,8);
                break;
        }
    }

    private void selectCode(boolean isChecked,int code) {
        if (isChecked){
            codes.add(code);
        }else{
            codes.remove((Integer) code);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LinPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, new LinPermission.PermissionsResultListener() {
            @Override
            public void onRequestPermissionSuccess(int pos, String permission) {
                LinCommon.showShortToast(activity, permission+"申请成功");
            }

            @Override
            public void onRequestPermissionFailure(int pos, String permission) {
                LinCommon.showShortToast(activity, permission+"申请失败");
            }
        });
    }
}
