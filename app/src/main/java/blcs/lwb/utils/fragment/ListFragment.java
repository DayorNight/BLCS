package blcs.lwb.utils.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import blcs.lwb.lwbtool.View.DividerItem;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.FileUtils;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.MyUtils;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.ListAdapter;
import butterknife.BindView;

public class ListFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView toolRecyclerView;

    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    protected void initView() {

        String[] list = FileUtils.getRootPath().list();
        if (list==null){
            MyUtils.toast(activity,"未读取到SD卡");
            return;
        }
        List<String> fileNames = Arrays.asList(list);
        Collections.sort(fileNames);
        ListAdapter listAdapter = new ListAdapter();
        RecyclerUtil.init(activity, OrientationHelper.VERTICAL, listAdapter, toolRecyclerView);
        listAdapter.setNewData(fileNames);
        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String fileName = (String) adapter.getData().get(position);
                LogUtils.e(fileName);
                File file = new File(FileUtils.SDCARD_ROOT + fileName);
                if(file!=null&&file.list()!=null&&file.list().length>0){
                    listAdapter.setNewData(Arrays.asList( file.list()));
                }else {
                    RxToast.info(activity, "没有文件");
                }
            }
        });
    }


    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

}
