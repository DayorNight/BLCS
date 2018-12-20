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
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import blcs.lwb.lwbtool.R;

/**TODO 通用操作类
 * 1、打电话
 * 2、发送信息，多号码
 * 3、发送信息，单个号码
 * 4、分享信息
 * 5、发送邮件
 * 6、打开网站
 * 7、复制文字
 * 8、启动新Activity方法，向左滑入效果
 * 9、显示与关闭进度弹窗方法
 * 10、快捷显示short toast方法，
 * 11、照片裁剪
 * 12、保存照片到SD卡上面
 * 13、检测网络是否可用
 * 14、检测Sdcard是否存在
 * 15、获取顶层 Activity
 * 16、检查是否有位置权限
 * 17、检查是否有权限
 * 18、自定义显示Toast时间
 * 19、定制Toast的布局显示
 */
public class CommonUtil {
	private static final String TAG = "CommonUtil";

	public CommonUtil() {/* 不能实例化**/}

	private static Intent intent = null;

	//电话<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/** TODO 打电话
	 * @param context
	 * @param phone
	 */
	public static void call(Activity context, String phone) {
		if (StringUtils.isNotEmpty(phone, true)) {
			Uri uri = Uri.parse("tel:" + phone.trim());
			intent  = new Intent(Intent.ACTION_CALL, uri);
			toActivity(context, intent);
			return;
		}
		showShortToast(context, "请先选择号码哦~");
	}

	//电话>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//信息<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**TODO 发送信息，多号码
	 * @param context
	 * @param phoneList
	 */
	public static void toMessageChat(Activity context, List<String> phoneList){
		if (context == null || phoneList == null || phoneList.size() <= 0) {
			Log.e(TAG, "sendMessage context == null || phoneList == null || phoneList.size() <= 0 " +
					">> showShortToast(context, 请先选择号码哦~); return; ");
			showShortToast(context, "请先选择号码哦~");
			return;
		}

		String phones = "";
		for (int i = 0; i < phoneList.size(); i++) {
			phones += phoneList.get(i) + ";";
		}
		toMessageChat(context, phones);
	}
	/**TODO 发送信息，单个号码
	 * @param context
	 * @param phone
	 */
	public static void toMessageChat(Activity context, String phone){
		if (context == null || StringUtils.isNotEmpty(phone, true) == false) {
			Log.e(TAG, "sendMessage  context == null || StringUtils.isNotEmpty(phone, true) == false) >> return;");
			return;
		}

		intent = new Intent(Intent.ACTION_VIEW);  
		intent.putExtra("address", phone);
		intent.setType("vnd.android-dir/mms-sms");    
		toActivity(context, intent);

	}

	//信息>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**TODO 分享信息
	 * @param context
	 * @param toShare
	 */
	public static void shareInfo(Activity context, String toShare) {
		if (context == null || StringUtils.isNotEmpty(toShare, true) == false) {
			Log.e(TAG, "shareInfo  context == null || StringUtils.isNotEmpty(toShare, true) == false >> return;");
			return;
		}

		intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");   
		intent.putExtra(Intent.EXTRA_SUBJECT, "选择分享方式");   
		intent.putExtra(Intent.EXTRA_TEXT, toShare.trim());    
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		toActivity(context, intent, -1);
	}

	/**TODO 发送邮件
	 * @param context
	 * @param emailAddress
	 */
	public static void sendEmail(Activity context, String emailAddress) {
		if (context == null || StringUtils.isNotEmpty(emailAddress, true) == false) {
			Log.e(TAG, "sendEmail  context == null || StringUtils.isNotEmpty(emailAddress, true) == false >> return;");
			return;
		}

		intent = new Intent(Intent.ACTION_SENDTO); 
		intent.setData(Uri.parse("mailto:"+ emailAddress));//缺少"mailto:"前缀导致找不到应用崩溃
		intent.putExtra(Intent.EXTRA_TEXT, "内容");  //最近在MIUI7上无内容导致无法跳到编辑邮箱界面
		toActivity(context, intent, -1);
	}
	
	/**TODO 打开网站
	 * @param context
	 * @param webSite
	 */
	public static void openWebSite(Activity context, String webSite) {
		if (context == null || StringUtils.isNotEmpty(webSite, true) == false) {
			Log.e(TAG, "openWebSite  context == null || StringUtils.isNotEmpty(webSite, true) == false >> return;");
			return;
		}

		if (! webSite.startsWith("http://") && ! webSite.startsWith("https://")) {
			webSite = "http://" + webSite;}
		final Uri uri = Uri.parse(webSite);          
		intent = new Intent(Intent.ACTION_VIEW, uri);
		toActivity(context, intent, -1);
	}

	/**TODO 复制文字
	 * @param context
	 * @param value
	 */
	public static void copyText(Context context, String value) {
		if (context == null || StringUtils.isNotEmpty(value, true) == false) {
			Log.e(TAG, "copyText  context == null || StringUtils.isNotEmpty(value, true) == false >> return;");
			return;
		}

		ClipData cD = ClipData.newPlainText("simple text", value);
		ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		clipboardManager.setPrimaryClip(cD);
		showShortToast(context, "已复制\n" + value);
	}


	//TODO 启动新Activity方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**TODO 打开新的Activity，向左滑入效果
	 * @param intent
	 */
	public static void toActivity(final Activity context, final Intent intent) {
		toActivity(context, intent, true);
	}
	/**TODO 打开新的Activity
	 * @param intent
	 * @param showAnimation
	 */
	public static void toActivity(final Activity context, final Intent intent, final boolean showAnimation) {
		toActivity(context, intent, -1, showAnimation);
	}
	/**TODO 打开新的Activity，向左滑入效果
	 * @param intent
	 * @param requestCode
	 */
	public static void toActivity(final Activity context, final Intent intent, final int requestCode) {
		toActivity(context, intent, requestCode, true);
	}
	/**TODO 打开新的Activity
	 * @param intent
	 * @param requestCode
	 * @param showAnimation
	 */
	public static void toActivity(final Activity context, final Intent intent, final int requestCode, final boolean showAnimation) {
		if (context == null || intent == null) {
			return;
		}
		context.runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (requestCode < 0) {
					context.startActivity(intent);
				} else {
					context.startActivityForResult(intent, requestCode);
				}
				if (showAnimation) {
					context.overridePendingTransition(R.anim.right_push_in, R.anim.hold);
				} else {
					context.overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
				}
			}
		});
	}
	//TODO 启动新Activity方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//TODO 显示与关闭进度弹窗方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private static ProgressDialog progressDialog = null;

	/**TODO 展示加载进度条,无标题
	 * @param stringResId
	 */
	public static void showProgressDialog(Activity context, int stringResId){
		try {
			showProgressDialog(context, null, context.getResources().getString(stringResId));
		} catch (Exception e) {
			Log.e(TAG, "showProgressDialog  showProgressDialog(Context context, null, context.getResources().getString(stringResId));");
		}
	}
	/**TODO 展示加载进度条,无标题
	 * @param dialogMessage
	 */
	public void showProgressDialog(Activity context, String dialogMessage){
		showProgressDialog(context, null, dialogMessage);
	}
	/**TODO 展示加载进度条
	 * @param dialogTitle Title 标题
	 * @param dialogMessage Message 信息
	 */
	public static void showProgressDialog(final Activity context, final String dialogTitle, final String dialogMessage){
		if (context == null) {
			return;
		}
		context.runOnUiThread(new Runnable() {
			@Override
			public void run() {

				if (progressDialog == null) {
					progressDialog = new ProgressDialog(context);
				}
				if(progressDialog.isShowing() == true) {
					progressDialog.dismiss();
				}
				if (dialogTitle != null && ! "".equals(dialogTitle.trim())) {
					progressDialog.setTitle(dialogTitle);
				}
				if (dialogMessage != null && ! "".equals(dialogMessage.trim())) {
					progressDialog.setMessage(dialogMessage);
				}
				progressDialog.setCanceledOnTouchOutside(false);
				progressDialog.show();
			}
		});
	}


	/** TODO 隐藏加载进度
	 */
	public static void dismissProgressDialog(Activity context) {
		if(context == null || progressDialog == null || progressDialog.isShowing() == false){
			return;
		}
		context.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				progressDialog.dismiss();
			}
		});
	}
	//TODO 显示与关闭进度弹窗方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//TODO show short toast 方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**TODO 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
	 * @param context
	 * @param stringResId
	 */
	public static void showShortToast(final Context context, int stringResId) {
		try {
			showShortToast(context, context.getResources().getString(stringResId));
		} catch (Exception e) {
			Log.e(TAG, "showShortToast  context.getResources().getString(resId) >>  catch (Exception e) {" + e.getMessage());
		}
	}
	/**TODO 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
	 * @param string
	 */
	public static void showShortToast(final Context context, final String string) {
		showShortToast(context, string, false);
	}
	/**快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
	 * @param string
	 * @param isForceDismissProgressDialog
	 */
	public static void showShortToast(final Context context, final String string, final boolean isForceDismissProgressDialog) {
		if (context == null) {
			return;
		}
		Toast.makeText(context, "" + string, Toast.LENGTH_SHORT).show();
	}
	//show short toast 方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




	public static void startPhotoZoom(Activity context, int requestCode, String path, int width, int height) {
		startPhotoZoom(context, requestCode, Uri.fromFile(new File(path)), width, height);
	}
	/**TODO 照片裁剪
	 * @param context
	 * @param requestCode
	 * @param fileUri
	 * @param width
	 * @param height
	 */
	public static void startPhotoZoom(Activity context, int requestCode, Uri fileUri, int width, int height) {
		intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(fileUri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");

		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", width);
		intent.putExtra("outputY", height);
		intent.putExtra("return-data", true);
		Log.i(TAG, "startPhotoZoom"+ fileUri +" uri");
		toActivity(context, intent, requestCode);
	}

	/**TODO 保存照片到SD卡上面
	 * @param path
	 * @param photoName
	 * @param formSuffix 
	 * @param photoBitmap
	 */
	public static String savePhotoToSDCard(String path, String photoName, String formSuffix, Bitmap photoBitmap) {
		if (photoBitmap == null || StringUtils.isNotEmpty(path, true) == false 
				|| StringUtils.isNotEmpty(StringUtils.getTrimedString(photoName)
						+ StringUtils.getTrimedString(formSuffix), true) == false) {
			Log.e(TAG, "savePhotoToSDCard photoBitmap == null || StringUtils.isNotEmpty(path, true) == false" +
					"|| StringUtils.isNotEmpty(photoName, true) == false) >> return null" );
			return null;
		}

		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File photoFile = new File(path, photoName + "." + formSuffix); // 在指定路径下创建文件
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(photoFile);
				if (photoBitmap != null) {
					if (photoBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
							fileOutputStream)) {
						fileOutputStream.flush();
						Log.i(TAG, "savePhotoToSDCard<<<<<<<<<<<<<<\n" + photoFile.getAbsolutePath() + "\n>>>>>>>>> succeed!");
					}
				}
			} catch (FileNotFoundException e) {
				Log.e(TAG, "savePhotoToSDCard catch (FileNotFoundException e) {\n " + e.getMessage());
				photoFile.delete();
				//				e.printStackTrace();
			} catch (IOException e) {
				Log.e(TAG, "savePhotoToSDCard catch (IOException e) {\n " + e.getMessage());
				photoFile.delete();
				//				e.printStackTrace();
			} finally {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					Log.e(TAG, "savePhotoToSDCard } catch (IOException e) {\n " + e.getMessage());
					//					e.printStackTrace();
				}
			}
			return photoFile.getAbsolutePath();
		}
		return null;
	}



	/**
	 * TODO 检测网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetWorkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}

		return false;
	}

	/**
	 * TODO 检测Sdcard是否存在
	 * 
	 * @return
	 */
	public static boolean isExitsSdcard() {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	/**TODO 获取顶层 Activity
	 * @param context
	 * @return
	 */
	public static String getTopActivity(Context context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		@SuppressWarnings("deprecation")
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

		return runningTaskInfos == null ? "" : runningTaskInfos.get(0).topActivity.getClassName();
	}


	/**TODO 检查是否有位置权限
	 * @param context
	 * @return
	 */
	public static boolean isHasLocationPermission(Context context){
		return isHasPermission(context, "android.permission.ACCESS_COARSE_LOCATION") || isHasPermission(context, "android.permission.ACCESS_FINE_LOCATION");
	}
	/**TODO 检查是否有权限
	 * @param context
	 * @param name
	 * @return
	 */
	public static boolean isHasPermission(Context context, String name){
		try {
			return PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(name, context.getPackageName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * TODO 自定义显示Toast时间
	 * @param context 上下文
	 * @param message 显示消息
	 * @param duration 显示时间
	 */
	public static void show(Context context, CharSequence message, int duration)
	{
			Toast.makeText(context, message, duration).show();
	}

	private static Toast toast;

	/**
	 * TODO 定制Toast的布局显示
	 *
	 * @param context 上下文
	 * @param layout 布局（R.layout.布局）
	 */
	public static void showToast(Context context, int layout)
	{
		if (toast != null)
		{
			toast.cancel();
		}
		toast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(layout, null);
		// toast.setBackground();
		toast.setView(view);
		toast.setGravity(Gravity.TOP, 0, 60);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();
	}

}