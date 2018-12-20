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
import android.content.res.ColorStateList;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import blcs.lwb.lwbtool.R;

/**
 * TODO 通用密码、手机号、验证码输入框输入字符判断及错误提示 类
 * 1、隐藏输入法
 * 2、显示输入框
 * 3、显示/隐藏输入框
 * 4、显示输入框
 * 5、显示/隐藏输入框
 * 6、判断edittext输入文字是否合法
 * 7、字符不合法提示
 */
public class EditTextUtils {

	//TODO 1显示/隐藏输入框<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private static InputMethodManager imm;

	/**1隐藏输入法
	 * @param context
	 * @param toGetWindowTokenView 包含et的父View，键盘根据toGetWindowTokenView的位置来弹出/隐藏
	 */
	public static void hideKeyboard(Context context, View toGetWindowTokenView){
		showKeyboard(context, null, toGetWindowTokenView, false);
	}
	/**2显示输入框
	 * @param context
	 * @param et
	 */
	public static void showKeyboard(Context context, EditText et){
		showKeyboard(context, et, true);
	}
	/**3显示/隐藏输入框
	 * @param context
	 * @param et
	 * @param show
	 */
	public static void showKeyboard(Context context, EditText et, boolean show){
		showKeyboard(context, et, null, show);
	}
	/**4显示输入框
	 * @param context
	 * @param et
	 * @param toGetWindowTokenView(为null时toGetWindowTokenView = et) 包含et的父View，键盘根据toGetWindowTokenView的位置来弹出/隐藏
	 */
	public static void showKeyboard(Context context, EditText et, View toGetWindowTokenView) {
		showKeyboard(context, et, toGetWindowTokenView, true);
	}
	/**5显示/隐藏输入框
	 * @param context
	 * @param et
	 * @param toGetWindowTokenView(为null时toGetWindowTokenView = et) 包含et的父View，键盘根据toGetWindowTokenView的位置来弹出/隐藏
	 * @param show
	 */
	public static void showKeyboard(Context context, EditText et, View toGetWindowTokenView, boolean show){
		if (context == null) {
			LogUtils.e( "showKeyboard  context == null >> return;");
			return;
		}

		imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);//imm必须与context唯一对应
		if (toGetWindowTokenView == null) {
			LogUtils.w( "showKeyboard   toGetWindowTokenView == null");
			toGetWindowTokenView = et;
		}
		if (toGetWindowTokenView == null) {
			LogUtils.e( "showKeyboard  toGetWindowTokenView == null && et == null  >> return;");
			return;
		}

		if (show == false) {
			imm.hideSoftInputFromWindow(toGetWindowTokenView.getWindowToken(), 0);
			if (et != null) {
				et.clearFocus();
			}
		} else {
			if (et != null) {
				et.setFocusable(true);
				et.setFocusableInTouchMode(true);
				et.requestFocus();
				imm.toggleSoftInputFromWindow(toGetWindowTokenView.getWindowToken()
						, InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
			}
		}
	}

	//TODO 显示/隐藏输入框>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//TODO 2对输入字符判断<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public final static int TYPE_NOT_ALLOWED_EMPTY = 0;
	public final static int TYPE_VERIFY = 1;
	public final static int TYPE_PASSWORD = 2;
	public final static int TYPE_PHONE = 3;
	public final static int TYPE_MAIL = 4;

	private static ColorStateList oringinalHintColor;

	/**6判断edittext输入文字是否合法
	 * @param context
	 * @param et
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, EditText et) {
		return isInputedCorrect(context, et, TYPE_NOT_ALLOWED_EMPTY, null);
	}
	/**7判断edittext输入文字是否合法
	 * @param context
	 * @param et
	 * @param errorRemind
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, EditText et, String errorRemind) {
		return isInputedCorrect(context, et, TYPE_NOT_ALLOWED_EMPTY, errorRemind);
	}
	/**8判断edittext输入文字是否合法
	 * @param context
	 * @param et
	 * @param type
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, EditText et, int type) {
		return isInputedCorrect(context, et, type, null);
	}
	/**判断edittext输入文字是否合法
	 * @param context
	 * @param stringResId
	 * @param et
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, int stringResId, EditText et) {
		return isInputedCorrect(context, et, TYPE_NOT_ALLOWED_EMPTY, stringResId);
	}
	/**判断edittext输入文字是否合法
	 * @param context
	 * @param et
	 * @param type
	 * @param stringResId
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, EditText et, int type, int stringResId) {
		try {
			if (context != null && stringResId > 0) {
				return isInputedCorrect(context, et, type, context.getResources().getString(stringResId));
			}
		} catch (Exception e) {
			LogUtils.e( "isInputedCorrect try { if (context != null && stringResId > 0) {catch (Exception e) \n" + e.getMessage());
		}
		return false;
	}
	/**判断edittext输入文字是否合法
	 * @param context
	 * @param et
	 * @param type
	 * @return
	 */
	public static boolean isInputedCorrect(Activity context, EditText et, int type, String errorRemind) {
		if (context == null || et == null) {
			LogUtils.e( "isInputedCorrect context == null || et == null >> return false;");
			return false;
		}
		oringinalHintColor = et.getHintTextColors();

		String inputed = StringUtils.getTrimedString(et);
		switch (type) {
			case TYPE_VERIFY:
				if (type == TYPE_VERIFY && inputed.length() < 4) {
					return showInputedError(context, et, StringUtils.isNotEmpty(errorRemind, true) ? errorRemind : "验证码不能小于4位");
				}
				break;
			case TYPE_PASSWORD:
				if (inputed.length() < 6) {
					return showInputedError(context, et, StringUtils.isNotEmpty(errorRemind, true) ? errorRemind : "密码不能小于6位");
				}
				if (StringUtils.isNumberOrAlpha(inputed) == false) {
					return showInputedError(context, et, StringUtils.isNotEmpty(errorRemind, true) ? errorRemind : "密码只能含有字母或数字");
				}
				break;
			case TYPE_PHONE:
				if (inputed.length() != 11) {
					return showInputedError(context, et, StringUtils.isNotEmpty(errorRemind, true) ? errorRemind : "请输入11位手机号");
				}
				if (StringUtils.isPhone(inputed) == false) {
					Toast.makeText(context, "您输入的手机号格式不对哦~", Toast.LENGTH_SHORT).show();
					return false;
				}
				break;
			case TYPE_MAIL:
				if (StringUtils.isEmail(inputed) == false) {
					return showInputedError(context, "您输入的邮箱格式不对哦~");
				}
				break;
			default:
				if (StringUtils.isNotEmpty(inputed, true) == false || inputed.equals(StringUtils.getTrimedString(et.getHint()))) {
					return showInputedError(context, et, StringUtils.isNotEmpty(errorRemind, true) ? errorRemind : StringUtils.getTrimedString(et));
				}
				break;
		}
		et.setHintTextColor(oringinalHintColor);
		return true;
	}

	/**字符不合法提示(toast)
	 * @param context
	 * @param resId
	 * @return
	 */
	public static boolean showInputedError(Activity context, int resId) {
		return showInputedError(context, null, resId);
	}
	/**字符不合法提示(et == null ? toast : hint)
	 * @param context
	 * @param et
	 * @param resId
	 * @return
	 */
	public static boolean showInputedError(Activity context, EditText et, int resId) {
		try {
			return showInputedError(context, et, context.getResources().getString(resId));
		} catch (Exception e) {
			LogUtils.e( "" + e.getMessage());
		}
		return false;
	}
	/**字符不合法提示(toast)
	 * @param context
	 * @param string
	 * @return
	 */
	public static boolean showInputedError(Activity context, String string) {
		return showInputedError(context, null, string);
	}
	/**字符不合法提示(et == null ? toast : hint)
	 * @param context
	 * @param et
	 * @param string
	 * @return
	 */
	public static boolean showInputedError(Activity context, EditText et, String string) {
		if (context == null || StringUtils.isNotEmpty(string, false) == false) {
			LogUtils.e( "showInputedError  context == null || et == null || StringUtil.isNotEmpty(string, false) == false >> return false;");
			return false;
		}
		if (et == null) {
			Toast.makeText(context, StringUtils.getTrimedString(string), Toast.LENGTH_SHORT).show();
		} else {
			et.setText("");
			et.setHint(string);
			et.setHintTextColor(context.getResources().getColor(R.color.red));
		}
		return false;
	}

	//TODO 对输入字符判断>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
