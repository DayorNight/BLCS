/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * TODO 屏幕相关类
 * 1、获取屏幕宽度，单位为px
 * 2、获取屏幕高度，单位为px
 * 3、获取系统dp尺寸密度值
 * 4、获取系统字体sp密度值
 * 5、获得状态栏的高度
 * 6、获取当前屏幕截图，包含状态栏
 * 7、获取当前屏幕截图，不包含状态栏
 * 8、获取DisplayMetrics对象
 * 9、获取屏幕像素点
 */
public class ScreenUtils {
	/**
	 * 1、获取屏幕宽度，单位为px
	 */
	public static int getScreenWidth(Context context){
		return getDisplayMetrics(context).widthPixels;
	}

	/**
	 * 2、获取屏幕高度，单位为px
	 */
	public static int getScreenHeight(Context context){
		return getDisplayMetrics(context).heightPixels;
	}

	/**
	 * 3、获取系统dp尺寸密度值
	 */
	public static float getDensity(Context context){
		return getDisplayMetrics(context).density;
	}

	/**
	 * 4、获取系统字体sp密度值
	 */
	public static float getScaledDensity(Context context){
		return getDisplayMetrics(context).scaledDensity;
	}

	/**
	 * 5、获得状态栏的高度
	 */
	public static int getStatusHeight(Context context){
		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

	/**
	 * 6、获取当前屏幕截图，包含状态栏
	 */
	public static Bitmap getSnapShotWithStatusBar(Activity activity){
		View decorView = activity.getWindow().getDecorView();
		decorView.setDrawingCacheEnabled(true);
		decorView.buildDrawingCache();
		Bitmap bmp = decorView.getDrawingCache();
		int width = getScreenWidth(activity);
		int height = getScreenHeight(activity);
		Bitmap bitmap = null;
		bitmap = Bitmap.createBitmap(bmp, 0, 0, width, height);
		decorView.destroyDrawingCache();
		return bitmap;
	}

	/**
	 * 7、获取当前屏幕截图，不包含状态栏
	 */
	public static Bitmap getSnapShotWithoutStatusBar(Activity activity){
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusHeight = frame.top;
		int width = getScreenWidth(activity);
		int height = getScreenHeight(activity);
		Bitmap bitmap = null;
		bitmap = Bitmap.createBitmap(bmp, 0, statusHeight, width, height - statusHeight);
		view.destroyDrawingCache();
		return bitmap;
	}

	/**
	 * 8、获取DisplayMetrics对象
	 */
	public static DisplayMetrics getDisplayMetrics(Context context){
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}
	// 状态栏高度
	private static  int statusBarHeight = 0;
	// 屏幕像素点
	private static final Point screenSize = new Point();

	/**
	 * 9、获取屏幕像素点
 	 */
	public static Point getScreenSize(Activity context) {

		if (context == null) {
			return screenSize;
		}
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		if (wm != null) {
			DisplayMetrics mDisplayMetrics = new DisplayMetrics();
			Display diplay = wm.getDefaultDisplay();
			if (diplay != null) {
				diplay.getMetrics(mDisplayMetrics);
				int W = mDisplayMetrics.widthPixels;
				int H = mDisplayMetrics.heightPixels;
				if (W * H > 0 && (W > screenSize.x || H > screenSize.y)) {
					screenSize.set(W, H);
				}
			}
		}
		return screenSize;
	}


	/**
	 * 获取虚拟功能键高度
	 */
	public static int getVirtualBarHeigh(Context context) {
		int vh = 0;
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		try {
			@SuppressWarnings("rawtypes")
			Class c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
			Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
			method.invoke(display, dm);
			vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vh;
	}
	public static int getVirtualBarHeigh(Activity activity) {
		int titleHeight = 0;
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusHeight = frame.top;
		titleHeight = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop() - statusHeight;
		return titleHeight;
	}
}
