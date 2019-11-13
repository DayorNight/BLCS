package blcs.lwb.utils.fragment.viewFragment.WeChat;

import android.graphics.Color;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.base.BaseActivity;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.AppUtils;
import blcs.lwb.lwbtool.utils.FileUtils;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.lwbtool.utils.StringUtils;
import blcs.lwb.lwbtool.utils.systemSetting.AppSizeUtils;
import blcs.lwb.lwbtool.utils.systemSetting.SDCardUtils;
import blcs.lwb.utils.bean.StorageSpace;
import blcs.lwb.utils.MyApplication;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.WeChatStorageAdapter;
import butterknife.BindView;

public class WeChatStorageActivity extends BaseActivity {
    @BindView(R.id.tool_recyclerView)
    RecyclerView rv_storage;
    @BindView(R.id.tl_toolbar)
    Toolbar tlToolbar;
    private List<StorageSpace> datas = new ArrayList<>();
    private WeChatStorageAdapter mAdapter;
    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void initView() {
        tlToolbar.setNavigationIcon(R.mipmap.ic_back_white);
        tlToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tlToolbar.setTitle(getString(R.string.WeChatStorageActivity));
        tlToolbar.setTitleTextColor(Color.WHITE);
        mAdapter = new WeChatStorageAdapter(this);
        RecyclerUtil.init(this, OrientationHelper.VERTICAL, mAdapter, rv_storage);
        AppSizeUtils.getInstance().setDatasListent(new AppSizeUtils.OnBackListent() {
            @Override
            public void backData(long cacheSize, long dataSize, long codeSize) {
                datas.clear();
                long appsize = dataSize + codeSize;
                long sdCardSize = SDCardUtils.getSDCardSize();
                long sdCardAvailableSize = SDCardUtils.getSDCardAvailableSize();
                float cound = ((float) appsize / sdCardSize) * 100;
                String mysize = StringUtils.format2Decimals(String.valueOf(cound));
                datas.add(new StorageSpace(AppUtils.getAppName(MyApplication.getContext()) + "已用空间", FileUtils.size(appsize), "占据手机" + mysize + "%存储空间", "管理存储空间"));
                datas.add(new StorageSpace("手机已用空间", FileUtils.size(sdCardSize - sdCardAvailableSize), "剩余" + FileUtils.size(sdCardAvailableSize) + "可用空间", "使用手机管家，清理系统空间"));
                mAdapter.setNewData(datas);
            }
        }).init(MyApplication.getContext());
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_wechat_storage;
    }

}
