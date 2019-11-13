package blcs.lwb.utils.fragment.toolFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import blcs.lwb.lwbtool.utils.StringUtils;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.mvp.presenter.StringPresenter;
import blcs.lwb.utils.mvp.view.IStringView;
import butterknife.BindView;
import butterknife.OnClick;


public class StringUtilsFragment extends BaseFragment implements IStringView {
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.btn_getTrimStr)
    Button btnGetTrimStr;
    @BindView(R.id.btn_getIsNotEmptyStr)
    Button btnGetIsNotEmptyStr;
    @BindView(R.id.btn_getIsPhoneStr)
    Button btnGetIsPhoneStr;
    @BindView(R.id.btn_getIsEmailStr)
    Button btnGetIsEmailStr;
    @BindView(R.id.btn_getIsNumerStr)
    Button btnGetIsNumerStr;
    @BindView(R.id.btn_getNumberOrAlpha)
    Button btnGetNumberOrAlpha;
    @BindView(R.id.btn_isIDCard)
    Button btnIsIDCard;
    @BindView(R.id.btn_isUrl)
    Button btnIsUrl;
    @BindView(R.id.btn_isFilePathExist)
    Button btnIsFilePathExist;
    @BindView(R.id.btn_getLengthStr)
    Button btnGetLengthStr;
    @BindView(R.id.tv_getNoBlanStr)
    TextView tvGetNoBlanStr;
    @BindView(R.id.tv_getTrimStr)
    TextView tvGetTrimStr;
    @BindView(R.id.tv_getCorrectUrl)
    TextView tvGetCorrectUrl;
    @BindView(R.id.tv_getCorrectEmail)
    TextView tvGetCorrectEmail;
    @BindView(R.id.tv_getPrice)
    TextView tvGetPrice;
    @BindView(R.id.tv_getNumber)
    TextView tvGetNumber;
    @BindView(R.id.tv_stringToDouble)
    TextView tvStringToDouble;
    @BindView(R.id.tv_stringToInt)
    TextView tvStringToInt;
    @BindView(R.id.tv_getCorrectPhone)
    TextView tvGetCorrectPhone;
    @BindView(R.id.cb_getLengthStr)
    CheckBox cbGetLengthStr;
    @BindView(R.id.cb_getIsNotEmptyStr)
    CheckBox cbGetIsNotEmptyStr;
    private String etString;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return new StringPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_string_utils;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @Override
    public void initUI() {

    }

    @OnClick({ R.id.btn_getTrimStr, R.id.btn_getNoBlanStr, R.id.btn_getLengthStr, R.id.btn_getIsNotEmptyStr, R.id.btn_getIsPhoneStr,
            R.id.btn_getIsEmailStr, R.id.btn_getIsNumerStr, R.id.btn_getNumberOrAlpha, R.id.btn_isIDCard, R.id.btn_isUrl, R.id.btn_isFilePathExist,
            R.id.btn_getNumber, R.id.btn_getCorrectUrl, R.id.btn_getCorrectPhone, R.id.btn_getCorrectEmail, R.id.btn_getPrice,
            R.id.btn_stringToDouble, R.id.btn_stringToInt})
    public void onViewClicked(View view) {
        etString = etInput.getText().toString();
        switch (view.getId()) {
            case R.id.btn_getTrimStr:
                tvGetTrimStr.setText(StringUtils.getTrimedString(etInput));
                break;
            case R.id.btn_getNoBlanStr:
                tvGetNoBlanStr.setText(StringUtils.getNoBlankString(etInput));
                break;
            case R.id.btn_getLengthStr:
                btnGetLengthStr.setText(getString(R.string.getLengthStr)+StringUtils.getLength(etInput,cbGetLengthStr.isChecked()));
                break;
            case R.id.btn_getIsNotEmptyStr:
                btnGetIsNotEmptyStr.setText(getString(R.string.IsNotEmptyStr)+StringUtils.isNotEmpty(etInput,cbGetIsNotEmptyStr.isChecked()));
                break;
            case R.id.btn_getIsPhoneStr:
                btnGetIsPhoneStr.setText(getString(R.string.IsPhoneStr)+StringUtils.isPhone(etString));
                break;
            case R.id.btn_getIsEmailStr:
                btnGetIsEmailStr.setText(getString(R.string.IsEmailStr)+StringUtils.isEmail(etString));
                break;
            case R.id.btn_getIsNumerStr:
                btnGetIsNumerStr.setText(getString(R.string.IsNumerStr)+StringUtils.isNumer(etString));
                break;
            case R.id.btn_getNumberOrAlpha:
                btnGetNumberOrAlpha.setText(getString(R.string.IsNumberOrAlpha)+StringUtils.isNumberOrAlpha(etString));
                break;
            case R.id.btn_isIDCard:
                btnIsIDCard.setText(getString(R.string.isIDCard)+StringUtils.isIDCard(etString));
                break;
            case R.id.btn_isUrl:
                btnIsUrl.setText(getString(R.string.isUrl)+StringUtils.isUrl(etString));
                break;
            case R.id.btn_isFilePathExist:
                btnIsFilePathExist.setText(getString(R.string.isFilePathExist)+StringUtils.isFilePathExist(etString));
                break;
            case R.id.btn_getNumber:
                tvGetNumber.setText(""+StringUtils.getNumber(etString));
                break;
            case R.id.btn_getCorrectUrl:
                tvGetCorrectUrl.setText(""+StringUtils.getCorrectUrl(etString));
                break;
            case R.id.btn_getCorrectPhone:
                tvGetCorrectPhone.setText(""+StringUtils.getCorrectPhone(etString));
                break;
            case R.id.btn_getCorrectEmail:
                tvGetCorrectEmail.setText(""+StringUtils.getCorrectEmail(etString));
                break;
            case R.id.btn_getPrice:
                tvGetPrice.setText(""+StringUtils.getPrice(etString));
                break;
            case R.id.btn_stringToDouble:
                tvStringToDouble.setText(""+StringUtils.stringToDouble(etString));
                break;
            case R.id.btn_stringToInt:
                tvStringToInt.setText(""+StringUtils.stringToInt(etString));
                break;
        }
    }
}
