package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import blcs.lwb.lwbtool.View.LabelList;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.RxToast;
import blcs.lwb.utils.R;
import blcs.lwb.utils.bean.TestBean;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class LabelListFragment extends BaseFragment {
    @BindView(R.id.labels)
    LabelList labelList;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_label_list;
    }

    @Override
    protected void initView() {

//        labelList = (labelList) findViewById(labels);

        //测试的数据
//        ArrayList<String> label = new ArrayList<>();
//        label.add("Android");
//        label.add("IOS");
//        label.add("前端");
//        label.add("后台");
//        label.add("微信开发");
//        label.add("游戏开发");
//        label.add("Java");
//        label.add("JavaScript");
//        label.add("C++");
//        label.add("PHP");
//        label.add("Python");
//        label.add("Swift");
//        labelList.setLabels(label);

        ArrayList<TestBean> testList = new ArrayList<>();
        testList.add(new TestBean("Android", 1));
        testList.add(new TestBean("IOS", 2));
        testList.add(new TestBean("前端", 3));
        testList.add(new TestBean("后台", 4));
        testList.add(new TestBean("微信开发", 5));
        testList.add(new TestBean("游戏开发", 6));
        testList.add(new TestBean("Java", 7));
        testList.add(new TestBean("JavaScript", 8));
        testList.add(new TestBean("C++", 9));
        testList.add(new TestBean("PHP", 10));
        testList.add(new TestBean("Python", 11));
        testList.add(new TestBean("Swift", 12));
        labelList.setLabels(testList, new LabelList.LabelTextProvider<TestBean>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, TestBean data) {
                return data.getName();
            }
        });

        // 设置最大显示行数，小于等于0则不限行数。
//        labelList.setMaxLines(1);
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


    @OnClick({R.id.btn_none, R.id.btn_single, R.id.btn_single_irrevocably, R.id.btn_multi, R.id.btn_multi_5, R.id.btn_multi_compulsory, R.id.btn_un_select, R.id.btn_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_none:
                labelList.setSelectType(LabelList.SelectType.NONE);
                break;
            case R.id.btn_single:
                labelList.setSelectType(LabelList.SelectType.SINGLE);
                break;
            case R.id.btn_single_irrevocably:
                labelList.setSelectType(LabelList.SelectType.SINGLE_IRREVOCABLY);
                break;
            case R.id.btn_multi:
                labelList.setSelectType(LabelList.SelectType.MULTI);
                labelList.setMaxSelect(0);
                break;
            case R.id.btn_multi_5:
                labelList.setSelectType(LabelList.SelectType.MULTI);
                labelList.setMaxSelect(5);
                break;
            case R.id.btn_multi_compulsory:
                labelList.setSelectType(LabelList.SelectType.MULTI);
                labelList.setMaxSelect(0);
                labelList.setCompulsorys(0, 1);
                break;
            case R.id.btn_un_select:
                labelList.clearAllSelect();
                break;
            case R.id.btn_click:
                labelList.setSelectType(LabelList.SelectType.NONE);
                labelList.setOnLabelClickListener(new LabelList.OnLabelClickListener() {
                    @Override
                    public void onLabelClick(TextView label, Object data, int position) {
                        RxToast.info(activity,position + " : " + data);
                    }
                });
                break;
        }
    }


}
