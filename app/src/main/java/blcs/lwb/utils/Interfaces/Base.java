package blcs.lwb.utils.Interfaces;

import android.os.Bundle;

public interface Base
{
	public interface IBaseView
	{

	}

	public interface IBaseFrament extends IBaseView
	{
	}

	public interface IBaseActivity extends IBaseView
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

	public interface IModel
	{

	}

}
