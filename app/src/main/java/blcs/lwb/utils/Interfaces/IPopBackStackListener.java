package blcs.lwb.utils.Interfaces;

import android.os.Bundle;

public interface IPopBackStackListener
{
	/**
	 * Frament退栈监听
	 * 
	 * @param returnCode
	 *            返回码
	 * @param bundle
	 *            用于传参
	 */
	void popBackListener(int returnCode, Bundle bundle);

	/**
	 * Frament退栈监听
	 */
	void popBackListener();

}
