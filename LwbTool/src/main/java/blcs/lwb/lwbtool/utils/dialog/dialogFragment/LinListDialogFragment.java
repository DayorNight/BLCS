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
import blcs.lwb.lwbtool.utils.RecyclerUtil;

/**
 * TODO 仿微信底部弹出框
 */
public class LinListDialogFragment extends BaseDialogFragment {
    private View view;
    private DialogFragmentAdapter mAdapter;
    private ArrayList<String> datas;
    private static LinListDialogFragment dialogFragment;
    private final static String Key = "LinListDialogFragment";
    private RecyclerView rv;

    public static LinListDialogFragment init(ArrayList<String> datas) {
        if (dialogFragment == null) {
            dialogFragment = new LinListDialogFragment();
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
    public LinListDialogFragment setOnClickListener(OnItemClickListener onItemClickListener) {
        linstener = onItemClickListener;
        return this;
    }
    public interface OnItemClickListener {
        void onItemClick(BaseQuickAdapter adapter, View view, int position);
    }
}
