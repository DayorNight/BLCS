package blcs.lwb.utils.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

import blcs.lwb.lwbtool.EditTextUtils;
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
	 * 退出时之前的界面进入动画,可在finish();前通过改变它的值来改变动画效果
	 */
	protected int enterAnim = R.anim.fade;
	/**
	 * 退出时该界面动画,可在finish();前通过改变它的值来改变动画效果
	 */
	protected int exitAnim = R.anim.right_push_out;

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
		//里面的代码不需要重写，通过super.finish();即可得到<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (toGetWindowTokenView != null) {
					EditTextUtils.hideKeyboard(BaseFragmentActivity.this, toGetWindowTokenView);
				}
				if (enterAnim > 0 && exitAnim > 0) {
					try {
						overridePendingTransition(enterAnim, exitAnim);
					} catch (Exception e) {
					}
				}
			}
		});
		//里面的代码不需要重写，通过super.finish();即可得到>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		baseP.onDetch();
        bind.unbind();
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

	//滑动返回>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//	@Override
//	public void addFrament(int viewId, String fm_name, Bundle bundle,boolean isAnim)
//	{
//		fragmentManager.addFrament(viewId,this, fm_name, bundle,isAnim);
//	}
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