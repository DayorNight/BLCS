package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.View.titanic.Titanic;
import blcs.lwb.lwbtool.View.titanic.TitanicTextView;
import blcs.lwb.lwbtool.View.titanic.Typefaces;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

public class MarqueeViewFragment extends BaseFragment {
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.marqueeView1)
    MarqueeView marqueeView1;
    @BindView(R.id.marqueeView2)
    MarqueeView marqueeView2;
    @BindView(R.id.Titanic_TextView)
    TitanicTextView titanicTextView;


    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {

        marquee();

        titanic();

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_bottom_navigation_view,menu);
    }


    /**
     * 水平波纹
     */
    private void titanic() {
        titanicTextView.setTypeface(Typefaces.get(getActivity(), "Satisfy-Regular.ttf"));
        Titanic titanic = new Titanic();
        titanic.start(titanicTextView);
//        titanic.cancel();/取消
    }

    /**
     * 跑马灯
     */
    private void marquee() {
        List<String> info = new ArrayList<>();
        info.add("1. 大家好，我是跑马灯。");
        info.add("2. 单个跑马灯实现。");
        info.add("3. singleLine=\"true\"");
        info.add("4. ellipsize=\"marquee\"");
        info.add("5. focusable=\"true\"");
        info.add("6. focusableInTouchMode=\"true\"");
        marqueeView.startWithList(info);
        // 在代码里设置自己的动画
        marqueeView1.startWithText(getString(R.string.marquee));
        marqueeView2.startWithText(getString(R.string.marquee), R.anim.anim_top_in, R.anim.anim_bottom_out);
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(activity, "position:"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }
    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_marquee;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {
    }
}
