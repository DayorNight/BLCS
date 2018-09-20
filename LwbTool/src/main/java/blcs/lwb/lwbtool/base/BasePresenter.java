package blcs.lwb.lwbtool.base;

/**
 * P层的 基类
 * @author CPC   https://www.jianshu.com/p/3a17382d44de
 */
public abstract class BasePresenter<T> {

	/**
	 * 绑定 view层
 	 */
	T view;
	public void onAttach(T view){this.view = view;}

	/**
	 * 解绑 view 层
 	 */
	public void onDetch(){
		if(view!=null){
			this.view = null;
		}
	}

	/**
	 * 获取view层
	 */
	public T getView(){
		return view;
	}
}
