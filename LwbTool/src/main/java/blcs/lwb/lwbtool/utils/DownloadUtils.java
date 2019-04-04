package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import blcs.lwb.lwbtool.R;

import static android.os.Build.VERSION_CODES.N;
import static android.support.v4.app.NotificationCompat.PRIORITY_MIN;

/**
 * 下载工具类（开发中一般用于APK应用升级）
 */
public class DownloadUtils
{
	private static int FILE_LEN = 0;
	private static Context mContext;
	private static RemoteViews mNotifiviews;
	public static String APK_UPGRADE = Environment
			.getExternalStorageDirectory() + "/DownLoad/apk/BLCS.apk";
	private static PendingIntent nullIntent;

	/**
	 * 判断8.0 安装权限
	 */
	public static void downApk(Activity context, String url) {
		if (Build.VERSION.SDK_INT >= 26) {
			boolean b = context.getPackageManager().canRequestPackageInstalls();
			if (b) {
				downloadAPK(context, url, null);
			} else {
				//请求安装未知应用来源的权限
				startInstallPermissionSettingActivity(context);
			}
		} else {
			downloadAPK(context, url, null);
		}
	}

	/**
	 * 开启安装APK权限(适配8.0)
	 */
	@RequiresApi(api = Build.VERSION_CODES.O)
	public static void startInstallPermissionSettingActivity(Activity context) {
		Uri packageURI = Uri.parse("package:" + context.getApplication().getPackageName());
		Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
		context.startActivity(intent);
	}

	/**
	 * 下载APK文件
	 */
	public static void downloadAPK(Context context, String url,String localAddress)
	{
		// 下载
		mContext = context;
		if (localAddress != null)
		{
			APK_UPGRADE = localAddress;
		}

		new UpgradeTask().execute(url);
	}

	static class UpgradeTask extends AsyncTask<String, Integer, Void>
	{
		@Override
		protected void onPreExecute()
		{
			// 发送通知显示升级进度
			sendNotify();
		}
		@Override
		protected Void doInBackground(String... params)
		{
			String apkUrl = params[0];
			InputStream is = null;
			FileOutputStream fos = null;
			try
			{
				URL url = new URL(apkUrl);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				// 设置连接超时时间
				conn.setConnectTimeout(25000);
				// 设置下载数据超时时间
				conn.setReadTimeout(25000);
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK)
				{
					return null;// 服务端错误响应
				}
				is = conn.getInputStream();
				FILE_LEN = conn.getContentLength();
				File apkFile = new File(APK_UPGRADE);
				// 如果文件夹不存在则创建
				if (!apkFile.getParentFile().exists())
				{
					apkFile.getParentFile().mkdirs();
				}
				fos = new FileOutputStream(apkFile);
				byte[] buffer = new byte[1024];
				int len = 0;
				int loadedLen = 0;// 当前已下载文件大小
				// 更新10次
				int updateSize = FILE_LEN / 10;
				int num = 0;
				while (-1 != (len = is.read(buffer)))
				{
					loadedLen += len;
					fos.write(buffer, 0, len);
					if (loadedLen > updateSize * num)
					{
						num++;
						publishProgress(loadedLen);
					}
				}
				fos.flush();
			} catch (MalformedURLException e)
			{
				e.printStackTrace();
			} catch (SocketTimeoutException e)
			{
				// 处理超时异常，提示用户在网络良好情况下重试
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally
			{
				if (is != null)
				{
					try
					{
						is.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				if (fos != null)
				{
					try
					{
						fos.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			// 更新通知
			updateNotify(values[0]);
		}

		@Override
		protected void onPostExecute(Void result)
		{
			Toast.makeText(mContext, "下载完成，请点击通知栏完成升级", Toast.LENGTH_LONG)
					.show();
			finishNotify();
		}
	}

	private static void sendNotify()
	{
		Intent intent = new Intent();
		nullIntent = PendingIntent.getActivity(mContext, 0,
				intent, 0);
		mNotifiviews = new RemoteViews(mContext.getPackageName(),
				R.layout.custom_notify);
		mNotifiviews.setViewVisibility(R.id.tv_custom_notify_number, View.VISIBLE);
		mNotifiviews.setViewVisibility(R.id.pb_custom_notify, View.VISIBLE);
        LinNotify.show(mContext,"","",mNotifiviews,LinNotify.NEW_MESSAGE,nullIntent);
	}

	private static void updateNotify(int loadedLen)
	{
		int progress = loadedLen * 100 / FILE_LEN;
		mNotifiviews.setTextViewText(R.id.tv_custom_notify_number, progress + "%");
		mNotifiviews.setProgressBar(R.id.pb_custom_notify, FILE_LEN, loadedLen,
				false);

		LinNotify.show(mContext,"","",mNotifiviews,LinNotify.NEW_MESSAGE,nullIntent);
	}

	private static void finishNotify()
	{
		mNotifiviews.setTextViewText(R.id.tv_custom_notify_number,  "100%");
//		Intent installAppIntent = getInstallAppIntent(mContext, APK_UPGRADE);
//		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,installAppIntent, 0);
		mNotifiviews.setTextViewText(R.id.tv_title, "下载完成，请点击完成升级");
		mNotifiviews.setViewVisibility(R.id.tv_custom_notify_number, View.INVISIBLE);
		mNotifiviews.setViewVisibility(R.id.pb_custom_notify, View.INVISIBLE);
		LinNotify.show(mContext,"","",mNotifiviews,LinNotify.NEW_MESSAGE,nullIntent);
	}

	/**
	 * 调往系统APK安装界面（适配7.0）
	 * @param context
	 * @param filePath
	 * @return
	 */
	public static Intent getInstallAppIntent(Context context, String filePath) {
		//apk文件的本地路径
		File apkfile = new File(filePath);
		if (!apkfile.exists()) {
			return null;
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri contentUri = getUriForFile(context, apkfile);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
		}
		intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
		return intent;
	}

	/**
	 * 将文件转换成uri
	 * @param mContext
	 * @param file
	 * @return
	 */
	public static Uri getUriForFile(Context mContext, File file) {
		Uri fileUri = null;
		if (Build.VERSION.SDK_INT >= 24) {
			fileUri = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".fileprovider", file);
		} else {
			fileUri = Uri.fromFile(file);
		}
		return fileUri;
	}

}
