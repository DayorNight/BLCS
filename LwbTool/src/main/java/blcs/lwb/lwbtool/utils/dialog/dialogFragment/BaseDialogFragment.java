package blcs.lwb.lwbtool.utils.dialog.dialogFragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import blcs.lwb.lwbtool.R;

/**
 * TODO 自定义弹出框
 */
public abstract class BaseDialogFragment extends DialogFragment {
    private static final String TAG = "BaseDialogFragment";
    private int DefaultGravity;
    private GradientDrawable drawable;
    private View view;

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
        if (DefaultGravity == Gravity.CENTER) {
            getDialog().getWindow().setLayout(attributes.width, attributes.height);
        } else if (DefaultGravity == Gravity.BOTTOM || DefaultGravity == Gravity.TOP) {
            getDialog().getWindow().setLayout(dm.widthPixels, attributes.height);
        } else if (DefaultGravity == Gravity.LEFT || DefaultGravity == Gravity.RIGHT) {
            getDialog().getWindow().setLayout(attributes.width, dm.heightPixels);
        } else if (DefaultGravity == Gravity.NO_GRAVITY) {
            getDialog().getWindow().setLayout(dm.widthPixels, dm.heightPixels);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initDialog();
        view = getView(inflater, container);
        if (drawable != null) {
            view.setBackground(drawable);
        }
        return view;
    }

    /**
     * 方向
     */
    public BaseDialogFragment setGravity(int gravity) {
        DefaultGravity = gravity;
        drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        switch (gravity) {
            case Gravity.CENTER:
                drawable.setCornerRadius(20);
                break;
            case Gravity.BOTTOM:
                drawable.setCornerRadii(new float[]{20, 20, 20, 20, 0, 0, 0, 0});
                break;
            case Gravity.TOP:
                drawable.setCornerRadii(new float[]{0, 0, 0, 0, 20, 20, 20, 20});
                break;
            case Gravity.LEFT:
                drawable.setCornerRadii(new float[]{0, 0, 20, 20, 20, 20, 0, 0});
                break;
            case Gravity.RIGHT:
                drawable.setCornerRadii(new float[]{20, 20, 0, 0, 0, 0, 20, 20});
                break;
            case Gravity.CENTER_HORIZONTAL:
                drawable.setColor(Color.TRANSPARENT);
                break;
        }
        return this;
    }

    /**
     * 自定义View
     */
    public abstract View getView(LayoutInflater inflater, ViewGroup container);

    private void initDialog() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = DefaultGravity;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        setCancelable(true);
    }

    public void show(FragmentManager fragmentManager) {
        setGravity(Gravity.CENTER);
        if(!isAdded()){
            show(fragmentManager, TAG);
        }

    }

    public void show(FragmentManager fragmentManager, int Gravity) {
        setGravity(Gravity);
        if(!isAdded()){
            show(fragmentManager, TAG);
        }
    }
}
