package blcs.lwb.utils.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

import blcs.lwb.lwbtool.manager.AppManager;
import blcs.lwb.lwbtool.utils.EditTextUtils;
import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.utils.manager.FramentManages;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import interfaces.OnFinishListener;

/**
 * FragmentActivity
 */
public abstract class BaseFragmentActivity extends AppCompatActivity implements OnFinishListener {
	protected BasePresenter baseP;// P层 自己在强转
	public FramentManages fragmentManager ;
	/**
	 * activity退出时隐藏软键盘需要，需要在调用finish方法前赋值
	 */
	protected View toGetWindowTokenView = null;
	private View view;
    private Unbinder bind;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 设定为竖屏
		setContentView(bindLayout(),this);
        bind = ButterKnife.bind(this);
        fragmentManager = new FramentManages(this);//片段管理
		if(baseP==null){// 绑定P
			baseP = bindPresenter();
		}
		baseP.onAttach(this);
	}

	//滑动返回<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	private OnFinishListener onFinishListener;
	public void setContentView(int layoutResID, OnFinishListener listener) {
		super.setContentView(layoutResID);
		onFinishListener = listener;
		view = LayoutInflater.from(this).inflate(layoutResID, null);
	}
	//滑动返回>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * 绑定P层
	 *
	 * @return
	 */
	protected abstract BasePresenter bindPresenter();

	/**
	 * data数据方法，必须在子类onCreate方法内setContentView后调用
	 */
	protected abstract int bindLayout();


	@Override
	public void finish() {
		super.finish();
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (toGetWindowTokenView != null) {
					EditTextUtils.hideKeyboard(BaseFragmentActivity.this, toGetWindowTokenView);
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		baseP.onDetch();
        bind.unbind();
		AppManager.getAppManager().finishActivity(this);
	}
	private boolean isOnKeyLongPress = false;
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		isOnKeyLongPress = true;
		return true;
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (isOnKeyLongPress) {
			isOnKeyLongPress = false;
			return true;
		}

		switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				if (onFinishListener != null) {
					onFinishListener.finish();
					return true;
				}
				break;
			default:
				break;
		}
		return super.onKeyUp(keyCode, event);
	}

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}