package blcs.lwb.lwbtool.View.JellyViewPager;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.nineoldandroids.view.ViewHelper;

public class JellyViewPager extends ViewPager {
	/**
	 * View最小缩放比例
	 */
	private static final float MIN_SCALE = 0.2f;

	/**
	 * 最大缩放比例
	 */
	private static final float MAX_SCALE = 0.8f;
	/**
	 * 滑动的最低速度
	 */
	private static float FLING_VELOCITY = 500;
	private static final int UNIT = 1000; // 计算速率的单位（毫秒）

	/**
	 * 手指滑动的距离，大于此距离时，移出屏幕
	 */
	private static float OUT_DISTANCE_BOUDARY;

	private static float MAX_DEGREE = 15;
	
	private float mTouchSlop;

	private VelocityTracker vTracker;

	private PagerAdapter mAdapter;
	private Spring mScaleSpring;
	private Spring tranSpring;
	private Spring rotateSpring;

	private final BaseSpringSystem mSpringSystem = SpringSystem.create();
	private final ExampleSpringListener mSpringListener = new ExampleSpringListener();

	private View currentView;
	private Rect currentViewRect = new Rect();
	
	private int currentItem = -1;

	/**
	 * 屏幕高度
	 */
	private int mHeight, mWidth;

	private OnPageChangeListener pageChangeListener;
	
	private SparseArray<Object> objs = new SparseArray<Object>();

	public JellyViewPager(Context context) {
		super(context);
		init(context);
	}

	public JellyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		super.setOnPageChangeListener(new MyPageChageListener());

		mScaleSpring = mSpringSystem.createSpring();
		tranSpring = mSpringSystem.createSpring();
		rotateSpring = mSpringSystem.createSpring();
		mScaleSpring.addListener(mSpringListener);
		tranSpring.addListener(mSpringListener);
		rotateSpring.addListener(mSpringListener);
		
		ViewConfiguration configuration = ViewConfiguration.get(context);
		//FLING_VELOCITY = configuration.getScaledMaximumFlingVelocity();
		mTouchSlop = configuration.getScaledTouchSlop();
	}

	
	@Override
	public void setAdapter(PagerAdapter adapter) {
		mAdapter = adapter;
		super.setAdapter(new ViewPagerAdapter());
	}
	
	@Override
	public void setOnPageChangeListener(OnPageChangeListener listener) {
		pageChangeListener = listener;
	}


	/**
	 * 重置spring
	 */
	private void resetSpring() {
		if (tranSpring.isAtRest()) {
			tranSpring.removeAllListeners();
			tranSpring.setCurrentValue(0);
			tranSpring.setEndValue(0);
			tranSpring.addListener(mSpringListener);
		}
		if (rotateSpring.isAtRest()) {
			rotateSpring.removeAllListeners();
			rotateSpring.setCurrentValue(0);
			rotateSpring.setEndValue(0);
			rotateSpring.addListener(mSpringListener);
		}
	}

	/**
	 * 显示下一页
	 */
	public void showNext() {
		resetSpring();
		animOutIfNeeded(mHeight, 0);
	}

	/**
	 * 显示上一页
	 */
	public void showPre() {
		resetSpring();
		animOutIfNeeded(-mHeight, 0);
	}

	private void nextPage() {
		super.setCurrentItem(currentItem + 1, false);
	}

	private void prePage() {
		super.setCurrentItem(currentItem - 1, false);
	}

	/**
	 * 是否有下一页
	 * 
	 * @return
	 */
	public boolean hasNext() {
		return getCurrentItem() < mAdapter.getCount() - 1;
	}

	/**
	 * 是否有上一页
	 * 
	 * @return
	 */
	public boolean hasPre() {
		return getCurrentItem() > 0;
	}

	/**
	 * 获取当前视图
	 * 
	 * @return
	 */
	private View getCurrentView() {
		View view = findViewFromObject(getCurrentItem());
		return view;
	}

	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mAdapter.getCount();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return mAdapter.isViewFromObject(view, obj);
		}

		@Override
		public void destroyItem(ViewGroup view, int position, Object object) {
			objs.remove(position);
			mAdapter.destroyItem(view, position, object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Object obj = mAdapter.instantiateItem(container, position);
			setObjectForPosition(obj, position);
			return obj;
		}
		
		@Override
		public void setPrimaryItem(ViewGroup container, int position,
                                   Object object) {
			mAdapter.setPrimaryItem(container, position, object);
		}
		
		@Override
		public void finishUpdate(ViewGroup container) {
			mAdapter.finishUpdate(container);
		}
		
	}
	
	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		super.onLayout(arg0, arg1, arg2, arg3, arg4);
		if(currentView == null){
			currentView = getCurrentView();
			ViewHelper.setScaleX(currentView, MAX_SCALE);
			ViewHelper.setScaleY(currentView, MAX_SCALE);
			currentView.getHitRect(currentViewRect);
		}
		mHeight = getHeight();
		mWidth = getWidth();
		OUT_DISTANCE_BOUDARY = MAX_SCALE * mHeight / 3;
	}


	/**
	 * 移出视图动画
	 * 
	 * @param scrollDis
	 *            滑动距离
	 * @param velocityY
	 *            滑动速度
	 */
	private void animOutIfNeeded(float scrollDis, float velocityY) {
		float tranY = 0;
		tranSpring.setOvershootClampingEnabled(true);
		// 下移
		if (velocityY > FLING_VELOCITY || (scrollDis > OUT_DISTANCE_BOUDARY)) {
			if (hasNext()) {
				tranY = mHeight;
				// 和endvalue不相等，不会rest，下一个view会出现旋转，所以要设置为rest
				rotateSpring.setAtRest();
			} else {
				// 角度回正
				rotateSpring.setEndValue(0);
			}
		} else if (velocityY < -FLING_VELOCITY
				|| (scrollDis < -OUT_DISTANCE_BOUDARY)) { // 上移
			if (hasPre()) {
				tranY = -mHeight;
				rotateSpring.setAtRest();
			} else {
				rotateSpring.setEndValue(0);
			}
		} else {
			// 不移动
			tranSpring.setOvershootClampingEnabled(false);
			rotateSpring.setEndValue(0);
		}
		tranSpring.setEndValue(tranY);
	}

	/**
	 * 确保获得正确位置的View
	 */
	private void ensureCorrectView() {
		if (currentItem != getCurrentItem()) {
			currentItem = getCurrentItem();
			currentView = getCurrentView();
		}
	}

	private class ExampleSpringListener extends SimpleSpringListener {
		@Override
		public void onSpringUpdate(Spring spring) {
			ensureCorrectView();
			float value = (float) spring.getCurrentValue();
			String springId = spring.getId();
			if (springId.equals(tranSpring.getId())) {
				ViewHelper.setTranslationY(currentView, value);
				if (spring.isAtRest()) {
					if (value >= mHeight) {
						nextPage();
					} else if (value <= -mHeight) {
						prePage();
					}
				}
			} else if (springId.equals(mScaleSpring.getId())) {
				ViewHelper.setScaleX(currentView,value);
				ViewHelper.setScaleY(currentView,value);
			} else if (springId.equals(rotateSpring.getId())) {
				ViewHelper.setRotation(currentView,value);
			}
		}
	}

	private class MyPageChageListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			if (pageChangeListener != null) {
				pageChangeListener.onPageSelected(position);
			}
			if (currentView != null) {
				ViewHelper.setTranslationY(currentView,0);
				ViewHelper.setRotation(currentView,0);
			}
			mScaleSpring.setCurrentValue(MIN_SCALE);
			mScaleSpring.setEndValue(MAX_SCALE);
		}
	}

	public void setObjectForPosition(Object obj, int position) {
		objs.put(Integer.valueOf(position), obj);
	}

	public View findViewFromObject(int position) {
		Object o = objs.get(Integer.valueOf(position));
		if (o == null) {
			return null;
		}
		if(o instanceof View){
			return (View)o;
		}else if(o instanceof Fragment){
			return ((Fragment)o).getView();
		}
		return null;
	}

	private static final boolean API_11;
	static {
		API_11 = Build.VERSION.SDK_INT >= 11;
	}

	
	private float downY, downX,distanceY;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (vTracker == null) {
			vTracker = VelocityTracker.obtain();
		}
		vTracker.addMovement(event);
		float currentY = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = event.getY();
			downX = event.getX();
			if(!currentViewRect.contains((int)downX, (int)downY)){
				return false;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			distanceY = currentY - downY;
			if(Math.abs(distanceY) > mTouchSlop){
				if(pageChangeListener != null){
					pageChangeListener.onPageScrolled(currentItem, (int) Math.abs(distanceY)/getHeight(), (int)distanceY);
					pageChangeListener.onPageScrollStateChanged(SCROLL_STATE_DRAGGING);
				}
				tranSpring.setEndValue(distanceY);
				float degree = MAX_DEGREE * distanceY / mHeight;
				if (downX < mWidth / 2) {
					degree = -degree;
				}
				rotateSpring.setEndValue(degree);
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			if(Math.abs(distanceY) > mTouchSlop){
				if(pageChangeListener != null){
					pageChangeListener.onPageScrollStateChanged(SCROLL_STATE_SETTLING);
				}
				final VelocityTracker tracker = vTracker;
				tracker.computeCurrentVelocity(UNIT);
				float velocityY = tracker.getYVelocity();
				animOutIfNeeded(currentY - downY, velocityY);
				if (vTracker != null) {
					vTracker.recycle();
					vTracker = null;
				}
			}
			break;
		}
		return true;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (vTracker == null) {
			vTracker = VelocityTracker.obtain();
		}
		vTracker.addMovement(event);
		float currentY = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = event.getY();
			downX = event.getX();
			resetSpring();
			break;
		case MotionEvent.ACTION_MOVE:
			float distance = Math.abs(currentY - downY);
			if(distance > mTouchSlop){
				 //拦截，不向下传递
				return true;
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			final VelocityTracker tracker = vTracker;
			tracker.computeCurrentVelocity(UNIT);
			float velocityY = tracker.getYVelocity();
			if (vTracker != null) {
				vTracker.recycle();
				vTracker = null;
			}
			if(velocityY > FLING_VELOCITY){
				//拦截，不向下传递
				return true;
			}
			break;
		}
		return false;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void manageLayer(View v, boolean enableHardware) {
		if (!API_11)
			return;
		int layerType = enableHardware ? View.LAYER_TYPE_HARDWARE
				: View.LAYER_TYPE_NONE;
		if (layerType != v.getLayerType())
			v.setLayerType(layerType, null);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void disableHardwareLayer() {
		if (!API_11)
			return;
		View v;
		for (int i = 0; i < getChildCount(); i++) {
			v = getChildAt(i);
			if (v.getLayerType() != View.LAYER_TYPE_NONE)
				v.setLayerType(View.LAYER_TYPE_NONE, null);
		}
	}
	
	
	@Override
	@Deprecated
	public void setCurrentItem(int item) {
		throw new RuntimeException("setCurrentItem cannot be used.");
	}

	@Override
	@Deprecated
	public void setCurrentItem(int item, boolean smoothScroll) {
		throw new RuntimeException("setCurrentItem cannot be used.");
	}
}
