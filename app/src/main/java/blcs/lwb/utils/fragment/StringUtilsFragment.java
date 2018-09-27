package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import blcs.lwb.lwbtool.LogUtils;
import blcs.lwb.lwbtool.StringUtils;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.mvp.presenter.StringPresenter;
import blcs.lwb.utils.mvp.view.IStringView;
import butterknife.BindView;
import butterknife.OnClick;


public class StringUtilsFragment extends BaseFragment implements IStringView {
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.btn_getTrimStr)
    Button btnGetTrimStr;
    @BindView(R.id.tv_getNoBlanStr)
    TextView tvGetNoBlanStr;
    @BindView(R.id.tv_getTrimStr)
    TextView tvGetTrimStr;
    @BindView(R.id.tv_getLengthStr)
    TextView tvGetLengthStr;
    @BindView(R.id.tv_getIsNotEmptyStr)
    TextView tv_getIsNotEmptyStr;
    @BindView(R.id.tv_getIsPhoneStr)
    TextView tvGetIsPhoneStr;
    @BindView(R.id.tv_getIsEmailStr)
    TextView tvGetIsEmailStr;
    @BindView(R.id.tv_getIsNumerStr)
    TextView tvGetIsNumerStr;
    @BindView(R.id.tv_getNumberOrAlpha)
    TextView tvGetNumberOrAlpha;
    @BindView(R.id.tv_isIDCard)
    TextView tvIsIDCard;
    @BindView(R.id.tv_isUrl)
    TextView tvIsUrl;
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
    @BindView(R.id.tv_isFilePathExist)
    TextView tvIsFilePathExist;
    @BindView(R.id.cb_getLengthStr)
    CheckBox cbGetLengthStr;
    @BindView(R.id.cb_getIsNotEmptyStr)
    CheckBox cbGetIsNotEmptyStr;
    private String etString;

    @Override
    public void setMiddleTitle(Toolbar title) {

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
//            case R.id.btn_getStr:
//                tvGetStr.setText(StringUtils.getCurrentString());
//                break;
            case R.id.btn_getTrimStr:
                tvGetTrimStr.setText(StringUtils.getTrimedString(etInput));
                break;
            case R.id.btn_getNoBlanStr:
                tvGetNoBlanStr.setText(StringUtils.getTrimedString(etInput));
                break;
            case R.id.btn_getLengthStr:
                tvGetLengthStr.setText(""+StringUtils.getLength(etInput,cbGetLengthStr.isChecked()));
                break;
            case R.id.btn_getIsNotEmptyStr:
                tv_getIsNotEmptyStr.setText(""+StringUtils.isNotEmpty(etInput,cbGetIsNotEmptyStr.isChecked()));
                break;
            case R.id.btn_getIsPhoneStr:
                tvGetIsPhoneStr.setText(""+StringUtils.isPhone(etString));
                break;
            case R.id.btn_getIsEmailStr:
                tvGetIsEmailStr.setText(""+StringUtils.isEmail(etString));
                break;
            case R.id.btn_getIsNumerStr:
                tvGetIsNumerStr.setText(""+StringUtils.isNumer(etString));
                break;
            case R.id.btn_getNumberOrAlpha:
                tvGetNumberOrAlpha.setText(""+StringUtils.isNumberOrAlpha(etString));
                break;
            case R.id.btn_isIDCard:
                tvIsIDCard.setText(""+StringUtils.isIDCard(etString));
                break;
            case R.id.btn_isUrl:
                tvIsUrl.setText(""+StringUtils.isUrl(etString));
                break;
            case R.id.btn_isFilePathExist:
                tvIsFilePathExist.setText(""+StringUtils.isFilePathExist(etString));
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
