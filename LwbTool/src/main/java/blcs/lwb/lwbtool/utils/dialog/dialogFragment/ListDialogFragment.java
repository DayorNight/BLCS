package blcs.lwb.lwbtool.utils.dialog.dialogFragment;

import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.adapter.DialogFragmentAdapter;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RecyclerUtil;

/**
 * TODO 仿IOS底部弹出框
 */
public class ListDialogFragment extends BaseDialogFragment {
    private View view;
    private DialogFragmentAdapter mAdapter;
    private ArrayList<String> datas;
    private static ListDialogFragment dialogFragment;
    private final static String Key = "BottomDialogFragment";
    private RecyclerView rv;

    public static ListDialogFragment init(ArrayList<String> datas) {
        if (dialogFragment == null) {
            dialogFragment = new ListDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(Key, datas);
            dialogFragment.setArguments(bundle);
        }
        return dialogFragment;
    }


    @Override
    public View getView(LayoutInflater inflater, ViewGroup container) {
        view =inflater.inflate(R.layout.tool_recyclerview, container,false);
        rv = view.findViewById(R.id.tool_recyclerView);
        Bundle arguments = getArguments();
        datas = arguments.getStringArrayList(Key);
        initRv();
        return view;
    }

    private void initRv() {
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

    public OnItemClickListener linstener;

    public ListDialogFragment setOnClickListener(OnItemClickListener onItemClickListener) {
        linstener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(BaseQuickAdapter adapter, View view, int position);
    }

}
