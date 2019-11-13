package blcs.lwb.utils.fragment.viewFragment.Viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.Constants;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;

/**
 * Created by lwb on 2018/6/5.
 */

public class PageFragment extends BaseFragment {

    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    boolean visible = true;
    public static final Fragment newInstance() {
        PageFragment fragment = new PageFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("OAuthUtils", OAuthUtils);
//        bundle.putString("url", url);
//        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        int res = bundle.getInt(Constants.KEY, R.mipmap.ic1);
        imageView1.setImageResource(res);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!visible) {
                    visible = true;
                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                } else {
                    visible = false;
                    textView1.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_page;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }
}
