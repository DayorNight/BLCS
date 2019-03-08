package blcs.lwb.lwbtool.utils.dialog.dialogFragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.adapter.DialogFragmentAdapter;
import blcs.lwb.lwbtool.utils.RecyclerUtil;

/**
 * TODO 自定义弹出框
 */
public class CustomDialogFragment extends DialogFragment {
    private static final String TAG = "CustomDialogFragment";
    private static CustomDialogFragment dialogFragment;

    public CustomDialogFragment() {}

    public static CustomDialogFragment init() {
        if (dialogFragment == null) {
            dialogFragment = new CustomDialogFragment();
        }
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.bottom_dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        getDialog().getWindow().setLayout(attributes.width, attributes.height);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initDialog();
        return inflater.inflate(R.layout.dialog_fragment_bottom, null);
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.CENTER;
        attributes.width = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        setCancelable(true);
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, TAG);
    }
}
