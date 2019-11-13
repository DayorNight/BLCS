package blcs.lwb.utils.fragment.viewFragment.WeChat;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.manager.AppManager;
import blcs.lwb.lwbtool.utils.IntentUtils;
import blcs.lwb.lwbtool.utils.MultiLanguageUtils;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.lwbtool.utils.SPUtils;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.MainActivity;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.BaseDemoAdapter.MultiLanguageAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

/**
 * TODO 多语言
 */

public class MultiLanguageFragment extends BaseFragment {
    @BindView(R.id.tool_recyclerView)
    RecyclerView mRecyclerView;
    private List<String> datas = new ArrayList<>();
    //    private MultiLanguageAdapter mAdapter;
    private RadioButton radioButton; //记录上一个点击事件
    private int checkPos;
    private MultiLanguageAdapter mAdapter;
    private MenuItem item;

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.tool_recyclerview;
    }

    @Override
    protected void initView() {
        //设置右上角的填充菜单
        item = activity.tlToolbar.getMenu().findItem(R.id.item_save);
        item.setVisible(true);
        activity.tlToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item_save) {
                    RxToast.info(activity, "保存");
                    //保存选中位置
                    SPUtils.put(activity, Constants.SP_MultiLanguage, checkPos);

                    //区分是选中哪种语言
                    switch (datas.get(checkPos)) {
                        case "跟随系统"://切换到 跟随系统
                            //获取手机系统语言
                            Locale locale = MultiLanguageUtils.getSystemLanguage().get(0);
                            String language = locale.getLanguage();
                            String country = locale.getCountry();
                            //切换成手机系统语言  例：手机系统是中文则换成中文
                            MultiLanguageUtils.changeLanguage(activity,language, country);
                            //清空SP数据 ，用于当系统切换语言时 应用可以同步保持切换 例：系统转换成英文 则应用语言也会变成英文
                            MultiLanguageUtils.changeLanguage(activity,null,null);
                            break;
                        case "简体中文":// 切换到 中文
                            MultiLanguageUtils.changeLanguage(activity, "zh", "ZH");
                            break;
                        case "English"://切换到 英文
                            MultiLanguageUtils.changeLanguage(activity, "en", "US");
                            break;
                        default://默认切换成中文
                            MultiLanguageUtils.changeLanguage(activity, "zh", "ZH");
                            break;
                    }
                    //关闭应用所有Activity
                    AppManager.getAppManager().finishAllActivity();
                    //启动 MainActivity
                    IntentUtils.toActivity(activity, MainActivity.class,true);
                }
                return true;
            }
        });

        mAdapter = new MultiLanguageAdapter(activity);
        RecyclerUtil.init(activity, OrientationHelper.VERTICAL, mAdapter, mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                checkPos = position;
                if (radioButton == null) {//第一次点击取消选中转态
                    int pos = (int) SPUtils.get(activity, Constants.SP_MultiLanguage, 0);
                    RadioButton radioButton = (RadioButton) adapter.getViewByPosition(mRecyclerView, pos, R.id.rb_multi_language);
                    radioButton.setButtonTintList((ColorStateList) getResources().getColorStateList(R.color.gray1));
                    radioButton.setChecked(false);
                } else {
                    radioButton.setButtonTintList((ColorStateList) getResources().getColorStateList(R.color.gray1));
                    radioButton.setChecked(false);
                }
                RadioButton rb = view.findViewById(R.id.rb_multi_language);
                radioButton = rb;
                rb.setButtonTintList((ColorStateList) getResources().getColorStateList(R.color.green));
                rb.setChecked(true);
            }
        });
        initData();
    }

    private void initData() {
        datas.clear();
        datas.add("跟随系统");
        datas.add("简体中文");
        datas.add("English");
        mAdapter.setNewData(datas);
    }

    @Override
    public void setMiddleTitle(Toolbar title) {
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }


    @Override
    public void onDestroy() {
        item.setVisible(false);
        super.onDestroy();
    }
}
