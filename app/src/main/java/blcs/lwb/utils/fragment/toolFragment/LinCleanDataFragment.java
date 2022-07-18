package blcs.lwb.utils.fragment.toolFragment;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.view.View;
import android.widget.Button;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.FileUtils;
import blcs.lwb.lwbtool.utils.LinCleanData;
import blcs.lwb.utils.MyApplication;
import blcs.lwb.utils.R;
import blcs.lwb.utils.databinding.FragmentLincleanDataBinding;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class LinCleanDataFragment extends BaseFragment {

    @BindView(R.id.btn_get_allCache)
    Button btn_get_allCache;
    @BindView(R.id.btn_clean_allCache)
    Button btn_clean_allCache;
    private FragmentLincleanDataBinding bind;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_linclean_data;
    }

    @Override
    protected void initView() {
        bind = DataBindingUtil.bind(mView);

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

    @OnClick({R.id.btn_get_allCache, R.id.btn_clean_allCache, R.id.btn_clean_appData, R.id.btn_clean_dataBases, R.id.btn_clean_sp, R.id.btn_clean_dataBases_name, R.id.btn_clean_content, R.id.btn_clean_cache_content, R.id.btn_clean_custom_file, R.id.btn_clean_alldata})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_allCache:
                String allCacheSize = LinCleanData.getAllCacheSize(MyApplication.context);
                bind.setContent(allCacheSize);
                break;
            case R.id.btn_clean_allCache:
                LinCleanData.clearAllCache(activity);
                break;
            case R.id.btn_clean_appData:
                LinCleanData.cleanInternalCache(activity);
                break;
            case R.id.btn_clean_dataBases:
                LinCleanData.cleanDatabases(activity);
                break;
            case R.id.btn_clean_sp:
                LinCleanData.cleanSharedPreference(activity);
                break;
            case R.id.btn_clean_dataBases_name:
                LinCleanData.cleanDatabaseByName(activity, "Blcs1");
                break;
            case R.id.btn_clean_content:
                LinCleanData.cleanFiles(activity);
                break;
            case R.id.btn_clean_cache_content:
                LinCleanData.cleanExternalCache(activity);
                break;
            case R.id.btn_clean_custom_file:
                LinCleanData.cleanCustomCache(FileUtils.SDCARD_ROOT);
                break;
            case R.id.btn_clean_alldata:
                LinCleanData.cleanApplicationData(activity);
                break;
        }
    }
}
