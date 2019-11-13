package blcs.lwb.utils.fragment.toolFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import blcs.lwb.lwbtool.utils.EditTextUtils;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class EditTextUtilsFragment extends BaseFragment {
    @BindView(R.id.btn_showKeyboard)
    Button btnShowKeyboard;
    @BindView(R.id.btn_hideKeyboard)
    Button btnHideKeyboard;
    @BindView(R.id.hide_showKeyboard)
    Button hideShowKeyboard;
    @BindView(R.id.judge_Invalid_Code)
    Button judgeInvalidCode;
    @BindView(R.id.et_input_code)
    EditText etInputCode;
    @BindView(R.id.judge_Invalid_password)
    Button judgeInvalidPassword;
    @BindView(R.id.et_input_password)
    EditText etInputPassword;
    @BindView(R.id.judge_Invalid_phone)
    Button judgeInvalidPhone;
    @BindView(R.id.et_input_phone)
    EditText etInputPhone;
    @BindView(R.id.judge_Invalid_Email)
    Button judgeInvalidEmail;
    @BindView(R.id.et_input_Email)
    EditText etInputEmail;
    private boolean ishide;
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
        return R.layout.fragment_edittext;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @OnClick({R.id.btn_showKeyboard, R.id.btn_hideKeyboard, R.id.hide_showKeyboard, R.id.judge_Invalid_Code, R.id.judge_Invalid_password, R.id.judge_Invalid_phone, R.id.judge_Invalid_Email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_showKeyboard:
                EditTextUtils.showKeyboard(getActivity(),etInputCode);
                break;
            case R.id.btn_hideKeyboard:
                EditTextUtils.hideKeyboard(getActivity(),btnHideKeyboard);
                break;
            case R.id.hide_showKeyboard:
                    EditTextUtils.showKeyboard(getActivity(),etInputCode,ishide);
                    ishide=!ishide;
                break;
            case R.id.judge_Invalid_Code:
                EditTextUtils.isInputedCorrect(getActivity(),etInputCode,EditTextUtils.TYPE_VERIFY);
                break;
            case R.id.judge_Invalid_password:
                EditTextUtils.isInputedCorrect(getActivity(),etInputPassword,EditTextUtils.TYPE_PASSWORD);
                break;
            case R.id.judge_Invalid_phone:
                EditTextUtils.isInputedCorrect(getActivity(),etInputPhone,EditTextUtils.TYPE_PHONE);
                break;
            case R.id.judge_Invalid_Email:
                EditTextUtils.isInputedCorrect(getActivity(),etInputEmail,EditTextUtils.TYPE_MAIL);
                break;
        }
    }
}
