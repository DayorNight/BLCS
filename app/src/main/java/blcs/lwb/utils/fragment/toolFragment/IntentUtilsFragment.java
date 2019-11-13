package blcs.lwb.utils.fragment.toolFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import blcs.lwb.lwbtool.utils.IntentUtils;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.SplashActivity;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class IntentUtilsFragment extends BaseFragment {
    @BindView(R.id.cb_open_Animation)
    CheckBox cbOpenAnimation;
    @BindView(R.id.cb_requestCode)
    CheckBox cbRequestCode;
    @BindView(R.id.btn_startActivity)
    Button btnStartActivity;
    @BindView(R.id.btn_startActivityForResult)
    Button btnStartActivityForResult;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.tv_resultCode)
    TextView tvResultCode;
    @BindView(R.id.et_input_args)
    EditText etInputArgs;
    public static final String Key="args";
    private static int RequestCode =400;
    private static int RequestCode1 =401;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_intent;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }


    @OnClick({R.id.btn_startActivity, R.id.btn_startActivityForResult, R.id.btn_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_startActivity:
                IntentUtils.toActivity(getActivity(), SplashActivity.class, cbOpenAnimation.isChecked());
                break;
            case R.id.btn_startActivityForResult:
                IntentUtils.toActivity(getActivity(), SplashActivity.class,RequestCode,cbOpenAnimation.isChecked());
                break;
            case R.id.btn_start:
                Bundle bundle = new Bundle();
                bundle.putString(Key,etInputArgs.getText().toString());
                if(cbRequestCode.isChecked()){
                    IntentUtils.toActivity(getActivity(), SplashActivity.class,bundle,RequestCode1,cbOpenAnimation.isChecked());
                }else{
                    IntentUtils.toActivity(getActivity(), SplashActivity.class,bundle,-1,cbOpenAnimation.isChecked());
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tvResultCode.setText("请求码："+requestCode+"\n返回码："+resultCode);
    }
}
