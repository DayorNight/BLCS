package blcs.lwb.lwbtool.utils.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.utils.MyUtils;

/**
 * @author Vondear
 * @date 2017/3/16
 */

public class RxDialogLoading extends RxDialog {

    private  Context context;
    private ProgressBar mLoadingView;
    private View mDialogContentView;
    private TextView mTextView;

    public RxDialogLoading(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        initView(context);
    }

    public RxDialogLoading(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    public RxDialogLoading(Context context) {
        super(context);
        initView(context);
    }

    public RxDialogLoading(Activity context) {
        super(context);
        initView(context);
    }

    public RxDialogLoading(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView(context);
    }

    private void initView(Context context) {
        mDialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_loading_spinkit, null);
        mLoadingView = mDialogContentView.findViewById(R.id.spin_kit);
        mTextView = mDialogContentView.findViewById(R.id.name);
        setContentView(mDialogContentView);
    }

    public void setLoadingText(CharSequence charSequence) {
        mTextView.setText(charSequence);
    }


    public void cancel(RxCancelType code, String str) {
        cancel();
        switch (code) {
            case normal:
                MyUtils.toast(context,str);
                break;
            case error:
                MyUtils.toast(context,str);
                break;
            case success:
                MyUtils.toast(context,str);
                break;
            case info:
                MyUtils.toast(context,str);
                break;
            default:
                MyUtils.toast(context,str);
                break;
        }
    }

    public void cancel(String str) {
        cancel();
        MyUtils.toast(context,str);
    }

    public ProgressBar getLoadingView() {
        return mLoadingView;
    }

    public View getDialogContentView() {
        return mDialogContentView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public enum RxCancelType {normal, error, success, info}
}
