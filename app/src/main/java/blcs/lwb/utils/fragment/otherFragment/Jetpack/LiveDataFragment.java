package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.NetworkLiveData;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class LiveDataFragment extends BaseFragment {
    @BindView(R.id.tv_liveData_netWork)
    TextView tvLiveDataNetWork;
    @BindView(R.id.tv_livedata)
    TextView tvLivedata;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_livedata;
    }

    @Override
    protected void initView() {
        NetworkLiveData.getInstance(activity).observe(this, new Observer<NetworkInfo>() {
            @Override
            public void onChanged(@Nullable NetworkInfo networkInfo) {
                if (networkInfo != null && networkInfo.isConnected()) {
                    String type = networkInfo.getTypeName();
                    tvLiveDataNetWork.setText("监听网络状态改变:"+type);
                }
            }
        });

        // 1.创建一个LiveData实例来保存数据
        MutableLiveData<String> currentName = new MutableLiveData<String>();
        // 2.创建Observer监听数据改变
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                //更新UI操作
                tvLivedata.setText(newName);
            }
        };
        // 3.添加Observer
        currentName.observe(this, nameObserver);
        // 设置值
//        currentName.setValue("xxxx");

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
    @OnClick(R.id.btn_liveData)
    public void click(){
        MyUtils.toUrl(this,"LiveData",getString(R.string.URL_LiveData));
    }
}
