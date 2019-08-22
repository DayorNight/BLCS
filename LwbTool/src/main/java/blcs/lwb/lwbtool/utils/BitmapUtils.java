package blcs.lwb.lwbtool.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

/**
 * TODO 图片处理工具类
 * 1、Bitmap转化为Drawable
 * 2、Drawable转化为Bitmap
 * 3、获得Resources的Bitmap资源
 * 4、byte[] 字节 吗转为 Bitmap
 * 5、Bitmap 转为字节码 byte[]
 * 6、通过文件路径获取到bitmap
 * 7、压缩图片
 * 8、缩放/裁剪图片
 * 9、获得本地的图片
 * 10、根据资源id获取指定大小的Bitmap对象
 * 11、根据文件路径获取指定大小的Bitmap对象
 * 12、获取指定大小的Bitmap对象
 * 13、将压缩的bitmap保存到SDCard卡临时文件夹，用于上传
 * 14、保存图片
 * 145、保存照片到SD卡上面
 */
public class BitmapUtils
{
	private static final String TAG = "BitmapUtils";
	/**
	 * 1、Bitmap转化为Drawable
	 */
	public static Drawable bitmap2Drawable(Bitmap bmp)
	{
		BitmapDrawable bd = new BitmapDrawable(bmp);
		return bd;
	}

	/**
	 * 2、Drawable转化为Bitmap
	 */
	public static Bitmap drawable2Bitmap(Drawable d)
	{
		BitmapDrawable bd = (BitmapDrawable) d;
		Bitmap bm = bd.getBitmap();
		return bm;
	}

	/**
	 * 3、获得Resources的Bitmap资源
	 */
	public static Bitmap getBitmapFromResources(Activity act, int resId)
	{
		Resources res = act.getResources();
		return BitmapFactory.decodeResource(res, resId);
	}

	/**
	 * 4、byte[]字节码转为Bitmap
	 */
	public static Bitmap convertBytes2Bimap(byte[] b)
	{
		if (b.length == 0)
		{
			return null;
		}
		return BitmapFactory.decodeByteArray(b, 0, b.length);
	}

	/**
	 * 5、Bitmap转为字节码byte[]
	 */
	public static byte[] convertBitmap2Bytes(Bitmap bm)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}



	/**
	 * 7、压缩图片
	 * @param srcPath 图片路径
	 * @param width 压缩的宽
	 * @param height 压缩的高
	 */
	public static Bitmap compressImageFromFile(String srcPath, int width, int height)
	{
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;// 只读边,不读内容
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		newOpts.inJustDecodeBounds = false;
		// 读取出图片实际的宽高
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		int be = 1;
		if (w > h && w > width)
		{
			be = (int) (w / width);
		}
		else if (w < h && h > height)
		{
			be = (int) (h / height);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置压缩比例
		newOpts.inPreferredConfig = Config.ARGB_8888;// 该模式是默认的,可不设
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;// 当系统内存不够时候图片自动被回收
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return bitmap;
	}

	/**
	 *  8、缩放/裁剪图片
	 * @param bm
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight)
	{
		// 获得图片的宽高
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
		return newbm;
	}
	/**TODO 照片裁剪
	 * @param context
	 * @param requestCode
	 * @param fileUri
	 * @param width
	 * @param height
	 */
	public static void startPhotoZoom(Activity context, int requestCode, Uri fileUri, int width, int height) {
		 Intent intent = new Intent("com.android.camera.action.CROP");
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
		IntentUtils.toActivity(context, intent, requestCode);
	}
	public static void startPhotoZoom(Activity context, int requestCode, String path, int width, int height) {
		startPhotoZoom(context, requestCode, Uri.fromFile(new File(path)), width, height);
	}
	/**
	 *  9、获得本地的图片
	 * @param url
	 * @return
	 */
	public static Bitmap getLoacalBitmap(String url)
	{

		try
		{
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
	}



	/**
	 *  10、根据资源id获取指定大小的Bitmap对象
	 * @param context   应用程序上下文
	 * @param id        资源id
	 * @param height    高度
	 * @param width     宽度
	 * @return
	 */
	public static Bitmap getBitmapFromResource(Context context, int id, int height, int width){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;//只读取图片，不加载到内存中
		BitmapFactory.decodeResource(context.getResources(), id, options);
		options.inSampleSize = calculateSampleSize(height, width, options);
		options.inJustDecodeBounds = false;//加载到内存中
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id, options);
		return bitmap;
	}
	/**
	 *  11、根据文件路径获取指定大小的Bitmap对象
	 * @param path      文件路径
	 * @param height    高度
	 * @param width     宽度
	 * @return
	 */
	public static Bitmap getBitmapFromFile(String path, int height, int width){
		if (TextUtils.isEmpty(path)) {
			throw new IllegalArgumentException("参数为空，请检查你选择的路径:" + path);
		}
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;//只读取图片，不加载到内存中
		BitmapFactory.decodeFile(path, options);
		options.inSampleSize = calculateSampleSize(height, width, options);
		options.inJustDecodeBounds = false;//加载到内存中
		Bitmap bitmap = BitmapFactory.decodeFile(path, options);
		return bitmap;
	}
	/**
	 *  12、获取指定大小的Bitmap对象
	 * @param bitmap    Bitmap对象
	 * @param height    高度
	 * @param width     宽度
	 * @return
	 */
	public static Bitmap getThumbnailsBitmap(Bitmap bitmap, int height, int width){
		if (bitmap == null) {
			throw new IllegalArgumentException("图片为空，请检查你的参数");
		}
		return ThumbnailUtils.extractThumbnail(bitmap, width, height);
	}

	/**
	 * 计算所需图片的缩放比例
	 * @param height
	 * @param width
	 * @param options
	 * @return
	 */
	private static int calculateSampleSize(int height, int width, BitmapFactory.Options options){
		int realHeight = options.outHeight;
		int realWidth = options.outWidth;
		int heigthScale = realHeight / height;
		int widthScale = realWidth / width;
		if(widthScale > heigthScale){
			return widthScale;
		}else{
			return heigthScale;
		}
	}

	/**
	 * 13、将压缩的bitmap保存到SDCard卡临时文件夹，用于上传
	 * @param bit
	 * @param scale 压缩大小为该控件大小的的N倍，主要用于放大后不失真
	 * @return
	 */
	public static File saveMyBitmap(String path, String picName, String bit, int scale) {
        FileOutputStream fOut = null;
		File file=new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		final File f = new File(bit, picName);
        Bitmap bitmap = BitmapFactory.decodeFile(bit);
        try {
			fOut = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
			fOut.flush();
			fOut.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return f;
	}

	/**
	 *  14、保存图片
	 */
	public static boolean saveBitmap(Bitmap mBitmap, String path, String imgName) {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
			return false;
		}
		FileOutputStream b = null;
		File file = new File(path);
		file.mkdirs();// 创建文件夹
		String fileName = path + imgName;
//        delFile(path, imgName);//删除本地旧图
		try {
			b = new FileOutputStream(fileName);
			mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				b.flush();
				b.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return true;
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
}
