package blcs.lwb.lwbtool.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * TODO Base64转换工具类
 * 1、通过路径生成Base64文件
 * 2、把bitmap转换成base64
 * 3、把base64转换成bitmap
 * 4、
 *
 */
public class Base64Utils
{

	/**
	 * 通过路径生成Base64文件
	 * 
	 * @param path
	 * @return
	 */
	public static String getBase64FromPath(String path)
	{
		String base64 = "";
		try
		{
			File file = new File(path);
			byte[] buffer = new byte[(int) file.length() + 100];
			@SuppressWarnings("resource")
			int length = new FileInputStream(file).read(buffer);
			base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return base64;
	}

	/**
	 * 把bitmap转换成base64
	 * 
	 * @param bitmap
	 * @param bitmapQuality
	 * @return
	 */
	public static String getBase64FromBitmap(Bitmap bitmap, int bitmapQuality)
	{
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, bitmapQuality, bStream);
		byte[] bytes = bStream.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}

	/**
	 * 把base64转换成bitmap
	 * 
	 * @param string
	 * @return
	 */
	public static Bitmap getBitmapFromBase64(String string)
	{
		byte[] bitmapArray = null;
		try
		{
			bitmapArray = Base64.decode(string, Base64.DEFAULT);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
	}

}