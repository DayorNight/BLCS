package blcs.lwb.lwbtool.utils.dialog.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.adapter.DialogFragmentAdapter;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RecyclerUtil;

/**
 * TODO 仿IOS底部弹出框
 *
 * 使用
    BottomDialogFragment fragment = new BottomDialogFragment(MyUtils.getArray(activity, R.array.WeChat_Function));
    fragment.show(getFragmentManager());
 */
public class BottomDialogFragment extends DialogFragment {
    private static final String TAG = "BottomDialogFragment";
    private View view;
    private DialogFragmentAdapter mAdapter;
    private List<String> datas;

    @SuppressLint("ValidFragment")
    public BottomDialogFragment(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStyle(R.style.bottom_dialog);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.bottom_dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    public BottomDialogFragment() {}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initDialog();
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fragment_bottom, container, false);
            RecyclerView rv = view.findViewById(R.id.rv_dialog_fragment);
            initRv(rv);
        }
        return view;
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        setCancelable(true);
    }

    private void initRv(RecyclerView rv) {
        mAdapter = new DialogFragmentAdapter();
        RecyclerUtil.init(getActivity(), OrientationHelper.VERTICAL, mAdapter, rv);
        mAdapter.setNewData(datas);
    }

    public void show(FragmentManager fragmentManager){
        show(fragmentManager,TAG);
    }
}
