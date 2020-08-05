package blcs.lwb.utils.fragment.otherFragment;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.Arrays;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinCommon;
import blcs.lwb.lwbtool.utils.MyUtils;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.lwbtool.utils.TimeUtil;
import blcs.lwb.utils.BuildConfig;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.UnuserdAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.BindViews;

/**
 * @Author BLCS
 * @Time 2020/8/5 11:19
 */
public class UnusedFunctionFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView rvComment;

    public String[] datas = {"切换请求地址","防多次点击"};
    public String[] items = { "线上服", "测试服", "开发服"};
    private AlertDialog alertDialog;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_unused_function;
    }

    @Override
    protected void initView() {
        UnuserdAdapter unuserdAdapter = new UnuserdAdapter();
        RecyclerUtil.init(getContext(), LinearLayoutManager.VERTICAL,unuserdAdapter,rvComment);
        unuserdAdapter.setNewData(Arrays.asList(datas));
        unuserdAdapter.setOnItemClickListener((adapter, view, position) -> {
            String data = (String) adapter.getData().get(position);
            switch (data){
                case "切换请求地址":
                    //添加列表
                    if (alertDialog==null&& BuildConfig.DEBUG){
                        alertDialog = new AlertDialog.Builder(getActivity())
                                .setTitle("选择环境")
                                .setItems(items, (dialogInterface, i) -> {
                                   switch (i){
                                       case 0:
                                           break;
                                       case 1:
                                           break;
                                       case 2:
                                           break;
                                   }
                                })
                                .create();
                    }
                    alertDialog.show();
                    break;
                case "防多次点击":
                    if (MyUtils.isFastClick()){
                        MyUtils.toast(getActivity(),"点击过于频繁");
                        return;
                    }
                    break;
                    default:break;
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
}
