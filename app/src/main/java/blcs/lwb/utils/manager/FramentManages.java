package blcs.lwb.utils.manager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import java.util.ArrayList;

import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.fragment.EditTextUtilsFragment;
import blcs.lwb.utils.fragment.MyFragment;
import blcs.lwb.utils.fragment.StringUtilsFragment;
import blcs.lwb.utils.mvp.BaseFragmentActivity;

/**
 * 对所有片段操作的管理
 * @author WESTAKE
 *
 */
public class FramentManages
{
	private ArrayList<BaseFragment> list_Frament;// 保存当前Activity的Frament
	public FragmentManager fm;// 片段管理器
	/**
	 *  片段名
	 */
	public final static String Demo="我的片段";
	public final static String Umeng="友盟统计";
	public final static String Umeng_Package="友盟多渠道打包";
	public final static String APK_Sign="APK签名打包";
	public final static String Log_Utils="LogUtils";
	public final static String String_Utils="StringUtils";
	public final static String EditText_Utils="EditTextUtils";

	/**
	 * 这个在Fragment中不能new出来,只能在Activity中new，每个Activity对应一个List_fragment来管理
	 */
	public FramentManages(BaseFragmentActivity activity)
	{
		list_Frament = new ArrayList<>();
		fm = activity.getSupportFragmentManager();
	}

	private BaseFragment getFrament(Activity activity, String alias)
	{
		switch (alias)
		{
			default:
				return null;
			case FramentManages.Demo:
            case FramentManages.Umeng:
            case FramentManages.Umeng_Package:
            case FramentManages.APK_Sign:
            case FramentManages.Log_Utils:
				return new MyFragment();
			case FramentManages.String_Utils:
				return new StringUtilsFragment();
			case FramentManages.EditText_Utils:
				return new EditTextUtilsFragment();
//			case FramentManages.UtilsDetail://工具详情界面
//				return new HomeTab1DetailFragment();
		}
	}

	/**
	 * 替换Frament 这里先删除再添加，完成替换操作
	 *
	 * @param viewId
	 *            Frament 放置的FramentLayout
	 * @param activity
	 * @param alias
	 *            别名
	 */
	public void replaceFrament(int viewId, BaseFragmentActivity activity, String alias)
	{

		BaseFragment base = getFrament(activity, alias);
		if (base == null)
		{
			return;
		}
		popBackStack();
		list_Frament.add(base);
		fm.beginTransaction()
				// 添加Frament
				.add(viewId, base,alias).addToBackStack(null)
				// 提交
				.commit();

	}

	/**
	 * 添加Frament
	 *
	 * @param viewId
	 *            Frament 放置的FramentLayout的id
	 * @param activity
	 *            对应的Activity
	 * @param alias
	 *            别名，用于管理Fragment的名字
	 * @param bundle
	 *            传递的参数，如果不传，设置null即可
	 * @param isAnim
	 *            是否添加动画
	 */
	public void addFrament(int viewId, BaseFragmentActivity activity, String alias,
                           Bundle bundle, boolean isAnim)
	{
		BaseFragment base = getFrament(activity, alias);
		if (base == null)
		{
			return;
		}

		list_Frament.add(base);
		if (bundle != null)
		{
			base.setArguments(bundle);
		}
		FragmentTransaction bt = fm.beginTransaction();
		if (isAnim)
		{
			// 添加动画
			bt.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out,
					R.anim.push_right_in, R.anim.push_right_out);
		}
		// 添加Frament
		bt.add(viewId, base,alias)
				// 添加到后退栈中
				.addToBackStack(null)
				// 提交
				.commitAllowingStateLoss();
	}

	public void popBackStack()
	{
		if (list_Frament.size() > 1)
		{
			// 从后退栈中弹出
			fm.popBackStack();
			list_Frament.remove(list_Frament.size() - 1);
		}
	}

	/**
	 * 获取上一个片段
	 *
	 * @return
	 */
	public BaseFragment getLastFrament()
	{
		if (list_Frament.size() - 1 >= 0)
		{
			return list_Frament.get(list_Frament.size() - 1);
		} else
		{
			return null;
		}
	}

	/**
	 * 获取当前Activity的所有片段 Fragment
	 *
	 * @return
	 */
	public ArrayList<BaseFragment> getAllFrament()
	{
		return list_Frament;
	}

	/**
	 * 清除当前Activity的所有片段 Fragment
	 */
	public void clearAllFrament()
	{
		for (int i = 0; i < list_Frament.size(); i++)
		{
			popBackStack();
		}
		list_Frament.clear();
	}
}
