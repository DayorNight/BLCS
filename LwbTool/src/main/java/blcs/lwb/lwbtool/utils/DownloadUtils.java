package blcs.lwb.lwbtool.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
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

/**
 * 下载工具类（开发中一般用于APK应用升级）
 * 
 * @author CPC
 * 
 */
public class DownloadUtils
{
	private static int FILE_LEN = 0;

	private static Context mContext;
	private static NotificationManager mNotifiMgr;
	private static Notification mNotifi;
	private static RemoteViews mNotifiviews;
    public static String APK_UPGRADE = Environment
            .getExternalStorageDirectory() + "/myapp/upgrade/"+AppUtils.getAppName(mContext)+".apk";
	/**
	 * 下载APK文件
	 * 
	 * @param context
	 *            上下文
	 * @param url
	 *            APK的地址
	 * @param localAddress
	 *            存储在本地的地址,如果为null将存储在默认的地址
	 */
	public static void downloadAPK(Context context, String url,
                                   String localAddress)
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
					// 服务端错误响应
					return null;
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
				// 更新13次

				int updateSize = FILE_LEN / 100;
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
		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
				intent, 0);
		mNotifiviews = new RemoteViews(mContext.getPackageName(),
				R.layout.custom_notify);
		mNotifiviews.setViewVisibility(R.id.tv_subtitle, View.VISIBLE);
		mNotifiviews.setViewVisibility(R.id.progressBar1, View.VISIBLE);

		mNotifi = new NotificationCompat.Builder(mContext)
				.setContent(mNotifiviews)
				.setAutoCancel(true)
				// 单击后自动删除
				// .setOngoing(true)// 无法删除的通知
				// 定制通知布局
				.setSmallIcon(R.mipmap.ic_launcher).setTicker("ticker")
				.setWhen(System.currentTimeMillis()).setSound(Uri.parse(""))
				//.setVibrate(new long[]{ 0, 100, 300, 400 })
				.setContentIntent(contentIntent).build();
		mNotifiMgr = (NotificationManager) mContext
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotifiMgr.notify(12345, mNotifi);
	}

	private static void updateNotify(int loadedLen)
	{
		int progress = loadedLen * 100 / FILE_LEN;
		mNotifiviews.setTextViewText(R.id.tv_subtitle, progress + "%");
		mNotifiviews.setProgressBar(R.id.progressBar1, FILE_LEN, loadedLen,
				false);
		//mNotifiviews.setViewVisibility(R.id.tv_title, View.INVISIBLE);
		mNotifiMgr.notify(12345, mNotifi);
	}

	private static void finishNotify()
	{
        Uri uri;
		Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT > N) {
             uri = FileProvider.getUriForFile(mContext, "demo.lwb.com.myutils.fileprovider", new File(APK_UPGRADE));
        } else {
             uri = Uri.fromFile(new File(APK_UPGRADE));
        }
		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
				intent, 0);
		mNotifi.contentIntent = contentIntent;
		mNotifiviews.setTextViewText(R.id.tv_title, "下载完成，请点击完成升级");
		mNotifiviews.setViewVisibility(R.id.tv_subtitle, View.INVISIBLE);
		mNotifiviews.setViewVisibility(R.id.progressBar1, View.INVISIBLE);
		mNotifiMgr.notify(12345, mNotifi);
	}
}
