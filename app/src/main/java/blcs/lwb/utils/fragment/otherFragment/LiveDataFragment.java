package blcs.lwb.utils.fragment.otherFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.net.NetworkInfo;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.NetworkLiveData;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LiveDataFragment extends BaseFragment {
    @BindView(R.id.tv_livedata_network)
    TextView tvLivedataNetwork;
    @BindView(R.id.et_livedata)
    EditText etLivedata;
    @BindView(R.id.tv_livedata)
    TextView tvLivedata;
    private MutableLiveData<String> currentName;

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
                    tvLivedataNetwork.setText("监听网络状态改变:"+type);
                }
            }
        });

        // 1.创建一个LiveData实例来保存数据
        currentName = new MutableLiveData<String>();
        // 2.创建Observer监听数据改变
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                //更新UI操作
                tvLivedata.setText(newName);
            }
        };
        // 添加Observer
        currentName.observe(this, nameObserver);

        etLivedata.addTextChangedListener(watcher);
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            currentName.setValue(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
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
