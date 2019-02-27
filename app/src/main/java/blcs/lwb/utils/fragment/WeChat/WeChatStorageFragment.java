package blcs.lwb.utils.fragment.WeChat;

import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.AppUtils;
import blcs.lwb.lwbtool.utils.FileUtils;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.lwbtool.utils.RxBus;
import blcs.lwb.lwbtool.utils.StringUtils;
import blcs.lwb.lwbtool.utils.systemSetting.AppSizeUtils;
import blcs.lwb.lwbtool.utils.systemSetting.SDCardUtils;
import blcs.lwb.utils.Bean.StorageSpace;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.WeChatStorageAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * TODO 存储空间
 * 定义AIDL
 * 开启获取应用大小权限
 *    <!--应用大小获取-->
 <uses-permission android:name="android.permission.GET_PACKAGE_SIZE" />
 <uses-permission
 android:name="android.permission.PACKAGE_USAGE_STATS"
 tools:ignore="ProtectedPermissions" />
 */

public class WeChatStorageFragment extends BaseFragment {

    @BindView(R.id.tool_recyclerView)
    RecyclerView mRecyclerView;
    private Disposable disposable;
    private List<StorageSpace> datas = new ArrayList<>();
    private WeChatStorageAdapter mAdapter;

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    protected void initView() {
        mAdapter = new WeChatStorageAdapter(activity);
        RecyclerUtil.init(activity, OrientationHelper.VERTICAL, mAdapter,mRecyclerView);

        disposable = RxBus.getDefault()
                .register(RxBus.Event.class, new Consumer<RxBus.Event>() {
                    @Override
                    public void accept(RxBus.Event event) {
                        String data = (String) event.getData();
                        long appsize = Long.valueOf(data);
                        long sdCardSize = SDCardUtils.getSDCardSize();
                        long sdCardAvailableSize = SDCardUtils.getSDCardAvailableSize();
                        float cound = ((float) appsize / sdCardSize)*100;
                        String mysize = StringUtils.format2Decimals(String.valueOf(cound));
                        datas.add(new StorageSpace(AppUtils.getAppName(activity)+"已用空间", FileUtils.size(appsize), "占据手机" + mysize + "%存储空间", "管理存储空间"));
                        datas.add(new StorageSpace("手机已用空间", FileUtils.size(sdCardSize - sdCardAvailableSize), "剩余" + FileUtils.size(sdCardAvailableSize) + "可用空间", "使用手机管家，清理系统空间"));
                        mAdapter.setNewData(datas);
                    }
                });
        AppSizeUtils.getAppAllSize(activity);
    }

    @Override
    public void setMiddleTitle(Toolbar title) {}

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(disposable);
    }
}
