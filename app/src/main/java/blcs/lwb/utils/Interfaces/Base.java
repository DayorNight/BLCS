package blcs.lwb.utils.Interfaces;

import android.os.Bundle;

public interface Base
{
	interface IBaseView
	{

	}

	interface IBaseFrament extends IBaseView
	{
	}

	interface IBaseActivity extends IBaseView
	{
		/**
		 * 显示Frament的操作
		 * 
		 * @param fm_name
		 *            对应Frament的tag ,tag存放在FramentManages类中
		 * @param bundle
		 *            传递的bundle
		 * @param isAnim
		 *            是否添加动画
		 */
		void addFrament(int viewId, String fm_name, Bundle bundle,
                        boolean isAnim);

	}

	interface IModel
	{

	}

}
