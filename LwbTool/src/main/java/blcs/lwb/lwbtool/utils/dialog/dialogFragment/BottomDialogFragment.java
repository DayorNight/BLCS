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
 * TODO 仿IOS底部弹出框
 */
public class BottomDialogFragment extends DialogFragment {
    private static final String TAG = "BottomDialogFragment";
    private View view;
    private DialogFragmentAdapter mAdapter;
    private ArrayList<String> datas;
    private static BottomDialogFragment dialogFragment;
    private final static String Key = "BottomDialogFragment";
    private int DefaultGravity = Gravity.BOTTOM;
    private RecyclerView rv;
    private GradientDrawable drawable;

    public BottomDialogFragment() {}

    public static BottomDialogFragment init(ArrayList<String> datas) {
        if (dialogFragment == null) {
            dialogFragment = new BottomDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(Key, datas);
            dialogFragment.setArguments(bundle);
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
        if(DefaultGravity == Gravity.CENTER){
            getDialog().getWindow().setLayout(attributes.width, attributes.height);
        }else if(DefaultGravity == Gravity.BOTTOM || DefaultGravity == Gravity.TOP){
            getDialog().getWindow().setLayout(dm.widthPixels,attributes.height);
        }else if(DefaultGravity == Gravity.LEFT || DefaultGravity == Gravity.RIGHT){
            getDialog().getWindow().setLayout(attributes.width,dm.heightPixels);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.tool_recyclerview, null);
        rv = view.findViewById(R.id.tool_recyclerView);
        initDialog();
        initUI();
        return view;
    }

    private void initUI() {
        Bundle arguments = getArguments();
        datas = arguments.getStringArrayList(Key);
        initRv();
    }

    public BottomDialogFragment setGravity(int gravity){
        DefaultGravity = gravity;
        drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        switch (gravity){
            case Gravity.CENTER:
                drawable.setCornerRadius(20);
                break;
            case Gravity.BOTTOM:
                drawable.setCornerRadii(new float[]{20,20,20,20,0,0,0,0});
                break;
            case Gravity.TOP:
                drawable.setCornerRadii(new float[]{0,0,0,0,20,20,20,20});
                break;
            case Gravity.LEFT:
                drawable.setCornerRadii(new float[]{0,0,20,20,20,20,0,0});
                break;
            case Gravity.RIGHT:
                drawable.setCornerRadii(new float[]{20,20,0,0,0,0,20,20});
                break;
        }
        return this;
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = DefaultGravity;
        attributes.width = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        setCancelable(true);
    }

    private void initRv( ) {
        if(drawable!=null) {
            rv.setBackground(drawable);
        }
        mAdapter = new DialogFragmentAdapter();
        RecyclerUtil.init(getActivity(), OrientationHelper.VERTICAL, mAdapter, rv,false);
        mAdapter.setNewData(datas);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (linstener != null) {
                    linstener.onItemClick(adapter, view, position);
                }
            }
        });
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, TAG);
    }

    public OnItemClickListener linstener;

    public BottomDialogFragment setOnClickListener(OnItemClickListener onItemClickListener) {
        linstener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(BaseQuickAdapter adapter, View view, int position);
    }
}
