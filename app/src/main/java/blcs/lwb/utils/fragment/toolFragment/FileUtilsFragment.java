package blcs.lwb.utils.fragment.toolFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.retrofit.RxHelper;
import blcs.lwb.lwbtool.utils.FileUtils;
import blcs.lwb.lwbtool.utils.LinCommon;
import blcs.lwb.lwbtool.utils.LinDownloadAPk;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.manager.FramentManages;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

public class FileUtilsFragment extends BaseFragment {

    @BindView(R.id.btn_file_getSize)
    Button btn_file_getSize;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_file_utils;
    }

    @Override
    protected void initView() {}

    @Override
    public void setMiddleTitle(Toolbar title) { }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {}

    @OnClick({R.id.btn_getRootFile, R.id.btn_new_fileCatalog, R.id.btn_new_file,
            R.id.btn_file_getSize, R.id.btn_delete_file})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getRootFile://获取SD卡根目录
                addFrament(FramentManages.ListDemo, "手机存储");
                break;
            case R.id.btn_new_fileCatalog://新建一个文件目录
                try {
                    FileUtils.mkdir(activity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_new_file://创建一个文件
                FileUtils.mkDir(FileUtils.FILE);
                LinCommon.showShortToast(activity,"文件创建成功");
                break;
            case R.id.btn_file_getSize://获取文件大小
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                        String size = FileUtils.size(new File(FileUtils.SDCARD_ROOT + "Android"));
                        observableEmitter.onNext(size);
                    }
                }).compose(RxHelper.observableIO2Main(activity)).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String size) throws Exception {
                        btn_file_getSize.setText("获取文件大小: "+size);
                    }
                });
                break;
            case R.id.btn_delete_file://删除文件
                boolean b = FileUtils.delFile(FileUtils.FILE);
                if(b) LinCommon.showShortToast(activity,"删除成功");
                else LinCommon.showShortToast(activity,"删除失败");
                break;
        }
    }
}
